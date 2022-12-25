package com.yujeans.justdo.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yujeans.justdo.board.Reply;
import com.yujeans.justdo.board.Repository.ReplyRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

	@Autowired
	private final ReplyRepository replyRepository;
	
	// 댓글 작성 / 수정
	@Transactional
	public void write(Reply reply) {
		replyRepository.save(reply);
	}
	
	// 댓글 상세보기
    public Reply replyView(Long id){
        
    	return replyRepository.findById(id).get();
    }
    
    // 댓글 리스트 
 	public List<Reply> replyList() {
 		
 		return replyRepository.findAll();
 	}
	
	
}
