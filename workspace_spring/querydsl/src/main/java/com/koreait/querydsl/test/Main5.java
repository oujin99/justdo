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

public class Main5 {
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
			 * [jpql]
			 * select	
			 *  count(m),		// 회원수
			 *  sum(m.age),		// 나이 합
			 *  avg(m.age),		// 평균 나이
			 *  max(m.age),		// 최대 나이
			 *  min(m.age)		// 최소 나이
			 * from		Member m
			 */
			
			List<Tuple> result = queryFactory
									.select( member.count(),
											 member.age.sum(),
											 member.age.avg(),
											 member.age.max(),
											 member.age.min()
									)
									.from(member)
									.fetch();
			
			Tuple tuple = result.get(0);
			
			System.out.println(tuple.get(member.count()));
			System.out.println(tuple.get(member.age.sum()));
			System.out.println(tuple.get(member.age.avg()));
			System.out.println(tuple.get(member.age.max()));
			System.out.println(tuple.get(member.age.min()));
			
			// group by 사용
			/*
			 * 팀의 이름과 각 팀의 평균 연령 구하기
			 */
			List<Tuple> result2 = queryFactory
									.select(team.name, member.age.avg())
									.from(member)
									.join(member.team, team)// member에 있는 team과 team join
									.groupBy(team.name)
									.fetch();
			
			System.out.println("teamA : " + result2.get(0).toString());
			System.out.println("teamB : " + result2.get(1).toString());
			
			// having
			List<Tuple> result3 = queryFactory
									.select(team.name, member.age.avg())
									.from(member)
									.join(member.team, team)
									.groupBy(team.name)
									.having(member.age.avg().gt(10))
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











