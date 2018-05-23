package com.transportation.app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.transportation.app.security.AuthenticationFiltter;
import com.transportation.app.security.AuthenticationSuccessHandler;
import com.transportation.app.security.CustomWebAuthenticationDetailsSource;
import com.transportation.app.security.FaceBookAuthenticationProvider;
import com.transportation.app.security.GoogleAuthenticationProvider;
import com.transportation.app.security.UserNameAndPasswordAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class TransportaionAppSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserNameAndPasswordAuthenticationProvider userNameAndPasswordAuthenticationProvider;
	
	@Autowired
	private FaceBookAuthenticationProvider faceBookAuthenticationProvider;
	
	@Autowired
	private GoogleAuthenticationProvider googleAuthenticationProvider;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(authenticationFiltter(), BasicAuthenticationFilter.class)
			.authorizeRequests().anyRequest().authenticated()
			.and()
				.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler)
					.authenticationEntryPoint(authenticationEntryPoint)
			.and()
				.formLogin().successHandler(authenticationSuccessHandler).authenticationDetailsSource(customWebAuthenticationDetailsSource)
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManager());
		super.configure(auth);
	}
	
	 @Bean
	  public AuthenticationManager authenticationManager() {
	    return new ProviderManager(Arrays.asList(userNameAndPasswordAuthenticationProvider, faceBookAuthenticationProvider, googleAuthenticationProvider));
	  }
	 
	 @Bean
	 public AuthenticationFiltter authenticationFiltter() {
		 return new AuthenticationFiltter(authenticationManager());
	 }
	
}
