package com.test.app.service;

import com.test.app.model.MemberVO;

public interface MemberService {
	public void insertMember(MemberVO vo);
	public MemberVO getMember_login(MemberVO vo);
	public MemberVO getMember_idCheck(MemberVO vo);
	public MemberVO getMember_nickCheck(MemberVO vo);
	public void updateMember_pw(MemberVO vo);
	public void deleteMember(MemberVO vo);
}
