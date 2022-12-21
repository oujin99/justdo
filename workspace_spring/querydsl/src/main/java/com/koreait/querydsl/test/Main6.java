package com.koreait.querydsl.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.querydsl.entity.Member;
import static com.koreait.querydsl.entity.QMember.*;
import static com.koreait.querydsl.entity.QTeam.*;

import java.util.List;

import com.koreait.querydsl.entity.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class Main6 {
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
			
			List<Member> result = queryFactory
									.selectFrom(member)
									// member에 있는 team, team을 조인
									.join(member.team, team)	
									.fetch();
			
			System.out.println(result.toString());
			
			/*
			 * 세타 조인
			 *  - 연관관계가 없는 필드로 조인
			 *  - 회원의 이름이 팀 이름과 같은 회원 조회 
			 */
			em.persist(new Member("teamA"));
			em.persist(new Member("teamB"));
			
			List<Member> result2 = queryFactory
									.select(member)
									.from(member, team)
									.where(member.username.eq(team.name))
									.fetch();
			
			System.out.println(result2.toString());
			
			/*
			 * 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
			 * SQL : 	select 	m.*, t.* 
			 * 			from	member 	m
			 * 					left outer join Team t 
			 * 						on m.team_id = t.team_id and t.name = 'teamA' 
			 */
			
			List<Tuple> result3 = queryFactory
									.select(member, team)
									.from(member)
									.join(member.team, team).on(team.name.eq("teamA"))
									.fetch();
			
			for( Tuple tuple : result3 ) {
				System.out.println("tuple : " + tuple);
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











