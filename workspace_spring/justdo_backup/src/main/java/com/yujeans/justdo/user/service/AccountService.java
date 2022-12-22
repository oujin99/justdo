package com.yujeans.justdo.user.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	@Autowired
	private final AccountRepository accountRepository;


	// 1. id 통해 name email phone img 가져오기 
	public List<Account> findprofileById(Long id) {
		return accountRepository.findProfileById(id);
	}

	
	
	// 3. edit Controller 서비스 실행 
	public void update(Long id, Account account) {
		
	}
/*
	@Transactional
	public void join(Account account) {
		
	}

	public void validateMemberCheck(Account account) throws IllegalAccessException{
		List<Account> findId = accountRepository.findId(account.getId());
		
		if( ! findId.isEmpty()) {
			throw new IllegalAccessException("회원정보가 다릅니다.");
		}
	}

	/*
	 *  id 통해 두게더 테이블 썸네일 값 가져오기  
	
	public List<AccountDogether> findThumbnail(Long id) {
		return accountRepository.findThumbnailById(id);
	}
	 */
	
 
	
}
