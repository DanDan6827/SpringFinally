package com.ict.testapp.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//SpringJUnit4ClassRunner 는 스프링 테스트를 위한 클래스임
@RunWith(SpringJUnit4ClassRunner.class)

//controller 및 web환경에 사용되는 bean 들을 자동 생성하여 등록
@WebAppConfiguration 

//자동으로 만들어줄 애플리케이션 컨택스트의 설정 파일 지정
@ContextConfiguration(locations= {"file:src/main/resources/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/spring-security.xml"})
public class TestEnrollMemberMethod {
	//logger : 로그출력을위한 log4j 객체
		private static final Logger logger = LoggerFactory.getLogger(TestEnrollMemberMethod.class);
		
		
		
	//테스트를위한 클래스 필드 선언
	//현재 실행중인 애플리케이션의 구성을 제공하는 인터페이스임	
	
	@Autowired
	private WebApplicationContext wac;
	//client 요청 내용을 Controller 가 받아서 처리하는 것과 같은 테스트용 클래스임
	private MockMvc mockMvc; // 브라우저에서 실행하는 클라이언트를 대신함
	
	
	//JUnit 테스트 진행 전에 먼저 실행할 것을 지정
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info("setup() 메소드 호출됨");
	}
	
	//테스트용 메소드 지정
	@Test
	public void testMemberEnroll() {
		logger.info("회원가입 테스트 시작");
		
		//mockMvc 를 이용해서 메소드 요청 url과 필요한 데이터 가 담긴 가상의
		//요청을 전달 (perform() 메소드 사용함)
		try {
			mockMvc.perform(post("/anroll.do").param("id","test01")
											.param("pw", "test01")
											.param("name", "테스터")
											.param("age", "80")
											.param("addr", "테스터 주소"))
			
			.andDo(print())//처리된 내용을 출력
			.andExpect(status().isOk());//응답상태값이 에러가 없는 정상 status 가 200인것을 검증함
			logger.info("회원가입 테스트 종료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
