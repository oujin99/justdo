package com.yujeans.justdo.user.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yujeans.justdo.jwt.provider.JwtTokenProvider;
import com.yujeans.justdo.jwt.token.TokenInfo;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.Credential;
import com.yujeans.justdo.user.LoginMethod;
import com.yujeans.justdo.user.repository.CredentialRepository;
import com.yujeans.justdo.user.repository.LoginMethodRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CredentialService {
	@Autowired
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired
    private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private final CredentialRepository credentialRepository;
	
	@Autowired
	private final LoginMethodRepository loginMethodRepository;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
 
    @Transactional
    public TokenInfo login(String username, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
 
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        // 로그인 정보
        Account account = credentialRepository.findAccountByUsername(username);
        
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication, account);
        return tokenInfo;
    }

    @Transactional
	public void save(Credential credential) {
    	String password = credential.getPassword();
    	password = passwordEncoder.encode(password);
    	credential.setPassword(password);
    	
    	credentialRepository.save(credential);
	}
    
    public Optional<Credential> findByUsername(String username) {
    	return credentialRepository.findByUsername(username);
    }
    
    public LoginMethod findLoginMethodByType(String type) {
		return loginMethodRepository.findByType(type);
    }
    
    public Account findUserInfo(String username) {
		return credentialRepository.findAccountByUsername(username);
	}
    
    public Credential findByAccountId(Long id) {
    	return credentialRepository.findByAccountId(id);
    }
}
