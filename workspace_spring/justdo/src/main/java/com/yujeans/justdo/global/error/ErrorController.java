package com.yujeans.justdo.global.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/error/isNotValidToken")
	public String isNotVaildToken() {
		//System.out.println("isNotValidToken 컨트롤러 왔어요");
		return "/error/notValidTokenError";
	}
}
