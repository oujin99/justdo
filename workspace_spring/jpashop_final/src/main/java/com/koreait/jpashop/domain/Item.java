package com.koreait.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.koreait.jpashop.exception.NotEnoughStockException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	// 비지니스 로직
	// stock 감소
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		// 부족하면
		if( restStock < 0 ) {
			// exception
			throw new NotEnoughStockException("need more stock");
		}
		
		this.stockQuantity = restStock;
	}
	
	// stock 증가
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	
	
}



















