package com.koreait.mylogin.loginWeb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.koreait.mylogin.loginWeb.member.Member;
import com.koreait.mylogin.loginWeb.member.MemberRepository;
import com.koreait.mylogin.loginWeb.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	// localhost:9090 -> home.html

	private final MemberRepository memberRepository;
	
	//@GetMapping
	public String home() {
		return "home";
	}
	
	//@GetMapping
	public String homeV2(@CookieValue(name="memberId", required = false) 
		Long memberId, Model model) {
		
		// 로그인 사용자가 아니라면 홈 화면으로 이동
		if (memberId == null) {
			return "home";
		} 
		
		// db 조회 를 위해 Repository 생성 
		Member loginMember = memberRepository.findById(memberId);
		
		if(loginMember == null) {
			return "home";
		}
		
		// login Home : 로그인 성공시 화면
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	//@GetMapping
	public String homeV3(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false); // 로그인한 사용자가 아니라면
		
		// 로그인 사용자가 아니라면 홈 화면으로 이동
		if ( session == null) {
			return "home";
		} 
		
		// 로그인 사용자 정보 
		Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
		
		if(loginMember == null) {
			return "home";
		}
		
		// login Home : 로그인 성공시 화면
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	@GetMapping
	public String homeV4(@SessionAttribute(name=SessionConst.LOGIN_MEMBER , required = false) 
		Member loginMember, Model model) {
		
		// 로그인 사용자 정보 
		if(loginMember == null) {
			return "home";
		}
		
		// login Home : 로그인 성공시 화면
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
}
