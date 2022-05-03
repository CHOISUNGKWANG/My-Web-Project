package com.test.app.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.app.logic.RandomCode;
import com.test.app.logic.SendMail;
import com.test.app.model.BoardVO;
import com.test.app.model.MemberVO;
import com.test.app.service.BoardService;
import com.test.app.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/view/login.do")
	public String login(MemberVO mvo,HttpSession session,Model model) {
		mvo = memberService.getMember_login(mvo);
		if(mvo == null){
			model.addAttribute("msg","잘못된 아이디 또는 비밀번호입니다.");
			return "login.jsp";
		}
		session.setAttribute("mid", mvo.getMid());
		session.setAttribute("mnick", mvo.getMnick());
		return "redirect:main.do";
	}
	
	@RequestMapping(value="/view/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:main.do";
	}
	
	@RequestMapping(value="/view/myInfo.do")
	public String myInfo(HttpServletRequest req,HttpSession session,MemberVO mvo,BoardVO bvo,Model model) {
		int cnt;
		if(req.getParameter("page")==null) {
			cnt = 1;
		}else {
			cnt = Integer.parseInt(req.getParameter("page"));
		}
		mvo.setMid((String)session.getAttribute("mid"));
		MemberVO data = memberService.getMember_idCheck(mvo);
		model.addAttribute("mdata",data);
		ArrayList<BoardVO> datas = boardService.getBoardList_nick(bvo,cnt);
		model.addAttribute("bdatas",datas);
		model.addAttribute("recordCount",boardService.getBoardCntNick(bvo));
		return "myInfo.jsp";
	}
	
	@RequestMapping(value="/view/insertMember.do")
	public String insertMember(MemberVO mvo,Model model) {
		memberService.insertMember(mvo);
		model.addAttribute("msg","회원가입을 축하드립니다!");
		return "login.jsp";
	}
	
	@RequestMapping(value="/view/deleteMember.do")
	public String deleteMember(MemberVO mvo,HttpSession session) {
		memberService.deleteMember(mvo);
		session.invalidate();
		return "redirect:main.do";
	}
	
	@RequestMapping(value="/view/updatePassword.do")
	public String updatePassword(MemberVO mvo) {
		memberService.updateMember_pw(mvo);
		return "myInfo.do";
	}
	
	@RequestMapping(value="/view/pwFind.do")
	public String pwFind(MemberVO mvo,RandomCode rc,SendMail sm,Model model) {
		String code = rc.randomCode();
		sm.sendMail(mvo.getMemail(), code);
		mvo.setMpw(code);
		memberService.updateMember_pw(mvo);
		model.addAttribute("msg","인증코드를 보냈습니다. 메일을 확인해주세요!");
		return "login.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/view/idCheckAjax.do")
	public String idCheckAjax(MemberVO mvo) {
		MemberVO data = memberService.getMember_idCheck(mvo);
		String result;
		if(data == null){
		      result = "0"; // 아이디 만들기 가능
		   }else{
		      result = "1"; // 아이디 이미 있음
		   }
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/view/nickCheckAjax.do")
	public String nickCheckAjax(MemberVO mvo) {
		MemberVO data = memberService.getMember_nickCheck(mvo);
		String result;
		if(data == null){
			result = "0"; // 닉네임 만들기 가능
		}else{
			result = "1"; // 닉네임 이미 있음
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/view/emailCheckAjax.do")
	public String emailCheckAjax(MemberVO mvo,RandomCode rc,SendMail sm) {
		String code = rc.randomCode();
		sm.sendMail(mvo.getMemail(), code);
		return code;
	}
	
}
