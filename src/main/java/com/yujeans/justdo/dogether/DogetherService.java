package com.yujeans.justdo.dogether;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.category.SecondCategory;
import com.yujeans.justdo.category.ThirdCategory;
import com.yujeans.justdo.user.Account;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DogetherService {
	private final DogetherRepository dogetherRepository;
	
	// dogether 등록 폼에 넣을 카테고리
	public List<FirstCategory> selectFirstCategory() {
		
		return dogetherRepository.selectFirstCategory();
	}
	
	public List<SecondCategory> selectSecondCategory(String selectFirst){

		return dogetherRepository.selectSecondCategory(selectFirst);
	}
	
	public List<ThirdCategory> selectThirdCategory(){
		return dogetherRepository.selectThirdCategory();
	}
	
	
	
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
