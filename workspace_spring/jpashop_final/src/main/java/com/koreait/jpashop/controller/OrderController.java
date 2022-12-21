package com.koreait.jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.domain.Order;
import com.koreait.jpashop.dto.OrderSearch;
import com.koreait.jpashop.service.ItemService;
import com.koreait.jpashop.service.MemberService;
import com.koreait.jpashop.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
	
	private final MemberService memberService;
	private final ItemService itemService;
	private final OrderService orderService;
	
	
	// request : order
	// member 조회, item 조회
	// response : order/orderForm
	@GetMapping("/order")
	public String createForm(Model model) {
		// member 조회
		List<Member> members = memberService.findMembers();
		// item 조회
		List<Item> items = itemService.findItems();
		
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		
		return "order/orderForm";
	}
	
	@PostMapping("/order")
	public String order( @RequestParam("memberId") 	Long memberId,
						 @RequestParam("itemId")	Long itemId,
						 @RequestParam("count")		int count) {
		
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}
	
	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch,  Model model) {
		// 조회
		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);
		return "order/orderList";
	}
	
	// 취소
	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId")Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
}

















