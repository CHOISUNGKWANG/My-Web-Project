package com.test.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.test.app.common.JDBCUtil;

@Repository("memberDAO")
public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	private final String M_INSERT = "insert into member values(?,?,?,?)"; // 회원가입
	private final String M_SELECTONE_IDPW = "select * from member where mid=? and mpw=?"; // 로그인
	private final String M_SELECTONE_ID = "select * from member where mid=?"; // 아이디 중복검사, 내 정보 불러오기 
	private final String M_SELECTONE_NICK = "select * from member where mnick=?"; // 닉네임 중복검사
	private final String M_UPDATE_PW = "update member set mpw=? where mid=?"; // 비밀번호 수정, 찾기
	private final String M_DELETE = "delete member where mid=?"; // 회원탈퇴
	
	// 회원가입
	public void insertMember(MemberVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(M_INSERT);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			pstmt.setString(3, vo.getMnick());
			pstmt.setString(4, vo.getMemail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	
	// 로그인
	public MemberVO getMember_login(MemberVO vo) {
		conn = JDBCUtil.connect();
		MemberVO data = null;
		try {
			pstmt = conn.prepareStatement(M_SELECTONE_IDPW);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new MemberVO();
				data.setMid(rs.getString("mid"));
				data.setMpw(rs.getString("mpw"));
				data.setMnick(rs.getString("mnick"));
				data.setMemail(rs.getString("memail"));
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
	
	// 아이디 중복검사, 내 정보 불러오기
	public MemberVO getMember_idCheck(MemberVO vo) {
		conn = JDBCUtil.connect();
		MemberVO data=null;
		try {
			pstmt = conn.prepareStatement(M_SELECTONE_ID);
			pstmt.setString(1, vo.getMid());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new MemberVO();
				data.setMid(rs.getString("mid"));
				data.setMpw(rs.getString("mpw"));
				data.setMnick(rs.getString("mnick"));
				data.setMemail(rs.getString("memail"));
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
	
	// 닉네임 중복검사
	public MemberVO getMember_nickCheck(MemberVO vo) {
		conn = JDBCUtil.connect();
		MemberVO data = null;
		try {
			pstmt = conn.prepareStatement(M_SELECTONE_NICK);
			pstmt.setString(1, vo.getMnick());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new MemberVO();
				data.setMid(rs.getString("mid"));
				data.setMpw(rs.getString("mpw"));
				data.setMnick(rs.getString("mnick"));
				data.setMemail(rs.getString("memail"));
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
	
	// 비밀번호 수정, 찾기
	public void updateMember_pw(MemberVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(M_UPDATE_PW);
			pstmt.setString(1, vo.getMpw());
			pstmt.setString(2, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	
	// 회원탈퇴
	public void deleteMember(MemberVO vo) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(M_DELETE);
			pstmt.setString(1, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	
}
