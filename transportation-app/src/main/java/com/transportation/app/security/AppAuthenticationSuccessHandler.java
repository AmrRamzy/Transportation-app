package com.transportation.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Component
public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		StringBuilder tokenStringBuilder = new StringBuilder(AppSecurityConstants.BEARER);

		tokenStringBuilder.append(AppJwtTokenUtils.generateToken(authentication));
		response.addHeader(AppSecurityConstants.AUTHORIZATION_HEADER, tokenStringBuilder.toString());
	}

}
