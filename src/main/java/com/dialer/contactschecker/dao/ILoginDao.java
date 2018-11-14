package com.dialer.contactschecker.dao;

import com.dialer.contactschecker.model.User;

public interface ILoginDao 
{
	public User login(String username,String password);
}
