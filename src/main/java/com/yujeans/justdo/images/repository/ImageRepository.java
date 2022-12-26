package com.yujeans.justdo.images.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yujeans.justdo.images.Images;



@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {

}
