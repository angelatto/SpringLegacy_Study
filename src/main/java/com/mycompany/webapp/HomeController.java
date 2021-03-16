package com.mycompany.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	// 현재 클래스 이름과 똑같이 주기. 로그가 어디서 출력되는지 위치를 알려주는 역할 
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/")
	public String home() {
		// 메소드 이름이 로그 레벨이다. 
		logger.error("error 메세지"); 
		logger.warn("warn 메세지"); // error level 일때는 출력이 안됨 
		logger.info("info 메세지"); // warn level 일때는 출력이 안됨 
		logger.debug("debug 메세지"); // info level 일때는 출력이 안됨 
		
		return "home";
	}
	
}
