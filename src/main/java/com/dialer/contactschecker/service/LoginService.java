package com.dialer.contactschecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialer.contactschecker.dao.ILoginDao;
import com.dialer.contactschecker.model.User;

@Service
public class LoginService implements ILoginService 
{   
	@Autowired
	private ILoginDao loginDao;

	@Override
	public User login(String username, String password) 
	{	
		return loginDao.login(username, password);
	}

}
