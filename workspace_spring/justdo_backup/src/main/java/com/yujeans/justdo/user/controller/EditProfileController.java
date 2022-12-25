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

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditProfileController {

	@Autowired
	private final EditProfileService editProfileService;
	
	@GetMapping("/profile/profile_write")
	public String profile_Write() {
		
		return "/profile/profile_write";
	}

	@GetMapping("/profile/profile_edit/{id}")
	public String profileEdit (@PathVariable("id") Long id, @ModelAttribute EditProfileDTO eDto, Model model) {
		
		model.addAttribute("editProfile", editProfileService.AccountView(id));
		return "profile/profile_edit";
	}


	
	@PostMapping("/profile/profile_update/{id}")
	public String profileEditForm(@PathVariable("id") Long id, @ModelAttribute EditProfileDTO eDto, Model model) {
		
		Account account = new Account();
		
//		account.setId(4L); 
		account.setId(id); 
		account.setName(eDto.getName());
		account.setEmail(eDto.getEmail());
		account.setPhone(eDto.getPhone());
		account.setImage(eDto.getImage());
		
		editProfileService.save(account);
		
		model.addAttribute("UpdateProfile", editProfileService.AccountList());

		
		return "redirect:profile/profile_edit{id}";
	}
	
}
