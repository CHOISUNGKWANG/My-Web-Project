package com.test.app.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.app.model.BoardVO;
import com.test.app.model.LikeVO;
import com.test.app.model.ReplySet;
import com.test.app.model.ReplyVO;
import com.test.app.model.StatsVO;
import com.test.app.service.BoardService;
import com.test.app.service.LikeService;
import com.test.app.service.ReplyService;
import com.test.app.service.StatsService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private StatsService statsService;
	
	@Autowired
	private ServletContext sc;

	@RequestMapping(value="/view/main.do")
	public String main(HttpServletRequest req,BoardVO bvo,Model model) {
		int cnt;
		if(req.getParameter("page")==null) {
			cnt = 1;
		}else {
			cnt = Integer.parseInt(req.getParameter("page"));
		}
		ArrayList<BoardVO> datas = boardService.getBoardList(bvo,cnt);
		model.addAttribute("recordCount",boardService.getBoardCntAll(bvo));
		model.addAttribute("bdatas",datas);
		return "main.jsp";
	}

	@RequestMapping(value="/view/search.do")
	public String search(HttpServletRequest req,BoardVO bvo,Model model) {
		int cnt = 0;
		int recordCount = 0;
		if(req.getParameter("page")==null) {
			cnt = 1;
		}else {
			cnt = Integer.parseInt(req.getParameter("page"));
		}
		ArrayList<BoardVO> datas = null;
		if(req.getParameter("keywords")==null||req.getParameter("keywords")=="") {
			recordCount=boardService.getBoardCntAll(bvo);
			datas = boardService.getBoardList(bvo,cnt);
		}else {
			if(req.getParameter("category").equals("btitle")) {
				bvo.setBtitle(req.getParameter("keywords"));
				datas = boardService.getBoardList_title(bvo,cnt);
				recordCount=boardService.getBoardCntTitle(bvo);
			}else if(req.getParameter("category").equals("mnick")) {
				bvo.setMnick(req.getParameter("keywords"));
				datas = boardService.getBoardList_nick(bvo,cnt);
				recordCount=boardService.getBoardCntNick(bvo);
			}
		}
		model.addAttribute("category", req.getParameter("category"));
		model.addAttribute("searchCondition", bvo.getSearchCondition());
		model.addAttribute("keywords", req.getParameter("keywords"));
		model.addAttribute("recordCount",recordCount);
		model.addAttribute("bdatas",datas);
		return "main.jsp";
	}

	@RequestMapping(value="/view/detail.do")
	public String userDetail(HttpSession session,BoardVO bvo,ReplyVO rvo,LikeVO lvo,Model model) {
		String mid=(String)session.getAttribute("mid");
		BoardVO data = boardService.getBoard(bvo);
		if(mid==null||!mid.equals(data.getMid())) {
			boardService.updateBoard_hitsUp(bvo);
		}
		data = boardService.getBoard(bvo);
		model.addAttribute("bdata",data);
		ArrayList<ReplySet> datas = replyService.getReplyList(rvo);
		model.addAttribute("rdatas",datas);
		lvo = likeService.getLike(lvo);
		model.addAttribute("ldata",lvo);
		return "detail.jsp";
	}

	@RequestMapping(value="/view/insertBoard.do")
	public String insertBoard(BoardVO bvo,HttpServletRequest req) throws IOException {
		String realFolder = sc.getRealPath("file");
		int maxSize = 1024*1024*200;
		String encType = "UTF-8";
		MultipartRequest mr = new MultipartRequest(req,realFolder,maxSize,encType,new DefaultFileRenamePolicy());
		String frn = mr.getFilesystemName("bimg");
		bvo.setMid(mr.getParameter("mid"));
		bvo.setMnick(mr.getParameter("mnick"));
		bvo.setBtitle(mr.getParameter("btitle"));
		bvo.setBimg(frn);
		bvo.setBcontent(mr.getParameter("bcontent"));
		boardService.insertBoard(bvo);
		return "redirect:main.do";
	}

	@RequestMapping(value="/view/updatePage.do")
	public String updatePage(BoardVO bvo,Model model) {
		BoardVO data = boardService.getBoard(bvo);
		model.addAttribute("bdata",data);
		return "updateBoard.jsp";
	}
	
	@RequestMapping(value="/view/updateBoard.do")
	public String updateBoard(BoardVO bvo,HttpServletRequest req) throws IOException {
		String realFolder = sc.getRealPath("file");
		int maxSize = 1024*1024*200;
		String encType = "UTF-8";
		MultipartRequest mr = new MultipartRequest(req,realFolder,maxSize,encType,new DefaultFileRenamePolicy());
		String frn = mr.getFilesystemName("bimg");
		if(frn==null) {
			BoardVO data=boardService.getBoard(bvo);
			bvo.setBimg(data.getBimg());
		}else {
			bvo.setBimg(frn);
		}	
		bvo.setBtitle(mr.getParameter("btitle"));
		bvo.setBcontent(mr.getParameter("bcontent"));
		boardService.updateBoard(bvo);
		return "detail.do";
	}

	@RequestMapping(value="/view/deleteBoard.do")
	public String deleteBoard(BoardVO bvo) {
		boardService.deleteBoard(bvo);
		return "redirect:main.do";
	}
	
	@ResponseBody
	@RequestMapping(value="/view/likeAjax.do")
	public String likeAjax(LikeVO lvo) {
		LikeVO data = likeService.getLike(lvo);
		String result;
		if(data==null) {
			likeService.insertLike(lvo);
			result="1";
		}else {
			likeService.deleteLike(lvo);
			result="0";
		}
		return result;
	}
	
	@RequestMapping(value="/view/stats.do")
	public String userDetail(StatsVO svo,Model model) {
		svo.setSposition("top");
		ArrayList<StatsVO> sdatas1 = statsService.getStatsList(svo);
		svo.setSposition("jng");
		ArrayList<StatsVO> sdatas2 = statsService.getStatsList(svo);
		svo.setSposition("mid");
		ArrayList<StatsVO> sdatas3 = statsService.getStatsList(svo);
		svo.setSposition("adc");
		ArrayList<StatsVO> sdatas4 = statsService.getStatsList(svo);
		svo.setSposition("sup");
		ArrayList<StatsVO> sdatas5 = statsService.getStatsList(svo);
		model.addAttribute("sdatas1",sdatas1);
		model.addAttribute("sdatas2",sdatas2);
		model.addAttribute("sdatas3",sdatas3);
		model.addAttribute("sdatas4",sdatas4);
		model.addAttribute("sdatas5",sdatas5);
		return "stats.jsp";
	}
	
}
