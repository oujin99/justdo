package com.yujeans.justdo.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Optional<Credential> findByUsername(String username);
    
    @Query("SELECT c.account FROM Credential c "
    		+ "WHERE c.username = :username")
    public Account findAccountByUsername(@Param(value = "username") String username);
    
    @Query("SELECT c FROM Credential c WHERE c.account.id = :id")
    public Credential findByAccountId(@Param(value = "id") Long id);
    
}
