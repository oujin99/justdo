package com.koreait.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.jpashop.domain.Item;
import com.koreait.jpashop.dto.ItemForm;
import com.koreait.jpashop.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	@GetMapping( value = "/items/new")
	public String createForm(Model model) {
		model.addAttribute("form", new ItemForm());
		return "items/createItemForm";
	}

	@PostMapping( value = "/items/new")
	public String create(ItemForm itemform) {
		Item item = new Item();
		item.setName(itemform.getName());
		item.setPrice(itemform.getPrice());
		item.setStockQuantity(itemform.getStockQuantity());
		
		itemService.saveItem(item);
		return "redirect:/";
	}
	
}
