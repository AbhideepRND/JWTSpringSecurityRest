package org.security.JWTSpring.exp.boot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Abhideep Bakshi
 *
 *	ApplicationContext - Initialize the Application context.	
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.security.JWTSpring.exp" })
@PropertySource("classpath:application.properties")
public class ApplicationContext extends WebMvcConfigurerAdapter {

	@Value("${db.driver}")
	private String dbDriverName;

	@Value("${db.url}")
	private String dbConnectionUrl;

	@Value("${db.username}")
	private String dbUsername;

	@Value("${db.password}")
	private String dbPassword;

	@Value("${queryPropertyUrl}")
	private String queryUrl;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(dbDriverName);
		driverManagerDataSource.setUrl(dbConnectionUrl);
		driverManagerDataSource.setUsername(dbUsername);
		driverManagerDataSource.setPassword(dbPassword);
		return driverManagerDataSource;
	}

	@Bean(name = { "sqlProperties" })
	public Properties sqlProperties() throws IOException {
		final Properties properties = new Properties();
		final ClassLoader classLoader = getClass().getClassLoader();
		final File file = new File(classLoader.getResource(queryUrl).getFile());
		final FileInputStream fileInputStream = new FileInputStream(file);
		properties.load(fileInputStream);
		return properties;
	}

	/*@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}*/

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
