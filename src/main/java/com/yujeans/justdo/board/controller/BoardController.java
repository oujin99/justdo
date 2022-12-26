package com.yujeans.justdo.board.controller;

import java.time.LocalDate;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.yujeans.justdo.board.Board;
import com.yujeans.justdo.board.Reply;
import com.yujeans.justdo.board.dto.BoardFormDto;
import com.yujeans.justdo.board.dto.ReplyFormDto;
import com.yujeans.justdo.board.service.BoardService;
import com.yujeans.justdo.board.service.ReplyService;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.Credential;
import com.yujeans.justdo.user.service.CredentialService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	private final BoardService boardService;
	
	@Autowired
	private final ReplyService replyService;

	@Autowired
	private final CredentialService credentialService;
	
	// 글작성 페이지 이동
	@GetMapping("/board/boardWrite") 
    public String boardwriteForm(@ModelAttribute BoardFormDto boardFormDto){

        return "/board/board_write";
    }

	// 글작성 
    @PostMapping("/board/boardWriteSub")
    public String boardWritePro(@Validated @ModelAttribute BoardFormDto boardFormDto
			,BindingResult bindingResult, Model model, HttpServletRequest request){

    	if(bindingResult.hasErrors()) {
			System.out.println("========= bindingResult Error =========");
			System.out.println(bindingResult.toString());
			System.out.println(bindingResult.getAllErrors().get(0));
			return "/board/board_write";
		}
    	
    	Board board = new Board();
    	
		Account account  = credentialService.findUserInfo((String)request.getAttribute("id")); 
		
		board.setContent(boardFormDto.getContent());
		board.setTitle(boardFormDto.getTitle());
		board.setStartDate(LocalDate.now().toString());
		board.setAccount(account);
        boardService.write(board);
        
        model.addAttribute("boardList", boardService.boardList());
        
        return "redirect:/board/boardList";
    }
    
    // 상세보기 이동
    @GetMapping("/board/boardView/{id}")
    public String boardView(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response
    		,Model model, ReplyFormDto replyFormDto){
    	
    	System.out.println("상세보기 id : " + id);
    	System.out.println("보드 뷰 테스트");
    	boardService.boardView(id, request, response);
    	Optional<Board> findResult =  boardService.findById(id);
    	Board board = findResult.get();
        model.addAttribute("boardView", board);
        model.addAttribute("replyList", replyService.findByBoard(board));
        
        Credential credential = credentialService.findByAccountId(board.getAccount().getId());
    	String isSameId = "false";
    	if(credential.getUsername().equals(request.getAttribute("id"))) {
    		isSameId = "true";
    	}
    	
    	Optional<Credential> loginCredential = credentialService.findByUsername(request.getAttribute("id").toString());
    	
    	model.addAttribute("isSameId", isSameId);
    	model.addAttribute("credentialId", loginCredential.get().getId());
        
        return "/board/board_view";
    }
    
    // 게시글 삭제
    @GetMapping("/board/boardDelete/{id}")
    public String boardDelete(@PathVariable("id") Long id){
    	System.out.println("board_id : "+id);
        boardService.boardDelete(id);
        return "redirect:/board/boardList";
    }
    
    // 게시글 수정 이동
    @GetMapping("/board/boardEdit/{id}")
    public String boardEdit(@PathVariable("id") Long id, Model model){
        System.out.println("수정폼");
        Optional<Board> result = boardService.findById(id);
    	Board board = result.get();
    	model.addAttribute("board", board);
    	
    	
//        model.addAttribute("boardEdit",boardService.boardView(id, request, response));

        return "/board/board_edit";
    }
    
	// 게시글 수정
    @PostMapping("/board/boardUpdate/{id}")
    public String boardUpdate(@PathVariable("id") Long id, @ModelAttribute  BoardFormDto boardFormDto, Model model, HttpServletRequest request){
    	Optional<Board> result = boardService.findById(id);
//		request.setAttribute("id", "test144");
//		Account account  = credentialService.findByUsername((String)request.getAttribute("id"));

    	Board board = result.get();
		board.setId(id);
		board.setContent(boardFormDto.getContent());
		board.setTitle(boardFormDto.getTitle());
		board.setStartDate(LocalDate.now().toString());
//		board.setAccount(account);
        boardService.write(board);
        
        model.addAttribute("boardUpdate", boardService.boardList()) ;
        
        return "redirect:/board/boardView/{id}";
    }
    
    // 게시판 리스트 페이징
    @GetMapping("/board/boardList")                 
    public String paging(Model model, @PageableDefault(sort = "id", direction = Direction.DESC, size=7)
            Pageable pageable) {
    	
    	Page<Board> boardList = boardService.getBoardList(pageable);
    	
//    	System.out.println("board.account : "+boardList.getContent().get(0).getAccount());
    	
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
