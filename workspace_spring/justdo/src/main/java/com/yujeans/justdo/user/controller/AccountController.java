package com.yujeans.justdo.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yujeans.justdo.user.Account;
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

	/* 1. 
	 * DB Account 테이블의 name,e-mail, phone, img 를 가져와
	 * profile.html 에 thymeleaf object 를 통해 적용 
	 */
	  
	@GetMapping("/profile/profile_backup")
	public String list_profile(HttpServletRequest request, Model model) {
		// id 값 가져오기 
		Long id = (Long) request.getAttribute("id");
		
		
		List<Account> user_profile = accountService.findprofileById(4L);
		
		model.addAttribute("user_profile", user_profile);
		
		for( Account account : user_profile) {
			System.out.println(" account 할당 값 = "+ account);
			System.out.println("user_profile 할당 값 = "+user_profile.toString());
//			Account(id=1, name=user1, email=test2@gmail.com, phone=010-1234-4567, address=서울, image=assets/img/class_2.png)
//			[Account(id=1, name=user1, email=test2@gmail.com, phone=010-1234-4567, address=서울, image=assets/img/class_2.png)]
		}
		return "profile/profile_backup";
	}
	
	/* 2. 
	 *  썸네일 의 고유 id 값( href) 를 
	 *  썸네일 슬라이드에 담고 클릭시 해당 썸네일 ( = 강의 페이지 ) 로 이동 
	
	
	@GetMapping("/profile")
	public String thumbnail_profile(Long id, Model model) {
		List<AccountDogether> thumbnail = accountService.findThumbnail(id);
		
		model.addAttribute("thumbnail", thumbnail);
		
		
		return "profile";
	*/


	/*
	 *  4. th:if , th:each 를 사용해서 
	 *  프로필 수정 <button> 자기 id 일 때만 보이게하기  
	 */
	

	
}
