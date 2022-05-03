package com.test.app.model;

public class LikeVO {
	private int bid;
	private String mid;
	
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
	
	@Override
	public String toString() {
		return "LikeVO [bid=" + bid + ", mid=" + mid + "]";
	}
	
}
