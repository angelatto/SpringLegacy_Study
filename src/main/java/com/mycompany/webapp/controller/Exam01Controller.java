package com.mycompany.webapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Board;

@Controller
public class Exam01Controller {
	
	// 현재 클래스 이름과 똑같이 주기. 로그가 어디서 출력되는지 위치를 알려주는 역할 
	private static final Logger logger = LoggerFactory.getLogger(Exam01Controller.class);
	
	@RequestMapping(value = "/home")
	public String home() {
		// 메소드 이름이 로그 레벨이다. 
		logger.error("error 메세지"); 
		logger.warn("warn 메세지"); // error level 일때는 출력이 안됨 
		logger.info("info 메세지"); // warn level 일때는 출력이 안됨 
		logger.debug("debug 메세지"); // info level 일때는 출력이 안됨 
		
		return "home";
	}

	@RequestMapping(value = "/exam01/boardlist")
	public String getBoardList(HttpServletRequest request, HttpServletResponse response) {
		// 요청 내용을 확인
		System.out.println("클라이언트 IP: " + request.getRemoteHost()); // 누가 나한테 요청했는지 
		
		// data 생성 및 JSP 전달 
		List<Board> list = new ArrayList<>();
		for(int i = 1; i < 10; i++) {
			Board board = new Board();
			board.setBno(i);
			board.setBtitle("제목" + i);
			board.setBcontent("내용" + i);
			board.setBwriter("spring");
			list.add(board);
		}
		
		request.setAttribute("list", list);

		return "exam01/boardlist";
	}
	
}
