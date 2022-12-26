package com.yujeans.justdo.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yujeans.justdo.file.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
