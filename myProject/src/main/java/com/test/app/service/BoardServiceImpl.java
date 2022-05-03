package com.test.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.model.BoardDAO;
import com.test.app.model.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
	}

	@Override
	public int getBoardCntAll(BoardVO vo) {
		return boardDAO.getBoardCntAll(vo);
	}

	@Override
	public int getBoardCntTitle(BoardVO vo) {
		return boardDAO.getBoardCntTitle(vo);
	}

	@Override
	public int getBoardCntNick(BoardVO vo) {
		return boardDAO.getBoardCntNick(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}

	@Override
	public ArrayList<BoardVO> getBoardList(BoardVO vo, int cnt) {
		return boardDAO.getBoardList(vo, cnt);
	}

	@Override
	public ArrayList<BoardVO> getBoardList_title(BoardVO vo, int cnt) {
		return boardDAO.getBoardList_title(vo, cnt);
	}

	@Override
	public ArrayList<BoardVO> getBoardList_nick(BoardVO vo, int cnt) {
		return boardDAO.getBoardList_nick(vo, cnt);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	@Override
	public void updateBoard_hitsUp(BoardVO vo) {
		boardDAO.updateBoard_hitsUp(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}

}
