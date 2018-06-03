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

import com.transportation.app.security.AppAuthenticationFiltter;
import com.transportation.app.security.AppAuthenticationSuccessHandler;
import com.transportation.app.security.AppCustomWebAuthenticationDetailsSource;
import com.transportation.app.security.AppFaceBookAuthenticationProvider;
import com.transportation.app.security.AppGoogleAuthenticationProvider;
import com.transportation.app.security.AppUserNameAndPasswordAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private AppUserNameAndPasswordAuthenticationProvider userNameAndPasswordAuthenticationProvider;
	
	@Autowired
	private AppFaceBookAuthenticationProvider faceBookAuthenticationProvider;
	
	@Autowired
	private AppGoogleAuthenticationProvider googleAuthenticationProvider;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private AppCustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource;
	
	@Autowired
	private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(authenticationFiltter(), BasicAuthenticationFilter.class)
			.authorizeRequests().anyRequest().authenticated()
			.and()
				.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler)
					.authenticationEntryPoint(authenticationEntryPoint)
			.and()
				.formLogin().successHandler(appAuthenticationSuccessHandler).authenticationDetailsSource(customWebAuthenticationDetailsSource)
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
	 public AppAuthenticationFiltter authenticationFiltter() {
		 return new AppAuthenticationFiltter(authenticationManager());
	 }
	
}
