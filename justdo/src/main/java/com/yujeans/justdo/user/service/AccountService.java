package com.yujeans.justdo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	@Autowired
	private final AccountRepository accountRepository;


	// id 통해 name email phone img 가져오기 
	public List<Account> findprofileById(Long id) {
		return accountRepository.findProfileById(id);
	}
	
	
 
	
}
