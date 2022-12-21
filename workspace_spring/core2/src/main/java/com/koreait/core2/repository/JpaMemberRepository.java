package com.koreait.core2.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.koreait.core2.member.Member;

@Repository
public class JpaMemberRepository implements MemberReposity {

	// JPA(@Entitiy)를 EntitiyManager 를 통해 모든지 동작 가능하다.
	private final EntityManager em;

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		// 멤버 data 저장 
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> finaAll() {
		// select 한 내용을 "모두" 리턴하는 메소드 
		return em.createQuery("select m from Member m", Member.class).getResultList();
	
		/*
		 *  select m from Member m : JPQL 이라는 쿼리 
		 */
	}

}
