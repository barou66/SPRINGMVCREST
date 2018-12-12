package com.dialer.contactschecker.service;

import com.dialer.contactschecker.model.User;

public interface IUserService {
	public User updateUserProfile(User user);
	public User findUserById(long id);

}
