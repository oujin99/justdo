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

public class Main3 {
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
			Member member5 = new Member(null, 100, teamB);
			Member member6 = new Member("member6", 100, teamB);
			Member member7 = new Member("member7", 100, teamB);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			em.persist(member6);
			em.persist(member7);
			
			// 초기화
			em.flush();
			em.clear();
			
			/*
			 * 회원 정렬 순선
			 *  - 1. 회원 나이 내림차순(desc)
			 *  - 2. 회원 이름 올림차순(asc)
			 *  - 단, 2에서 회원 이름이 없으면 마지막에 출력(nulls last)
			 */
			List<Member> result = queryFactory
									.selectFrom(member)
									.where(member.age.eq(100))
									.orderBy(member.age.desc(), member.username.asc().nullsLast())
									.fetch();
			
			System.out.println("result1 : " + result.get(0));
			System.out.println("result2 : " + result.get(1));
			System.out.println("result3 : " + result.get(2));
			
			
			
			
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}











