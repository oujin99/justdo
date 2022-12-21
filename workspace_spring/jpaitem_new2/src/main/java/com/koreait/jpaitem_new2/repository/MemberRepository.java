package com.koreait.jpaitem_new2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.jpaitem_new2.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	// @PersistenceContext : JPA 가 지원해주는 표준 어노테이션 
	// 		Spring 이 EntityManager 를 생성해 em 에 주입하고 
	//		em 을 통해 EntityManage 메소드를 사용가능케 한다 
//	@PersistenceContext
	@Autowired // 객체 의존성 주입 
	private final EntityManager em;

	// 생성자 주입 ( @RequiredArgsConstructor ) 
	public MemberRepository(EntityManager em) {
		super();
		this.em = em;
	}
	
	// 비니지스 로직 구현
	
	// 저장 
	public void save(Member member) {
		em.persist(member);
	}
	// 1건 조회
	public Member findOne( Long id) {
		return em.find(Member.class, id);
	}	
	// (조건 X ) 여러 건 조회
	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
	
	// 이름으로 조회 
	public List<Member> findByName(String name){
		return em.createQuery("select m from Member m where m.name = name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
	
	
	
	
	
}
