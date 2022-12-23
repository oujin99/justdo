package com.yujeans.justdo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yujeans.justdo.dto.ProfileEdit;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.service.EditProfileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditProfileController {

	@Autowired
	private final EditProfileService editProfileService;
	
	@PostMapping("")
	public String profileEdit (@PathVariable("id") Long id, @ModelAttribute ProfileEdit profileEdit, Model model) {
		
		Account account = new Account();
		account.setId(id);
		account.setName(profileEdit.getName());
		account.setEmail(profileEdit.getEmail());
		account.setPhone(profileEdit.getPhone());
		account.setImage(profileEdit.getImage());
		
		editProfileService.edit(account);
		
		model.addAttribute("editProfile", editProfileService.AccountList() );
		
		return null;
	}
	
}
