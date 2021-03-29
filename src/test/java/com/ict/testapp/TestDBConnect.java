package com.ict.testapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//어노테이션 사용 : spring-test 가 제공함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/resources/root-context.xml"})
public class TestDBConnect {
	//logger : 로그출력을위한 log4j 객체
	private static final Logger logger = LoggerFactory.getLogger(TestDBConnect.class);
	
	@Autowired
	private ApplicationContext wac;
	
	@Before
	public void beforeClass() {
		logger.info("---테스트 시작---");
	}
	
	@After
	public void afterclass() {
		logger.info("---테스트 종료---");
	}
	
	@Test
	public void DBTest() {
		// root-context 의 설정 내요에 따라 sqlSession의 null여부
		//컨테이너에서 getBean()을 이용해서 생성된 객체 정보를 받아옴 
		SqlSessionTemplate sqlSession = wac.getBean("sqlSessionTemplate",SqlSessionTemplate.class);
		logger.info("sqlSessionTemplate :"+sqlSession.toString());
	}
}
