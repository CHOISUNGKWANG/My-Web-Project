package com.test.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.model.Reply2VO;
import com.test.app.model.ReplyDAO;
import com.test.app.model.ReplySet;
import com.test.app.model.ReplyVO;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public void insertReply2(Reply2VO vo) {
		replyDAO.insertReply2(vo);
	}

	@Override
	public ArrayList<Reply2VO> getReply2List(Reply2VO vo) {
		return replyDAO.getReply2List(vo);
	}

	@Override
	public void deleteReply2(Reply2VO vo) {
		replyDAO.deleteReply2(vo);
	}

	@Override
	public void insertReply(ReplyVO vo) {
		replyDAO.insertReply(vo);
	}

	@Override
	public ArrayList<ReplySet> getReplyList(ReplyVO vo) {
		return replyDAO.getReplyList(vo);
	}

	@Override
	public void deleteReply(ReplyVO vo) {
		replyDAO.deleteReply(vo);
	}

}
