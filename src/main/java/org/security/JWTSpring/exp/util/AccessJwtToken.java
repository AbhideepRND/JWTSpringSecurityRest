package org.security.JWTSpring.exp.util;

import io.jsonwebtoken.Claims;
import com.fasterxml.jackson.annotation.JsonIgnore;

public final class AccessJwtToken {
	private final String rawToken;
	@JsonIgnore
	private Claims claims;

	protected AccessJwtToken(final String token, Claims claims) {
		this.rawToken = token;
		this.claims = claims;
	}

	public String getToken() {
		return this.rawToken;
	}

	public Claims getClaims() {
		return claims;
	}
}
