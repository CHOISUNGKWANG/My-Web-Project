package com.test.app.service;

import java.util.ArrayList;

import com.test.app.model.BoardVO;

public interface BoardService {
	public void insertBoard(BoardVO vo);
	public int getBoardCntAll(BoardVO vo);
	public int getBoardCntTitle(BoardVO vo);
	public int getBoardCntNick(BoardVO vo);
	public BoardVO getBoard(BoardVO vo);
	public ArrayList<BoardVO> getBoardList(BoardVO vo,int cnt);
	public ArrayList<BoardVO> getBoardList_title(BoardVO vo,int cnt);
	public ArrayList<BoardVO> getBoardList_nick(BoardVO vo,int cnt);
	public void updateBoard(BoardVO vo);
	public void updateBoard_hitsUp(BoardVO vo);
	public void deleteBoard(BoardVO vo);
}
