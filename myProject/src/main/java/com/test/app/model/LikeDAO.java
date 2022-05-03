package com.test.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.test.app.common.JDBCUtil;

@Repository("likeDAO")
public class LikeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	private final String L_INSERT = "insert into blike (bid,mid) values(?,?)"; // 좋아요 등록
	private final String L_SELECTONE = "select * from blike where bid=? and mid=?"; // 좋아요 상태 확인용
	private final String L_DELETE = "delete blike where bid=? and mid=?"; // 좋아요 해제

	// 좋아요 등록
	public void insertLike(LikeVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(L_INSERT);
			pstmt.setInt(1, vo.getBid());
			pstmt.setString(2, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

	// 좋아요 상태 확인용
	public LikeVO getLike(LikeVO vo) {
		conn = JDBCUtil.connect();
		LikeVO data = null;
		try {
			pstmt = conn.prepareStatement(L_SELECTONE);
			pstmt.setInt(1, vo.getBid());
			pstmt.setString(2, vo.getMid());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new LikeVO();
				data.setBid(rs.getInt("bid"));
				data.setMid(rs.getString("mid"));
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

	// 좋아요 해제
	public void deleteLike(LikeVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(L_DELETE);
			pstmt.setInt(1, vo.getBid());
			pstmt.setString(2, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
}
