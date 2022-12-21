package com.koreait.jpql.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "Orders")
public class Order {
	
	@Id @GeneratedValue
	private Long id;
	private int orderAmount;
	@Embedded
	private Address address;

}



















