package com.ict.testapp.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.testapp.member.model.vo.Member;

@Repository("memberDao")
public class MemberDao {
	@Autowired
	//스프링 마이바티스 연동 객체 사용
	private SqlSessionTemplate sqlsession;

	public int selectCheckid(String id) {
		
		return sqlsession.selectOne("memberMapper.selectCheackid",id);
	}
	
	public int insertMember(Member member) {
		return sqlsession.insert("memberMapper.insertMember", member);
	}
	public Member selectLogin(String id) {
		return sqlsession.selectOne("memberMapper.selectMember",id);
	}
	
}
