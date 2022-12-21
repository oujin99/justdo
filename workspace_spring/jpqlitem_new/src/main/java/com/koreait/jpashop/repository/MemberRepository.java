package com.koreait.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.jpashop.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	// @PersistenceContext : jpa가 지원해주는 표준, 
	//						 spring이 entitymanager를 만들어서 em에다가 주입
//	@PersistenceContext
	// @Autowired			: spring boot lib사용시 @Autowired을 지원한다.
	@Autowired	
	private final EntityManager em;
	
	// 생성자 주입
//	private MemberRepository(EntityManager em) {
//		this.em = em;
//	}
	
	// 저장
	public void save(Member member) {
		em.persist(member);
	}
	
	// 1건 조회
	public Member findOne( Long id ) {
//		Member member = em.find(Member.class, id);
//		return member;
		
		return em.find(Member.class, id);
	}
	
	// 여러건 조회
	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
	
	// 이름으로 조회
	public List<Member> findByName(String name){
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				 .setParameter("name", name)
				 .getResultList();
	}
	
	
	
}















