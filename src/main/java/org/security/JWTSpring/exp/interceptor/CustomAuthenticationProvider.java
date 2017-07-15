package org.security.JWTSpring.exp.interceptor;

import java.util.List;

import org.security.JWTSpring.exp.bo.UserCredential;
import org.security.JWTSpring.exp.dao.impl.GenericDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author Abhideep Bakshi
 *
 *	CustomAuthenticationProvider - Custom authentication using database.
 */
@Component(value = "CustomAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	@Qualifier(value = "UserCredentialDaoImpl")
	private GenericDaoImpl<UserCredential> serachCredentialDao;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		final String username = (String) auth.getPrincipal();
		final String password = (String) auth.getCredentials();

		final UserCredential userCredentialBO = new UserCredential();
		userCredentialBO.setUsername(username);

		List<UserCredential> search = serachCredentialDao.search(userCredentialBO);
		if (search.size() <= 0) {
			throw new BadCredentialsException("Username not found.");
		}
		UserCredential userCredential = search.get(0);
		if (userCredential == null) {
			throw new BadCredentialsException("Username not found.");
		}

		if (!password.equals(userCredential.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}

		return new UsernamePasswordAuthenticationToken(userCredentialBO, password, userCredential.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return UsernamePasswordAuthenticationToken.class.equals(arg0);
		// return true;
	}

}
