package com.ict.testapp.member.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.testapp.member.model.service.MemberService;
import com.ict.testapp.member.model.vo.Member;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	//회원 서비스 관련 페이지 이동 처리용 -------------------------------
	
	@RequestMapping("enrollPage.do")
	public String moveEnrollPage() {
		return "member/enrollPage";
	}
	
	
	//회원 서비스 요청 처리용------------------------------------------
	
	//회원가입시 작성 아이디 중복 체크 확인용
	@RequestMapping(value = "idCheck.do",method = RequestMethod.POST)
	public void idDuplicationCheck(@RequestParam("id")String id,HttpServletResponse response) throws IOException {
		logger.info("idCheck.do :"+ id);
		
		int idCount = memberService.selectCheckid(id);
		String returnValue = null;
		if (idCount==0) {
			returnValue = "ok";
		}else {
			returnValue="dup";
		}
		//ajax 통신은 입출력 스트림을 이용함
		//response 를이용해서 출력 스트림을 만들고 값 전송하기
		response.setContentType("text/html; charset=utf-8");//데이터타입 정해주기
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}
	
	//회원가입 처리(패스워드 암호화 처리)
	@RequestMapping(value = "anroll.do",method = RequestMethod.POST)
	public String enrollMemberMethod(Member member,Model model) {
		logger.info("enrool.do :"+member);//넘어오는 파라미터값 확인
		
		//패스워드 암호화 처리
		member.setPw(bcryptPasswordEncoder.encode(member.getPw()));
		logger.info("pw encode:"+member.getPw()+","+member.getPw().length());//암호화된 패스워드 값과 그 길이 확인 
		
		//서비스로 전송하고 결과를 받기
		int result = memberService.InsertMember(member);
		
		//받은 결과로 성공/실패 페이지 내보내기
		if (result>0) {
			return "common/main";
		}else {
			model.addAttribute("message","회원가입 실패");
			return "common/error";
		}
	}
	
	//로그인 요청 처리(패스워드 암호화 처리)
	@RequestMapping(value = "login.do",method = RequestMethod.POST)
	public String loginCheakMethod(@RequestParam("id")String id,@RequestParam("pw")String pw,HttpSession session,Model model) {
		logger.info("login.do :"+id+","+pw);
		
		//패스워드 암호화 처리후 Member객체에 기록 저장
		Member member = new Member();
		member.setId(id);
		member.setPw(bcryptPasswordEncoder.encode(pw));
		logger.info("암호화pw:"+member.getPw());
		Member loginMember = memberService.loginCheck(id);
		//전송은 패스워드 (일반글자)와 조회해온 패스워드(암호화글자) 비교시
		//matchs()사용한다
		logger.info("pw비교:"+bcryptPasswordEncoder.matches(pw, loginMember.getPw()));
		
		 if (loginMember != null) {
			 if (bcryptPasswordEncoder.matches(pw, loginMember.getPw())) {
				 session.setAttribute("loginMember", loginMember);
				 return "common/main";
			}else {
				model.addAttribute("message","로그인 실패! 패스워드가 일치하지 않습니다.");
				return "common/error";
				
			}
		}else {
			model.addAttribute("message","로그인 실패! 일치하는 아이디가 없습니다.");
			return "common/error";
		}
	}
	//로그아웃 처리
	@RequestMapping("logout.do")
	public String logoutMethod(HttpSession session) {
		session.invalidate();
		return "redirect:main.do";
	}
	
	
	
}
