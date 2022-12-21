package com.koreait.mylogin.loginWeb.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberRepository memberRepository;

	// addMemberForm.html -> th:object , 취소 -> home.html로 이동 url 입력
	
	/*
	 * model.addAttribute("member", new Member())	
	 * 		-> @ModelAttribute("member")Member member
	 */
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member")Member member) {
//		model.addAttribute("member", new Member());
		return "members/addMemberForm";
	}
	
	@PostMapping("/add")
	public String save(@ModelAttribute Member member) {
		memberRepository.save(member);
		return "redirect:/";
	}
	
}


















