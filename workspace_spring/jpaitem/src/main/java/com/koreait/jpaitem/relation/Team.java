package com.koreait.jpaitem.relation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Team {

	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "team") // Member테이블의 변수명과 동일해야한다
	private List<Member> member = new ArrayList<Member>();
	
	public void addMember(Member member) {
		member.setTeam(this);
		this.member.add(member);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", member=" + member + "]";
	}
	
}
