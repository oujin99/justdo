package com.yujeans.justdo.user.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.yujeans.justdo.user.Account;

public interface EditProfileRepository_backup extends JpaRepository<Account, Long> {
	
	// Q. public 을 사용할 순 없는가 ? 
//	Optional<Account> findById(Long id);
	
	// select * from Account a where username = :username;
	//  Q. 이 값이 테이블 전체를 가져오는가? 
//	List<Account> findByAllName(String username);
	

}
