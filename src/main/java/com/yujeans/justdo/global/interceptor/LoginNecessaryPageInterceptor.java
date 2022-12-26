package com.yujeans.justdo.global.interceptor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginNecessaryPageInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//		log.info("::::인터셉터 지나갑니다~~");
		boolean validToken = isValidToken(request);
		
		if(validToken) {
			// 토큰이 유효하면 로그인 된 상태
			// 페이지 묶지않고 인터셉터 true로 흘려보내주기
			
			return true;
		}
//		else {
//			// 로그인 되지 않은상태 (로그인 해야 이용할 수 있다는 메시지를 담은 페이지를 띄워줘야 함)
//			response.sendRedirect("/error/isNotValidToken");
//		}
		
		return false;
	}
	
	public boolean isValidToken(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		
		String token = "";

		// 쿠키가 1개이상 있다면
		if(cookies!=null) {
			
			for(Cookie cookie : cookies) {
				
				System.out.println("cookie value : "+cookie.getValue());
				System.out.println("cookie name : "+cookie.getName());
				System.out.println("cookie length : "+cookies.length);
				
				// 쿠키키값 얻어오면 키값.equals("access_token") 추가할 예정
				if(cookie.getName().equals("access_token")) {
					token = cookie.getValue();
//					System.out.println("access_token : " + token);
				}
			}
		}else {
			// 쿠키가 널일때
			System.out.println("cookies : "+cookies);
			
		}
		
		// token 변수에 토큰을 넣었으니 이걸 이용해 카카오로 부터 tokeninfo를 받아오자
		// tokeninfo가 정상적으로 받아와 지면 토큰이 유효하다는 것
		// 정상적으로 받아지지 않고 에러코드를 뱉는다면 토큰이 유효하지 않다는 것
		// kapi.kakao.com/v1/user/access_token_info?Authorization=Bearer ${ACCESS_TOKEN}
		
		String requestTokenInfo = "https://kapi.kakao.com/v1/user/access_token_info";
		
		try {
			
			URL url = new URL(requestTokenInfo);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Bearer "+token);
			conn.setRequestMethod("GET");
			
			int responseCode = conn.getResponseCode();
			System.out.println("request tokeninfo responseCode = " + responseCode);
			
			// responseCode가 200이면 유효한 토큰 , 그외는 에러 (401 == 유효하지 않은 토큰 등..)
			if(responseCode==200) {
				return true;
			}

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
