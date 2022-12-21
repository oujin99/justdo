package com.koreait.jpaitem.embedded;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main_embedded {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
//			member.setUsername("userA");
//			member.setAddress( new Address("서울", "역삼", "1234"));
//			member.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
//			
//			em.persist(member);
			
			Address addr = new Address("서울","역삼","123");
			
			Member member = new Member();
			member.setUsername("user1");
			member.setAddress(addr);
			em.persist(member);
			
			Address codyAddr = new Address(addr.getCity(), addr.getStreet(), addr.getZipcode());
			
			Member member2 = new Member();
			member2.setUsername("user2");
			member2.setAddress(codyAddr);
			em.persist(member2);
			
//			member.getAddress().setCity("newCity");
			
			// user 1 의 주소만 NewCity 로 변경하고 싶다면
			Address newAddr = new Address("newCity", "역삼", "123");
			member.setAddress(newAddr);
			em.persist(member);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
		
	}
}
