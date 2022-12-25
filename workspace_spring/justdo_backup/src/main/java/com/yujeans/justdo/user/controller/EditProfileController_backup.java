package com.yujeans.justdo.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yujeans.justdo.profileDto.EditProfileDTO;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.service.EditProfileService;
import com.yujeans.justdo.user.service.EditProfileService_backup;

import lombok.RequiredArgsConstructor;

//@Controller
@RequiredArgsConstructor
public class EditProfileController_backup {

	@Autowired
	private final EditProfileService editProfileService;
	
	/*
	 *  GET 방식으로 id 값을 받으면 
	 *  id에 맞는 profile_edit 페이지가 post 방식으로 open 
	 *  id 는 ??? 
	 *  
	 */
	
	/*
	 * 작성 페이지 이동   
	 */
	@GetMapping("/profile/profile_write")
	public String profile_Write() {
		
		return "/profile/profile_write";
	}
	
	/*
	 *  get 으로 id 를 받아와서 
	 *  post -> "/profile/profile_edit" 전환 
	 */
	
	@GetMapping("/profile/profile_edit/{id}")
	public String profileEdit (@PathVariable("id") Long id, @ModelAttribute EditProfileDTO eDto, Model model) {
		
//		Account account = new Account();
//		
//		account.setId(id); // 임시 하드코딩 
//		account.setName("이름");
//		account.setEmail("이메일");
//		account.setPhone("폰번호");
//		account.setImage("/image.png");
		
		// 수정값 객체 view에 보내기 = ?? 
//		model.addAttribute("eDto", eDto);
		
		// edto - > Account 테이블로 
//		editProfileService.save(account);
		
		// Account 테이블 -> Model 객체로 
//		model.addAttribute("editProfile", editProfileService.AccountList() );
//		Optional<Account> accountTest = editProfileService.findByAccountId(id);
//		System.out.println("test : " + accountTest.get.get().getId());
//		model.addAttribute("editProfile", editProfileService.findByAccountId(id).get());
//		System.out.println("AccountList : " + editProfileService.AccountList());
//		model.addAttribute("editProfile", editProfileService.AccountList());
//		System.out.println(editProfileService.AccountList());
		
//		model.addAttribute("edto", account);
//		System.out.println(eDto.toString());
		
//		model.addAttribute("editProfile", editProfileService.AccountList());
//		System.out.println(editProfileService.AccountList());
		
		return "/profile/profile_edit";
	}

	/*
	 *  post -> "/profile/profile_edit" 받아와서 실행하는 메소드 
	 *  리턴 값은 redirect로 새로고침?
	 */
	
	@PostMapping("/profile/profile_edit/{id}")
	public String profileEditForm(@PathVariable("id") Long id, @ModelAttribute EditProfileDTO eDto, Model model) {
		
		Account account = new Account();
		
//		account.setId(1L); 
		account.setId(id); 
		account.setName(eDto.getName());
		account.setEmail(eDto.getEmail());
		account.setPhone(eDto.getPhone());
		account.setImage(eDto.getImage());
		
		editProfileService.save(account);
//		System.out.println(eDto.toString());
		
		model.addAttribute("editProfile", editProfileService.AccountList());

		
		return "redirect:/profile/profile_edit{id}";
	}
	
}
