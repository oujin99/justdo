package com.koreait.jpaitem_new2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.jpaitem_new2.domain.Member;
import com.koreait.jpaitem_new2.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//회원가입
	@Transactional // springFramework 트랜잭션 import
	public Long join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}
	
	
	
}
