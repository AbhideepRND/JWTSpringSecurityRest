package org.security.JWTSpring.exp.restcontroller;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.security.JWTSpring.exp.dto.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandler {

	@RequestMapping(value = "/error", method={RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<LoginResponse> error( HttpServletRequest request) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.addException("Unable to login");
		String header = request.getHeader("ErrorMsg");
		return  new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
	}

}
