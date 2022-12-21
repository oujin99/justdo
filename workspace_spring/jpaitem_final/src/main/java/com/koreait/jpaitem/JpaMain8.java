package com.koreait.jpaitem;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.koreait.jpaitem.embedded.Address;
import com.koreait.jpaitem.embedded.Member;
import com.koreait.jpaitem.embedded.Period;

public class JpaMain8 {

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
			
			// 파라미터 바인딩 - 이름기준
			/*
			TypedQuery<Member> query
				= em.createQuery("select m from Member m where m.username = :username", Member.class);
			query.setParameter("username", "member1");
			Member result = query.getSingleResult();
			System.out.println("result : " + result.getUsername());
			 */
			
			// 파라미터 바인딩 - 위치기반(쓰지말것)
			Member result
				= em.createQuery("select m from Member m where m.username = ?1", Member.class)
				.setParameter(1, "member1")
				.getSingleResult();
			System.out.println("result : " + result.getUsername());
			
			
			
			
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}











