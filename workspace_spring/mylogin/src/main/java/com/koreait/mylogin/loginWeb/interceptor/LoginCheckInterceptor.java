package com.koreait.mylogin.loginWeb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.koreait.mylogin.loginWeb.session.SessionConst;

public class LoginCheckInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return HandlerInterceptor.super.preHandle(request, response, handler);

		String requestURI = request.getRequestURI();
		System.out.println(" 인터셉터 = " + requestURI);
		HttpSession session = request.getSession(false);
		
		if ( session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
			System.out.println("미 인증 사용자 요청 ");
			// 로그인 화면으로 이동
			response.sendRedirect("/login?redirectURL=" + requestURI);
			return false;
			
		}
		return true;
	}
}
