package com.dialer.contactschecker.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.dialer.contactschecker.model.DialerSetting;

public interface IDialerSettingDao {	
	public boolean  updateDialerSettings(DialerSetting dialerSetting);
	public DialerSetting getDialerSettings();

}
