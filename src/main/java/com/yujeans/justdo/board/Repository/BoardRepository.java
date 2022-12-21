package com.yujeans.justdo.board.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yujeans.justdo.board.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

	
}
