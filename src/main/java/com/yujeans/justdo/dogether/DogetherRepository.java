package com.yujeans.justdo.dogether;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.yujeans.justdo.user.Account;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DogetherRepository {
	private final EntityManager em;
	
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
