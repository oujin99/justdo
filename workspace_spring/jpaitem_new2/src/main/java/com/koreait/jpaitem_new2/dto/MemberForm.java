package com.koreait.jpaitem_new2.dto;

import javax.validation.constraints.NotEmpty;

public class MemberForm {

	@NotEmpty(message = "회원이름은 필수 입니다" )
	private String name;
	private String city;
	private String street;
	private String zipcode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
