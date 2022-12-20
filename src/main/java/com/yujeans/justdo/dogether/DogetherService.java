package com.yujeans.justdo.dogether;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.user.Account;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DogetherService {
	private final DogetherRepository dogetherRepository;
	
	@Transactional
	public void saveDogether(Dogether dogether){
		
		dogetherRepository.saveDogether(dogether);
	}
	
	public Dogether findDogether(Long dogetherId){
		
		return dogetherRepository.findDogether(dogetherId);
		
	}
	
	// class_detail에 두리더 정보 넣기 위함(두리더 사진)
	public AccountDogether findAccountDogether(Long dogetherId) {
		
		return dogetherRepository.findAccountDogether(dogetherId);
	}
	
	public Account findAccount(Long accountDogetherId) {
		
		return dogetherRepository.findAccount(accountDogetherId);
	}
}
