package com.mycompany.webapp.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	// DB field랑 이름이 같아야 함
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private int bhitcount;
	private MultipartFile[] battach;  // DB에는 없는 필드 -> 이 정보를 가지고 아래 3가지 정보를 setter 해줘야 한다. 
	private String battachoname;
	private String battachsname;  // 실제 저장한 이름 
	private String battachtype;
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public int getBhitcount() {
		return bhitcount;
	}
	public void setBhitcount(int bhitcount) {
		this.bhitcount = bhitcount;
	}
	public String getBattachoname() {
		return battachoname;
	}
	public void setBattachoname(String battachoname) {
		this.battachoname = battachoname;
	}
	public String getBattachsname() {
		return battachsname;
	}
	public void setBattachsname(String battachsname) {
		this.battachsname = battachsname;
	}
	public String getBattachtype() {
		return battachtype;
	}
	public void setBattachtype(String battachtype) {
		this.battachtype = battachtype;
	}
	public MultipartFile[] getBattach() {
		return battach;
	}
	public void setBattach(MultipartFile[] battach) {
		this.battach = battach;
	}
	

}
