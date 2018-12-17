package com.dialer.contactschecker.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dialer.contactschecker.model.SipProviders;
import com.dialer.contactschecker.model.VocalScript;

import net.sf.json.JSONObject;


@Service
@PropertySource(value = "classpath:/contactlistchecker.properties", ignoreResourceNotFound = false)
public class Webservice {

	private static Logger logger = LoggerFactory.getLogger(Webservice.class);
	private String addSipAddress;
	@Autowired
	private Environment env;
	
	{
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("contactlistchecker.properties");
		Properties properties = new Properties();
		
		try 
		{
			properties.load(inputStream);
			addSipAddress = properties.getProperty("add_sip_address");
			/*modifySipAddress = properties.getProperty("mod_sip_address");
			delSipAddress = properties.getProperty("del_sip_address");
			makSipAddress = properties.getProperty("mak_sip_address");
			excSipAddress = properties.getProperty("exc_sip_address");
			addIvrAddress = properties.getProperty("add_ivr_address");
			modifyIvrAddress = properties.getProperty("mod_ivr_address");
			delIvrAddress = properties.getProperty("del_ivr_address");
			RecordingPath = properties.getProperty("RecordingPath");
			if(!RecordingPath.endsWith("/"))
				RecordingPath=RecordingPath+"/";
			if(properties.containsKey(InetAddress.getLocalHost().getHostName()+"_bg_color"))
				backgroundColor = properties.getProperty(InetAddress.getLocalHost().getHostName()+"_bg_color");
			else
				backgroundColor = properties.getProperty("background_color");*/
			
		} 
		catch (IOException e)
		{
			logger.error("Can't load 'contactlistchecker.properties' file!",e);
		}
		finally
		{
			try 
			{
				inputStream.close();
			}
			catch (IOException e)
			{
				logger.error("Can't close inputstream 'contactlistchecker.properties'",e);
			}
		}
		
	}

	public boolean addSIPWebservice(SipProviders sipProvider)
			throws ClientProtocolException, IOException, HttpHostConnectException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(addSipAddress);  
		JSONObject json = new JSONObject();
		json.put("Authorization_Username", sipProvider.getPVD_AUTHORIZATIONUSERNAME());
		json.put("Caller_Id", sipProvider.getPVD_CALLERID());
		json.put("Concurrent_Calls", sipProvider.getPVD_CONCURRENTCALLS().toString()); // May be STRing
		json.put("Login", sipProvider.getPVD_LOGIN());
		json.put("Password", sipProvider.getPVD_PASSWORD());
		json.put("Port", sipProvider.getPVD_SIPSERVERPORT().toString());// May be STRing
		json.put("Provider_Name", sipProvider.getPVD_NAME());
		json.put("SipServer", sipProvider.getPVD_SIPSERVERPORT());
		json.put("OutboundProxy", sipProvider.getPVD_OUTBOUNDPROXY());

		logger.trace("---------json-----------" + json.toString());
		HttpEntity entity;

		entity = new StringEntity(json.toString());
		httpPost.setEntity(entity);

		httpPost.setHeader("Accept", "text/html,*/*;");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

		logger.trace("Request: " + httpPost.getRequestLine());
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity2 = response.getEntity();
		logger.trace("----------------------------------------");
		logger.trace(response.getStatusLine().toString());
		if (entity2 != null) {
			logger.trace("Response content length: " + entity2.getContentLength());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity2.getContent(), "UTF-8"));
		String result = reader.readLine();
		if (result.contains("true")) {
			logger.trace("Successed to add provider '" + sipProvider.getPVD_NAME() + "'");
			return true;
		} else {
			logger.error("Failed to add provider '" + sipProvider.getPVD_NAME() + "'");
			return false;
		}
	}

	public boolean modifySIPWebservice(SipProviders sipProvider)
			throws ClientProtocolException, IOException, HttpHostConnectException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(env.getProperty("mod_sip_address"));
		JSONObject json = new JSONObject();
		json.put("Authorization_Username", sipProvider.getPVD_AUTHORIZATIONUSERNAME());
		json.put("Caller_Id", sipProvider.getPVD_CALLERID());
		json.put("Concurrent_Calls", sipProvider.getPVD_CONCURRENTCALLS()); // May be STRing
		json.put("Login", sipProvider.getPVD_LOGIN());
		json.put("Password", sipProvider.getPVD_PASSWORD());
		json.put("Port", sipProvider.getPVD_SIPSERVERPORT().toString());// May be STRing
		json.put("Provider_Name", sipProvider.getPVD_NAME());
		json.put("SipServer", sipProvider.getPVD_SIPSERVERPORT());
		json.put("OutboundProxy", sipProvider.getPVD_OUTBOUNDPROXY());

		logger.trace("---------json-----------" + json.toString());
		HttpEntity entity;

		entity = new StringEntity(json.toString());
		httpPost.setEntity(entity);

		httpPost.setHeader("Accept", "text/html,*/*;");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

		logger.trace("Request: " + httpPost.getRequestLine());
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity2 = response.getEntity();
		logger.trace("----------------------------------------");
		logger.trace(response.getStatusLine().toString());
		if (entity2 != null) {
			logger.trace("Response content length: " + entity2.getContentLength());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity2.getContent(), "UTF-8"));
		String result = reader.readLine();
		if (result.contains("true")) {
			logger.trace("Successed to modify provider '" + sipProvider.getPVD_NAME() + "'");
			return true;
		} else {
			logger.error("Failed to modify provider '" + sipProvider.getPVD_NAME() + "'");
			return false;
		}
	}

	public boolean deleteSIPWebservice(SipProviders sipProvider)
			throws ClientProtocolException, IOException, HttpHostConnectException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(env.getProperty("del_sip_address"));
		JSONObject json = new JSONObject();
		json.put("Provider_Name", sipProvider.getPVD_NAME());

		logger.trace("---------json-----------" + json.toString());
		HttpEntity entity;

		entity = new StringEntity(json.toString());
		httpPost.setEntity(entity);

		httpPost.setHeader("Accept", "text/html,*/*;");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

		logger.trace("Request: " + httpPost.getRequestLine());
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity2 = response.getEntity();
		logger.trace("----------------------------------------");
		logger.trace(response.getStatusLine().toString());
		if (entity2 != null) {
			logger.trace("Response content length: " + entity2.getContentLength());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity2.getContent(), "UTF-8"));
		String result = reader.readLine();
		if (result.contains("true")) {
			logger.trace("Successed to delete provider '" + sipProvider.getPVD_NAME() + "'");
			return true;
		} else {
			logger.error("Failed to delete provider '" + sipProvider.getPVD_NAME() + "'");
			return false;
		}
	}

	public boolean addVocalScriptWebservice(VocalScript vocalScript)
			throws ClientProtocolException, IOException, HttpHostConnectException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(env.getProperty("add_ivr_address"));
		JSONObject json = new JSONObject();
		json.put("VSC_DIALPLANNUMBER", vocalScript.getVscDialPlanNumber());
		json.put("VSC_NAME", vocalScript.getVscName());
		json.put("VSC_TYPE", vocalScript.getVscType());
		json.put("VSC_SCRIPT", vocalScript.getVscScript());

		logger.trace("---------json-----------" + json.toString());
		HttpEntity entity;

		entity = new StringEntity(json.toString());
		httpPost.setEntity(entity);

		httpPost.setHeader("Accept", "text/html,*/*;");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

		logger.trace("Request: " + httpPost.getRequestLine());
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity2 = response.getEntity();
		logger.trace("----------------------------------------");
		logger.trace(response.getStatusLine().toString());
		if (entity2 != null) {
			logger.trace("Response content length: " + entity2.getContentLength());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity2.getContent(), "UTF-8"));
		String result = reader.readLine();
		if (result.contains("true")) {
			logger.trace("Successed to add vocalscript '" + vocalScript.getVscName() + "'");
			return true;
		} else {
			logger.error("Failed to add vocalscript '" + vocalScript.getVscName() + "'");
			return false;
		}
	}

	public boolean modifyVocalScriptWebservice(VocalScript vocalScript)
			throws ClientProtocolException, IOException, HttpHostConnectException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(env.getProperty("mod_ivr_address"));
		JSONObject json = new JSONObject();
		json.put("VSC_DIALPLANNUMBER", vocalScript.getVscDialPlanNumber());
		json.put("VSC_NAME", vocalScript.getVscName());
		json.put("VSC_TYPE", vocalScript.getVscType());
		json.put("VSC_SCRIPT", vocalScript.getVscScript());

		logger.trace("---------json-----------" + json.toString());
		HttpEntity entity;

		entity = new StringEntity(json.toString());
		httpPost.setEntity(entity);

		httpPost.setHeader("Accept", "text/html,*/*;");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

		logger.trace("Request: " + httpPost.getRequestLine());
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity2 = response.getEntity();
		logger.trace("----------------------------------------");
		logger.trace(response.getStatusLine().toString());
		if (entity2 != null) {
			logger.trace("Response content length: " + entity2.getContentLength());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity2.getContent(), "UTF-8"));
		String result = reader.readLine();
		if (result.contains("true")) {
			logger.trace("Successed to modify vocalscript '" + vocalScript.getVscName() + "'");
			return true;
		} else {
			logger.error("Failed to modify vocalscript '" + vocalScript.getVscName() + "'");
			return false;
		}
	}

	public boolean deleteVocalScriptWebservice(VocalScript vocalScript)
			throws ClientProtocolException, IOException, HttpHostConnectException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(env.getProperty("del_ivr_address"));
		JSONObject json = new JSONObject();
		json.put("VSC_NAME", vocalScript.getVscName());
		json.put("VSC_TYPE", vocalScript.getVscType());
		json.put("VSC_DIALPLANNUMBER", vocalScript.getVscDialPlanNumber());

		logger.trace("---------json-----------" + json.toString());
		HttpEntity entity;

		entity = new StringEntity(json.toString());
		httpPost.setEntity(entity);

		httpPost.setHeader("Accept", "text/html,*/*;");
		httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

		logger.trace("Request: " + httpPost.getRequestLine());
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity2 = response.getEntity();
		logger.trace("----------------------------------------");
		logger.trace(response.getStatusLine().toString());
		if (entity2 != null) {
			logger.trace("Response content length: " + entity2.getContentLength());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity2.getContent(), "UTF-8"));
		String result = reader.readLine();
		if (result.contains("true")) {
			logger.trace("Successed to delete vocalscript '" + vocalScript.getVscName() + "'");
			return true;
		} else {
			logger.error("Failed to delete vocalscript '" + vocalScript.getVscName() + "'");
			return false;
		}
	}

}
