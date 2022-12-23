package com.yujeans.justdo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.repository.EditProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditProfileService {

	@Autowired
	private final EditProfileRepository editProfileRepository;
	
	// edto 값을 -> Account 테이블로 
	public void save(Account account) {
		editProfileRepository.save(account);
	}
	
	// Account 테이블 값을 - > "editProfile" Model 객체로 
	public List<Account> AccountList(){
		return editProfileRepository.findAll();
	}

		
	
}
