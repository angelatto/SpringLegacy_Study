package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.BoardsService;

@Controller
@RequestMapping("/exam04")
public class Exam04Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Exam04Controller.class);
	
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
	public String getBoardList( //@RequestParam(defaultValue="1")int pageNo, 
			String pageNo, Model model, HttpSession session) {
		
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
		/*
		 *  이페이저 의미 해석하기 
		 *  1. rowsPerpage 페이지당 행 수  - 10 
		 *  2. pagesPerGroup 그룹당 페이지 수 - 5
		 *  3. totalRows 전체 행 수 - 디비에서 구해옴 
		 *  4. intPageNo  현재 페이지 번호 - 파라미터로 넘어옴 
		 */
		List<Board> list = boardsService.getBoardList(pager);
		model.addAttribute("list", list); // jsp에 list 넘겨주기 
		model.addAttribute("pager", pager);
		
		return "exam04/boardlist";
	}
	
	
	@GetMapping("/createForm")
	public String insertForm(HttpSession session) {
		String uid = (String)session.getAttribute("loginUid");
		if(uid == null) {
			return "redirect:/exam07/loginForm";
		}
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
	public String saveBoard(Board board, HttpSession session){
		/*
		 *  이렇게 (커맨드)객체로 전달받아서 필요한것만 setter해서 넣으면 된다. 
		 *  이게 가능하려면 폼태그에서 name이 DTO의 필드네임과 동일해야 한다. 
		 */
		board.setBwriter((String)session.getAttribute("loginUid"));
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
	
	@GetMapping("/createFormWithAttach")
	public String createFormWithAttach(HttpSession session) {
		String uid = (String)session.getAttribute("loginUid");
		if(uid == null) {
			return "redirect:/exam07/loginForm";
		}
		return "exam04/createFormWithAttach";
	}
	
	@PostMapping("/createWithAttach")
	public String createWithAttach(Board board) {
//			String btitle, String bcontent, MultipartFile battach) {
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
		logger.info(board.getBtitle());
		logger.info(board.getBcontent());
		logger.info(board.getBattachoname());
		logger.info(board.getBattachsname());
		logger.info(board.getBattachtype());
		
		board.setBwriter("user1");
		boardsService.saveBoard(board);  // 디비에 저장을 한거고 .. 
		
		return "redirect:/exam04/list"; 
	}
	
	@GetMapping("/downloadAttach")
	public void downloadAttach(int bno, HttpServletResponse response) {
		/*
		 *  얘가 실행하고 나서 결과는 그림의 데이터이기 때문에 문자열을 반환하지 않는다. 
		 */
		try {
			Board board = boardsService.getBoard(bno);
			
			// 응답 HTTP 헤더에 저장될 응답 바디의 타입 
			response.setContentType(board.getBattachtype());
			
			// 응답 HTTP 헤더에 다운로드할 수 있도록 파일 이름을 지정
			String originalName = board.getBattachoname();
			// 한글 파일일 경우, 깨짐 현상을 방지 
			// header에는 한글을 절대 넣을 수 없다. 헤더에는 아스키코드만 해석할 수 있으니까 UTF-8에서 변환한다. 
			originalName = new String(originalName.getBytes("UTF-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + originalName + "\"");  // 헤더의 위치 지정
			// attachment값이  파일로 다운로드 가능하게 해줌 
			
			// 응답 HTTP 바디로 이미지 데이터를 출력 
			InputStream in = new FileInputStream("/Users/homecj/dev/workspace/sts/Douzone/uploadfiles/" + board.getBattachsname());	    
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
			out.flush();
			in.close();
			out.close();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
			
}