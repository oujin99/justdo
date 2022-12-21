package com.koreait.Springtest3.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
	private int number;
	private String name;
	private String phone;
	private String Gender;
	
	public Member(int number, String name, String phone, String gender) {
		super();
		this.number = number;
		this.name = name;
		this.phone = phone;
		Gender = gender;
	}
	
	
}
