package com.dialer.contactschecker.daoimpl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dialer.contactschecker.dao.IUserDao;
import com.dialer.contactschecker.model.User;

@Repository
public class UserDaoImpl implements IUserDao {

	private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User updateUserProfile(User user) {
		try {
			String sql = "update musers_usr set USR_PASSWORD=?, USR_EMAIL=?, USR_DEFAULT_LANGUAGE=? where USR_ID=?";

			int result = jdbcTemplate.update(sql, new Object[] { user.getUsr_password(), user.getUsr_email(),
					user.getUsr_default_language(), user.getUsr_id() });
			if (result > 0) {
				jdbcTemplate.execute("commit");
				/// ActionContext.getContext().getSession().put(Constants.SESSION_INFO,user);
				return user;
			} else {
				jdbcTemplate.execute("rollback");
				return null;
			}
		} catch (Exception e) {
			jdbcTemplate.execute("rollback");
			logger.error("JDBC Error:", e);
			return null;
		}

	}
	
	public User findUserById(long id) {
		
		String sql = "select * from musers_usr where USR_ID=?";
		
		try 
		{
			User user = (User) jdbcTemplate.queryForObject(sql,
					new Object[] { id}, new BeanPropertyRowMapper<User>(User.class));
			return user;
			
		}
		catch (EmptyResultDataAccessException e) 
		{
			logger.error("Empty result for login '"+id+"'",e);
			return null;
		}
		catch (Exception e) 
		{
			logger.error("Can't get user info for login '"+id+"'",e);
			return null;
		}
		
	}

}
