package com.yujeans.justdo.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccountController {
	private final AccountService accountService;
	
	// User 의 id를 통해 name,email.phone,img를 가져와 ( DB -> Back) 
	// view 단으로 이동 
  
	@GetMapping("/profile")
	public String list_profile(Long id, Model model) {
		List<Account> user_profile = accountService.findprofileById(id);
		model.addAttribute("user_profile", user_profile);
		return "profile";
	}
	
	// 썸네일 이미지 클릭시 해당 강의 페이지로 이동 
	
	// 채팅하기 버튼 클릭시 채팅하기 로 이동
	
	// 수강하기 버튼 클릭시 수강하기 페이지로 이동
	
	// 프로필 수정 
	// view 에서 name,email,phone,intro 정보변경 ( Front to DB ) 
}