package com.dialer.contactschecker.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.dialer.contactschecker.dao.IDialerSettingDao;
import com.dialer.contactschecker.model.DialerSetting;

@Repository
public class DialerSettingDaoImpl implements IDialerSettingDao {

	private static Logger logger = LoggerFactory.getLogger(DialerSettingDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public DialerSettingDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean updateDialerSettings(DialerSetting dialerSetting) {
		try {
			String sql1 = "update mdialersettings_dst set DST_VALUE=? where DST_OPTIONNAME='max_call_duration'";
			String sql2 = "update mdialersettings_dst set DST_VALUE=? where DST_OPTIONNAME='max_number_of_retries'";
			String sql3 = "update mdialersettings_dst set DST_VALUE=? where DST_OPTIONNAME='wait_duration_of_retries'";
			String sql4 = "update mdialersettings_dst set DST_VALUE=? where DST_OPTIONNAME='amd_detection'";

			int result1 = jdbcTemplate.update(sql1, new Object[] { dialerSetting.getMaxCall() });
			int result2 = jdbcTemplate.update(sql2, new Object[] { dialerSetting.getMaxNumber() });
			int result3 = jdbcTemplate.update(sql3, new Object[] { dialerSetting.getWaitDuration() });
			String amdDetectionString = "false";
			if (dialerSetting.isAmdDetection())
				amdDetectionString = "true";
			int result4 = jdbcTemplate.update(sql4, new Object[] { amdDetectionString });

			if (result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0) {
				jdbcTemplate.execute("commit");
				return true;
			} else {
				jdbcTemplate.execute("rollback");
				return false;
			}
		} catch (Exception e) {
			jdbcTemplate.execute("rollback");
			logger.error("JDBC Error:", e);
			return false;
		}
	}

	@Override
	public DialerSetting getDialerSettings() {
		String sql = "select DST_OPTIONNAME,DST_VALUE from mdialersettings_dst";

		SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);

		DialerSetting ds = new DialerSetting();
		while (srs.next()) {
			switch (srs.getString("DST_OPTIONNAME")) {
			case "max_call_duration":
				ds.setMaxCall(Integer.valueOf(srs.getString("DST_VALUE")));
				break;
			case "max_number_of_retries":
				ds.setMaxNumber(Integer.valueOf(srs.getString("DST_VALUE")));
				break;
			case "wait_duration_of_retries":
				ds.setWaitDuration(Integer.valueOf(srs.getString("DST_VALUE")));
				break;
			case "amd_detection":
				ds.setAmdDetection(Boolean.valueOf(srs.getString("DST_VALUE")));
				break;
			}
		}

		return ds;
	}

}
