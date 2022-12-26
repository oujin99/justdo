package com.yujeans.justdo.board.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yujeans.justdo.board.Board;
import com.yujeans.justdo.board.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

	@Autowired
	private final BoardRepository boardRepository;
	
	@Autowired
	private final static String VIEWCOOKIENAME = "alreadyViewCookie";
	
	// 게시글 작성 / 수정
	@Transactional
	public void write(Board board) {
		boardRepository.save(board);
	}

	// 게시글 리스트 
	public List<Board> boardList() {
		
		return boardRepository.findAll();
	}
	

	
	 // 게시글 조회수
	public int boardView(Long id, HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		boolean checkCookie = false;
		int result = 0;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// 이미 조회를 한 경우 체크
				if (cookie.getName().equals(VIEWCOOKIENAME + id))
					checkCookie = true;

			}
			if (!checkCookie) {
				Cookie newCookie = createCookieForForNotOverlap(id);
				response.addCookie(newCookie);
				result = boardRepository.updateView(id);
			}
		} else {
			Cookie newCookie = createCookieForForNotOverlap(id);
			response.addCookie(newCookie);
			result = boardRepository.updateView(id);
		}

		return result;
	}
	/*
	 * 조회수 중복 방지를 위한 쿠키 생성 메소드
	 * @param cookie
	 * @return
	 * */
	 private Cookie createCookieForForNotOverlap(Long id) {
	     Cookie cookie = new Cookie(VIEWCOOKIENAME+id, String.valueOf(id));
	     cookie.setComment("조회수 중복 증가 방지 쿠키");	// 쿠키 용도 설명 기재
	     cookie.setMaxAge(getRemainSecondForTommorow()); 	// 하루를 준다.
	     cookie.setHttpOnly(true);				// 서버에서만 조작 가능
	     return cookie;
	 }
	
	 // 다음 날 정각까지 남은 시간(초)
	 private int getRemainSecondForTommorow() {
	     LocalDateTime now = LocalDateTime.now();
	     LocalDateTime tommorow = LocalDateTime.now().plusDays(1L).truncatedTo(ChronoUnit.DAYS);
	     return (int) now.until(tommorow, ChronoUnit.SECONDS);
	 }
    	
 
    
    // 게시글 삭제
    @Transactional
    public void boardDelete(Long id){
        
    	boardRepository.deleteById(id);
    }

    // 페이징 처리
    @Transactional
	public Page<Board> getBoardList(Pageable pageable) {
    	Page<Board> boardList = boardRepository.findAll(pageable);
		return boardList;
	}
    
    // 페이징 검색
    @Transactional
    public Page<Board> getSearchList(String keyword, Pageable pageable) {
        Page<Board> searchList = boardRepository.findByTitleContaining(keyword, pageable);
        return searchList;
    }
    
    // 상세보기 / 수정 페이징 id값 이동
    public Optional<Board> findById(Long id) {
    	
    	return boardRepository.findById(id);
    }
    
    
}
