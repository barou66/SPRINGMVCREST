package com.dialer.contactschecker.serviceimpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialer.contactschecker.dao.ILoginDao;
import com.dialer.contactschecker.model.User;
import com.dialer.contactschecker.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService 
{   
	@Autowired
	private ILoginDao loginDao;
	
	 @Autowired 
	 private HttpSession httpSession;

	@Override
	public User login(String username, String password) 
	{	
		User user = loginDao.login(username, password);
		
		if(null != user) {
			httpSession.setAttribute("currentUser", user);
		}
		
		return user;
	}
	
	public  User getCurrentUser() {
		return (User) httpSession.getAttribute("currentUser");
		
	}

}
