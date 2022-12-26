package com.yujeans.justdo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	@GetMapping
	public String mainPage(@RequestParam(required=false, defaultValue="false") boolean joinStatus, Model model) {
		model.addAttribute("joinStatus", true);
		return "/main/index";
	}
}
