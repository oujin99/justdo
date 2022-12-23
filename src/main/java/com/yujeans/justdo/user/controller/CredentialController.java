package com.yujeans.justdo.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yujeans.justdo.jwt.provider.JwtTokenProvider;
import com.yujeans.justdo.jwt.token.TokenInfo;
import com.yujeans.justdo.jwt.util.SecurityUtil;
import com.yujeans.justdo.user.dto.CredentialLoginRequestDto;
import com.yujeans.justdo.user.service.CredentialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CredentialController {
    private final CredentialService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public TokenInfo login(@RequestBody CredentialLoginRequestDto credentialLoginRequestDto) {
    	System.out.println("레스트컨트롤러 로그인 : " + credentialLoginRequestDto.getUsername());
    	System.out.println(passwordEncoder.encode(credentialLoginRequestDto.getPassword()));
    	System.out.println("레스트컨트롤러 패스워드 : " + credentialLoginRequestDto.getPassword());
        String username = credentialLoginRequestDto.getUsername();
        String password = credentialLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(username, password);
        return tokenInfo;
    } 
    
    @PostMapping("/test")
    public String test() {
    	System.out.println("SecurityUtil.getCurrentMemberId() : " + SecurityUtil.getCurrentUsername());
        return "success";
    }
    
 // Request Header 에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}