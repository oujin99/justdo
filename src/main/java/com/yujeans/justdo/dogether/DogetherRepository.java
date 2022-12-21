package com.yujeans.justdo.dogether;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.category.SecondCategory;
import com.yujeans.justdo.category.ThirdCategory;
import com.yujeans.justdo.user.Account;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DogetherRepository{
	private final EntityManager em;
	
	// dogether 등록 폼에 넣을 카테고리
	public List<FirstCategory> selectFirstCategory() {
		return em.createQuery("SELECT fc FROM FirstCategory fc", FirstCategory.class).getResultList();
	}
	
	public List<SecondCategory> selectSecondCategory(String selectFirst){
		String sql = "SELECT DISTINCT sc.name, sc.id  FROM Category c LEFT OUTER JOIN FirstCategory fc ON fc.id = c.FIRST_CATEGORY_ID LEFT OUTER JOIN SecondCategory sc ON sc.id = c.SECOND_CATEGORY_ID LEFT OUTER JOIN ThirdCategory tc ON tc.id = c.THIRD_CATEGORY_ID WHERE fc.NAME  = '요리'";
		return em.createQuery("SELECT sc FROM SecondCategory sc whe", SecondCategory.class).getResultList();
	}
	
	public List<ThirdCategory> selectThirdCategory(){
		return em.createQuery("SELECT tc FROM ThirdCategory tc", ThirdCategory.class).getResultList();
	}
	
	//--------------------------------------
	
	public void saveDogether(Dogether dogether){
		em.persist(dogether);
		
	}
	
	public Dogether findDogether(Long dogetherId){
		return em.find(Dogether.class, dogetherId);
	}
	
	public AccountDogether findAccountDogether(Long dogetherId) {
		return em.find(AccountDogether.class, dogetherId);
	}
	
	public Account findAccount(Long accountDogetherId) {
		
		return em.find(Account.class, accountDogetherId);
		
	}
}
