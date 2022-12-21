package com.koreait.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.koreait.jpashop.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;
	
	// 저장
	public void save(Item item) {
		// 처음에 item의 id가 없으면 신규등록
//		if(item.getId() == null) {
			// 신규
			em.persist(item);
//		} else {
//			// jpa를 통해서 db에 한번 들어간 값
//			em.merge(item);
//		}
	}
	
	public List<Item> findAll(){
		return em.createQuery("select i from Item i",Item.class)
				 .getResultList();
	}
	
	// item 하나조회
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	
	
}
















