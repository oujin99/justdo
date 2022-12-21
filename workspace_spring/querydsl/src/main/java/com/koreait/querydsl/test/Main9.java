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
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class Main9 {
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
			
			// 결과 반환
			// 대상이 하나.
			List<String> result = queryFactory
									.select(member.username)
									.from(member)
									.fetch();
			
			// 대상이 둘 이상일 때 사용
			List<Tuple> result2 = queryFactory
									.select(member.username, member.age)
									.from(member)
									.fetch();
			
			// jpql
// 			List<MemberDto> result3 = em.createQuery(
// 					"select new com.koreait.querydsl.dto.MemberDto(m.username, m.age)"
// 					, MemberDto.class)
// 					.getResultList();
			
			// queryDSL
 			/*
 			 * 프로퍼티 접근 -> setter 접근 방법
 			 * bean
 			 *  1param 	: type지정(ex,MemberDto.class)
 			 *  2param~ : 꺼내올 값 나열
 			 */
 			List<MemberDto> result4 = queryFactory
 								.select(Projections.bean(MemberDto.class, 
 										member.username, member.age))
 								.from(member)
 								.fetch();
 			
 			// 필드 직접 접근
 			// getter, setter 없어도 된다.
 			List<MemberDto> result5 = queryFactory
						.select(Projections.fields(MemberDto.class, 
								member.username, member.age))
						.from(member)
						.fetch();
 			
 			// 생성자 접근
 			
 			// 별칭이 다를 때
 			List<UserDto> result6 = queryFactory
						.select(Projections.fields(UserDto.class, 
								member.username.as("name"), 
								member.age))
						.from(member)
						.fetch();
 			
 			for( UserDto userDto : result6 ) {
 				System.out.println("userDTO : " + userDto);
 			}
 			
 			// 서브쿼리 별칭
 			QMember mSub = new QMember("mSub");
 			
 			List<UserDto> result7 = queryFactory
					.select(Projections.fields(UserDto.class, 
							member.username.as("name"), 
							// ExpressionUtils : 필드나, 서브쿼리 이름 줄때
							ExpressionUtils.as(
								JPAExpressions
									.select(mSub.age.max())
									.from(mSub), "age")))
					.from(member)
					.fetch();
			
			for( UserDto userDto : result7 ) {
				System.out.println("userDTO : " + userDto);
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











