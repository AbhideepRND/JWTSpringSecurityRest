package org.security.JWTSpring.exp.restcontroller;

import org.security.JWTSpring.exp.dto.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Abhideep Bakshi
 * 	
 * 	
 *
 */
@RestController
@RequestMapping(value="/erp")
@Secured("ROLE_USER")
public class SpringRestController {

	@RequestMapping( path="/UserDetails",method={RequestMethod.GET},
				consumes={MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE}
				)
	@ResponseBody
	public ResponseEntity<String> getUserDetails(){
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
		
	}
	
}
