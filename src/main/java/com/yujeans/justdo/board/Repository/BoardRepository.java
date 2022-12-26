package com.yujeans.justdo.board.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yujeans.justdo.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	public Page<Board> findByTitleContaining(String keyword, Pageable pageable);
	
	@Modifying
    @Query("update Board set views = views + 1 where id = :id")
    public int updateView(@Param(value = "id") Long id);
}
