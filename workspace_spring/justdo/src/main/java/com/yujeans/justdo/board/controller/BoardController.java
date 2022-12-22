package com.yujeans.justdo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/board/boardListForm")
	public String boardListForm() {
		return "board/board_list";
	}
	
}
