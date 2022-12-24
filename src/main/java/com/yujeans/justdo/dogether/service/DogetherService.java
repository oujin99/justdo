package com.yujeans.justdo.dogether.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.dogether.repository.DogetherRepository;

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
//   
//   public List<ThirdCategory> selectThirdCategory(){
//      return dogetherRepository.selectThirdCategory();
//   }
   
   
   
   
   @Transactional
   public void saveDogether(Dogether dogether){
      
      dogetherRepository.save(dogether);
      
//      dogetherRepository.saveDogether(dogether);
   }
   
   
   
   
   // class_detail에 두리더 정보 넣기 위함(두리더 사진)
//   public AccountDogether findAccountDogether(Long dogetherId) {
//      
//      return dogetherRepository.findAccountDogether(dogetherId);
//   }
   
//   public Account findAccount(Long accountDogetherId) {
//      
//      return dogetherRepository.findAccount(accountDogetherId);
//   }
   
   // 두게더 상세보기로 바로 넘어가기 위해 redirect로 dogether_id 넘기기 - 시퀀스
   public Long selectDogetherId() {
      
      return dogetherRepository.selectDogetherId();
   }
   
   // 두게더 상세보기 위함
   public Dogether findDogether(Long id){
      
      return dogetherRepository.findDogether(id);
      
   }
}