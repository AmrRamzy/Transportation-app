package com.transportation.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("in AuthenticationEntryPoint");
//		response.setContentType("Text");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		// Object to JSON in String
		String msg = "unAuthorized User Please login";
		response.getOutputStream().println(msg);
	}

}
