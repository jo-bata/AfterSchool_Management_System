package com.skuniv.AfterSchoolServer;

import java.util.Date;

public class Classinfo {
	private String cc;//강의코드
	private String cn;//강의이름
	private int cd;//강의날짜
	private int ct;//강의시간
	private String sn;//학교이름
	private String sd;//강의시작일
	private String fd;//강의종강일
	private String tn;//교재이름
	
	

	public Classinfo(String cc, String cn, int cd, int ct, String sn, String sd, String fd, String tn) {
		
		this.cc = cc;
		this.cn = cn;
		this.cd = cd;
		this.ct = ct;
		this.sn = sn;
		this.sd = sd;
		this.fd = fd;
		this.tn = tn;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public int getCd() {
		return cd;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}

	public int getCt() {
		return ct;
	}

	public void setCt(int ct) {
		this.ct = ct;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getFd() {
		return fd;
	}

	public void setFd(String fd) {
		this.fd = fd;
	}

	public String getTn() {
		return tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	
	
	
	
}
