package com.yujeans.justdo.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yujeans.justdo.user.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
}
