package com.yujeans.justdo;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yujeans.justdo.api.kakao.KakaoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	@Autowired
	private final KakaoService kakaoService;
	
	@GetMapping
	public String mainPage(HttpServletRequest request, Model model) {
		
		Cookie[] cookies = request.getCookies();
		
		// 쿠키가 있다면
		if(cookies!=null) {
			
			for(Cookie cookie : cookies) {
				
				// 쿠키의 이름이 access_token이라면 (로그인 됐다면 있을것임)
				if(cookie.getName().equals("access_token")) {
					String access_token = cookie.getValue();
					
					// id , nickname , profile_image , thumbnail_image , email 담겨있음
					HashMap<String, Object> userInfoMap = kakaoService.getUserInfo(access_token);
					
					// 모델에 값 넣기
					model.addAttribute("nickname", userInfoMap.get("nickname"));
					model.addAttribute("profile_image", userInfoMap.get("profile_image"));
					model.addAttribute("thumbnail_image", userInfoMap.get("thumbnail_image"));
					model.addAttribute("email", userInfoMap.get("email"));
					model.addAttribute("loginState", "ok");
				}
			}
		}
		
		return "/main/index";
	}
}
