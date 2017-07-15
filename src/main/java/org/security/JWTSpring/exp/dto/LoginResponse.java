package org.security.JWTSpring.exp.dto;

import java.util.List;

import org.security.JWTSpring.exp.Constaints;
import org.security.JWTSpring.exp.bo.Role;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="LoginResponse", namespace=Constaints.LOGIN_RES_NAMESPACE)
@JsonPropertyOrder(value={"UserName", "Roles"})
public class LoginResponse extends GenericResponse{

	private String username;
	private List<Role> authorities;
	
	@JsonProperty("UserName")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonProperty("Roles")
	public List<Role> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	
	
}
