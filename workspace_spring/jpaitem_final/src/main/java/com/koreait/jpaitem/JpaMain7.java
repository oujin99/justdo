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

public class JpaMain7 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			List<Member> result =  em.createQuery(
									"select m from Member m where m.username like '%kim%' ", 
									Member.class).getResultList();
			
			for( Member member : result ) {
				System.out.println("member = " + member);
			}
			
			// TypedQuery
			// 타입정보가 Member로 명확할때
			TypedQuery<Member> query =
					em.createQuery("select m from Member m ", Member.class);
			
			// 타입정보가 String.class로 반환이 명확할 때
			TypedQuery<String> query2 =
					em.createQuery("select m.username from Member m ", String.class);
			
			
			// m.username(String), m.age(int) : 이렇게 반환타입이 명확하지 않을 때 
			Query query3 =
					em.createQuery("select m.username, m.age from Member m ");
			
			// getResultList()
			TypedQuery<Member> query4 
				= em.createQuery("select m from Member m", Member.class);
			List<Member> resultList = query4.getResultList();
			
			// 결과값 하나일때, getSingleResult()
			TypedQuery<Member> query6
				= em.createQuery("select m from Member m where m.id = 10", Member.class);
			Member result2 = query6.getSingleResult();
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}











