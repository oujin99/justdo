package com.koreait.querydsl.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.querydsl.entity.Member;
import static com.koreait.querydsl.entity.QMember.*;
import com.koreait.querydsl.entity.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class Main {
	static JPAQueryFactory queryFactory;
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		queryFactory = new JPAQueryFactory(em);
		tx.begin();
		
		try {
			Team teamA = new Team("teamA");
			Team teamB = new Team("teamB");
			em.persist(teamA);
			em.persist(teamB);
			
			Member member1 = new Member("member1", 10, teamA);
			Member member2 = new Member("member2", 20, teamA);
			Member member3 = new Member("member3", 30, teamB);
			Member member4 = new Member("member4", 40, teamB);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			
			// 초기화
			em.flush();
			em.clear();
			
			// jpql
			// member1 찾기
			String qString = "select m from Member m where m.username = :username";
			Member findByJpql = em.createQuery(qString, Member.class)
								  .setParameter("username", "member1")
								  .getSingleResult();
			
			System.out.println("findByJpql : " + findByJpql.getUsername().equals("member1"));
			
			// queryDSL
			//QMember m = new QMember("m");	// QMember의 이름 부여, 별칭 부여
			//QMember m = QMember.member;
			
			Member findByQueryDSL = queryFactory.select(member)
										.from(member)
										.where(member.username.eq("member1")
												.and(member.age.eq(10)))	// 파라미터 바인딩
										.fetchOne();
			
//			System.out.println("findByQueryDSL : " + findByQueryDSL.getUsername().equals("member1"));
			System.out.println("findByQueryDSL : " + findByQueryDSL.toString());
			
			Member findQueryDSL2 = queryFactory.selectFrom(member)
											.where(member.username.eq("member1")
													.and(member.age.between(10, 30)))
											.fetchOne();
			
			Member findQueryDSL3 = queryFactory.selectFrom(member)
											.where(
												member.username.eq("member1"),
												member.age.between(10, 30)
											)
											.fetchOne();
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}











