package com.transportation.app.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.transportation.app.data.AppUserData;
import com.transportation.app.repository.AppUserDataRepository;

@Service
public class AppUserNameAndPasswordAuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider{

	@Autowired
	private AppUserDataRepository userDataRepo;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken passwordAuthenticationToken=null;
		System.out.println("in UserNameAndPasswordAuthenticationProvider");
		String authToken=((AppCustomWebAuthenticationDetails)authentication.getDetails()).getOuthToken();
		if(authToken!=null && "Username&Password".equals(authToken)) {
			String password=(String) authentication.getCredentials();
			AppUserData userData=userDataRepo.findByEmail((String)authentication.getPrincipal());

			if(userData!=null && !StringUtils.isEmpty(userData.getPassword()) && BCrypt.checkpw(password, userData.getPassword())) {
				passwordAuthenticationToken=new UsernamePasswordAuthenticationToken("admin", "password", new ArrayList<>());
			}
			
		}
		return passwordAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
