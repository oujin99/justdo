package com.yujeans.justdo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yujeans.justdo.dto.EditProfileDTO;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.service.EditProfileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditProfileController {

	@Autowired
	private final EditProfileService editProfileService;
	
//	@GetMapping("/profile/profile_eidt")
	@PostMapping("/profile/profile_eidt/{id}")
	public String profileEdit (@PathVariable("id") Long id, @ModelAttribute EditProfileDTO eDto, Model model) {
		
		Account account = new Account();
		
		account.setId(id); // 임시 하드코딩 
		account.setName(eDto.getName());
		account.setEmail(eDto.getEmail());
		account.setPhone(eDto.getPhone());
		account.setImage(eDto.getImage());
		
		// edto 값을 -> Account 테이블로 
		editProfileService.save(account);
		
		// Account 테이블 값을 - > Model 객체 로 
		model.addAttribute("editProfile", editProfileService.AccountList() );
		
		return "/profile/profile_edit";
	}
	
}
