package com.dialer.contactschecker.daoimpl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dialer.contactschecker.dao.ILoginDao;
import com.dialer.contactschecker.model.User;

@Repository
public class LoginDaoImpl implements ILoginDao 
{
	private static Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public LoginDaoImpl(DataSource dataSource) 
	{
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User login(String username, String password)
	{
		String sql = "select * from musers_usr where USR_LOGIN=? and USR_PASSWORD=?";
		
		try 
		{
			User user = (User) jdbcTemplate.queryForObject(sql,
					new Object[] { username, password }, new BeanPropertyRowMapper<User>(User.class));
			return user;
			
		}
		catch (EmptyResultDataAccessException e) 
		{
			logger.error("Empty result for login '"+username+"'",e);
			return null;
		}
		catch (Exception e) 
		{
			logger.error("Can't get user info for login '"+username+"'",e);
			return null;
		}
	}	

}
