package com.koreait.mylogin.loginWeb.item;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Item {
	private Long id;
	private String itemName;
	private Integer price;
	private Integer quantity;
	
//	private Boolean open;			// 판매여부
//	private List<String> regions;	// 등록 지역
//	private ItemType itemType;		// 상품 종류
//	private String deliveryCode;	// 배송 방식
	
	public Item() {}

	public Item(String itemName, Integer price, Integer quantity) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	
}


