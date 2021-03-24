package com.mycompany.webapp.controller;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.BoardsService;

@Controller
@RequestMapping("/exam04")
public class Exam04Controller {

	@Autowired
	private BoardsService boardsService;
	
	@Autowired
	private DataSource dataSource;
	// BasicDataSource는 javax.sql.DataSource를 구현한 객체이기 때문에 주입할 수 있다. 
	
	@RequestMapping("/content")
	public String content(Model model) {
		Connection conn = null;
		try {
			// 커넥션 풀에서 커넥션 객체를 대여해 오기 
			conn = dataSource.getConnection();
			model.addAttribute("connStatus", "성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("connStatus", "실패");
		} finally {
			try{
				// 커넥션 풀로 커넥션 객체를 반납하기 
				conn.close();
			}catch(Exception e) {}
		}
		
		return "exam04/content";
	}
	
	// rest api는 경로를 지정할 때 명사로 지어야 한다. 
//	@GetMapping("/list")
//	public String getBoardList(Model model) {
//		List<Board> list = boardsService.getBoardList();
//		model.addAttribute("list", list); // jsp에 list 넘겨주기 
//		return "exam04/boardlist";
//	}
	
	@GetMapping("/list")
	public String getBoardList(@RequestParam(defaultValue="1")int pageNo, Model model) {
		int totalRows = boardsService.getTotalRows();
		Pager pager = new Pager(10, 5, totalRows, pageNo);
		/*
		 *  이페이저 의미 해석하기 
		 *  1. rowsPerpage 페이지당 행 수  - 10 
		 *  2. pagesPerGroup 그룹당 페이지 수 - 5
		 *  3. totalRows 전체 행 수 - 디비에서 구해옴 
		 *  4. pageNo  현재 페이지 번호 - 파라미터로 넘어옴 
		 */
		
		List<Board> list = boardsService.getBoardList(pager);
		model.addAttribute("list", list); // jsp에 list 넘겨주기 
		model.addAttribute("pager", pager);
		
		return "exam04/boardlist";
	}
	
	
	@GetMapping("/createForm")
	public String insertForm() {
		return "exam04/createForm";
	}
	
	
//	@PostMapping("/create")
//	public String saveBoard(String btitle, String bcontent) {
//		Board board = new Board();
//		board.setBtitle(btitle);
//		board.setBcontent(bcontent);
//		board.setBwriter("chaejeong");
//		
//		boardsService.saveBoard(board);
//		return "redirect:/exam04/list";  // get방식 요청 
//	}
	
	@PostMapping("/create")
	public String saveBoard(Board board) {
		/*
		 *  이렇게 (커맨드)객체로 전달받아서 필요한것만 setter해서 넣으면 된다. 
		 *  이게 가능하려면 폼태그에서 name이 DTO의 필드네임과 동일해야 한다. 
		 */
		board.setBwriter("chaejeong");
		boardsService.saveBoard(board);
		return "redirect:/exam04/list";  // get방식 요청 
	}
	
	@GetMapping("/read")
	public String read(int bno, Model model) {
		// 조회수 올리는 코드 
		boardsService.addBhitCount(bno);
		
		Board board = boardsService.getBoard(bno);
		model.addAttribute("board", board);
		return "exam04/read";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(int bno, Model model) {
		Board board = boardsService.getBoard(bno);
		model.addAttribute("board", board);
		return "exam04/updateForm";
	}
	
	@PostMapping("/update")
	public String updateBoard(Board board) {
		boardsService.updateBoard(board);
		return "redirect:/exam04/list";  // get방식 요청 
	}
	
	@GetMapping("/delete")
	public String deleteBoard(int bno) {
		boardsService.deleteBoard(bno);
		return "redirect:/exam04/list";  // get방식 요청 return 
	}
	
	
}