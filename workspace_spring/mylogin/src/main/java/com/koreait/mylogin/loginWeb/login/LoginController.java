package com.koreait.mylogin.loginWeb.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.mylogin.loginWeb.member.Member;
import com.koreait.mylogin.loginWeb.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm) {
		return "login/loginForm";
	}
	
	//@PostMapping("/login")
	public String login(@ModelAttribute LoginForm form
			, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		} 
		
		/*
		 *  - addAttribute 		: url 뒤에 붙는다.
		 *  - addFlashAttribute : url뒤에 붙지 않는다.
		 */
		// 로그인 성공
		Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
		response.addCookie(idCookie);
		
		redirectAttributes.addFlashAttribute("msg", "로그인 성공");
		return "redirect:/";
	}
	
	//@PostMapping("/login")
	public String login2(@ModelAttribute LoginForm form
			, Model model, RedirectAttributes redirectAttributes, HttpServletRequest req ) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		} 
		
		// 로그인 성공
		HttpSession session = req.getSession();
		
		// 세션에 로그인 회원 종보 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		redirectAttributes.addFlashAttribute("msg", "로그인 성공");
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login3(@ModelAttribute LoginForm form
			, Model model, RedirectAttributes redirectAttributes, HttpServletRequest req
			,@RequestParam(defaultValue = "/")String redirectURL ) {
		
		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인실패");
			return "login/loginForm";
		} 
		
		// 로그인 성공
		HttpSession session = req.getSession();
		
		// 세션에 로그인 회원 종보 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		redirectAttributes.addFlashAttribute("msg", "로그인 성공");
		return "redirect:" + redirectURL;
	}
	
	//@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		// 로그아웃시 이전화면 이동 + 쿠키 값 리셋 
		
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logout2(HttpServletRequest req) {
		// 로그아웃시 세션 삭제하고 + 홈 화면 이동
		
		/*
		 *  req.getSession(true)
		 *  	세션이 있으면 기존 세션을 반환
		 *  	단, 세션이 없으면 새로운 세션을 생성해서 반환한다 
		 *  
		 *  req.getSession(false)
		 *  	세션이 있으면 기존 세션을 반환
		 *  	세션이 없으면 새로운 세션을 생성하지 않고 null 로 반환 
		 */
		HttpSession session = req.getSession(false);
		
		if( session != null) {
			session.invalidate();
		}
		
		
		return "redirect:/";
	}
}







