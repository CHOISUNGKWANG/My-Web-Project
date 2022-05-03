package com.test.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.app.model.Reply2VO;
import com.test.app.model.ReplyVO;
import com.test.app.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="/view/insertReply.do")
	public String insertReply(ReplyVO rvo) {
		replyService.insertReply(rvo);
		return "detail.do";
	}
	
	@RequestMapping(value="/view/deleteReply.do")
	public String deleteReply(ReplyVO rvo) {
		replyService.deleteReply(rvo);
		return "detail.do";
	}
	
	@RequestMapping(value="/view/insertReply2.do")
	public String insertReply2(Reply2VO rvo2) {
		replyService.insertReply2(rvo2);
		return "detail.do";
	}
	
	@RequestMapping(value="/view/deleteReply2.do")
	public String deleteReply2(Reply2VO rvo2) {
		replyService.deleteReply2(rvo2);
		return "detail.do";
	}
	
}
