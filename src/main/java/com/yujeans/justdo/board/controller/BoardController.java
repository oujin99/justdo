package com.yujeans.justdo.board.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
		System.out.println("boardFormDto.getContent() : "+boardFormDto.getContent());
		System.out.println("boardFormDto.getTitle() : "+boardFormDto.getTitle());
		board.setContent(boardFormDto.getContent());
		board.setTitle(boardFormDto.getTitle());
		board.setStartDate(LocalDate.now().toString());
        boardService.write(board);
        
        model.addAttribute("boardList", boardService.boardList()) ;
        
        return "redirect:/board/boardList";
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
    
    
    // 게시판 리스트 페이징
    @GetMapping("/board/boardList")                 
    public String paging(Model model, @PageableDefault(sort = "id", direction = Direction.DESC, size=7)
            Pageable pageable) {
    	
    	Page<Board> boardList = boardService.getBoardList(pageable);
    	
        // 이전 다음 버튼
        model.addAttribute("first", pageable.first().getPageNumber());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("last", pageable.getPageSize()-1);
        
        // 이전 다음 페이지 유무
        model.addAttribute("hasPrev", boardList.hasPrevious()); 
        model.addAttribute("hasNext", boardList.hasNext()); 
        
        // 토탈페이지 확인
        model.addAttribute("total", boardList.getTotalPages()-1);
        
        // 페이징 번호 처리
        int nowPage = boardList.getPageable().getPageNumber()+1;
        int startPage = Math.max(1, nowPage -2);
        int endPage = Math.min(nowPage+2, boardList.getTotalPages());
        
        int pageSize = 5;
        int lastPage = boardList.getTotalPages();
        int prevOfLastPage = boardList.getTotalPages()-1;
        
        if(nowPage==1 || nowPage==2) {
        	if(lastPage<=pageSize) {
        		endPage = lastPage;
        	}else {
        		endPage = pageSize;
        	}
        }else if(nowPage==prevOfLastPage || nowPage==lastPage) {
        	if((lastPage-4)<=0) {
        		startPage = 1;
        	}else {
        		startPage = lastPage-4;
        	}
        }
        
        model.addAttribute("boardList", boardList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
//        System.out.println("nowPage : "+nowPage);
//        System.out.println("startPage : "+startPage);
//        System.out.println("endPage : "+endPage);
//        System.out.println("totalPage : "+boardList.getTotalPages());
        
        return "/board/board_list";
    }
    
    // 게시판 검색후 페이징
    @GetMapping("/board/boardSearchList")                 
    public String searchPaging(String keyword, Model model, @PageableDefault(sort = "id", direction = Direction.DESC, size=7)
    Pageable pageable)  {
    	
    	Page<Board> searchList = boardService.getSearchList(keyword, pageable);
    	
    	// 이전 다음 버튼
    	model.addAttribute("first", pageable.first().getPageNumber());
    	model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
    	model.addAttribute("next", pageable.next().getPageNumber());
    	model.addAttribute("last", pageable.getPageSize()-1);
    	
    	// 이전 다음 페이지 유무
    	model.addAttribute("hasPrev", searchList.hasPrevious()); 
    	model.addAttribute("hasNext", searchList.hasNext()); 
    	
    	// 토탈페이지 확인
    	model.addAttribute("total", searchList.getTotalPages()-1);
    	
    	// 페이징 번호 처리
        int nowPage = searchList.getPageable().getPageNumber()+1;
        int startPage = Math.max(1, nowPage -2);
        int endPage = Math.min(nowPage+2, searchList.getTotalPages());
        
        int pageSize = 5;
        int lastPage = searchList.getTotalPages();
        int prevOfLastPage = searchList.getTotalPages()-1;
        
        if(nowPage==1 || nowPage==2) {
        	if(lastPage<=pageSize) {
        		endPage = lastPage;
        	}else {
        		endPage = pageSize;
        	}
        }else if(nowPage==prevOfLastPage || nowPage==lastPage) {
        	if((lastPage-4)<=0) {
        		startPage = 1;
        	}else {
        		startPage = lastPage-4;
        	}
        }
        
		System.out.println("nowPage : "+nowPage);
		System.out.println("startPage : "+startPage);
		System.out.println("endPage : "+endPage);
		System.out.println("lastPage : "+lastPage);        
         
        model.addAttribute("searchList", searchList);
    	model.addAttribute("nowPage", nowPage);
    	model.addAttribute("startPage", startPage);
    	model.addAttribute("endPage", endPage);
    	
    	return "/board/board_search_list";
    }

  

}
