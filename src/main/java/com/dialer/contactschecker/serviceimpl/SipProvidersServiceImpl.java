package com.dialer.contactschecker.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialer.contactschecker.dao.ISipProvidersDao;
import com.dialer.contactschecker.model.SipProviders;
import com.dialer.contactschecker.service.ISipProvidersService;

@Service
public class SipProvidersServiceImpl implements ISipProvidersService{
	
	@Autowired
	private ISipProvidersDao sipProviderDao;

	@Override
	public SipProviders add(SipProviders sipProvider) {
		return sipProviderDao.add(sipProvider);
	}

	@Override
	public SipProviders update(SipProviders sipProvider) {
		return sipProviderDao.update(sipProvider);
	}

	@Override
	public boolean delete(String sipproviderId) {
		return sipProviderDao.delete(sipproviderId);
	}

	@Override
	public List<SipProviders> getAll() {
		return sipProviderDao.getAll();
	}

	@Override
	public SipProviders getById(String sipproviderId) {
		return sipProviderDao.getById(sipproviderId);
	}

}
