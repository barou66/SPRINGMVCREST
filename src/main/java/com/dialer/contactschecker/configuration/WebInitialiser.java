package com.dialer.contactschecker.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WebInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("*********************ROOT*******************************");
		return new Class[] { AppConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		 return null;
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/" };
	}

}
