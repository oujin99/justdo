package com.koreait.querydsl.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.querydsl.dto.MemberDto;
import com.koreait.querydsl.dto.UserDto;
import com.koreait.querydsl.entity.Member;
import com.koreait.querydsl.entity.QMember;

import static com.koreait.querydsl.entity.QMember.*;
import static com.koreait.querydsl.entity.QTeam.*;

import java.util.List;

import com.koreait.querydsl.entity.Team;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class Main10 {
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
			
			String usernameParam = null;
			Integer ageParam = null;
			
			List<Member> result = searchMember(usernameParam, ageParam);
			System.out.println("result.size() : " + result.size());
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
	
	private static List<Member> searchMember(String usernameParam, Integer ageParam){
		BooleanBuilder builder = new BooleanBuilder();
		if(usernameParam != null) {
			builder.and(member.username.eq(usernameParam));
		}
		
		if( ageParam != null ) {
			builder.and(member.age.eq(ageParam));
		}
		
		System.out.println("builder : " + builder.toString());
		
		return queryFactory
				.selectFrom(member)
				.where(builder)
				.fetch();
		
	}
	
	

}











