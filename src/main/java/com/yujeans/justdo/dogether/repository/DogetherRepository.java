package com.yujeans.justdo.dogether.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.user.Account;


@Repository
public interface DogetherRepository extends JpaRepository<Dogether, Long>{
	
	// dogether 등록 폼에 넣을 카테고리
	@Query(value = "SELECT fc FROM FirstCategory fc")
	public List<FirstCategory> selectFirstCategory();
	
	// 두게더 상세보기로 바로 넘어가기 위해 redirect로 dogether_id 넘기기
	@Query(value = "SELECT Dogether_seq.CURRVAL FROM DUAL", nativeQuery = true)
	Long selectDogetherId();
	
	@Query(value = "SELECT d FROM Dogether d "
			+ "WHERE d.id = :id")
	public Dogether findDogether(@Param("id")Long id);
	
	

}
