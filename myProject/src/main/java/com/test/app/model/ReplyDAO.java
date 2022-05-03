package com.test.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.test.app.common.JDBCUtil;

// reply + reply2
@Repository("replyDAO")
public class ReplyDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	// reply2
	private final String R2_INSERT = "insert into reply2 (rrid,rid,mid,mnick,rrcontent) values((select nvl(max(rrid),0)+1 from reply2),?,?,?,?)"; // 대댓글 등록
	private final String R2_SELECTALL_RID = "select * from reply2 where rid=? order by rrid asc"; // 대댓글 등록순으로 불러오기
	private final String R2_DELETE = "delete reply2 where rrid=?"; // 대댓글 삭제

	// reply
	private final String R_INSERT = "insert into reply (rid,bid,mid,mnick,rcontent) values((select nvl(max(rid),0)+1 from reply),?,?,?,?)"; // 댓글 등록
	private final String RS_SELECTALL_BID = "select * from reply where bid=? order by rid asc"; // 댓글 등록순으로 불러오기
	private final String R_DELETE = "delete reply where rid=?"; // 댓글 삭제

	// 대댓글 등록
	public void insertReply2(Reply2VO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(R2_INSERT);
			pstmt.setInt(1, vo.getRid());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getMnick());
			pstmt.setString(4, vo.getRrcontent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

	// 대댓글 등록순으로 불러오기
	public ArrayList<Reply2VO> getReply2List(Reply2VO vo) {
		conn = JDBCUtil.connect();
		ArrayList<Reply2VO> datas = new ArrayList<Reply2VO>();
		try {
			pstmt = conn.prepareStatement(R2_SELECTALL_RID);
			pstmt.setInt(1, vo.getRid());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply2VO data = new Reply2VO();
				data.setRrid(rs.getInt("rrid"));
				data.setRid(rs.getInt("rid"));
				data.setMid(rs.getString("mid"));
				data.setMnick(rs.getString("mnick"));
				data.setRrcontent(rs.getString("rrcontent"));
				data.setRrdate(rs.getString("rrdate"));
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

	// 대댓글 삭제
	public void deleteReply2(Reply2VO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(R2_DELETE);
			pstmt.setInt(1, vo.getRrid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

	// 댓글 등록
	public void insertReply(ReplyVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(R_INSERT);
			pstmt.setInt(1, vo.getBid());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getMnick());
			pstmt.setString(4, vo.getRcontent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

	// 댓글+대댓글 등록순으로 불러오기
	public ArrayList<ReplySet> getReplyList(ReplyVO vo) {
		conn = JDBCUtil.connect();
		ArrayList<ReplySet> datas = new ArrayList<ReplySet>();
		try {
			pstmt = conn.prepareStatement(RS_SELECTALL_BID);
			pstmt.setInt(1, vo.getBid());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplySet rps = new ReplySet();

				ReplyVO replyVO = new ReplyVO();
				replyVO.setRid(rs.getInt("rid"));
				replyVO.setBid(rs.getInt("bid"));
				replyVO.setMid(rs.getString("mid"));
				replyVO.setMnick(rs.getString("mnick"));
				replyVO.setRcontent(rs.getString("rcontent"));
				replyVO.setRdate(rs.getString("rdate"));
				rps.setReplyVO(replyVO);

				ArrayList<Reply2VO> reply2List = new ArrayList<Reply2VO>();
				pstmt=conn.prepareStatement(R2_SELECTALL_RID);
				pstmt.setInt(1, rs.getInt("rid"));
				ResultSet rs2 = pstmt.executeQuery();
				while(rs2.next()) {
					Reply2VO reply2VO = new Reply2VO();
					reply2VO.setRrid(rs2.getInt("rrid"));
					reply2VO.setRid(rs2.getInt("rid"));
					reply2VO.setMid(rs2.getString("mid"));
					reply2VO.setMnick(rs2.getString("mnick"));
					reply2VO.setRrcontent(rs2.getString("rrcontent"));
					reply2VO.setRrdate(rs2.getString("rrdate"));
					reply2List.add(reply2VO);
				}
				rs2.close();
				rps.setReply2List(reply2List);

				datas.add(rps);
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

	// 댓글 삭제
	public void deleteReply(ReplyVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(R_DELETE);
			pstmt.setInt(1, vo.getRid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	
}
