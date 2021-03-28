package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.User;

@Controller
@RequestMapping("/exam09")
public class Exam09Controller {
	
	private static final Logger logger
		= LoggerFactory.getLogger(Exam09Controller.class);
	
	@GetMapping("/joinForm")
	public String validate1() {
		return "exam09/joinForm";
	}

	@PostMapping("/join")
	public String join(User user) {
		return "redirect:/home";
	}
	
	
	

}
