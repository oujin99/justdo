package com.yujeans.justdo.api.kakao;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.Credential;
import com.yujeans.justdo.user.service.AccountService;
import com.yujeans.justdo.user.service.CredentialService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class KakaoController {
	
	@Autowired
	private final KakaoService kakaoService;
	
	@Autowired
	private final CredentialService credentialService;
	
	@Autowired
	private final AccountService accountService;
	
	@GetMapping("/kakao/login")
	public String kakaoLogin(@RequestParam String code, HttpServletResponse response, HttpServletRequest request) {
//		System.out.println(code);
		System.out.println("카카오가 불렀어");
		
//		System.out.println("인가코드 : "+code);
		
		String token = kakaoService.getToken(code);
		
		// 카카오로부터 받은 토큰 쿠키에 저장
		Cookie cookie = new Cookie("access_token", token);
		cookie.setMaxAge(6*60*60); // 6시간
		cookie.setHttpOnly(true); // XSS 공격 방지
		cookie.setSecure(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		System.out.println("token : " + token);
		
		Map<String,Object> userInfoMap = kakaoService.getUserInfo(token);
		String username = (String) userInfoMap.get("id");
		Credential isFirstLogin = credentialService.findByUsername(username)
							.orElseGet(() -> firstJoinUser(userInfoMap));
		
		return "redirect:/";
	}
	
	@GetMapping("/kakao/logout")
	public String kakaoLogout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그아웃 됩니다.");
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
//				System.out.println("로그아웃시 삭제할 쿠키 이름 : "+cookie.getName());
//				System.out.println("로그아웃시 삭제할 쿠키 밸류 : "+cookie.getValue());
				
				Cookie forDeleteCookie = new Cookie(cookie.getName(), null);
				forDeleteCookie.setMaxAge(0);
				forDeleteCookie.setPath("/");
				response.addCookie(forDeleteCookie);
			}
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/kakao/cookietest1")
	public String cookietest1() {
		System.out.println("쿠키테스트1 컨트롤러");
		
		return "/kakao/cookietest1";
	}
	
	@GetMapping("/kakao/cookietest2")
	public String cookietest2() {
		System.out.println("쿠키테스트2 컨트롤러");
		
		
		return "/kakao/cookietest2";
	}
	
	private Credential firstJoinUser(Map<String,Object> userInfoMap) {
		Account account = new Account();
		account.setName((String) userInfoMap.get("nickname"));
		account.setImage((String) userInfoMap.get("thumbnail_image"));
		account.setProfile((String) userInfoMap.get("profile_image"));
		account.setEmail((String) userInfoMap.get("email"));
		accountService.save(account);
		
		Credential credential = new Credential();
		credential.setAccount(account);
		credential.setUsername((String) userInfoMap.get("id"));
		credential.setPassword("social_kakao");
		credential.setLoginMethod(credentialService.findLoginMethodByType("kakao"));
		credential.setRoles(Arrays.asList("USER"));
		credentialService.save(credential);
		return credential;
	}
}
