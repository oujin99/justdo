package com.yujeans.justdo.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yujeans.justdo.api.kakao.KakaoService;
import com.yujeans.justdo.jwt.filter.JwtAuthenticationFilter;
import com.yujeans.justdo.jwt.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
 
	@Autowired
    private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
    private final KakaoService kakaoService;
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
		        .httpBasic().disable()
		        .csrf().disable()
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and()
		        .authorizeRequests()
		        .antMatchers("/api/login").permitAll()
		        .antMatchers("/api/test").hasRole("USER")
		        .antMatchers("/user/mypage").hasRole("USER")
		        .antMatchers("/dogether/registForm").hasRole("USER")
		        .and()
		        
		        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, kakaoService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
      return web -> web.ignoring()
    		  .antMatchers("/css/**", "/favicon/**", "/images/**", "/img/**", "/javascripts/**", "/js/**", "/manifest.*", "/error", "/**/*.html", "/*.ico", "/json/**", "/*.json");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}