package com.yujeans.justdo.images.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yujeans.justdo.images.Images;



@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {


	@Query(value = "SELECT i FROM Images i "
			+ "WHERE i.id = :id")
	public Images getFileInfo(@Param("id")Long id);
}
