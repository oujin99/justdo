package com.yujeans.justdo.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.repository.AccountDogetherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountDogetherService {
	
	@Autowired
	private final AccountDogetherRepository accountDogetherRepository;

	@Transactional
	public void save(AccountDogether accountDogether) {
		accountDogetherRepository.save(accountDogether);
	}
	
	public AccountDogether findByAccountAndDogether(Account account, Dogether dogether) {
		return accountDogetherRepository.findByAccountAndDogether(account, dogether);
	}
	
	
	
}
