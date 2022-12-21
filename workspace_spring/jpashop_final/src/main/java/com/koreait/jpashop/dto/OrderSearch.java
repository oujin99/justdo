package com.koreait.jpashop.dto;

import com.koreait.jpashop.domain.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

	private String memberName;
	private OrderStatus orderStatus;
}






