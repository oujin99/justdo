package com.koreait.jpaitem_new2.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.jpaitem_new2.domain.Address;
import com.koreait.jpaitem_new2.domain.Member;
import com.koreait.jpaitem_new2.dto.MemberForm;
import com.koreait.jpaitem_new2.repository.MemberRepository;
import com.koreait.jpaitem_new2.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("/member/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new Member());
		return "members/createMemberForm";
	}

	// BindingResult = validation 다음에 binding이 있으면,
	// errors 를 Binding에 담아준다
	
	//회원가입 
	@PostMapping("/members/new")
	public String create(@Valid MemberForm form, BindingResult result) {

		// error 발생 시
		if (result.hasErrors()) {
			return "members/createMemberForm";
		}

		// 정상 실행시 , service 실행
		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);
		
		memberService.join(member);
		return "redirect:/";
	}
	

}
