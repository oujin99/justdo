package com.koreait.item.domain.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.item.domain.item.DeliveryCode;
import com.koreait.item.domain.item.Item;
import com.koreait.item.domain.item.ItemRepository;
import com.koreait.item.domain.item.ItemType;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
// @RequiredArgsConstructor 
// : final 이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다
public class ItemController {

	private final ItemRepository itemRepository;
	
//	@Autowired
	// 생성자가 1개만 있으면 @Autowired 생략가능
//	public ItemController(ItemRepository itemRepository) {
//		this.itemRepository = itemRepository;
//	}
	
	// @ModelAttribute : 
	// 		Controller를 호출할때(어떤 메서드가 호출이 되던간에)
	//		model에 자동으로 해당 내용이 담기는게 보장된다.
	@ModelAttribute("regions")
	public Map<String, String> regions(){
		// LinkedHashMap : 순서가 보장된다.
		Map<String, String> regions = new LinkedHashMap<String, String>();
		regions.put("SEOUL", "서울");
		regions.put("BUSAN", "부산");
		regions.put("JEJU", "제주");
		return regions;
	}
	
	@ModelAttribute("itemType")
	public ItemType[] itemTypes() {
		// enum에 있는 값을 배열로 넘겨준다.
		return ItemType.values();
	}
	
	@ModelAttribute("deliveryCodes")
	public List<DeliveryCode> deliveryCodes(){
		List<DeliveryCode> deliveryCodes = new ArrayList<DeliveryCode>();
		deliveryCodes.add( new DeliveryCode("FAST", "빠른배송") );
		deliveryCodes.add( new DeliveryCode("NORMAL", "일반배송") );
		deliveryCodes.add( new DeliveryCode("SLOW", "느린배송") );
		return deliveryCodes;
	}
	
	
	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}
	
	// /basic/items/아이템의ID
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("item", new Item());
		return "basic/addForm";
	}
	
//	@PostMapping("/add")
	public String save(	@RequestParam String 	itemName,
						@RequestParam int		price,
						@RequestParam Integer	quantity,
						Model model) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		
		itemRepository.save(item);

		model.addAttribute("item", item);
		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String saveV2( @ModelAttribute("item")Item item) {
		// @ModelAttribute 가 해주는 역할
//		Item item = new Item();
//		item.setItemName(itemName);
//		item.setPrice(price);
//		item.setQuantity(quantity);
		
		itemRepository.save(item);

//		model.addAttribute("item", item);
		return "basic/item";
	}
	
	/*
	 * @ModelAttribute 에서 name 생략 
	 *  -> 생략시 model에 저장되는 name은 클래스명 첫 글자만 소문자로 등록
	 *  	Item -> item
	 */
//	@PostMapping("/add")
	public String saveV3( @ModelAttribute Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	/*
	 * @ModelAttribute 자체도 생략 가능,그러나 가독성을 위해 권장하지 않음
	 */
//	@PostMapping("/add")
	public String saveV4( Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String saveV5( Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/" + item.getId();
	}
	
	/*
	 * redirect:/basic/items/{itemId}
	 * 	-> @PathVariable  		: {itemId}
	 *  -> 나머지는 파라미터로 처리	: ?status=true
	 */
//	@PostMapping("/add")
	public String saveV6( Item item, RedirectAttributes redirectAttributes) {
		
		System.out.println("item.open = " + item.getOpen());
		System.out.println("item.regions = " + item.getRegions());
		System.out.println("item.itemType = " + item.getItemType());

		Item saveItem =  itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status",true);
		
		return "redirect:/basic/items/{itemId}";
	}
	
	/*
	 *  BindingResult : Item 객체에 값이 잘 담기지 않을 때, 
	 *  	BindingResult 객체에 값이 담긴다.
	 *  	Spring 에서 제공하는 검증 오류를 보관하는 객체, 검증 오류가 발생시 여기에 보관 
	 *  
	 *  	주의 ) BindingResult 는 검증할 대상 바로 다음에 설정해야한다 ( 순서 중요 ) 
	 *  	BindingResult 는 Model 에 자동으로 포함된다.
	 */
//	@PostMapping("/add")
	public String saveV7( Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		// StringUtils.hasText
		// 값이 있을경우 true , 공백이나 null 들어올 경우에는 false를 반환하게 된다.
		if ( ! StringUtils.hasText(item.getItemName()) ) {
			// FieldError : Field 단위에 error를 처리하는 Spring 제공 객체 
			bindingResult.addError( new FieldError("item", "itemName", "상품 이름은 필수 입니다."));
		}
		
		// 검증에 실패하면 다시 addForm 화면으로 돌아가도록 설정
		
		if ( bindingResult.hasErrors()) {
			System.out.println("errors= " + bindingResult);
			return "basic/addForm";
		}
		
		// Price 조건
		
		if (item.getPrice() == null || item.getPrice() < 1000 
				|| item.getPrice() > 10000000) {
			bindingResult.addError( new FieldError("item", "price", "가격은 1000~1000000 사이여야합니다"));
		}
		
		// quantity 조건
		
		if ( item.getQuantity() == null || item.getQuantity() > 100000) {
			bindingResult.addError( new FieldError("item", "quantity", " 수량은 최대 9999개 까지 가능합니다"));
		}
		
		System.out.println("item.open = " + item.getOpen());
		System.out.println("item.regions = " + item.getRegions());
		System.out.println("item.itemType = " + item.getItemType());

		Item saveItem =  itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status",true);
		
		return "redirect:/basic/items/{itemId}";
	}

	//@PostMapping("/add")
	public String saveV8 ( Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		/*
		 *  FieldError 파라미터 
		 *  	- objectName	=	오류가 발생한 객체이름
		 *  	- field			=	오류 필드
		 *  	- rejectedvalue	=	사용자가 입력한 잘못된 값 
		 *  	- bindingFailure= 	타입오류와 같은 바인딩 실패인지 오류 - 바인딩 값이 없기에 false
		 *  	- codes			=	메세지 코드
		 *  	- Arguments		= 	메세지에서 사용하는 인자 
		 *  	- deFaultMessage=	기본 오류 메세지 
		 */
		
		if ( ! StringUtils.hasText(item.getItemName()) ) {
			bindingResult.addError( new FieldError("item", "itemName", item.getItemName(), false , null, null, "상품이름은 필수 입니다."));
		}
		
		
		if ( bindingResult.hasErrors()) {
			System.out.println("errors= " + bindingResult);
			return "basic/addForm";
		}
		
		
		if (item.getPrice() == null || item.getPrice() < 1000 
				|| item.getPrice() > 10000000) {
			bindingResult.addError( new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1000~1000000 사이여야합니다"));
		}
		
		
		if ( item.getQuantity() == null || item.getQuantity() > 100000) {
			bindingResult.addError( new FieldError("item", "quantity", item.getQuantity(), false, null, null, " 수량은 최대 9999개 까지 가능합니다"));
		}
		
		System.out.println("item.open = " + item.getOpen());
		System.out.println("item.regions = " + item.getRegions());
		System.out.println("item.itemType = " + item.getItemType());

		Item saveItem =  itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status",true);
		
		return "redirect:/basic/items/{itemId}";
	}
	
	@PostMapping("/add")
	public String saveV9 ( Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		String defaultmessage = "지역변수로 오류 설정해보기";
		
		if ( ! StringUtils.hasText(item.getItemName()) ) {
			bindingResult.addError( new FieldError("item", "itemName", item.getItemName(), false ,
					new String[] {"required.item.itemName","required.default"}, null, defaultmessage));
		}
		
		
		if ( bindingResult.hasErrors()) {
			System.out.println("errors= " + bindingResult);
			return "basic/addForm";
		}
		
		
		if (item.getPrice() == null || item.getPrice() < 1000 
				|| item.getPrice() > 10000000) {
			bindingResult.addError( new FieldError("item", "price", item.getPrice(), false,
					new String[] {"range.item.price"}, new Object[] {1000,1000000}, defaultmessage));
		}
		
		
		if ( item.getQuantity() == null || item.getQuantity() > 100000) {
			bindingResult.addError( new FieldError("item", "quantity", item.getQuantity(), false,
					new String[] {"max.item.quantity"}, new Object[] {9999}, defaultmessage));
		}
		
		System.out.println("item.open = " + item.getOpen());
		System.out.println("item.regions = " + item.getRegions());
		System.out.println("item.itemType = " + item.getItemType());

		Item saveItem =  itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", saveItem.getId());
		redirectAttributes.addAttribute("status",true);
		
		return "redirect:/basic/items/{itemId}";
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm( @PathVariable Long itemId, Model model ) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		// 상세페이지
		return "redirect:/basic/items/{itemId}";
	}
	
	// 테스트용 데이터 추가
	@PostConstruct
	public void init() {
//		System.out.println("초기화 메서드");
		itemRepository.save(new Item("testA", 10000, 10));
		itemRepository.save(new Item("testB", 20000, 20));
	}
	
	// 종료 메서드
	@PreDestroy
	public void destroy() {
		System.out.println("종료 메서드");
	}
	
	
	
	
}












