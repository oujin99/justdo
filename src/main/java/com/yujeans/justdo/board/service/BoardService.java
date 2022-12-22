package com.yujeans.justdo.board.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.yujeans.justdo.board.Board;
import com.yujeans.justdo.board.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

	@Autowired
	private final BoardRepository boardRepository;

	// 게시글 작성 / 수정
	@Transactional
	public void write(Board board) {
		boardRepository.save(board);
	}

	// 게시글 리스트 
	public List<Board> boardList() {
		
		return boardRepository.findAll();
	}
	
	 // 게시글 상세보기
    public Board boardView(Long id){
        
    	return boardRepository.findById(id).get();
    }
    
    // 게시글 삭제
    @Transactional
    public void boardDelete(Long id){
        
    	boardRepository.deleteById(id);
    }
    
 
}
