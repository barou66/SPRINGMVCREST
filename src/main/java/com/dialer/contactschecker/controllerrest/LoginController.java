package com.dialer.contactschecker.controllerrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dialer.contactschecker.model.User;
import com.dialer.contactschecker.service.ILoginService;
import com.dialer.contactschecker.util.AppConstants;
import com.dialer.contactschecker.util.AppEncrypt;

@RestController
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private ILoginService loginService;
	
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
	@RequestMapping(value = "login/{username}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("username") String username,
			@PathVariable("password") String password) {

		User user = loginService.login(username,password/*AppEncrypt.encryptDefault(password)*/);
		if (null != user) {

			 logger.info("User Login is: {}",username);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		return new ResponseEntity<User>(HttpStatus.EXPECTATION_FAILED);
	}

}
