package com.dialer.contactschecker.dao;

import com.dialer.contactschecker.model.User;

public interface IUserDao {
	public User updateUserProfile(User user);
	public User findUserById(long id);

}
