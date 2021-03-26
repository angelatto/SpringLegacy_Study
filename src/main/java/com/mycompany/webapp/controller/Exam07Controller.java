package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.User;
import com.mycompany.webapp.service.UsersService;

@Controller
@RequestMapping("/exam07")
public class Exam07Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Exam07Controller.class);
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "exam07/joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder(); // 스프링 시큐리티에서 제공하는 객체 
		user.setUpassword(bpe.encode(user.getUpassword())); // 패스워드만 암호화해서 변경한다. 
		usersService.join(user);
		
		return "redirect:/exam07/loginForm";
	}
	
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "exam07/loginForm";
	}
	
	
	@PostMapping("/login")
	public String login(User user, HttpSession session) {
		String result = usersService.login(user);
		if(result.equals("success")) { // 로그인 성공 
			session.removeAttribute("loginError");
			session.setAttribute("loginUid", user.getUid());
			return "redirect:/home";
		}else {
			session.setAttribute("loginError", result);
			return "redirect:/exam07/loginForm"; // redirect 할 때는 model로 넘겨줄수 없으니까. 세션이용 
		}
		/*  모델에 저장된 것처럼 세션에 저장된 것도 jsp에서 EL 로 작성할 수 있다. */
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//session.invalidate();
		session.removeAttribute("loginUid");	
		return "redirect:/home";	
	}
	
			
}