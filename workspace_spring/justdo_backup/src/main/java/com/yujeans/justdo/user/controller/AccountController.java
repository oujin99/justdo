package com.yujeans.justdo.user.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.EditAccount;
import com.yujeans.justdo.user.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
//@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {
	
	@Autowired
	private final AccountService accountService;
	
	/* 1. 
	 * DB Account 테이블의 name,e-mail, phone, img 를 가져와
	 * profile.html 에 thymeleaf object 를 통해 적용 
	 * 
	 *  현재 수정 DB 적용 안된 상태 
	 */
	  
	@GetMapping("/profile/profile")
	public String list_profile(HttpServletRequest request, Model model) {
		Long id = (Long) request.getAttribute("id");
		
		// DB - Account 테이블 값 가져오기 
		// id 파라미터  변경 필요
		List<Account> user_profile = accountService.findprofileById(4L);  
		
		model.addAttribute("user_profile", user_profile);
		
		return "profile/profile";
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

	
}
