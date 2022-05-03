package com.test.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.test.app.common.JDBCUtil;

@Repository("statsDAO")
public class StatsDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	private final String S_INSERT = "insert into stats (sposition,srank,schampsrc,schamp,stiersrc,swinrate,spickrate) values(?,?,?,?,?,?,?)"; // 크롤링 데이터 저장
	private final String S_SELECTALL_POSITION = "select * from stats where sposition=?"; // 포지션 별 데이터 불러오기
	private final String S_DELETE = "delete from stats"; // 크롤링 데이터 전체 삭제

	// 크롤링 데이터 저장
	public void insertStats(StatsVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(S_INSERT);
			pstmt.setString(1, vo.getSposition());
			pstmt.setString(2, vo.getSrank());
			pstmt.setString(3, vo.getSchampsrc());
			pstmt.setString(4, vo.getSchamp());
			pstmt.setString(5, vo.getStiersrc());
			pstmt.setString(6, vo.getSwinrate());
			pstmt.setString(7, vo.getSpickrate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}

	// 포지션 별 데이터 불러오기
	public ArrayList<StatsVO> getStatsList(StatsVO vo) {
		conn = JDBCUtil.connect();
		ArrayList<StatsVO> datas = new ArrayList<StatsVO>();
		try {
			pstmt = conn.prepareStatement(S_SELECTALL_POSITION);
			pstmt.setString(1, vo.getSposition());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				StatsVO data = new StatsVO();
				data.setSposition(rs.getString("sposition"));
				data.setSrank(rs.getString("srank"));
				data.setSchampsrc(rs.getString("schampsrc"));
				data.setSchamp(rs.getString("schamp"));
				data.setStiersrc(rs.getString("stiersrc"));
				data.setSwinrate(rs.getString("swinrate"));
				data.setSpickrate(rs.getString("spickrate"));
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

	// 크롤링 데이터 전체 삭제
	public void deleteStats(StatsVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(S_DELETE);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	
}
