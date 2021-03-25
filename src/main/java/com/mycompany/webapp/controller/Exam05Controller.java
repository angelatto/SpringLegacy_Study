package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.BoardsService;

@Controller
@RequestMapping("/exam05")
public class Exam05Controller {

	private static final Logger logger = 
			LoggerFactory.getLogger(Exam05Controller.class);
	
	@Autowired
	private BoardsService boardsService;
	
	@RequestMapping("/content")
	public String content(Model model) {
	
		return "exam05/content";
	}
	
	@GetMapping("/list")
	public String getBoardList(@RequestParam(defaultValue="1")int pageNo, Model model) {
		int totalRows = boardsService.getTotalRows();
		Pager pager = new Pager(10, 5, totalRows, pageNo);
		List<Board> list = boardsService.getBoardList(pager);
		model.addAttribute("list", list); // jsp에 list 넘겨주기 
		model.addAttribute("pager", pager);
		return "exam05/boardlist";
	}
	
	
	@GetMapping("/read")
	public String read(int bno, Model model) {
		// 조회수 올리는 코드 
		boardsService.addBhitCount(bno);
		
		Board board = boardsService.getBoard(bno);
		model.addAttribute("board", board);
		return "exam05/read";
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
			logger.info(originalName);
			// 한글 파일일 경우, 깨짐 현상을 방지 
			// header에는 한글을 절대 넣을 수 없다. 
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
	
	@GetMapping("/updateForm")
	public String updateForm(int bno, Model model) {
		Board board = boardsService.getBoard(bno);
		model.addAttribute("board", board);
		return "exam05/updateForm";
	}
	
	//  응답 바디에 JSON 데이터를 리턴한다. 응답 헤더에 JSON 데이터라고 명시해줘야 한다. 
	@PostMapping(value="/update", produces="application/json;charset=UTF-8")  //AJAX는 REDIRECT 사용 못함
	@ResponseBody
	public String update(Board board) {
		boardsService.updateBoard(board);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		
		return jsonObject.toString();
	}
	

	@GetMapping(value="/delete", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String delete(int bno) {
		boardsService.deleteBoard(bno);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		
		return jsonObject.toString();
	}
	
	@GetMapping("/createForm")
	public String createForm() {
		return "exam05/createForm";
	}
	
	@PostMapping(value="/create", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String create(Board board) {
		MultipartFile battach = board.getBattach()[0];
		
		if(battach != null && !battach.isEmpty()) { // 첨부가 있을 경우 
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			String saveName = new Date().getTime() + "-" + battach.getOriginalFilename();
			board.setBattachsname(saveName);
			
			File file = new File("/Users/homecj/dev/workspace/sts/Douzone/uploadfiles/" + saveName);
			try {
				battach.transferTo(file);   //???? 
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
		boardsService.saveBoard(board);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		
		return jsonObject.toString();
	}
			
}