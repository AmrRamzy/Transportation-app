package com.transportation.app.security;

import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AppGoogleAuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken passwordAuthenticationToken=null;
		System.out.println("in GoogleAuthenticationProvider");
		String authToken=((AppCustomWebAuthenticationDetails)authentication.getDetails()).getOuthToken();
		if(authToken!=null && "google".equals(authToken)) {
			passwordAuthenticationToken=new UsernamePasswordAuthenticationToken("admin", "password", new ArrayList<>());
			
		}
		return passwordAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
