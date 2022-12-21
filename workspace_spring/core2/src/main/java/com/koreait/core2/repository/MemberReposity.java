package com.koreait.core2.repository;

import java.util.List;

import com.koreait.core2.member.Member;

public interface MemberReposity {
	
	/*
	 *  ActionForward 와 유사하게
	 *  Repository 를 통해 DB 에서 수행할 업무를 
	 *  메인 Repository 에서 메소드(java Bean) 형태로 생성하고
	 *  
	 *  하위 Repository 에 상속시켜 Java Bean 형태로 
	 *  스프링 컨테이너 내부를 순환시키는 구조 
	 *  
	 */
	
	// 회원 저장
	Member save(Member member);
	
	// 회원 전체 찾기 
	List<Member> finaAll();	
	
}
