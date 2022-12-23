package com.yujeans.justdo.category.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yujeans.justdo.category.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoryController {
	
	@Autowired
	private final CategoryService categoryService;
	
//	@GetMapping("/test")
//	public String categoryfind() {
//		List<Tuple> categorList = categoryService.findCategory();
//		for( Tuple tuple: categorList) {
//			System.out.println(tuple.get(0).toString());
//			System.out.println(tuple.get(1).toString());
//			System.out.println(tuple.get(2).toString());
//		}
//		return "/index.html";
//	}
	
//	@ResponseBody
//	@PostMapping("/category/second")
//	public List<SecondCategoryDTO> findSelectedCategory(@RequestBody CategoryDTO cateData ){
////		System.out.println("Ȯ��~~~~" + cateData.getCateData());
//		List<Tuple> categorys = categoryService.findSelectedCategory(cateData.getCateData());
//		List<SecondCategoryDTO> secondCategoryDTOs = new ArrayList<SecondCategoryDTO>();
//		
//		for(Tuple tuple : categorys) {
//			int i = 0;
////			System.out.println(tuple.get(i));
//			SecondCategoryDTO secondCategoryDTO = new SecondCategoryDTO();
//			secondCategoryDTO.setName(tuple.get(i).toString());
//			secondCategoryDTOs.add(secondCategoryDTO);
//			i++;
//		}
//		
//		return secondCategoryDTOs; 
//	}
	
	
}
