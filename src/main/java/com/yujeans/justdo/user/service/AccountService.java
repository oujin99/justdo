package com.yujeans.justdo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {
	@Autowired
	private final AccountRepository accountRepository;

	public void save(Account account) {
		accountRepository.save(account);
	}
}
