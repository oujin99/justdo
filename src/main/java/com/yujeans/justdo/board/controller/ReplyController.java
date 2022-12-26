package com.yujeans.justdo.board.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.yujeans.justdo.board.Reply;
import com.yujeans.justdo.board.dto.ReplyFormDto;
import com.yujeans.justdo.board.service.ReplyService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReplyController {

	@Autowired
	private final ReplyService replyService;
	
	// 댓글작성 
    @PostMapping("/board/commentWriteSub")
    public String boardWritePro(@ModelAttribute ReplyFormDto replyFormDto, Model model){
		Reply reply = new Reply();
		reply.setContent(replyFormDto.getContent());
		reply.setStartDate(LocalDate.now().toString());
		replyService.write(reply);
		
		model.addAttribute("replyList", replyService.replyList());
        
        return "redirect:/board/boardView";
    }




}
