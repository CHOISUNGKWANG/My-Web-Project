package com.test.app.service;

import java.util.ArrayList;

import com.test.app.model.Reply2VO;
import com.test.app.model.ReplySet;
import com.test.app.model.ReplyVO;

public interface ReplyService {
	public void insertReply2(Reply2VO vo);
	public ArrayList<Reply2VO> getReply2List(Reply2VO vo);
	public void deleteReply2(Reply2VO vo);
	public void insertReply(ReplyVO vo);
	public ArrayList<ReplySet> getReplyList(ReplyVO vo);
	public void deleteReply(ReplyVO vo);
}
