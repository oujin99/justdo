package com.yujeans.justdo.dogether.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DogetherController {
	
	@GetMapping("/dogether/registForm")
	public String dogetherRegistForm(HttpServletRequest request) {
		
		return "dogether/dogether_regist";
	}
	
}
