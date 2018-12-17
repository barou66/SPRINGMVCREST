package com.dialer.contactschecker.controllerrest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dialer.contactschecker.model.SipProviders;
import com.dialer.contactschecker.webservice.Webservice;


public class logTest {
	
	private static Logger logger = LoggerFactory.getLogger(logTest.class);
	
	public static void main(String[] args) {
		/*logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");

        String variable = "Hello John";*/
		SipProviders sip =new SipProviders();
		sip.setPVD_NAME("Gamaaaa");
		sip.setPVD_LOGIN("admin");
		sip.setPVD_AUTHORIZATIONUSERNAME("admin");
		sip.setPVD_SIPSERVERADDRESS("192.168.0.1");
		sip.setPVD_SIPSERVERPORT(8000);
		sip.setPVD_CONCURRENTCALLS(2);
		sip.setPVD_CALLERID("03333333");
		sip.setPVD_OUTBOUNDPROXY("8000");
		
		Webservice w = new Webservice();
		
		try {
			System.out.println(w.addSIPWebservice(sip)+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        logger.debug("Printing variable value {} ",sip);
  
	}

}
