package com.ict.testapp.member.model.service;

import com.ict.testapp.member.model.vo.Member;

public interface MemberService {
	 Member loginCheck(String id);
	 int InsertMember(Member member);
	int selectCheckid(String id);
}
