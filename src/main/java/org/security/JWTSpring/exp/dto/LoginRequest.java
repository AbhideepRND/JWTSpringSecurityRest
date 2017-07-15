package org.security.JWTSpring.exp.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.security.JWTSpring.exp.Constaints;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value="LoginRequest", namespace=Constaints.LOGIN_REQ_NAMESPACE)
public class LoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6643956329562287038L;
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	@JsonProperty("Username")
	@NotNull
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	@JsonProperty("Password")
	@NotNull
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
