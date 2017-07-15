package org.security.JWTSpring.exp.bo;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Abhideep Bakshi
 *
 *	Roles - Contain the roles of users.
 */
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8110309774539117055L;
	private String roleName;

	public Role(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		return this.roleName;
	}
}
