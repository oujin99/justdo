package com.yujeans.justdo.board.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yujeans.justdo.board.Board;
import com.yujeans.justdo.board.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

	List<Reply> findByBoard(Board board);

}
