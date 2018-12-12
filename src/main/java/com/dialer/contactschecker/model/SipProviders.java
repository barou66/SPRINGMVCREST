package com.dialer.contactschecker.model;

public class SipProviders
{
	private String PVD_ID;
	private String PVD_NAME;
	private String PVD_LOGIN;
	private String PVD_AUTHORIZATIONUSERNAME;
	private String PVD_SIPSERVERADDRESS;
	private Integer PVD_SIPSERVERPORT;
	private String PVD_PASSWORD;
	private Integer PVD_CONCURRENTCALLS;
	private String PVD_CALLERID;
	private String PVD_OUTBOUNDPROXY;
	
	
	
	public SipProviders() {
		super();
	}
	
	





	public SipProviders(String pVD_ID2, String pVD_NAME2, String pVD_LOGIN2, String pVD_AUTHORIZATIONUSERNAME2,
			String pVD_SIPSERVERADDRESS2, String pVD_SIPSERVERPORT2, String pVD_PASSWORD2, String pVD_CONCURRENTCALLS2,
			String pVD_CALLERID2, String pVD_OUTBOUNDPROXY2) {
	}





	public String getPVD_ID() {
		return PVD_ID;
	}



	public void setPVD_ID(String pVD_ID) {
		PVD_ID = pVD_ID;
	}



	public String getPVD_NAME() {
		return PVD_NAME;
	}



	public void setPVD_NAME(String pVD_NAME) {
		PVD_NAME = pVD_NAME;
	}



	public String getPVD_LOGIN() {
		return PVD_LOGIN;
	}



	public void setPVD_LOGIN(String pVD_LOGIN) {
		PVD_LOGIN = pVD_LOGIN;
	}



	public String getPVD_AUTHORIZATIONUSERNAME() {
		return PVD_AUTHORIZATIONUSERNAME;
	}



	public void setPVD_AUTHORIZATIONUSERNAME(String pVD_AUTHORIZATIONUSERNAME) {
		PVD_AUTHORIZATIONUSERNAME = pVD_AUTHORIZATIONUSERNAME;
	}



	public String getPVD_SIPSERVERADDRESS() {
		return PVD_SIPSERVERADDRESS;
	}



	public void setPVD_SIPSERVERADDRESS(String pVD_SIPSERVERADDRESS) {
		PVD_SIPSERVERADDRESS = pVD_SIPSERVERADDRESS;
	}



	public String getPVD_PASSWORD() {
		return PVD_PASSWORD;
	}



	public void setPVD_PASSWORD(String pVD_PASSWORD) {
		PVD_PASSWORD = pVD_PASSWORD;
	}

	public String getPVD_CALLERID() {
		return PVD_CALLERID;
	}



	public void setPVD_CALLERID(String pVD_CALLERID) {
		PVD_CALLERID = pVD_CALLERID;
	}



	public String getPVD_OUTBOUNDPROXY() {
		return PVD_OUTBOUNDPROXY;
	}



	public void setPVD_OUTBOUNDPROXY(String pVD_OUTBOUNDPROXY) {
		PVD_OUTBOUNDPROXY = pVD_OUTBOUNDPROXY;
	}

	


	public Integer getPVD_SIPSERVERPORT() {
		return PVD_SIPSERVERPORT;
	}







	public void setPVD_SIPSERVERPORT(Integer pVD_SIPSERVERPORT) {
		PVD_SIPSERVERPORT = pVD_SIPSERVERPORT;
	}







	public Integer getPVD_CONCURRENTCALLS() {
		return PVD_CONCURRENTCALLS;
	}







	public void setPVD_CONCURRENTCALLS(Integer pVD_CONCURRENTCALLS) {
		PVD_CONCURRENTCALLS = pVD_CONCURRENTCALLS;
	}







	@Override
	public String toString() {
		return "SipProviders [PVD_ID=" + PVD_ID + ", PVD_NAME=" + PVD_NAME + ", PVD_LOGIN=" + PVD_LOGIN
				+ ", PVD_AUTHORIZATIONUSERNAME=" + PVD_AUTHORIZATIONUSERNAME + ", PVD_SIPSERVERADDRESS="
				+ PVD_SIPSERVERADDRESS + ", PVD_SIPSERVERPORT=" + PVD_SIPSERVERPORT + ", PVD_PASSWORD=" + PVD_PASSWORD
				+ ", PVD_CONCURRENTCALLS=" + PVD_CONCURRENTCALLS + ", PVD_CALLERID=" + PVD_CALLERID
				+ ", PVD_OUTBOUNDPROXY=" + PVD_OUTBOUNDPROXY + "]";
	}
	
	
	
	
}