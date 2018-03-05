package com.skuniv.AfterSchoolServer;

public class Student {
	private String sc;//학생코드
	private String sn;//학생이름
	private String scn;//학교이름
	private int g;//학년
	private int c;//반
	private String sbd;//생년월일
	private String spn;//학생전화번호
	private String ppn;//부모님전화번호
	private int vps;//자유수강여부
	
	
	public Student(String sc, String sn, String scn, int g, int c, String sbd, String spn, String ppn, int vps) {
		super();
		this.sc = sc;
		this.sn = sn;
		this.scn = scn;
		this.g = g;
		this.c = c;
		this.sbd = sbd;
		this.spn = spn;
		this.ppn = ppn;
		this.vps = vps;
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
	public String getScn() {
		return scn;
	}
	public void setScn(String scn) {
		this.scn = scn;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public String getSbd() {
		return sbd;
	}
	public void setSbd(String sbd) {
		this.sbd = sbd;
	}
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public String getPpn() {
		return ppn;
	}
	public void setPpn(String ppn) {
		this.ppn = ppn;
	}
	public int getVps() {
		return vps;
	}
	public void setVps(int vps) {
		this.vps = vps;
	}
	
	
	
	
}
