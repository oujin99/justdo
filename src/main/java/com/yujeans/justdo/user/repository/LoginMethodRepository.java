package com.yujeans.justdo.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yujeans.justdo.user.LoginMethod;

public interface LoginMethodRepository extends JpaRepository<LoginMethod, Long> {
	LoginMethod findByType(String type);
}
