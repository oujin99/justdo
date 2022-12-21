package com.yujeans.justdo.board.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.yujeans.justdo.board.Board;
import com.yujeans.justdo.board.dto.BoardFormDto;
import com.yujeans.justdo.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardContoroller {

	@Autowired
	private final BoardService boardService;

	// 글작성 페이지 이동
	@GetMapping("/board/boardWrite") 
    public String boardwriteForm(){

        return "/board/boardwrite";
    }

	// 글작성 
    @PostMapping("/board/boardWriteSub")
    public String boardWritePro(@ModelAttribute BoardFormDto boardFormDto, Model model){
		Board board = new Board();
		board.setContent(boardFormDto.getContent());
		board.setTitle(boardFormDto.getTitle());
		board.setStartDate(LocalDate.now().toString());
        boardService.write(board);
        
        model.addAttribute("boardList", boardService.boardList()) ;
        
        return "/board/boardList";
    }
    
    // 리스트 페이지 이동
    @GetMapping("/board/boardList")
    public String boardList(Model model){
        System.out.println(boardService.boardList());
        model.addAttribute("boardList",boardService.boardList());
        
        return "/board/boardList";
    }
    
    // 상세보기 이동
    @GetMapping("/board/boardView")
    public String boardView(Model model , Long id){

        model.addAttribute("boardView",boardService.boardView(id));
        return "/board/boardView";
    }
    
    // 게시글 삭제
    @GetMapping("/board/boardDelete")
    public String boardDelete(Long id){
        boardService.boardDelete(id);

        return "redirect:/board/boardList";
    }
    
    // 게시글 수정 이동
    @GetMapping("/board/boardModify/{id}")
    public String boardModify(@PathVariable("id") Long id , Model model){
        System.out.println("수정폼");
        model.addAttribute("boardModify",boardService.boardView(id));

        return "/board/boardmodify";
    }
    
	// 게시글 수정
    @PostMapping("/board/boardUpdate/{id}")
    public String boardUpdate(@PathVariable("id") Long id , Board board , Model model){

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        model.addAttribute("message" , "글 수정 완료.");
        model.addAttribute("SearchUrl" , "/board/list");

        // 절대 이렇게 하면 안되고 Jpa에서 제공하는 변경감지나 Merge 기능을 따로 공부하자.
        boardService.write(boardTemp);

        return "Message";
    }
    
    
 

}
