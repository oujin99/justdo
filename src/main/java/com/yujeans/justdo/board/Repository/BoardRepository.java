package com.yujeans.justdo.board.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yujeans.justdo.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	Page<Board> findByTitleContaining(String keyword, Pageable pageable);
}
