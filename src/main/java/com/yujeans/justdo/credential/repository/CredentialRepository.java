package com.yujeans.justdo.credential.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long>{
	
	@Query(value = "SELECT c.account FROM Credential c "
			+ "WHERE c.username = :username")
	public Account findUserInfo(@Param("username")String username);
	

}
