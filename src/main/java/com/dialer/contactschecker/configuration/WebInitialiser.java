package com.dialer.contactschecker.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class WebInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(WebInitialiser.class);
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("[[[====================Initialization====================]]");
		return new Class[] { AppConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		 return null;
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/api/*" };
	}

}
