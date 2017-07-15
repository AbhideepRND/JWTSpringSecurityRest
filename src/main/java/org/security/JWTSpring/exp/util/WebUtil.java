package org.security.JWTSpring.exp.util;

import javax.servlet.http.HttpServletRequest;

import org.security.JWTSpring.exp.Constraint;

public class WebUtil {

	/**
	 * To validate the request header
	 * @param request - HttpServletRequest
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
}
