package com.koreait.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import oracle.net.aso.m;

public class JpaMain2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// transaction 발생
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// 비영속 상태
			Member member = new Member();
			member.setId(100L);
			member.setName("Hello");
			 
			// 영속 상태
			// EntityManager 안에있는 영속 컨텍스트에 관리가 된다는 뜻.
			// DB쿼리가 실행되는지 확인
			System.out.println("======== before ===========");
			em.persist(member);
			System.out.println("======== after ===========");
			
			// 쿼리는 이 시점에 날아간다.
			tx.commit();	
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
		
		
		
		
	}

}
