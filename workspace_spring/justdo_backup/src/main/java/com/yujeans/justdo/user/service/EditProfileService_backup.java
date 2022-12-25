package com.yujeans.justdo.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.repository.EditProfileRepository;
import com.yujeans.justdo.user.repository.EditProfileRepository_backup;

import lombok.RequiredArgsConstructor;

//@Service
@RequiredArgsConstructor
public class EditProfileService_backup {

	@Autowired
	private final EditProfileRepository_backup editProfileRepository_backup;
	
	public void save(Account account) {
		editProfileRepository_backup.save(account);
	}
	
	// 전체 리턴 
	public List<Account> AccountList(){
		return editProfileRepository_backup.findAll();
	}
	
	public Optional<Account> findByAccountId(Long id) {
		return editProfileRepository_backup.findById(id);
	}

		
	
}
