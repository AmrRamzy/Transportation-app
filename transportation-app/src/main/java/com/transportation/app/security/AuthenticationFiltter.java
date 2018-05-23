package com.transportation.app.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;


public class AuthenticationFiltter extends BasicAuthenticationFilter{

	public AuthenticationFiltter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url=request.getRequestURI();
		System.out.println("in AuthenticationFiltter");
		System.out.println(url);
		String authorization=request.getHeader("Authorization");
		if("Amr".equals(authorization)) {
			UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken("temp", "temp", new ArrayList<>());
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		chain.doFilter(request, response);
	}

	
}
