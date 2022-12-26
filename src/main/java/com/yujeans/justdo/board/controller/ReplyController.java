package com.yujeans.justdo.board.controller;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.yujeans.justdo.board.Reply;
import com.yujeans.justdo.board.dto.ReplyFormDto;
import com.yujeans.justdo.board.service.BoardService;
import com.yujeans.justdo.board.service.ReplyService;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.service.CredentialService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class ReplyController {

	@Autowired
	private final ReplyService replyService;
	
	@Autowired
	private final CredentialService credentialService;
	
	@Autowired
	private final BoardService boardService;
	
	// 댓글작성 
    @PostMapping("/board/commentWriteSub/{id}")
    public String boardWritePro(@Validated @ModelAttribute ReplyFormDto replyFormDto, Model model
    		,BindingResult bindingResult, HttpServletRequest request, @PathVariable("id") Long id){
		
    	if(bindingResult.hasErrors()) {
			System.out.println("========= bindingResult Error =========");
			System.out.println(bindingResult.toString());
			System.out.println(bindingResult.getAllErrors().get(0));
			return "/board/board_view";
		}
    	
    	Reply reply = new Reply();
    	
    	Account account  = credentialService.findUserInfo((String)request.getAttribute("id"));
    	
		reply.setContent(replyFormDto.getContent());
		reply.setStartDate(LocalDate.now().toString());
		reply.setAccount(account);
		reply.setBoard(boardService.findById(id).get());
		replyService.write(reply);
		model.addAttribute("replyList", replyService.replyList());
        
        return "redirect:/board/boardView/"+id;
    }
    
     // 댓글 삭제
    @Transactional
    @GetMapping("/board/commentDelete/{boardId}/{replyId}")
    public String boardDelete(@PathVariable("boardId") Long boardId, @PathVariable("replyId") Long replyId, Model model){
    	System.out.println("댓글삭제 : " + replyId);
    	replyService.replyDelete(replyId);
        return "redirect:/board/boardView/" + boardId;
    }




}
