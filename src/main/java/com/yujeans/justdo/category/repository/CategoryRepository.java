package com.yujeans.justdo.category.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yujeans.justdo.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	 @Query(value = "SELECT DISTINCT c.secondCategory FROM Category c "
	          + "LEFT OUTER JOIN c.firstCategory fc "
	          + "LEFT OUTER JOIN c.secondCategory sc WHERE fc.name = :name")
	 public List<Tuple> findCategory(@Param("name")String name);
}
