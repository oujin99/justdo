package com.yujeans.justdo.user.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
	private final AccountService accountService;
	
	/* 1. 
	 * DB Account 테이블의 name,e-mail, phone, img 를 가져와
	 * profile.html 에 thymeleaf object 를 통해 적용 
	 */
	  
	@GetMapping("/profile/profile_backup")
	public String list_profile(HttpServletRequest request, Model model) {
		Long id = (Long) request.getAttribute("id");
		
		List<Account> user_profile = accountService.findprofileById(id);
		
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

	/* 3. 
	 * 프로필 수정 페이지 에서 수정하기 클릭시
	 * 해당 user id 를 통해 DB 테이블 값 변경 
	 */
	
	@PostMapping("/{userId}/edit")
	public String edit_profile(@PathVariable Long id, @ModelAttribute Account account) {
		
		accountService.update(id,account);
		
		return "redirect:/profile/profile_backup/{userId}";
		
		
	}
	
	
	
	/*
	 *  4. th:if , th:each 를 사용해서 
	 *  프로필 수정 <button> 자기 id 일 때만 보이게하기  
	 */
	
	/* 3.
	 * 프로필 수정 ( profile_addForm.html ) 에서 수정한 값을 
	 * DB에 적용 
	
	
//	@GetMapping("/profile/profile_backup")
	@PostMapping("/{userId}/edit") // th:action="@{/profile_addForm/edit}" th:object="${edit}" method="post"
	public String edit_profile(@ModelAttribute EditAccount edit, BindingResult result) throws IllegalAccessException{
		
		// error 발생시
		if( result.hasErrors()) {
			return "redirect:/";
		}
		
		Account account = new Account();
		
		account.setName(edit.getName());
		account.setEmail(edit.getEmail());
		account.setPhone(edit.getPhone());
		account.setImage(edit.getImage());
		
		accountService.join(account);
		return "profile/profile_backup";
	}
	 */
	
}
