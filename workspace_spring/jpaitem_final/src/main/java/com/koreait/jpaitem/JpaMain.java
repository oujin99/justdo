package com.koreait.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.relration.Member;
import com.koreait.jpaitem.relration.Team;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			/*
			Team team = new Team();
			team.setName("TeamA");
			// 영속상태가 되면, PK의 값이 세팅이 된 후 영속상태 된다.
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
			member.setTeamid(team.getId());
			em.persist(member);
			
			// select 
			// 어느팀 소속인지 알고 싶을 때 jpa or db 에게 계속 물어봐야 한다.
			Member findMember = em.find(Member.class, member.getId());
			Long findTeamid = findMember.getTeamid();
			Team findTeam = em.find(Team.class, findTeamid);
			System.out.println("findTeam : " + findTeam.getName());
			*/
			
			Team team = new Team();
			team.setName("TeamA");
			// 영속상태가 되면, PK의 값이 세팅이 된 후 영속상태 된다.
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
//			member.setTeam(team);
			em.persist(member);
			
			// 강제 db 쿼리를 보고 싶을때
			em.flush();
			em.clear();
			
			// select 
			// find시에 1차캐시에서 가지고 와서 select 문이 없다.
			Member findMember = em.find(Member.class, member.getId());
			Team findTeam = findMember.getTeam();
			System.out.println("findTeam : " + findTeam.getName());
			
			// 수정 
//			Team newTeam = em.find(Team.class, 1L);
//			findMember.setTeam(newTeam);
//			System.out.println("findTeamName : " + newTeam.getName());
//			System.out.println("findTeam.getId() : " + newTeam.getId());
			
			// 양방향 매핑
			Member findSideMember = em.find(Member.class, member.getId());
			List<Member> members = findSideMember.getTeam().getMember();
			
			System.out.println(members);
			
			for( Member m : members ) {
				System.out.println("result = " + m.getName());
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











