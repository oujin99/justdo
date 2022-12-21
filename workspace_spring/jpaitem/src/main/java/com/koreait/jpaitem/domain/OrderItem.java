package com.koreait.jpaitem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
//	@Column(name = "ORDER_ID")
//	private Long orderId;
//	@Column(name = "ITEM_ID")
//	private Long itemId;
	private int orderPrice;
	private int count;
	
	// 단방향 FK 
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	
}
