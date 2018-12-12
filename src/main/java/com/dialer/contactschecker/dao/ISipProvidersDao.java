package com.dialer.contactschecker.dao;

import java.util.List;

import com.dialer.contactschecker.model.SipProviders;

public interface ISipProvidersDao {
	public SipProviders add(SipProviders sipProvider);
	public SipProviders update(SipProviders sipProvider);
	public boolean delete(String sipproviderId);
	public List<SipProviders> getAll();
	public SipProviders getById(String sipproviderId);

}
