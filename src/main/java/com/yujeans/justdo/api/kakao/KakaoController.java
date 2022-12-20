package com.yujeans.justdo.api.kakao;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class KakaoController {
	
	@Autowired
	private final KakaoService kakaoService;
	
	@GetMapping("/kakao/login")
	public String test(@RequestParam String code, Model model, HttpServletResponse response) {
//		System.out.println(code);
		System.out.println("카카오가 불렀어");
		
//		System.out.println("인가코드 : "+code);
		
		String token = kakaoService.getToken(code);
		
		// 카카오로부터 받은 토큰 쿠키에 저장
		Cookie cookie = new Cookie("access_token", token);
		cookie.setHttpOnly(true); // XSS 공격 방지
		cookie.setSecure(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		System.out.println("token : "+token);
		
		// id , nickname , profile_image , thumbnail_image , email 담겨있음
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(token);
		
//		System.out.println("닉네임 : "+userInfo.get("nickname"));
//		System.out.println("프로필 이미지 : "+userInfo.get("profile_image"));
		
//		System.out.println(kakaoService.getAgreementInfo(token));
		
		return "redirect:/";
	}
	
	@GetMapping("/kakao/logout")
	public String kakao_logout(HttpServletRequest request, HttpServletResponse response) {
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
	
}
