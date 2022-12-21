package com.koreait.core2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.core2.member.Member;
import com.koreait.core2.repository.MemberReposity;
import com.koreait.core2.repository.MemoryMemberRepository;

@Service
@Transactional
public class MemberService {

	// MemberRepository memberReposity = new MemoryMemberRepository();
	private final MemberReposity memberRepository;
	
	/*
	 *  마찬가지로, @Autowired 어노테이션을 통해
	 *  @Service 어노테이션 + @Repository 어노테이션을 연결한다
	 */
	@Autowired
	public MemberService( MemberReposity memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원 가입
	public int join(Member member) {
		memberRepository.save(member);
		return member.getId();
		
	}
	// 전체회원 조회 
	public List<Member> findMembers(){
		return memberRepository.finaAll();
	}
}
