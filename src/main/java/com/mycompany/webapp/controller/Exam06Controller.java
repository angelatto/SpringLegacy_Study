package com.mycompany.webapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.User;

@Controller
@RequestMapping("/exam06")
public class Exam06Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Exam06Controller.class);
	
	@RequestMapping("/content")
	public String content(Model model) {
	
		return "exam06/content";
	}
	
	@GetMapping("createCookie")
	public String createCookie(HttpServletResponse response) {
		String uid = "spring";
		Cookie cookie = new Cookie("uid", uid);
		//cookie.setDomain("192.168.3.166");
		//cookie.setPath("/webapp/exam06");
		//cookie.setHttpOnly(true);
		cookie.setMaxAge(-5);
		
		response.addCookie(cookie);  // 쿠키에 대한 정보가 응답 헤더에 담겨서 클라이언트로 전달된다. 
		return "redirect:/exam06/content";
	}
	
//	@GetMapping("readCookie")
//	public String readCookie(HttpServletRequest request) {
//		Cookie[] cookieArr = request.getCookies();
//		for(Cookie cookie : cookieArr) {
//			logger.info(cookie.getName() + ": " + cookie.getValue());
//			if(cookie.getName().equals("uid")) {
//				logger.info(cookie.getValue() + " 활용하기");
//			}
//		}
//		return "redirect:/exam06/content";
//	}
	
	@GetMapping("readCookie")
	public String readCookie(@CookieValue String uid) {
		logger.info(uid + " 활용하기");
		return "redirect: /exam06/content";
	}
	
	
	@GetMapping("deleteCookie")
	public String deleteCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("uid", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return "redirect:/exam06/content";
	}
	
	@GetMapping("/sessionSaveObject")
	public String sessionSaveObject(HttpSession session) {
		User user = new User();
		user.setUid("spring");
		session.setAttribute("user", user);		
		return "redirect:/exam06/content";
	}
	

	@GetMapping("/sessionReadObject")
	public String sessionReadObject(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(user != null) {
			logger.info(user.getUid());
		}
		return "redirect:/exam06/content";
	}
	

	@GetMapping("/sessionRemoveObject")
	public String sessionRemoveObject(HttpSession session) {
		// 개별 객체를 삭제할 때 
		session.removeAttribute("user");
		
		// 세션에 저장된 모든 객체를 삭제할 경우 
		session.invalidate();
		return "redirect:/exam06/content";
	}
	
			
}