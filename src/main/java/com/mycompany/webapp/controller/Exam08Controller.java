package com.mycompany.webapp.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.BoardsService;
import com.mycompany.webapp.service.UsersService;

@Controller
@RequestMapping("/exam08")
public class Exam08Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Exam08Controller.class);
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BoardsService boardsService;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "exam08/loginForm";
	}
	
	/*  권한이 유저이상이어야 요청을 정상적으로 수행할 수 있다. */
	@GetMapping("/user/boardlist")
	public String boardlist(String pageNo, HttpSession session, Model model) {

		int intPageNo = 1; // 디폴트 값 
		if(pageNo == null) {  // 파라미터로 넘어온 값이 없으면 세션에서 찾아보기 
			// 세션에서 Pager를 찾고, 있으면 pageNo를 설정 
			Pager pager = (Pager) session.getAttribute("pager");
			if(pager != null) {
				intPageNo = pager.getPageNo();
			}
		}else { // 파라미터로 넘어온 값이 있으면 
			intPageNo = Integer.parseInt(pageNo);
		}
		
		int totalRows = boardsService.getTotalRows();
		Pager pager = new Pager(10, 5, totalRows, intPageNo);
		session.setAttribute("pager", pager);

		List<Board> list = boardsService.getBoardList(pager);
		model.addAttribute("role", "USER");
		model.addAttribute("list", list); // jsp에 list 넘겨주기 
		model.addAttribute("pager", pager);
		
		return "exam08/boardlist";
	}
	
	/*  권한이 admin이어야 요청을 정상적으로 수행할 수 있다. */
	@GetMapping("/admin/boardlist")
	public String boardlist2(String pageNo, HttpSession session, Model model) {

		int intPageNo = 1; // 디폴트 값 
		if(pageNo == null) {  // 파라미터로 넘어온 값이 없으면 세션에서 찾아보기 
			// 세션에서 Pager를 찾고, 있으면 pageNo를 설정 
			Pager pager = (Pager) session.getAttribute("pager");
			if(pager != null) {
				intPageNo = pager.getPageNo();
			}
		}else { // 파라미터로 넘어온 값이 있으면 
			intPageNo = Integer.parseInt(pageNo);
		}
		
		int totalRows = boardsService.getTotalRows();
		Pager pager = new Pager(10, 5, totalRows, intPageNo);
		session.setAttribute("pager", pager);

		List<Board> list = boardsService.getBoardList(pager);
		model.addAttribute("role", "ADMIN");
		model.addAttribute("list", list); // jsp에 list 넘겨주기 
		model.addAttribute("pager", pager);
		
		return "exam08/boardlist";
	}

	@GetMapping("/user/createForm")
	public String createForm() {
		return "exam08/createFormWithAttach";
	}
	
	@PostMapping("/user/createWithAttach")
	public String createWithAttach(Board board, Authentication auth) {
		logger.info(auth.getName());
		
		MultipartFile battach = board.getBattach()[0];

		if(!battach.isEmpty()) { // 첨부가 있을 경우 
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			String saveName = new Date().getTime() + "-" + battach.getOriginalFilename();
			board.setBattachsname(saveName);
			
			File file = new File("/Users/homecj/dev/workspace/sts/Douzone/uploadfiles/" + saveName);
			try {
				battach.transferTo(file); // 여기서 이미지 파일 지정 경로에 저장 ... 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		board.setBwriter(auth.getName());
		boardsService.saveBoard(board);  // 디비에 저장을 한거고 .. 
		
		return "redirect:boardlist";
	}
	
}