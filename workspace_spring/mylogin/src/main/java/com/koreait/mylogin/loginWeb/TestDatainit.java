package com.koreait.mylogin.loginWeb;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.koreait.mylogin.loginWeb.item.Item;
import com.koreait.mylogin.loginWeb.item.ItemRepository;
import com.koreait.mylogin.loginWeb.member.Member;
import com.koreait.mylogin.loginWeb.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDatainit {

	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	 // 테스트 데이터 추가
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("testA", 10000, 10));
		itemRepository.save(new Item("testB", 20000, 20));
		
		Member member = new Member();
		member.setLoginId("test");
		member.setPassword("test!");
		member.setName("테스터");
		
		memberRepository.save(member);
	}
}
