package com.koreait.jpashop.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.koreait.jpashop.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;
	
	//저장
	public void save(Item item) {
		em.persist(item);
	}
}
