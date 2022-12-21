package com.koreait.jpaitem.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Item {

	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long Id;
	private String name;
	private int price;
	private int stockQuantity;
	
	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItems
		= new ArrayList<OrderItem>();
	
	public void addOrderItem(OrderItem orderItem) {
		orderItem.setItem(this);
		this.orderItems.add(orderItem);
	}
	
}














