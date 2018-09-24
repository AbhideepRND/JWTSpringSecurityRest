package org.security.JWTSpring.exp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;
import org.security.JWTSpring.exp.bo.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JWTTokenGenerator {
	@Autowired
	@Qualifier(value = "tokenProperties")
	private Properties tokenProperties;

	public AccessJwtToken generateToken(UserCredential userContext) {
		if (StringUtils.isEmpty(userContext.getUsername())) {
			throw new IllegalArgumentException("Cannot create JWT Token without username");
		}
		if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) {
			throw new IllegalArgumentException("User doesn't have any privileges");
		}
		Claims claims = Jwts.claims().setSubject(userContext.getUsername());
		claims.put("scopes",
				userContext.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));
		LocalDateTime currentTime = LocalDateTime.now();
		String token = Jwts.builder().setClaims(claims).setIssuer(tokenProperties.getProperty("token-issuer"))
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(
						Date.from(currentTime.plusMinutes(Long.valueOf(tokenProperties.getProperty("token-expire")))
								.atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS512, tokenProperties.getProperty("token-key")).compact();
		return new AccessJwtToken(token, claims);

	}

}
