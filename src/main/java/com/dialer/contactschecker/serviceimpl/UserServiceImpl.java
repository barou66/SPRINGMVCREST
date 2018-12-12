package com.dialer.contactschecker.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialer.contactschecker.dao.IUserDao;
import com.dialer.contactschecker.model.User;
import com.dialer.contactschecker.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;

	@Override
	public User updateUserProfile(User user) {
		User userUpdate = this.userDao.updateUserProfile(user);
		
		return userUpdate;
	}

	@Override
	public User findUserById(long id) {
		User user = this.userDao.findUserById(id);
		return user;
	}

}
