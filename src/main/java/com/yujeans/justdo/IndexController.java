package com.yujeans.justdo;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	@GetMapping
	public String mainPage(HttpServletRequest request, Model model) {
		
//		System.out.println("request attribute loginState : "+request.getAttribute("loginState"));
//		System.out.println("request attribute nickname : "+request.getAttribute("nickname"));
//		System.out.println("request attribute id : "+request.getAttribute("id"));
		
		return "/main/index";
	}
}
