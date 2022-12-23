package com.yujeans.justdo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yujeans.justdo.user.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccountController {
	
	@Autowired
	private final AccountService accountService;
	
	@GetMapping("/user/login")
	public String loginForm() {
		
		return "/user/login";
	}
	
	@GetMapping("/user/signup")
	public String signupForm() {
		
		return "/user/signup";
	}
	
	@GetMapping("/user/mypage")
	public String myPageForm() {
		return "/user/mypage";
	}
	
	
	
}
