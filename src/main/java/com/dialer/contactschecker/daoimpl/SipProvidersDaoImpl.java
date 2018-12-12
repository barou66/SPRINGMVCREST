package com.dialer.contactschecker.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.dialer.contactschecker.dao.ISipProvidersDao;
import com.dialer.contactschecker.model.SipProviders;

@Repository
public class SipProvidersDaoImpl implements ISipProvidersDao {

	private static Logger logger = LoggerFactory.getLogger(SipProvidersDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public SipProvidersDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public SipProviders add(SipProviders sipProvider) {
		
		logger.info("SIPPROVIDERS INFO {}",sipProvider);

		try {
			String id = UUID.randomUUID().toString();
			String sql2 = "insert into msipproviders_pvd(PVD_ID,PVD_NAME,PVD_LOGIN,PVD_AUTHORIZATIONUSERNAME,PVD_SIPSERVERADDRESS,PVD_SIPSERVERPORT,PVD_PASSWORD,PVD_CONCURRENTCALLS,PVD_CALLERID,PVD_OUTBOUNDPROXY) values(?,?,?,?,?,?,?,?,?,?)";
			int result = jdbcTemplate.update(sql2,
					new Object[] { id, sipProvider.getPVD_NAME(), sipProvider.getPVD_LOGIN(),
							sipProvider.getPVD_AUTHORIZATIONUSERNAME(), sipProvider.getPVD_SIPSERVERADDRESS(),
							sipProvider.getPVD_SIPSERVERPORT(), sipProvider.getPVD_PASSWORD(),
							sipProvider.getPVD_CONCURRENTCALLS(), sipProvider.getPVD_CALLERID(),
							sipProvider.getPVD_OUTBOUNDPROXY() });

			if (result > 0) {
				jdbcTemplate.execute("commit");
				return sipProvider;
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

	@Override
	public SipProviders update(SipProviders sipProvider) {
		try {
			String sql = "update msipproviders_pvd set PVD_NAME=?, PVD_LOGIN=?, PVD_AUTHORIZATIONUSERNAME=?, PVD_SIPSERVERADDRESS=?, PVD_SIPSERVERPORT=?, PVD_PASSWORD=?, PVD_CONCURRENTCALLS=?, PVD_CALLERID=?, PVD_OUTBOUNDPROXY=? where PVD_ID=?";

			int result = jdbcTemplate.update(sql,
					new Object[] { sipProvider.getPVD_NAME(), sipProvider.getPVD_LOGIN(),
							sipProvider.getPVD_AUTHORIZATIONUSERNAME(), sipProvider.getPVD_SIPSERVERADDRESS(),
							sipProvider.getPVD_SIPSERVERPORT(), sipProvider.getPVD_PASSWORD(),
							sipProvider.getPVD_CONCURRENTCALLS(), sipProvider.getPVD_CALLERID(),
							sipProvider.getPVD_OUTBOUNDPROXY(), sipProvider.getPVD_ID() });
			if (result > 0) {
				jdbcTemplate.execute("commit");
				return sipProvider;
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

	@Override
	public boolean delete(String sipproviderId) {
		try
		{
			String sql2 = "delete from mdistribution_cmp_pvd where ID_PVD=?";
			String sql = "delete from msipproviders_pvd where PVD_ID=?";

			int result2 = jdbcTemplate.update(sql2, new Object[] { sipproviderId });
			int result = jdbcTemplate.update(sql, new Object[] { sipproviderId });

			if (result2>=0 && result > 0)
			{
				jdbcTemplate.execute("commit");
				return true;
			}
			else
			{
				if(result2<0)
					logger.error("Provider with id '"+sipproviderId+"' can't be deleted from Distribution table");			
				if(result==0)
					logger.error("Provider with id '"+sipproviderId+"' not found in Providers table");
				jdbcTemplate.execute("rollback");
				return false;
			}
		}
		catch(Exception e)
		{
			jdbcTemplate.execute("rollback");
			logger.error("JDBC Error:",e);
			return false;
		}
	}

	@Override
	public List<SipProviders> getAll() {
		String sql = "select * from msipproviders_pvd";

		SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
		List<SipProviders> sipList = new ArrayList<SipProviders>();
		while (srs.next()) {
			SipProviders sip = new SipProviders();
			sip.setPVD_ID(srs.getString("PVD_ID"));
			sip.setPVD_NAME(srs.getString("PVD_NAME"));
			sip.setPVD_LOGIN(srs.getString("PVD_LOGIN"));
			sip.setPVD_PASSWORD(srs.getString("PVD_PASSWORD"));
			sip.setPVD_CALLERID(srs.getString("PVD_CALLERID"));
			sip.setPVD_AUTHORIZATIONUSERNAME(srs.getString("PVD_AUTHORIZATIONUSERNAME"));
			sip.setPVD_OUTBOUNDPROXY(srs.getString("PVD_OUTBOUNDPROXY"));
			sip.setPVD_SIPSERVERADDRESS(srs.getString("PVD_SIPSERVERADDRESS"));
			sip.setPVD_CONCURRENTCALLS(srs.getInt("PVD_CONCURRENTCALLS"));
			sip.setPVD_SIPSERVERPORT(srs.getInt("PVD_SIPSERVERPORT"));
			
			sipList.add(sip);
		}

		return sipList;
	}

	@Override
	public SipProviders getById(String sipproviderId) {
		String sql = "select * from msipproviders_pvd where PVD_ID=?";

		SipProviders provider = (SipProviders) jdbcTemplate.queryForObject(sql, new Object[] { sipproviderId },
				new BeanPropertyRowMapper<SipProviders>(SipProviders.class));
		return provider;
	}

}
