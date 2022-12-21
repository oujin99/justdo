package com.koreait.jpaitem.relration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter 
public class Member {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String name;
	
//	@Column(name = "TEAM_ID")
//	private Long teamid;
	
	
	// @ManyToOne : 여기에선 Team이 하나
	// @JoinColumn(name = "TEAM_ID") : 관계 컬럼을 적어준다. TEAM_ID와 조인해야 한다.
	// 외래키가 있는 객체가 주인
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	@Setter(value = AccessLevel.NONE)	// lombok 에서 자동 setter 생성을 막아준다.
	private Team team;

	// 일반적으로 setter의 형태가 아니면 메서드 이름을 바꿔준다.
	// 추후 소스코드를 봤을때 단순 setter의 작업이 아닌 중요한 작업을 진행하는지를 파악 할 수 있다.
	public void changeTeam(Team team) {
		this.team = team;
		// this : 나 자신의 인스턴스를 넣어준다.
		team.getMember().add(this);
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	
	// lombok -> @ToString
	@Override
	public String toString() {
		return "Member [id=" + id 
				+ ", name=" + name 
				+ ", team=" + team + "]";
	}
	
	
	
	
}















