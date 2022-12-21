package com.koreait.querydsl.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.querydsl.entity.Member;
import static com.koreait.querydsl.entity.QMember.*;

import java.util.List;

import com.koreait.querydsl.entity.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class Main2 {
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
			
			// List
//			List<Member> fetch = queryFactory
//									.selectFrom(member)
//									.fetch();
//			
//			// 단건
//			Member fetch2 = queryFactory
//								.selectFrom(member)
//								.fetchOne();
			
			Member fetch3 = queryFactory
								.selectFrom(member)
								.fetchFirst();
			
			System.out.println(fetch3);
			
			long count = queryFactory
							.selectFrom(member)
							.fetchCount();
			
			Long totalCount = queryFactory
								.select(member.count())
								.from(member)
								.fetchOne();
			System.out.println("totalCnt = " + totalCount);
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}











