package com.koreait.mylogin.loginWeb.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koreait.mylogin.loginWeb.interceptor.Loginterceptor;

@Component
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Loginterceptor())
			.order(1)
			.addPathPatterns("/**")
			.excludePathPatterns("/error");
		registry.addInterceptor(new Loginterceptor())
		.order(2)
		.addPathPatterns("/**")
		.excludePathPatterns("/","/members/add", 
				"/login", "/logout","/css/*");
	}
	
	
	
	//@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = 
				new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new LoginFilter()); // LogFilter 클래스 
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*"); // 모든 url에 전부 적용
		
		return filterRegistrationBean;
	}
	
	// 등록 
//	@Bean
	public FilterRegistrationBean loginCheckFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = 
				new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new LoginCheckFilter()); // LogFilter 클래스 
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.addUrlPatterns("/*"); // 모든 url에 전부 적용
		
		return filterRegistrationBean;
	}
}
