package com.mycompany.webapp.controller;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam02")
public class Exam02Controller {
	private static final Logger logger 
		= LoggerFactory.getLogger(Exam02Controller.class);
	
	@RequestMapping("/method1form")
	public String method1form(HttpServletRequest request, HttpServletResponse response) {
		logger.info("실행");
		return "exam02/method1form";
	}
	
	@RequestMapping("/method1")
	public String method1(HttpServletRequest request, HttpServletResponse response) {
		logger.info("실행");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//		}
		String name = request.getParameter("name");
		logger.info(name);
		return "exam02/method1form";
	}
	
	@RequestMapping("/method2")
	public String method2(HttpServletRequest request, HttpServletResponse response) {
		logger.info("실행");

		return "redirect:/exam02/method1form";
	}
	

}
