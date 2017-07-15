package org.security.JWTSpring.exp.enums;

/**
 * @author Abhideep Bakshi
 *
 *	NativeSqlQueryName - Select the query from query.properties file.
 *	
 */
public enum NativeSqlQueryName {

	SelectUserCredential("SelectUserCredential");

	private String propertyName;

	private NativeSqlQueryName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}
}
