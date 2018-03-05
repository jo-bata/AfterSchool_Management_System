package com.skuniv.AfterSchoolServer;

public class TimeTable {
	private String sn;//학교이름
	private String cn;//수업이름
	private int cd;//수업요일
	private int ct;//수업시간
	
	public TimeTable(String sn,String cn,int cd,int ct) {
		this.sn=sn;
		this.cn=cn;
		this.cd=cd;
		this.ct=ct;
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

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	
	
}
