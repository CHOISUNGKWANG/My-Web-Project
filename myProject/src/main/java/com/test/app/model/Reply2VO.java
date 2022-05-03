package com.test.app.model;

public class Reply2VO {
	private int rrid;
	private int rid;
	private String mid;
	private String mnick;
	private String rrcontent;
	private String rrdate;
	
	public int getRrid() {
		return rrid;
	}
	public void setRrid(int rrid) {
		this.rrid = rrid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMnick() {
		return mnick;
	}
	public void setMnick(String mnick) {
		this.mnick = mnick;
	}
	public String getRrcontent() {
		return rrcontent;
	}
	public void setRrcontent(String rrcontent) {
		this.rrcontent = rrcontent;
	}
	public String getRrdate() {
		return rrdate;
	}
	public void setRrdate(String rrdate) {
		this.rrdate = rrdate;
	}
	
	@Override
	public String toString() {
		return "Reply2VO [rrid=" + rrid + ", rid=" + rid + ", mid=" + mid + ", mnick=" + mnick + ", rrcontent="
				+ rrcontent + ", rrdate=" + rrdate + "]";
	}
	
}
