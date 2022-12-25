package com.yujeans.justdo.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yujeans.justdo.global.interceptor.AllPageLoginCheckInterceptor;
import com.yujeans.justdo.global.interceptor.LoginNecessaryPageInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private final LoginNecessaryPageInterceptor loginNecessaryPageInterceptor;
	
	@Autowired
	private final AllPageLoginCheckInterceptor allPageLoginCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터 등록 (excludePathPatterns 에 파라미터로 제외하고싶은 페이지의 경로를 넣으면 된다.)
//		registry.addInterceptor(loginNecessaryPageInterceptor)
//					.addPathPatterns("/kakao/cookietest1")
//					.addPathPatterns("/kakao/cookietest2")
//					.addPathPatterns("/dogether/registForm");
		
		registry.addInterceptor(allPageLoginCheckInterceptor)
					.addPathPatterns("/**")
					.excludePathPatterns("/css/**", "/favicon/**", "/images/**", "/img/**", "/javascripts/**", "/js/**", "/manifest.*", "/error", "/json/**", "/*.json")
					.excludePathPatterns("/**/*.html", "/*.ico")
					.excludePathPatterns("/user/login", "/user/signup", "/kakao/login", "/kakao/logout");
	}
	
}
