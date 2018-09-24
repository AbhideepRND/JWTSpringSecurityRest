package org.security.JWTSpring.exp.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.security.JWTSpring.exp.Constraint;
import org.security.JWTSpring.exp.util.WebUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	private AuthenticationFailureHandler failureHandler;
	private AuthenticationSuccessHandler successHandler;
	private ObjectMapper objectMapper;

	public JwtTokenAuthenticationProcessingFilter(RequestMatcher matcher, AuthenticationFailureHandler failureHandler,
			AuthenticationSuccessHandler successHandler, ObjectMapper objectMapper) {
		super(matcher);
		this.failureHandler = failureHandler;
		this.successHandler = successHandler;
		this.objectMapper = objectMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		final String requestToken = WebUtil.tokenExtract(request.getHeader(Constraint.HTTP_HEADER_TOKEN));
		RawAccessJwtToken token = new RawAccessJwtToken(requestToken);
		return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request, response, failed);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
	}

}
