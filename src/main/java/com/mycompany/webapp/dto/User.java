package com.mycompany.webapp.dto;

public class User {
	private String uid;
	private String uname;
	private String upassword;
	private String[] uhobby;  // list로 받으면 안되고 꼭 배열로 해야한다. 왜??
	private String ujob;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String[] getUhobby() {
		return uhobby;
	}
	public void setUhobby(String[] uhobby) {
		this.uhobby = uhobby;
	}
	public String getUjob() {
		return ujob;
	}
	public void setUjob(String ujob) {
		this.ujob = ujob;
	}
	
	
	

}
