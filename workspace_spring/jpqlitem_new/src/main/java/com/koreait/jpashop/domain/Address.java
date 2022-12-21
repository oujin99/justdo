package com.koreait.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {

	private String city;
	private String street;
	private String zipcode;
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	// jpa스펙상 만들어 놓은 기본 생성자
	// 함부로 new를 통해서 생성하지 못하도록 한다.
	protected Address(){}
	
}
