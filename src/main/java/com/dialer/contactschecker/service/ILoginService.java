package com.dialer.contactschecker.service;

import com.dialer.contactschecker.model.User;

public interface ILoginService 
{
	public User login(String username, String password);
	public  User getCurrentUser();
}
