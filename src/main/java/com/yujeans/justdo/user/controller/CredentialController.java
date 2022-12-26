package com.yujeans.justdo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yujeans.justdo.jwt.token.TokenInfo;
import com.yujeans.justdo.jwt.util.SecurityUtil;
import com.yujeans.justdo.user.dto.CredentialLoginRequestDto;
import com.yujeans.justdo.user.service.CredentialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CredentialController {
    @Autowired
	private final CredentialService credentialService;
    
    @PostMapping("/login")
    public TokenInfo login(@RequestBody CredentialLoginRequestDto credentialLoginRequestDto) {
//    	System.out.println("레스트컨트롤러 로그인 : " + credentialLoginRequestDto.getUsername());
//    	System.out.println("레스트컨트롤러 패스워드 : " + credentialLoginRequestDto.getPassword());
        String username = credentialLoginRequestDto.getUsername();
        String password = credentialLoginRequestDto.getPassword();
        TokenInfo tokenInfo = credentialService.login(username, password);
        return tokenInfo;
    } 
    
    @PostMapping("/abc")
    public String test() {
//    	System.out.println("SecurityUtil.getCurrentMemberId() : " + SecurityUtil.getCurrentUsername());
        return "success";
    }
    
}