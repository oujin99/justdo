package com.yujeans.justdo.global.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yujeans.justdo.api.kakao.KakaoService;
import com.yujeans.justdo.jwt.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AllPageLoginCheckInterceptor implements HandlerInterceptor{

	@Autowired
	private final KakaoService kakaoService;
	
	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//			System.out.println("인터셉터 !_!_!_!__!!requestURI : "+request.getRequestURI());
			
			Cookie[] cookies = request.getCookies();
			
			// 쿠키가 있다면
			if(cookies!=null) {
				
				for(Cookie cookie : cookies) {
					String accessToken = "";
					// 쿠키의 이름이 access_token이라면 (로그인 됐다면 있을것임)
					if(cookie.getName().equals("access_token")) {
						accessToken = cookie.getValue();
						
						// id , nickname , profile_image , thumbnail_image , email 담겨있음
						HashMap<String, Object> userInfoMap = kakaoService.getUserInfo(accessToken);
						
						// 모델에 값 넣기
						request.setAttribute("id", userInfoMap.get("id"));
						request.setAttribute("nickname", userInfoMap.get("nickname"));
						request.setAttribute("profile_image", userInfoMap.get("profile_image"));
						request.setAttribute("thumbnail_image", userInfoMap.get("thumbnail_image"));
						request.setAttribute("email", userInfoMap.get("email"));
						request.setAttribute("loginMethod", "kakao");
						request.setAttribute("loginState", "ok");
					} else if (cookie.getName().equals("basic_token")) {
						accessToken = cookie.getValue();
//						System.out.println("accessToken : " + accessToken);
						// id , nickname , profile_image , thumbnail_image , email 담겨있음
						Map<String, Object> userInfoMap = jwtTokenProvider.getUserInfo(accessToken);
						
						// 모델에 값 넣기
						request.setAttribute("id", userInfoMap.get("id"));
						request.setAttribute("nickname", userInfoMap.get("nickname"));
//						request.setAttribute("profile_image", userInfoMap.get("profile_image"));
						request.setAttribute("thumbnail_image", userInfoMap.get("thumbnail_image"));
						request.setAttribute("email", userInfoMap.get("email"));
						request.setAttribute("loginMethod", "basic");
						request.setAttribute("loginState", "ok");
						
//						System.out.println("id : "+userInfoMap.get("id"));
					
					}
				}
			}
		
		return true;
	}
	
	
	
}
