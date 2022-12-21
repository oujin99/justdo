package com.koreait.mylogin.loginWeb.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	public List<Member> findAll(){
		return new ArrayList<Member>(store.values());
	}

	public Member findByLoginId(String loginId) {
		List<Member> all =findAll();
		for(Member m : all) {
			if( m.getLoginId().equals(loginId) ) {
				return m;
			}
		}
		
		return null;
	}
	
}

















