package com.yujeans.justdo.api.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class KakaoController {
	
	@Autowired
	private final KakaoService kakaoService;
	
	@GetMapping("/kakao/test")
	public String test(@RequestParam String code) {
		System.out.println(code);
		System.out.println("카카오가 불렀어");
		return "redirect:/";
	}
	
}
