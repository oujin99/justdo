package com.yujeans.justdo.board.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class BoardController {

	@Autowired
	private final BoardService boardService;

	// 글작성 페이지 이동
	@GetMapping("/board/boardWrite") 
    public String boardwriteForm(){

        return "/board/board_write";
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
        
        return "redirect:/board/boardList";
    }
    
    // 리스트 페이지 이동
    @GetMapping("/board/boardList")
    public String boardList(Model model){
        System.out.println(boardService.boardList());
        model.addAttribute("boardList",boardService.boardList());
        
        return "/board/board_list";
    }
    
    // 상세보기 이동
    @GetMapping("/board/boardView")
    public String boardView(Model model , Long id){
    	
        model.addAttribute("boardView",boardService.boardView(id));
        return "/board/board_view";
    }
    
    // 게시글 삭제
    @DeleteMapping("/board/boardDelete/{id}")
    public String boardDelete(@PathVariable("id") Long id){
    	System.out.println("board_id : "+id);
        boardService.boardDelete(id);
        return "redirect:/board/boardList";
    }
    
    // 게시글 수정 이동
    @GetMapping("/board/boardEdit/{id}")
    public String boardEdit(@PathVariable("id") Long id , Model model){
        System.out.println("수정폼");
        model.addAttribute("boardEdit",boardService.boardView(id));

        return "/board/board_edit";
    }
    
	// 게시글 수정
    @PostMapping("/board/boardUpdate/{id}")
    public String boardUpdate(@PathVariable("id") Long id, @ModelAttribute  BoardFormDto boardFormDto, Model model){
		Board board = new Board();
		board.setId(id);
		board.setContent(boardFormDto.getContent());
		board.setTitle(boardFormDto.getTitle());
		board.setStartDate(LocalDate.now().toString());
        boardService.write(board);
        
        model.addAttribute("boardUpdate", boardService.boardList()) ;
        
        return "redirect:/board/boardEdit/{id}";
    }

    



}
