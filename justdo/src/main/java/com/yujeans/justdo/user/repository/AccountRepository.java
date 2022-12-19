package com.yujeans.justdo.user.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.yujeans.justdo.user.Account;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
	private final EntityManager em;


	public List<Account> findProfileById(Long id) {
		String url = "select a.name, a.email, a.phone, a.img from account a";
		
		return (List<Account>) em.createQuery(url, Account.class);
//		return em.find(Account.class, id);
	}
}