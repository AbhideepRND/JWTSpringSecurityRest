package org.security.JWTSpring.exp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.security.JWTSpring.exp.dto.LoginRequest;
import org.security.JWTSpring.exp.exception.RestAuthenticationException;
import org.security.JWTSpring.exp.util.WebUtil;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;


public class RestRequestFilter extends AbstractAuthenticationProcessingFilter{

	final private AuthenticationFailureHandler failureHandler;
	final private AuthenticationSuccessHandler successHandler;
	final private ObjectMapper objectMapper;
	
	public RestRequestFilter(String defaultFilterProcessesUrl, AuthenticationFailureHandler failureHandler, 
			AuthenticationSuccessHandler successHandler, ObjectMapper objectMapper) {
		super(defaultFilterProcessesUrl);
		this.failureHandler = failureHandler;
		this.successHandler = successHandler;
		this.objectMapper = objectMapper;
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		if(!HttpMethod.POST.toString().equals(request.getMethod()) || !WebUtil.validateHeader(request)){
			throw new RestAuthenticationException("Not a valid request");
		}
		
		LoginRequest readValue = this.objectMapper.readValue(request.getReader(),LoginRequest.class);
		
		if(readValue.getUsername() == null && readValue.getPassword() == null){
			throw new RestAuthenticationException("Username and password is invalid");
		}
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(readValue.getUsername(), readValue.getPassword());
		
		return this.getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		this.successHandler.onAuthenticationSuccess(request, response, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		this.failureHandler.onAuthenticationFailure(request, response, failed);
	}

	
}
