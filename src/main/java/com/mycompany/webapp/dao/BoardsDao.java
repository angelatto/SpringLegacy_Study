package com.mycompany.webapp.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;

@Repository
public class BoardsDao {
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public List<Board> selectAll(){
		List<Board> list = sst.selectList("boards.selectAll");  // boards 맵퍼에 있는 sql 문 실행 
		return list;
	}
	
	public List<Board> selectByPage(Pager pager) {
		List<Board> list = sst.selectList("boards.selectByPage", pager); 
		return list;
	}

	
	public int insert(Board board) {
		int rows = sst.insert("boards.insert", board); // 성공적으로 실행되면 1 리턴 
		return rows;
	}

	public Board selectByBno(int bno) {
		Board board = sst.selectOne("boards.selectByBno", bno);
		return board;
	}
	
	public int update(Board board) { // return 값은 영향받은 행 수를 의미한다. 
		int rows = sst.update("boards.update", board);
		return rows;
	}
	
	public int deleteByBno(int bno) {
		int rows = sst.delete("boards.deleteByBno", bno);
		return rows;
	}
	
	public int updateBhitCount(int bno) {
		int rows = sst.update("boards.updateBhitCount", bno);
		return rows;
	}
	
	public int count() {
		int rows = sst.selectOne("boards.count");
		return rows;
	}


}
