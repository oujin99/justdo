package com.koreait.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import oracle.net.aso.m;

public class JpaMain3 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// transaction 발생
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Member2 member2 = new Member2();
//			member2.setId("ID_A");
			member2.setUsername("HELLO");
			
			em.persist(member2);
			
			tx.commit();	
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
		
		
		
		
	}

}
