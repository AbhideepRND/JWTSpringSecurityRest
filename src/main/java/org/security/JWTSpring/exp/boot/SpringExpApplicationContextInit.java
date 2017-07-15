package org.security.JWTSpring.exp.boot;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Abhideep Bakshi
 *
 *	SpringExpApplicationContextInit - This class get loaded before the spring container get load.
 */
public class SpringExpApplicationContextInit implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext context) {
		ConfigurableEnvironment environment = context.getEnvironment();
		environment.addActiveProfile("database");
	}
}
