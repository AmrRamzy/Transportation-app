package com.transportation.app.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.transportation.app.data.UserData;
import com.transportation.app.repository.UserDataRepository;

@Service
public class UserNameAndPasswordAuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider{

	@Autowired
	private UserDataRepository userDataRepo;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken passwordAuthenticationToken=null;
		System.out.println("in AuthenticationProvider");
		String authToken=((CustomWebAuthenticationDetails)authentication.getDetails()).getOuthToken();
		if(authToken!=null && "app".equals(authToken)) {
			UserData userData=userDataRepo.findByEmail((String)authentication.getPrincipal());
//			Iterable<UserData> userDataList = userDataRepo.findAll();
//			UserData userData1=new UserData();
////			userData1.setId(10l);
//			userData1.setEmail("admin");
//			userData1.setUsername("admin");
//			userData1.setPassword("admin");
//			userDataRepo.save(userData1);
			if(userData!=null) {
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
