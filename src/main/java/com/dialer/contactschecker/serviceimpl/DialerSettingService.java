package com.dialer.contactschecker.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialer.contactschecker.dao.IDialerSettingDao;
import com.dialer.contactschecker.model.DialerSetting;
import com.dialer.contactschecker.service.IDialerSettingService;

@Service
public class DialerSettingService implements IDialerSettingService {

	@Autowired
	private IDialerSettingDao dialerSettingDao;

	@Override
	public boolean updateDialerSettings(DialerSetting dialerSetting) {
		return dialerSettingDao.updateDialerSettings(dialerSetting);
	}

	@Override
	public DialerSetting getDialerSettings() {
		return dialerSettingDao.getDialerSettings();
	}

}
