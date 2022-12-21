package com.koreait.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.domain.Order;
import com.koreait.jpashop.domain.OrderItem;
import com.koreait.jpashop.dto.OrderSearch;
import com.koreait.jpashop.repository.ItemRepository;
import com.koreait.jpashop.repository.MemberRepository;
import com.koreait.jpashop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;

	// 주문
	@Transactional
	public Long order(Long memberId, Long ItemId, int count) {
		// 엔티티 조회
		// jpa 영속성 컨텍스트 영역어 들어 옴
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(ItemId);
		
		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		// 주문 생성
		Order order = Order.createOrder(member, orderItem); 
				
		// 주문 저장
		orderRepository.save(order);
				
		return order.getId();
	}
	
	public List<Order> findOrders(OrderSearch orderSearch){
		return orderRepository.findAll(orderSearch);
	}
	
	// 취소
	@Transactional
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		// 취소(update)에 대한 비지니스 로직 처리
		order.cancel();
	}
	
}






















