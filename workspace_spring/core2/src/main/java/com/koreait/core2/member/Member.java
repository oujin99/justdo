package com.koreait.core2.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

// @Entitiy : JPA 가 관리하는 클래스 
@Entity
public class Member {
	// PK 키 
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySequence")
	@SequenceGenerator(name = "mySequence", sequenceName = "member_seq" , allocationSize = 1) 
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
}
