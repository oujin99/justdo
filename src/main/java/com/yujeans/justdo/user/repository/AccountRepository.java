package com.yujeans.justdo.user.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.user.Account;

import lombok.RequiredArgsConstructor;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
//	SELECT a.* FROM ACCOUNT a 
//	LEFT OUTER JOIN CREDENTIAL c 
//	ON a.ID = c.ACCOUNT_ID 
//	WHERE c.USERNAME = 'oujin4174@gamil.com'
	
//	@Query(value = "SELECT c.Account FROM Credential c "
//			+ "LEFT OUTER JOIN Account a WHERE c.username = :username")
//	public Account findUserInfo(@Param("username")String username);
//	
}
