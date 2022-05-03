package com.test.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.model.MemberDAO;
import com.test.app.model.MemberVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Autowired 
	private MemberDAO memberDAO;
	
	@Override
	public void insertMember(MemberVO vo) {
		memberDAO.insertMember(vo);
	}

	@Override
	public MemberVO getMember_login(MemberVO vo) {
		return memberDAO.getMember_login(vo);
	}

	@Override
	public MemberVO getMember_idCheck(MemberVO vo) {
		return memberDAO.getMember_idCheck(vo);
	}

	@Override
	public MemberVO getMember_nickCheck(MemberVO vo) {
		return memberDAO.getMember_nickCheck(vo);
	}

	@Override
	public void updateMember_pw(MemberVO vo) {
		memberDAO.updateMember_pw(vo);
	}

	@Override
	public void deleteMember(MemberVO vo) {
		memberDAO.deleteMember(vo);
	}

}
