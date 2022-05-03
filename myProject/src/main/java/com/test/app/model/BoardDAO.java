package com.test.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.test.app.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	private final String B_INSERT = "insert into board (bid,mid,mnick,btitle,bimg,bcontent)"
			+ " values((select nvl(max(bid),0)+1 from board),?,?,?,?,?)"; // 글 등록
	private final String B_SELECTONE_BID = "select * from board where bid=?"; // 글 정보 불러오기
	private final String B_SELECTALL_NEW = "select * from (select rownum r,a.* from (select * from board order by bid desc) a) where r between ? and ?"; // 글 목록 최신순으로 불러오기
	private final String B_SELECTALL_HITS = "select * from (select rownum r,a.* from (select * from board order by bhits desc) a) where r between ? and ?"; // 글 목록 조회순으로 불러오기
	private final String B_SELECTALL_LIKE = "select * from (select rownum r,a.* from (select * from board order by blike desc) a) where r between ? and ?"; // 글 목록 좋아요순으로 불러오기
	private final String B_SELECTALL_BTILTE_NEW = "select * from (select rownum r,a.* from(select * from board where btitle like '%'||?||'%' order by bid desc) a) where r between ? and ?"; // 글 목록 좋아요순으로 불러오기
	private final String B_SELECTALL_BTILTE_HITS = "select * from (select rownum r,a.* from(select * from board where btitle like '%'||?||'%' order by bhits desc) a) where r between ? and ?"; // 글 목록 좋아요순으로 불러오기
	private final String B_SELECTALL_BTILTE_LIKE = "select * from (select rownum r,a.* from(select * from board where btitle like '%'||?||'%' order by blike desc) a) where r between ? and ?"; // 글 목록 좋아요순으로 불러오기
	private final String B_SELECTALL_MNICK_NEW = "select * from (select rownum r,a.* from(select * from board where mnick like '%'||?||'%' order by bid desc) a) where r between ? and ?"; // 글 목록 좋아요순으로 불러오기
	private final String B_SELECTALL_MNICK_HITS = "select * from (select rownum r,a.* from(select * from board where mnick like '%'||?||'%' order by bhits desc) a) where r between ? and ?"; // 글 목록 좋아요순으로 불러오기
	private final String B_SELECTALL_MNICK_LIKE = "select * from (select rownum r,a.* from(select * from board where mnick like '%'||?||'%' order by blike desc) a) where r between ? and ?"; // 글 목록 좋아요순으로 불러오기
	private final String B_UPDATE = "update board set btitle=?, bimg=?, bcontent=? where bid=?"; // 글 수정
	private final String B_UPDATE_BLIKECNT = "update board set blike=? where bid=?"; // 좋아요 수 업데이트
	private final String B_UPDATE_BHITS = "update board set bhits=bhits+1 where bid=?"; // 조회수 +1
	private final String B_DELETE = "delete board where bid=?"; // 글 삭제

	private final String L_SELECTCNT_BLIKE = "select count(*) from blike where bid=?"; // 좋아요 수 카운트
	private final String L_SELECTCNT_BTITLE = "select count(*) from board where btitle like '%'||?||'%'"; // 해당 제목 카운트
	private final String L_SELECTCNT_MNICK = "select count(*) from board where mnick like '%'||?||'%'"; // 해당 작성자 카운트
	private final String B_SELECTCNT_ALL = "select count(*) from board"; // 게시글 수 카운트
	
	// 글 등록
	public void insertBoard(BoardVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(B_INSERT);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMnick());
			pstmt.setString(3, vo.getBtitle());
			pstmt.setString(4, vo.getBimg());
			pstmt.setString(5, vo.getBcontent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	
	// 전체 게시글 수 
	public int getBoardCntAll(BoardVO vo) {
		int cnt = 0;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(B_SELECTCNT_ALL);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return cnt;
	}
	
	// 제목검색 게시글 수 
	public int getBoardCntTitle(BoardVO vo) {
		int cnt = 0;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(L_SELECTCNT_BTITLE);
			pstmt.setString(1, vo.getBtitle());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return cnt;
	}
	
	// 작성자검색 게시글 수 
	public int getBoardCntNick(BoardVO vo) {
		int cnt = 0;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(L_SELECTCNT_MNICK);
			pstmt.setString(1, vo.getMnick());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return cnt;
	}

	// 글 정보 불러오기
	public BoardVO getBoard(BoardVO vo) {
		conn = JDBCUtil.connect();
		BoardVO data = null;
		try {
			pstmt = conn.prepareStatement(B_SELECTONE_BID);
			pstmt.setInt(1, vo.getBid());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new BoardVO();
				data.setBid(rs.getInt("bid"));
				data.setMid(rs.getString("mid"));
				data.setMnick(rs.getString("mnick"));
				data.setBtitle(rs.getString("btitle"));
				data.setBimg(rs.getString("bimg"));
				data.setBcontent(rs.getString("bcontent"));
				data.setBhits(rs.getInt("bhits"));
				
				pstmt = conn.prepareStatement(L_SELECTCNT_BLIKE);
				pstmt.setInt(1, vo.getBid());
				ResultSet rs2 = pstmt.executeQuery();
				rs2.next();
				int blikeCnt = rs2.getInt(1);
				rs2.close();
				
				pstmt = conn.prepareStatement(B_UPDATE_BLIKECNT);
				pstmt.setInt(1, blikeCnt);
				pstmt.setInt(2, vo.getBid());
				pstmt.executeUpdate();
				
				data.setBlike(blikeCnt);
				data.setBdate(rs.getString("bdate"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	// 글 목록 불러오기
	public ArrayList<BoardVO> getBoardList(BoardVO vo,int cnt) {
		conn = JDBCUtil.connect();
		ArrayList<BoardVO> datas = new ArrayList<BoardVO>();
		try {
			if(vo.getSearchCondition()==null) {
				pstmt = conn.prepareStatement(B_SELECTALL_NEW);
			}else {
				if(vo.getSearchCondition().equals("new")) {
					pstmt = conn.prepareStatement(B_SELECTALL_NEW);
				} else if(vo.getSearchCondition().equals("hits")) {
					pstmt = conn.prepareStatement(B_SELECTALL_HITS);
				} else if(vo.getSearchCondition().equals("like")) {
					pstmt = conn.prepareStatement(B_SELECTALL_LIKE);
				} 
			}
			pstmt.setInt(1, cnt*10-9);
			pstmt.setInt(2, cnt*10);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO data = new BoardVO();
				data.setBid(rs.getInt("bid"));
				data.setMid(rs.getString("mid"));
				data.setMnick(rs.getString("mnick"));
				data.setBtitle(rs.getString("btitle"));
				data.setBimg(rs.getString("bimg"));
				data.setBcontent(rs.getString("bcontent"));
				data.setBhits(rs.getInt("bhits"));
				
				pstmt = conn.prepareStatement(L_SELECTCNT_BLIKE);
				pstmt.setInt(1, rs.getInt("bid"));
				ResultSet rs2 = pstmt.executeQuery();
				rs2.next();
				int blikeCnt = rs2.getInt(1);
				rs2.close();
				
				pstmt = conn.prepareStatement(B_UPDATE_BLIKECNT);
				pstmt.setInt(1, blikeCnt);
				pstmt.setInt(2, rs.getInt("bid"));
				pstmt.executeUpdate();
				
				data.setBlike(blikeCnt);
				data.setBdate(rs.getString("bdate"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}	

	// 제목검색
	public ArrayList<BoardVO> getBoardList_title(BoardVO vo,int cnt) {
		conn = JDBCUtil.connect();
		ArrayList<BoardVO> datas = new ArrayList<BoardVO>();
		try {
			if(vo.getSearchCondition().equals("new")) {
				pstmt = conn.prepareStatement(B_SELECTALL_BTILTE_NEW);
			} else if(vo.getSearchCondition().equals("hits")) {
				pstmt = conn.prepareStatement(B_SELECTALL_BTILTE_HITS);
			} else if(vo.getSearchCondition().equals("like")) {
				pstmt = conn.prepareStatement(B_SELECTALL_BTILTE_LIKE);
			}
			pstmt.setString(1, vo.getBtitle());
			pstmt.setInt(2, cnt*10-9);
			pstmt.setInt(3, cnt*10);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO data = new BoardVO();
				data.setBid(rs.getInt("bid"));
				data.setMid(rs.getString("mid"));
				data.setMnick(rs.getString("mnick"));
				data.setBtitle(rs.getString("btitle"));
				data.setBimg(rs.getString("bimg"));
				data.setBcontent(rs.getString("bcontent"));
				data.setBhits(rs.getInt("bhits"));

				pstmt = conn.prepareStatement(L_SELECTCNT_BLIKE);
				pstmt.setInt(1, rs.getInt("bid"));
				ResultSet rs2 = pstmt.executeQuery();
				rs2.next();
				int blikeCnt = rs2.getInt(1);
				rs2.close();
				
				pstmt = conn.prepareStatement(B_UPDATE_BLIKECNT);
				pstmt.setInt(1, blikeCnt);
				pstmt.setInt(2, rs.getInt("bid"));
				pstmt.executeUpdate();

				data.setBlike(blikeCnt);
				data.setBdate(rs.getString("bdate"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}	

	// 닉네임검색, 내가 쓴 글 목록 불러오기
	public ArrayList<BoardVO> getBoardList_nick(BoardVO vo,int cnt) {
		conn = JDBCUtil.connect();
		ArrayList<BoardVO> datas = new ArrayList<BoardVO>();
		try {
			if(vo.getSearchCondition()==null) {
				pstmt = conn.prepareStatement(B_SELECTALL_MNICK_NEW);
			}else {
				if(vo.getSearchCondition().equals("new")) {
					pstmt = conn.prepareStatement(B_SELECTALL_MNICK_NEW);
				} else if(vo.getSearchCondition().equals("hits")) {
					pstmt = conn.prepareStatement(B_SELECTALL_MNICK_HITS);
				} else if(vo.getSearchCondition().equals("like")) {
					pstmt = conn.prepareStatement(B_SELECTALL_MNICK_LIKE);
				}
			}
			pstmt.setString(1, vo.getMnick());
			pstmt.setInt(2, cnt*10-9);
			pstmt.setInt(3, cnt*10);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO data = new BoardVO();
				data.setBid(rs.getInt("bid"));
				data.setMid(rs.getString("mid"));
				data.setMnick(rs.getString("mnick"));
				data.setBtitle(rs.getString("btitle"));
				data.setBimg(rs.getString("bimg"));
				data.setBcontent(rs.getString("bcontent"));
				data.setBhits(rs.getInt("bhits"));

				pstmt = conn.prepareStatement(L_SELECTCNT_BLIKE);
				pstmt.setInt(1, rs.getInt("bid"));
				ResultSet rs2 = pstmt.executeQuery();
				rs2.next();
				int blikeCnt = rs2.getInt(1);
				rs2.close();
				
				pstmt = conn.prepareStatement(B_UPDATE_BLIKECNT);
				pstmt.setInt(1, blikeCnt);
				pstmt.setInt(2, rs.getInt("bid"));
				pstmt.executeUpdate();
				
				data.setBlike(blikeCnt);
				data.setBdate(rs.getString("bdate"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}	

	// 글 수정
	public void updateBoard(BoardVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(B_UPDATE);
			pstmt.setString(1, vo.getBtitle());
			pstmt.setString(2, vo.getBimg());
			pstmt.setString(3, vo.getBcontent());
			pstmt.setInt(4, vo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

	// 조회수 +1
	public void updateBoard_hitsUp(BoardVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(B_UPDATE_BHITS);
			pstmt.setInt(1, vo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
 
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(B_DELETE);
			pstmt.setInt(1, vo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	
}
