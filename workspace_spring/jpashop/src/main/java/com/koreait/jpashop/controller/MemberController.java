package com.koreait.jpashop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.jpashop.domain.Address;
import com.koreait.jpashop.domain.Member;
import com.koreait.jpashop.dto.MemberForm;
import com.koreait.jpashop.service.MemberService;

import lombok.RequiredArgsConstructor;
import oracle.net.aso.m;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	@GetMapping("/members/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}
	
	// BindingResult : validation 다음에 Binding이 있으면 , 
	// error를 Binding에 담아준다.
	@PostMapping("/members/new")
	public String create(@Valid MemberForm form, BindingResult result) throws IllegalAccessException {
		
		// error발생시
		if( result.hasErrors() ) {
			return "members/createMemberForm";
		}
		
		// 정상로직, service
		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);
		
		memberService.join(member);
		return "redirect:/";
	}
	
	// request : members
	// response : members/memberList
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList_";
	}
	
	
	
	
}








