package com.yujeans.justdo.user.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yujeans.justdo.user.Credential;
import com.yujeans.justdo.user.repository.CredentialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
 
    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }
 
    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Credential credential) {
//    	System.out.println("credential.getRoles().toArray() : " + credential.getRoles().toArray());
//    	System.out.println("credential.getRoles().toArray(new String[0]) : " + credential.getRoles().toArray(new String[0]).toString());
//    	System.out.println("credential.getRoles().toArray(new String[0]) : " + (credential.getRoles().toArray(new String[0]))[0].toString());
//    	System.out.println("1 : " +credential.getRoles().get(0).toString());
//    	System.out.println("2 : " +credential.getRoles().toArray().length);
//    	System.out.println("3 : " +credential.getId());
//    	System.out.println("4 : " +credential.getUsername());
//    	System.out.println("5-1 : " +credential.getAccount().getAddress());
//    	System.out.println("5-2 : " +credential.getAccount().getEmail());
//    	System.out.println("5-3 : " +credential.getAccount().getImage());
//    	System.out.println("5-4 : " +credential.getAccount().getName());
//    	System.out.println("5-5 : " +credential.getAccount().getPhone());
//    	System.out.println("5-5 : " +credential.getAccount().getId());
//    	System.out.println("6 : " +credential.getAuthorities());
//    	System.out.println("7-1 : " +credential.getLoginMethod());
//    	System.out.println("7-2 : " +credential.getLoginMethod().getType());
//    	System.out.println("7-3 : " +credential.getLoginMethod().getId());
    	System.out.println("credential.getPassword() : " + credential.getPassword());
        return User.builder()
                .username(credential.getUsername())
                .password(credential.getPassword())
                .roles(credential.getRoles().toArray(new String[0]))
                .build();
    }
}
