package com.yujeans.justdo.dogether.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DogetherController {
	
	@GetMapping("/dogether/registForm")
	public String dogetherRegistForm() {
		
		return "dogether/dogether_regist";
	}
	
}
