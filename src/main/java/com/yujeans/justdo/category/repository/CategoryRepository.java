package com.yujeans.justdo.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yujeans.justdo.category.Category;
import com.yujeans.justdo.category.SecondCategory;
import com.yujeans.justdo.category.ThirdCategory;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	 @Query(value = "SELECT DISTINCT c.secondCategory FROM Category c "
	          + "LEFT OUTER JOIN c.firstCategory fc "
	          + "LEFT OUTER JOIN c.secondCategory sc WHERE fc.name = :name")
	 public List<SecondCategory> findSecondCategory(@Param("name")String name);
	 
	 @Query(value = "SELECT c.thirdCategory FROM Category c "
	          + "LEFT OUTER JOIN c.secondCategory sc "
	          + "LEFT OUTER JOIN c.thirdCategory tc WHERE sc.name = :name")
	 public List<ThirdCategory> findThirdCategory(@Param("name")String name);
	 
	 
	 @Query(value = "SELECT c.id FROM Category c "
	 		+ "LEFT OUTER JOIN c.firstCategory fc "
	 		+ "LEFT OUTER JOIN c.secondCategory sc "
	 		+ "LEFT OUTER JOIN c.thirdCategory tc WHERE tc.name = :selectedThird")
	 public Long findCategoryId(@Param("selectedThird")String selectedThird);
}


