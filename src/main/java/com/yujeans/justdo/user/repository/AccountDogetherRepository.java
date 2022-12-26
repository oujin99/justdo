package com.yujeans.justdo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.user.Account;

public interface AccountDogetherRepository extends JpaRepository<AccountDogether, Long>{

	@Query(value = "SELECT ad FROM AccountDogether ad WHERE ad.account = :account and ad.dogether = :dogether")
	public AccountDogether findByAccountAndDogether(@Param("account") Account account, @Param("dogether") Dogether dogether);
	
}
