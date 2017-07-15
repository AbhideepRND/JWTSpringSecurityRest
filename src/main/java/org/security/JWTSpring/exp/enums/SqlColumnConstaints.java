package org.security.JWTSpring.exp.enums;

/**
 * @author Abhideep Bakshi
 *
 *	SqlColumnConstaints - Sql column names.
 */
public enum SqlColumnConstaints {

	Username("username"), Password("password"), Role("roles");

	private String columnName;

	private SqlColumnConstaints(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

}
