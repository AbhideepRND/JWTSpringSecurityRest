package org.security.JWTSpring.exp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.security.JWTSpring.exp.bo.Role;
import org.security.JWTSpring.exp.bo.UserCredential;
import org.security.JWTSpring.exp.enums.NativeSqlQueryName;
import org.security.JWTSpring.exp.enums.SqlColumnConstaints;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Abhideep Bakshi
 *
 *      UserCredentialDaoImpl- CRUD operation over user details.
 *
 */
@Repository(value = "UserCredentialDaoImpl")
public class UserCredentialDaoImpl extends GenericDaoImpl<UserCredential> {

	@Override
	public List<UserCredential> search(UserCredential userData) {

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(userData);

		return search(NativeSqlQueryName.SelectUserCredential, namedParameters, getRowMapper());
	}

	private RowMapper<UserCredential> getRowMapper() {
		return new RowMapper<UserCredential>() {

			@Override
			public UserCredential mapRow(ResultSet result, int arg1) throws SQLException {
				final UserCredential userCredential = new UserCredential();
				userCredential.setUsername(result.getString(SqlColumnConstaints.Username.getColumnName()));
				userCredential.setPassword(result.getString(SqlColumnConstaints.Password.getColumnName()));
				final String[] roles = result.getString(SqlColumnConstaints.Role.getColumnName()).split(":");
				final List<Role> arrayList = new ArrayList<Role>(roles.length);
				for (String role : roles) {
					arrayList.add(new Role(role));
				}
				userCredential.setAuthorities(arrayList);
				return userCredential;
			}

		};
	}

}
