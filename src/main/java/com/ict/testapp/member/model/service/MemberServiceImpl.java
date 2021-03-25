package com.ict.testapp.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.testapp.member.model.dao.MemberDao;
import com.ict.testapp.member.model.vo.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member loginCheck(String id) {
		return memberDao.selectLogin(id);
	}

	@Override
	public int InsertMember(Member member) {
		
		return memberDao.insertMember(member);
	}

	@Override
	public int selectCheckid(String id) {
		
		return memberDao.selectCheckid(id);
	}

}
