package org.security.JWTSpring.exp.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * @author Abhideep Bakshi
 *
 *	SpringIntegrationBootConfig - Spring boot runner file
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
// @EnableAutoConfiguration
@Import(ApplicationContext.class)
public class SpringIntegrationBootConfig extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringIntegrationBootConfig.class);
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringIntegrationBootConfig.class);
		springApplication.addInitializers(new SpringExpApplicationContextInit());
		springApplication.run(args);
	}
}