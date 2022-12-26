package com.yujeans.justdo.jwt.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.yujeans.justdo.api.kakao.KakaoService;
import com.yujeans.justdo.jwt.provider.JwtTokenProvider;
import com.yujeans.justdo.jwt.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private final KakaoService kakaoService;
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//    	System.out.println("필터 시작----------------");
//    	System.out.println("requestUrI : " +((HttpServletRequest) request).getRequestURI()); 
    	
    	validUserAuthentication((HttpServletRequest)request);
    	
//    	System.out.println("필터 끝--------------");
        chain.doFilter(request, response);
    }
    
    public void validUserAuthentication(HttpServletRequest request) {
    	// 1. Request Header 에서 JWT 토큰 추출
    	String token = resolveToken(request);
//        System.out.println("token : " + token);
       
        String kakaoResolveToken = ""; 
        if(!StringUtils.hasText(token)) {
        	kakaoResolveToken = kakaoResolveToken(request);
        }
//        System.out.println("카카오 어트리뷰트 아이디 ");
//        System.out.println(kakaoResolveToken);
//        boolean t1 = token != null && jwtTokenProvider.validateToken(token);
//        boolean t2 = kakaoResolveToken != null;
//        System.out.println("T1 : " + t1);
//        System.out.println("T2 : " + t2);
        
        // 2. validateToken 으로 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
//        	System.out.println("필터 IF문 탔어!!!");
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println("USER AUTHENTICATION : " + SecurityUtil.getCurrentUsername());
        } else if(kakaoResolveToken != null) {
//        	System.out.println("USER 필터 탔어 !!!!!!");
//        	System.out.println("카카오 토큰 출력 테스트");
//            System.out.println(kakaoResolveToken);
        	HashMap<String, Object> userInfoMap = kakaoService.getUserInfo(kakaoResolveToken);
//        	System.out.println("카카오 유저 인포 맵에서 가져온 아이디");
//        	System.out.println(userInfoMap.get("id"));
            Collection<? extends GrantedAuthority> authorities =
            		Arrays.stream("ROLE_USER,".split(","))
            			  .map(SimpleGrantedAuthority::new)
            			  .collect(Collectors.toList());
        	Authentication authentication = new UsernamePasswordAuthenticationToken(userInfoMap.get("id"), "", authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println("USER KAKAO : " + SecurityUtil.getCurrentUsername());
        }
    }
    
 // Request Header 에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            return bearerToken.substring(7);
//        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
        	for(Cookie cookie : cookies) {
        		if(cookie.getName().equals("basic_token")) {
        			String token = cookie.getValue();
        			if (StringUtils.hasText(token)) {
        	            return token;
        	        }
        		}
        	}
        }
    	
        return null;
    }
    
    private String kakaoResolveToken(HttpServletRequest request) {
    	Cookie[] cookies = request.getCookies();
    	if(cookies!=null) {
    		for(Cookie cookie : cookies) {
    			if(cookie.getName().equals("access_token")) {
    				String token = cookie.getValue();
    				if (StringUtils.hasText(token)) {
    					return token;
    				}
    			}
    		}
    	}
    	
    	return null;
    }

    
    
}
