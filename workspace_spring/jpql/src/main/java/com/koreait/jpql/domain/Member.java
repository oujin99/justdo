package com.koreait.jpql.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Member {

	@Id @GeneratedValue
	private Long id;
	private String username;
	private int age;
	
	// 연관관계
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;
	
	
}















