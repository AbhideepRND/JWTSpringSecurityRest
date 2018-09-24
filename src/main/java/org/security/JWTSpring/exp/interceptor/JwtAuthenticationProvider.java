package org.security.JWTSpring.exp.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.security.JWTSpring.exp.bo.Role;
import org.security.JWTSpring.exp.bo.UserCredential;
import org.security.JWTSpring.exp.security.JwtAuthenticationToken;
import org.security.JWTSpring.exp.security.RawAccessJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component(value = "JwtAuthenticationProvider")
public class JwtAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	@Qualifier(value = "tokenProperties")
	private Properties tokenProperties;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
		Jws<Claims> jwsClaims = rawAccessToken.parseClaims(tokenProperties.getProperty("token-key"));
		String subject = jwsClaims.getBody().getSubject();
		List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
		List<Role> authorities = scopes.stream().map(authority -> new Role(authority)).collect(Collectors.toList());

		UserCredential context = new UserCredential();
		context.setAuthorities(authorities);
		context.setUsername(subject);
		return new JwtAuthenticationToken(context, context.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return JwtAuthenticationToken.class.isAssignableFrom(arg0);
	}

}