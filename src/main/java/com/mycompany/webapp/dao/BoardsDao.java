package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;


/* 마이바티즈가 제공하는 어노테이션 
 * 인터페이스이기 때문에 구현 객체가 아니다. 
 * 그러나 이 Mapper 어노테이션이 자동으로 구현 객체로 생성해준다. 
 * 근데 여기서,, 객체로는 만들어줬는데 어떤 xml과 연결되어있는지 정보가없다. 
 *  -> <mapper namespace="com.mycompany.webapp.dao.BoardsDao"> 이렇게 맵퍼에 인터페이스를 연결해준다. 
 * 
 * 
 * 이 Mapper 어노테이션이 제대로 작동하려면 설정을 해주어야 한다. (root/component-scan.xml)
 * 
 * 
 */

@Mapper
public interface BoardsDao { 
	/* class가 아닌 interface(사용설명서)로 만들기 
	 *  주의 - 아래 메소드 이름은 Mybatis의 xml에 있는 sql문 id와 같아야 한다. 
	 * 
	 */
	
	public List<Board> selectAll();
	public List<Board> selectByPage(Pager pager);
	public int insert(Board board);
	public Board selectByBno(int bno);
	public int update(Board board);
	public int deleteByBno(int bno);
	public int updateBhitCount(int bno);
	public int count();
	
}
