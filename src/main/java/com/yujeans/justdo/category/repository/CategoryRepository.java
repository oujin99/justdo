package com.yujeans.justdo.category.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yujeans.justdo.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
//	 @Query(value = "SELECT c.firstCategory, c.secondCategory, c.thirdCategory FROM Category c "
//	 		+ "LEFT OUTER JOIN c.firstCategory "
//	 		+ "LEFT OUTER JOIN c.secondCategory "
//	 		+ "LEFT OUTER JOIN c.thirdCategory")
	 
//	 @Query(value = "SELECT DISTINCT sc.name FROM Category c "
//	 		+ "   LEFT OUTER JOIN c.firstCategory fc "
//	 		+ "   LEFT OUTER JOIN c.secondCategory sc "
//	 		+ "WHERE fc.name = :cateData")
//	 public List<Tuple> findCategory(@Param("cateData") String cateData);
//	
//	 @Query(value = "SELECT tc.name  FROM Category c "
//	 		+ "   LEFT OUTER JOIN c.firstCategory fc  "
//	 		+ "   LEFT OUTER JOIN c.secondCategory sc "
//	 		+ "   LEFT OUTER JOIN c.thirdCategory tc "
//	 		+ "WHERE sc.name  = :sec_cateData")
//	 public List<Tuple> findSecondCategory(@Param("sec_cateData") String sec_cateData);
//	 
}
