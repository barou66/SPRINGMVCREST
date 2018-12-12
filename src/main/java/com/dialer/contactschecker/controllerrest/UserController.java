package com.dialer.contactschecker.controllerrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dialer.contactschecker.model.User;
import com.dialer.contactschecker.service.ILoginService;
import com.dialer.contactschecker.service.IUserService;
import com.dialer.contactschecker.util.AppConstants;
import com.dialer.contactschecker.util.AppEncrypt;

@RestController
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ILoginService loginService;
	

	
	//------------------- Update a User --------------------------------------------------------//
	@CrossOrigin(origins = AppConstants.CLIENT_ORIGIN)
    @RequestMapping(value = "api/user/{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,@RequestBody User user) {
		
		User u = loginService.getCurrentUser();
		
		logger.info("Current USER:{}",u);
        
		 User currentUser =  userService.findUserById(id);
         
        if (currentUser==null) {
        	logger.info("User with id {} can't be found",user.getUsr_id());
            
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        
        
        user.setUsr_password(AppEncrypt.encryptDefault(user.getUsr_password()));
        currentUser = userService.updateUserProfile(user);
 
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }
	
	

}
