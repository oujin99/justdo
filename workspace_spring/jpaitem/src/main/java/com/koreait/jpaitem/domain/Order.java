package com.koreait.jpaitem.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

//@Entity
@Table(name = "ORDERS")
@Getter @Setter
public class Order {

	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
//	@Column(name = "MEMBER_ID")
//	private Long memberId; //FK 
	private LocalDateTime orderDate;
	private String status;
	
	// 단방향 FK
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	// 두번째 단방향 
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public void addOrderItems(OrderItem orderitem) {
		orderitem.setOrder(this);
		this.orderItems.add(orderitem);
	}
}
