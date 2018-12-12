package com.dialer.contactschecker.controllerrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dialer.contactschecker.model.SipProviders;


public class logTest {
	
	private static Logger logger = LoggerFactory.getLogger(logTest.class);
	
	public static void main(String[] args) {
		/*logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");

        String variable = "Hello John";*/
		SipProviders sip =new SipProviders();
        logger.debug("Printing variable value {} ",sip);
	}

}
