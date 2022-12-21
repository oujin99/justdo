package com.koreait.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController{
	
	@GetMapping("hello")
	public String hello(Model model) {
		/*
		 *  model 이라는 이름의 Model 값을 호출하여 
		 *  String hello 가 실행되는 순간 
		 *  data 라는 key 값에 hello ! value 값을  Model 영역에 담는다 
		 * 
		 */
		System.out.println("Controller 도착");
		model.addAttribute("data", "hello !");
		return "hello";
	}
	
	// http://localhost:9092/hello-mvc?name=SpringMVC
//	@GetMapping("hello-mvc")
//	public String helloMVC(@RequestParam("name") String param , Model model) {
//		// @RequestParam("name") = ?name=
//		// - requried : 파라미터값 필수 여부 , true = 필수 , false = 필수 아님 
//		
//		// SpringMVC = String param 값에 입력 후
//		// Model 에 저장하여 출력 
//		
//		model.addAttribute("name", param);
//		return "hello-template";
//		
//	}
	
	@GetMapping("hello-mvc")
	public String helloMVC2(@RequestParam(value="name", required = false , defaultValue = "required test" ) String param , Model model) {
		// - requried : 파라미터값 필수 여부 , true = 필수 , false = 필수 아님 
		// - defaultValue : 파라미터 미 선언 시 기본값으로 model 에 값 추가하기
		
		model.addAttribute("name", param);
		return "hello-template";
		
	}

}
