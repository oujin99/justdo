package com.koreait.jpaitem.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter @Data
public class Member {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String name;
	
//	@Column(name = "TEAM_ID")
//	private Long teamid;
	
	/*
	 * 	FK 키 생성하기
	 * 		연관관계로 Team 테이블 정보를 가져온다
	 * 		JoinColumn 은 name 을 반드시 명시해야한다 
	 */
	@ManyToOne	// Team 클래스에서 "하나" 가져오기 
	@JoinColumn(name = "TEAM_ID") // 연관 컬럼 이어주기 , TEAM 명칭과 반드시 동일해야한다 
	@Setter(value = AccessLevel.NONE) // lombok 옵션 setter 설정을 막는다 
	private Team team;
	
	
	// 단방향 2개 설정 메소드 
	public void changeTeam(Team team) {
		this.team = team;
		team.getMember().add(this);
		// = team.getMember().add(member);
	}


	public void setTeam(Team team) {
		this.team = team;
	}

	
	
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", team=" + team + "]";
	}
	
	
	
}
