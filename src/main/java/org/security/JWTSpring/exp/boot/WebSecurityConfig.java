package org.security.JWTSpring.exp.boot;

import org.security.JWTSpring.exp.security.RESTAuthenticationEntryPoint;
import org.security.JWTSpring.exp.security.RESTAuthenticationFailureHandler;
import org.security.JWTSpring.exp.security.RESTAuthenticationSuccessHandler;
import org.security.JWTSpring.exp.security.RestRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Abhideep Bakshi
 *
 *         WebSecurityConfig - Configure the web security and roles access.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier(value = "CustomAuthenticationProvider")
	AuthenticationProvider authProvider;

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;

	/*@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;*/
	
	@Autowired private AuthenticationSuccessHandler successHandler;
	 @Autowired private AuthenticationFailureHandler failureHandler;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	protected RestRequestFilter buildAjaxLoginProcessingFilter() throws Exception {
		RestRequestFilter filter = new RestRequestFilter("/erp/auth/login", failureHandler,
				successHandler, objectMapper);
		filter.setAuthenticationManager(this.authenticationManager);
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
		.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
				.antMatchers("/erp/**").access("hasRole('ROLE_USER')")
		.and()
			.addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

		super.configure(http);
	}

}
