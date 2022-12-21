package com.koreait.jpaitem.domain;

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
public class Member {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
	private String city;
	private String street;
	private String zipcode;
	
	// 두번째 단 방향 
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();
	
	public void addOrder(Order order) {
		order.setMember(this);
		this.orders.add(order);
	}
}
