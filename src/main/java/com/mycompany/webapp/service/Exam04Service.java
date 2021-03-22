package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Exam04Dao;
import com.mycompany.webapp.dto.Board;

@Service 
public class Exam04Service {
	
	@Autowired
	private Exam04Dao exam04Dao;
	
	public List<Board> getBoardList(){ 
		List<Board> list = exam04Dao.selectAll();
		return list;
	}
}
