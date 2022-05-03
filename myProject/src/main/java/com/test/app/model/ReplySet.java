package com.test.app.model;

import java.util.ArrayList;

public class ReplySet {
	private ReplyVO replyVO;
	private ArrayList<Reply2VO> reply2List = new ArrayList<Reply2VO>();
	
	public ReplyVO getReplyVO() {
		return replyVO;
	}
	public void setReplyVO(ReplyVO replyVO) {
		this.replyVO = replyVO;
	}
	public ArrayList<Reply2VO> getReply2List() {
		return reply2List;
	}
	public void setReply2List(ArrayList<Reply2VO> reply2List) {
		this.reply2List = reply2List;
	}
	
	@Override
	public String toString() {
		return "ReplySet [replyVO=" + replyVO + ", reply2List=" + reply2List + "]";
	}
	
}

