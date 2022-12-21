package com.koreait.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpql.domain.Address;
import com.koreait.jpql.domain.Member;
import com.koreait.jpql.domain.Team;

import oracle.net.aso.m;

public class JpaMain5 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			for(int i = 0; i<100; i++) {
				Member member = new Member();
				member.setUsername("member" + i);
				member.setAge(i);
				em.persist(member);
			}
			
			em.flush();
			em.clear();
			
			// 페이징 처리
			String jpql = "select m from Member m order by m.id";
			List<Member> resultList = em.createQuery(jpql, Member.class)
										.setFirstResult(30)
										.setMaxResults(10)
										.getResultList();
			
			System.out.println("result.size : " + resultList.size());
			
			for( Member member1 : resultList ) {
				System.out.println("member1 = " + member1.toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}











