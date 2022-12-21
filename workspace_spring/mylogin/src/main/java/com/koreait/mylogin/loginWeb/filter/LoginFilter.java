package com.koreait.mylogin.loginWeb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		String requestURI = httpServletRequest.getRequestURI();
		
		System.out.println("request URI = " + requestURI);
		
		chain.doFilter(request, response);
		
		System.out.println("request URI = " + requestURI);
		
	}	

}
