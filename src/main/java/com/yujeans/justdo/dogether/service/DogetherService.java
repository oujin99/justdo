package com.yujeans.justdo.dogether.service;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.dogether.SimpleDogetherDto;
import com.yujeans.justdo.dogether.repository.DogetherRepository;
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
	
	@Transactional
	public void saveDogether(Dogether dogether){
		
		dogetherRepository.save(dogether);
		
//		dogetherRepository.saveDogether(dogether);
	}
	
	// 소분류 카테고리 이름을 이용해 해당 두게더 가져오기
	public List<Dogether> findDogetherByThirdCategoryName(String thirdCategoryName) {
		return dogetherRepository.findDogetherByThirdCategoryName(thirdCategoryName);
	}
	
	// 두게더 상세보기로 바로 넘어가기 위해 redirect로 dogether_id 넘기기 - 시퀀스
	public Long selectDogetherId() {
		
		return dogetherRepository.selectDogetherId();
	}
	
	// 두게더 상세보기 위함
	public Dogether findDogether(Long id){
		
		return dogetherRepository.findDogether(id);
		
	}

	// 타이틀로 검색
	public List<Dogether> findDogetherByTitle(String requestText) {
		return dogetherRepository.findDogetherByTitle(requestText);
	}
	
	// account id로 검색
	public List<Dogether> findDogetherByAccountId(Account account){
		return dogetherRepository.findDogetherByAccountId(account);
	}
	
	// 
	public List<Dogether> findDogetherInfoByAccountIdOfAccountDogether(Account account){
		return dogetherRepository.findDogetherInfoByAccountIdOfAccountDogether(account);
	}
}
