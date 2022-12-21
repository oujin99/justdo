package com.koreait.core2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	/*
	 *  포트 9090 입력 시 호출되는 가장 메인 index.html 
	 */
	
	@GetMapping("/")
	public String Home() {
		return "home";
	}
	
}	
