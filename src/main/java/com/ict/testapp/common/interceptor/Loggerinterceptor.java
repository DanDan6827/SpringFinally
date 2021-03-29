package com.ict.testapp.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터는 디스패처 서블릿과 컨트롤러 사이에서 구동되는 클래스
//log4j.xml 에서 debug 레벨로 로그를 남겨지도록 설정 해둠
//로깅 이벤트로 발생되는 시간을 절약할 수 있음

public class Loggerinterceptor extends HandlerInterceptorAdapter {
	//org.slf4j.Logger 타입의 logger 를 생성함
	//LoggerFactory.getLogger() 메소드의 매게변수로 현재 클래스 객체를 전달함
	private Logger logger = LoggerFactory.getLogger(Loggerinterceptor.class);
	
	//요청 컨트롤러 구동 직전에 자동 실행되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {
		//debug 레벨일때 로그처리 하도록 함
		if (logger.isDebugEnabled()) {
			logger.debug("=================== START====================");
			logger.debug(request.getRequestURI());
			logger.debug("=============================================");
		}
		return super.preHandle(request, response, handler);
		//항상 true 리턴함
		
	}
	
	//요청 컨트롤러 가 실행되고난 다음에 자동 실행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response , Object handler,ModelAndView modelandView) throws Exception {
		super.postHandle(request, response, handler, modelandView);
		if (logger.isDebugEnabled()) {
			logger.debug("------------------------------------");
		}
	}
	//뷰리졸버가 뷰를 찾아서 내보내고 난다음에 자동 실행되는 메소드
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,
			Object handler,Exception ex) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("============== END ==============");
		}
		super.afterCompletion(request, response, handler, ex);
	}
}
