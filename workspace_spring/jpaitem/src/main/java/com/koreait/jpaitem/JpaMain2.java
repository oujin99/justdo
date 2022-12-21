package com.koreait.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.relation.Member;
import com.koreait.jpaitem.relation.Team;

public class JpaMain2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
//			Member member = new Member();
//			member.setName("member1");
//			em.persist(member);
//			
//			Team team = new Team();
//			team.setName("TeamA");
//			team.getMember().add(member);
//			em.persist(team);
			
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
//			member.changeTeam(team); //team.getMember().add(member);
			em.persist(member);
			
			team.addMember(member);
			
			em.flush();
			em.clear();
			
			/* 	객체 지향적인 입장에서 양쪽에 모두 값을 넣어줘야한다
			* 	양방향 매핑시에는 양쪽에 값을 모두 입력해주어야햔다
			* 	DB를 다시 다녀오지 않고 객체 상태로만 사용 할 수 있다.
			* 
			* 		MEMBER 테이블에도 member.setTeam(team); 하고
			* 		TEAM 테이블 에도 team.getMember().add(member); 해야한다 
			*/
			
			
			
			System.out.println("==========================================");

			Team findTeam = em.find(Team.class, team.getId());
			List<Member> members = findTeam.getMember();
			
			for (Member m : members) {
				System.out.println("m 객체 = " + m.getName());
			}
			
			System.out.println("==========================================");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			
		}finally {
			em.close();
			emf.close();
		}
		
	}
}
