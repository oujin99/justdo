package com.koreait.jpaitem_new2.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {

	
	public String getCity() {
		return city;
	}


	public String getStreet() {
		return street;
	}


	public String getZipcode() {
		return zipcode;
	}


	private String city;
	private String street;
	private String zipcode;
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	protected Address(){}
	
}
