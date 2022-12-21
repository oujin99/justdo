package com.koreait.querydsl.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.querydsl.entity.Member;
import com.koreait.querydsl.entity.QMember;

import static com.koreait.querydsl.entity.QMember.*;
import static com.koreait.querydsl.entity.QTeam.*;

import java.util.List;

import com.koreait.querydsl.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class Main8 {
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
			
			// case문
			List<String> result = queryFactory
									.select(member.age
										.when(10).then("열살")
										.when(20).then("스무살")
										.otherwise("기타"))
									.from(member)
									.fetch();
			
			for(String s : result) {
				System.out.println("s : " + s);
			}
			
			// 상수, 문자 더하기
			List<Tuple> result2 = queryFactory
									.select(member.username, Expressions.constant("A"))
									.from(member)
									.fetch();
			
			for(Tuple s : result2) {
				System.out.println("Tuple : " + s);
			}					
			
			// concat
			// {username}_{age}
			// stringValue() : 문자로 바꾼다
			List<String> result3 = queryFactory
					.select(member.username.concat("_").concat(member.age.stringValue()))
					.from(member)
					.fetch();
			
			
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}











