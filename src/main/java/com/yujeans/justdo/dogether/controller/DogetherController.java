package com.yujeans.justdo.dogether.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DogetherController {
	
	@GetMapping("/dogether/registForm")
	public String dogetherRegistForm(HttpServletRequest request) {
		System.out.println("nickname : "+request.getAttribute("nickname"));
		System.out.println("id : "+request.getAttribute("id"));
		
		return "dogether/dogether_regist";
	}
	
	
	@GetMapping("/dogether/listForm/{thirdCategoryName}")
	public String dogetherListForm(@PathVariable("thirdCategoryName") String thirdCategory) {
		System.out.println("thirdCategoryName : "+thirdCategory);
		return "dogether/dogether_list";
	}
	
	@GetMapping("/dogether/listForm")
	public String dogetherListForm() {
		return "dogether/dogether_list";
	}
	
	
}
