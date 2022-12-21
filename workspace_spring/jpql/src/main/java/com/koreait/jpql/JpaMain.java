package com.koreait.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpql.domain.Member;

import oracle.net.aso.m;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			em.flush();
			em.clear();
			
			// 영속성 컨텍스트에 관리가 될까 안될까?
			List<Member> result = em.createQuery("select m from Member m", Member.class)
									.getResultList();
			
			// update 가 된다 -> 영속성 컨텍스트에서 관리가 된다.
			// update 가 안된다 -> 영속성 컨텍스트에서 관리가 안된다.
			Member findMember = result.get(0);
			findMember.setAge(20);
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}











