package org.security.JWTSpring.exp.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GenericResponse {

	private List<String> exception;
	private List<String> error;
	private List<String> warning;
	
	@JsonProperty("Exceptions")
	public List<String> getException() {
		return exception;
	}
	
	public void addException(String exception) {
		if(CollectionUtils.isEmpty(this.exception)){
			this.exception = new ArrayList<String>();
		}
		this.exception.add(exception);
	}
	
	@JsonProperty("Errors")
	public List<String> getError() {
		return error;
	}
	
	public void addError(String error) {
		if(CollectionUtils.isEmpty(this.error)){
			this.error = new ArrayList<String>();
		}
		this.error.add(error);
	}
	
	@JsonProperty("Warnings")
	public List<String> getWarning() {
		return warning;
	}
	
	public void addWarning(String warning) {
		if(CollectionUtils.isEmpty(this.warning )){
			this.warning = new ArrayList<String>();
		}
		this.warning.add(warning);
	}
}
