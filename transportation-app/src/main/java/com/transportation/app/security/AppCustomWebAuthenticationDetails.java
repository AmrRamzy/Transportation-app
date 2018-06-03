package com.transportation.app.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class AppCustomWebAuthenticationDetails extends WebAuthenticationDetails{

	private String outhToken;
	public AppCustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		outhToken=request.getParameter("outhToken");
	}
	public String getOuthToken() {
		return outhToken;
	}
	
	

}
