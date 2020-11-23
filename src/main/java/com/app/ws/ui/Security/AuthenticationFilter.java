package com.app.ws.ui.Security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.ws.SpringApplicationContext;
import com.app.ws.ui.Service.UserService;
import com.app.ws.ui.shared.dto.UserDto;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.ws.ui.model.request.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private  final AuthenticationManager authenticationMangaer;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationMangaer =authenticationManager;
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			UserLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
			
			 return authenticationMangaer.authenticate(new UsernamePasswordAuthenticationToken(
					 creds.getEmail(),
					 creds.getPassword(),
					 new ArrayList<>()
					 )
					 );
			
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
		

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String username = ((User) authResult.getPrincipal()).getUsername();
		
		
		String token = Jwts.builder().setSubject(username).
				setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME)).
				signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).
				compact();

		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		UserDto  userDto = userService.getUserByEmail(username);
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("UserId", userDto.getUserId());
		
		
	}
	
	
	
	

}
