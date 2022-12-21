package com.koreait.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.relation.Member;
import com.koreait.jpaitem.relation.Team;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			/*
			// 연관관계 매핑 시작 
			Team team = new Team();
			team.setName("TeamA");
			// em.persist = 영속상태로 전환 
			// 영속상태가 되면 PK의 값이 세팅된 이후, 영속상태로 전환된다.
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
//			member.setTeam(team.getId());
			// 영속상태로 전환 
			em.persist(member);
			
			// select
			//  어느팀 소속인지 알고 싶을 때 JPA 또는 DB에 계속 출력하여 찾아야한다.
			Member findMember = em.find(Member.class, member.getId());
//			Long findTeamId = findMember.getteamId();
			Team findTeam = em.find(Team.class, findTeamid);
			System.out.println("findTEAM 결과값 = " + findTeam.getName());
			*/
			
			
			// 연관관계 매핑 시작 
			Team team = new Team();
			team.setName("TeamA");
			// em.persist = 영속상태로 전환 
			// 영속상태가 되면 PK의 값이 세팅된 이후, 영속상태로 전환된다.
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
			member.setTeam(team);
			em.persist(member);
			
			// 강제로 DB 쿼리를 조회할 때
			em.flush();	// 영속성 컨텍스트 넣기 
			em.clear();	// 영속성 컨텍스트 비워내기
			
			//select 
			// find 시, 1차 캐시에 가지고 오기에 select 문을 없다 
			Member findMember = em.find(Member.class, member.getId());
			Team findTeam = findMember.getTeam();
			System.out.println("findTEAM 결과값 = " + findTeam.getName());
		
//			// update 수정
//			Team newTeam = em.find(Team.class, 1L); // 10번으로 변경
//			findMember.setTeam(newTeam);
//			System.out.println(" findTeamName = " + newTeam.getName());
//			System.out.println(" findTeam.getId() = " + newTeam.getId());
			
			// 양방향 매핑 
			Member findSideMember = em.find(Member.class, member.getId());
			List<Member> members =  findSideMember.getTeam().getMember();
			
			for ( Member m : members) {
				System.out.println("result = " + m.getName());
			}
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			
		}finally {
			em.close();
			emf.close();
		}
		
	}
}
