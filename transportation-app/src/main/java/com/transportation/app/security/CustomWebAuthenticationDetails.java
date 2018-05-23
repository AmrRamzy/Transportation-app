package com.transportation.app.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails{

	private String outhToken;
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		outhToken=request.getParameter("outhToken");
	}
	public String getOuthToken() {
		return outhToken;
	}
	
	

}
