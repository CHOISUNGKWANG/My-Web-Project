package com.test.app.model;

public class BoardVO {
	private int bid;
	private String mid;
	private String mnick;
	private String btitle;
	private String bimg;
	private String bcontent;
	private int bhits;
	private int blike;
	private String bdate;
	private String searchCondition;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
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
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public int getBhits() {
		return bhits;
	}
	public void setBhits(int bhits) {
		this.bhits = bhits;
	}
	public int getBlike() {
		return blike;
	}
	public void setBlike(int blike) {
		this.blike = blike;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bid=" + bid + ", mid=" + mid + ", mnick=" + mnick + ", btitle=" + btitle + ", bimg=" + bimg
				+ ", bcontent=" + bcontent + ", bhits=" + bhits + ", blike=" + blike + ", bdate=" + bdate
				+ ", searchCondition=" + searchCondition + "]";
	}
	
}
