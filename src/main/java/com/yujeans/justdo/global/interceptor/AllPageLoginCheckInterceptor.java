package com.yujeans.justdo.global.interceptor;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yujeans.justdo.api.kakao.KakaoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AllPageLoginCheckInterceptor implements HandlerInterceptor{

	@Autowired
	private final KakaoService kakaoService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//			System.out.println("requestURI : "+request.getRequestURI());
			
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
						request.setAttribute("id", userInfoMap.get("id"));
						request.setAttribute("nickname", userInfoMap.get("nickname"));
						request.setAttribute("profile_image", userInfoMap.get("profile_image"));
						request.setAttribute("thumbnail_image", userInfoMap.get("thumbnail_image"));
						request.setAttribute("email", userInfoMap.get("email"));
						request.setAttribute("loginState", "ok");
					}
				}
			}
		
		return true;
	}
	
	
	
}
