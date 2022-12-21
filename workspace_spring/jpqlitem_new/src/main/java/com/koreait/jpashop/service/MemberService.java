package com.koreait.jpashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

//	@Autowired
	private final MemberRepository memberRepository;
	
	// 회원가입
	@Transactional
	public Long join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}
	
}
















