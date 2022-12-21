package com.koreait.Springtest3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.Springtest3.member.Member;


@Controller
@RequestMapping("/members")
public class MemberController {

	@GetMapping("/memberList")
	public String list(Model model) {
		Member memberA = new Member(1,"memberA","01012345678","남자");
		Member memberB = new Member(2,"memberB","01034567890","여자");
		Member memberC = new Member(3,"memberC","01088467210","남자");
		Member memberD = new Member(4,"memberD","01062348971","여자");
		Member memberE = new Member(5,"memberE","01084623791","남자");
		Member memberF = new Member(6,"memberF","01074236983","여자");
		
		List<Member> memberList = new ArrayList<Member>();
		memberList.add(memberA);
		memberList.add(memberB);
		memberList.add(memberC);
		memberList.add(memberD);
		memberList.add(memberE);
		memberList.add(memberF);
		
		model.addAttribute("member", memberList);
		
		return "members/memberList";
	}
}
