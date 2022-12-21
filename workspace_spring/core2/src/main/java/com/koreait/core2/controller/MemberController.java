package com.koreait.core2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.core2.member.Member;
import com.koreait.core2.member.MemberFormDTO;
import com.koreait.core2.service.MemberService;

@Controller
public class MemberController {

	/*
	 * Controller 가 Service를 의존한다 라고 표현 Service는 여러 Controller에서 가져다 쓸 수 있기 때문에
	 * Spring Container 에 등록을 해야한다
	 */

//	MemberService mService = new MemberService();

	/*
	 * Spring 스럽게 작업하기 Service는 Spring Container 에 하나만 생성 및 등록 해서 같이 공유해서 쓸 수 있다.
	 */

	/*
	 * 필드 주업 ( Field Injection) - final 키워드를 사용할 수 없어, 순환참조가 발생할 수 있다. 권장하지 않는다
	 * // @Autowired private final memberService memberService;
	 */

	/*
	 * 수정자 주입 ( Setter Injection ) - public 으로 노출이 되기 때문에 다른곳 에서도 주입이 가능하다.
	 * 
	 * // @Autuwired public void setMember ( MemberService memberService){
	 * this.memberService = memberService ; }
	 */

	private final MemberService memberService;

	/*
	 *  클래스의 의존관계를 @Autowired 어노테이션 한번의 선언만으로
	 *  스프링 컨테이너가 자동으로 연결해 주는 것. 
	 *  Java 내부 메소드 실행을 담당하는 @Service 어노테이션을 연결함으로 써
	 *  
	 *  사용자 요청에 알맞게 @Controller 어노테이션의 업무를 실행 
	 *  
	 *  쉽게말해, @Controller 어노테이션과 @Service 어노테이션을 잇겟다는 의미
	 */
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping(value = "/members/new")
	public String createFrom() {
		return "members/createMemberForm";
	}

	@PostMapping(value = "/members/new")
	public String create(MemberFormDTO form) {
    	/* 사용자가 이름을 입력하면 
	        * 해당 이름을 MemberFormDTO 값에 저장하고 
	        *  @ Serivce의 join(member)를 실행하여 java 메소드 내에 저장 
	        * 저장된 데이터가 @Repository 를 통해 DB에 저장 
	    */ 
		Member member = new Member();
		member.setName(form.getName());

		memberService.join(member);

		// 홈 화면으로 되돌아 가기
		return "redirect:/";
		// return "forward:/";
	}

	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}

}
