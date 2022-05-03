package com.test.app.model;

public class StatsVO {
	private String sposition;
	private String srank;
	private String schampsrc;
	private String schamp;
	private String stiersrc;
	private String swinrate;
	private String spickrate;
	
	public String getSposition() {
		return sposition;
	}
	public void setSposition(String sposition) {
		this.sposition = sposition;
	}
	public String getSrank() {
		return srank;
	}
	public void setSrank(String srank) {
		this.srank = srank;
	}
	public String getSchampsrc() {
		return schampsrc;
	}
	public void setSchampsrc(String schampsrc) {
		this.schampsrc = schampsrc;
	}
	public String getSchamp() {
		return schamp;
	}
	public void setSchamp(String schamp) {
		this.schamp = schamp;
	}
	public String getStiersrc() {
		return stiersrc;
	}
	public void setStiersrc(String stiersrc) {
		this.stiersrc = stiersrc;
	}
	public String getSwinrate() {
		return swinrate;
	}
	public void setSwinrate(String swinrate) {
		this.swinrate = swinrate;
	}
	public String getSpickrate() {
		return spickrate;
	}
	public void setSpickrate(String spickrate) {
		this.spickrate = spickrate;
	}
	
	@Override
	public String toString() {
		return "StatsVO [sposition=" + sposition + ", srank=" + srank + ", schampsrc=" + schampsrc + ", schamp="
				+ schamp + ", stiersrc=" + stiersrc + ", swinrate=" + swinrate + ", spickrate=" + spickrate + "]";
	}
	
}
