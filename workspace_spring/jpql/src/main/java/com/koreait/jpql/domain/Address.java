package com.koreait.jpql.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter @Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
}


















