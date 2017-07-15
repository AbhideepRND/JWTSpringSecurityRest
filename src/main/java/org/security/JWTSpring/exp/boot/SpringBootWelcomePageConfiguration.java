package org.security.JWTSpring.exp.boot;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @author Abhideep Bakshi
 *
 */
//@Configuration
public class SpringBootWelcomePageConfiguration extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

	/**
	 * redirect a user to the welcome page when he visits tha app without a
	 * destination url.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

}