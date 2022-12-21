package com.koreait.mylogin.loginWeb.item;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
// @RequiredArgsConstructor 
// : final 이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다
public class ItemController {

	private final ItemRepository itemRepository;
	
	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "items/items";
	}
	
	// /items/items/아이템의ID
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "items/item";
	}
	
	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("item", new Item());
		return "items/addForm";
	}
	
	
	@PostMapping("/add")
	public String saveV9( Item item, BindingResult bindingResult, 
			RedirectAttributes redirectAttributes) {
		if( !StringUtils.hasText(item.getItemName()) ) {
			bindingResult.addError
				( new FieldError("item", "itemName", item.getItemName(), false, 
						new String[] {"required.item.itemName","required.default"}, null, null));
		}
		if( item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000 ) {
			bindingResult.addError
				( new FieldError("item", "price", item.getPrice(), false, 
						new String[] {"range.item.price"}, new Object[] {1000, 10000}, null));
		}
		if( item.getQuantity() == null || item.getQuantity() > 10000 ) {
			bindingResult.addError
			( new FieldError("item", "quantity", item.getQuantity(), false, 
					new String[] {"max.item.quantity"}, new Object[] {9999}, null));
		}
		
		if(bindingResult.hasErrors()) {
			System.out.println("errors = " + bindingResult);
			return "items/addForm";
		}

		Item saveItem =  itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status",true);
		
		return "redirect:/items/items/{itemId}";
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm( @PathVariable Long itemId, Model model ) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "items/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		// 상세페이지
		return "redirect:/items/items/{itemId}";
	}

}












