package com.yujeans.justdo.user.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.user.Account;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
	private final EntityManager em;


	
	/* 
	 *  id 통해 DB 내부의 name,e-mail,phone, img 조회 
	 */
	public List<Account> findProfileById(Long id) {
//		String sql = "select a.name, a.email, a.phone, a.image from Account a where 	a.id = :id";
		String backup = "select a from Account a where a.id = :id";
		
		return em.createQuery(backup, Account.class)
				.setParameter("id", id)
				.getResultList();
		
	}


	/*
	 *  파라미터 id 를통해 
	 *  Account 테이블 id 조회하기 
	
	
	public List<Account> findId(Long id) {
		String url = "select a.id from Account a where a.id = :id";
		
		return em.createQuery(url, Account.class)
				.setParameter("id", id)
				.getResultList();
	}
	
	
	
	/* 	2.
	 *  dogether 테이블의 img 값  = 썸네일 이미지 가져오기 
	 *  "|location.href='@{profile_addForm.html}'|"
	 *  
	*/
	
	public List<AccountDogether> findThumbnailById(Long id){
		String sql = "select ad.image from account_doghther ad where ad.id = :id ";
		return em.createQuery(sql, AccountDogether.class)
				.setParameter("id", id)
				.getResultList();
		//.getSingleResult() 
		
	}
	 
}
