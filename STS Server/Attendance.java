package com.skuniv.AfterSchoolServer;

import java.util.Date;

public class Attendance {
	private String sc;//학생코드
	private String sn;//학생이름
	private String adt=null;//출석일
	
	
	
	public Attendance(String sc, String sn,String adt) {
		this.sc = sc;
		this.sn = sn;
		this.adt = adt;
	}
	
	
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getAdt() {
		return adt;
	}
	public void setAdt(String adt) {
		this.adt = adt;
	}
	
	
	
}


