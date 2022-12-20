package com.yujeans.justdo.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yujeans.justdo.global.interceptor.LoginCheckInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private final LoginCheckInterceptor checkInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터 등록 (excludePathPatterns 에 파라미터로 제외하고싶은 페이지의 경로를 넣으면 된다.)
		registry.addInterceptor(checkInterceptor)
					.addPathPatterns("/kakao/cookietest1")
					.addPathPatterns("/kakao/cookietest2");
	}
	
}
