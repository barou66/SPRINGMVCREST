package com.dialer.contactschecker.service;

import com.dialer.contactschecker.model.DialerSetting;

public interface IDialerSettingService {
	public boolean updateDialerSettings(DialerSetting dialerSetting);
	public DialerSetting getDialerSettings();
}
