package com.koreait.jpaitem;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.embedded.Address;
import com.koreait.jpaitem.embedded.Member;
import com.koreait.jpaitem.embedded.Period;

public class JpaMain5 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			/*
			Member member = new Member();
			member.setUsername("user");
			member.setAddress( new Address("서울","역삼", "123") );
			member.setPeriod( new Period(LocalDateTime.now(), LocalDateTime.now()) );
			
			em.persist(member);
			*/
			
			Address addr = new Address("서울", "역삼", "123");
			
			Member member = new Member();
			member.setUsername("user1");
			member.setAddress(addr);
			em.persist(member);
			
			Address codpyAddr = new Address(addr.getCity(), addr.getStreet(), addr.getZipcode());
			
			Member member2 = new Member();
			member2.setUsername("user2");
			// user1과 user2가 같은 addr을 가지고 있다.
			member2.setAddress(codpyAddr);
			em.persist(member2);
			
			// user1의 주소만 newCity로 변경하고 싶다.
//			member.getAddress().setCity("newCity");
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}











