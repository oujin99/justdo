package com.koreait.mylogin.loginWeb.filter;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.PatternMatchUtils;

import com.koreait.mylogin.loginWeb.session.SessionConst;

public class LoginCheckFilter implements Filter {

	private static final String[] Whitelist = {"/","/members/add", 
			"/login", "/logout","/css/*"};
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println("인증 체크 필터 시작 ");
		if ( isLoginCheckPath(requestURI)) {
			// 매칭성공 
			System.out.println("인증 체크 로직 실행  " + requestURI);
			HttpSession session = httpRequest.getSession(false);
			
			// 로그인 기록이 없는 사용자 일 경우
			if( session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
				System.out.println("미 인증 사용자 요청 ");
				httpResponse.sendRedirect("/login?redirectURL= " + requestURI); // 로그인으로 돌려보내기
				return;
			} 
		}
		
		// 로그인 기록이 있는 사용자 라면 다음단계 필터로 이동
		chain.doFilter(request, response);
		
	}
	
	/*
	 * 화이트 릿트의 경우 인증 체크 X 
	 * simpleMatch : 파라미터 문자열 ( String requestURI ) 가
	 * 특정 데이터 변수 ( Whitelist) 와 같은지 검사하는 메소드 
	 */
	private boolean isLoginCheckPath(String requestURI) {
		// whiteList 와 requestURI 가 매칭(=서로 같으면)이 되면 True , 매칭 안되면 false 
		return ! PatternMatchUtils.simpleMatch(Whitelist, requestURI);
	}

}
