package com.mycompany.webapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/*
 * SimpleUrlAuthenticationSuccessHandler
 *  사용자가 로그인 성공 후에 무조건 홈으로 이동 
 * 
 * SavedRequestAwareAuthenticationSuccessHandler
 *  로그인 성공 후에 사용자가 접근하고자 했던 페이지로 이동, 즉 기억하고 있다는 의미이다. 
 * 
 */
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// 로그인 성공했을 때 실행하고픈 코드를 작성 
		logger.info("실행");
		super.onAuthenticationSuccess(request, response, authentication);
		
	}
	
}
