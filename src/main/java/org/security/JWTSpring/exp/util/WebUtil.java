package org.security.JWTSpring.exp.util;

import javax.servlet.http.HttpServletRequest;

import org.security.JWTSpring.exp.Constraint;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.util.StringUtils;

public class WebUtil {

	/**
	 * To validate the request header
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @return - boolean - true for valid and false for invalid
	 */
	public static boolean validateHeader(HttpServletRequest request) {
		final boolean isValidHeader;
		if (request.getHeader(Constraint.HTTP_HEADER_CONSUMER) != null
				|| request.getHeader(Constraint.HTTP_HEADER_MACHINE) != null
				|| request.getHeader(Constraint.HTTP_HEADER_USER_REF) != null) {
			isValidHeader = true;
		} else {
			isValidHeader = false;
		}
		return isValidHeader;
	}

	public static String tokenExtract(final String requestToken) {
		final String HEADER_PREFIX = "Bearer:";
		if (StringUtils.isEmpty(requestToken)) {
			throw new AuthenticationServiceException("Authorization header cannot be blank!");
		}

		if (requestToken.length() < HEADER_PREFIX.length()) {
			throw new AuthenticationServiceException("Invalid authorization header size.");
		}

		return requestToken.substring(HEADER_PREFIX.length(), requestToken.length());
	}
}
