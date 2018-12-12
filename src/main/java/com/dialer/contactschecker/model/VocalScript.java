package com.dialer.contactschecker.model;
public class VocalScript {
	private String vscId;
	private int vscDialPlanNumber; 
	private String vscName;
	private int vscType;
	private String vscScript;
	
	public String getVscId() {
		return vscId;
	}
	
	public int getVscDialPlanNumber() {
		return vscDialPlanNumber;
	}
	
	public String getVscName() {
		return vscName;
	}
	
	public int getVscType() {
		return vscType;
	}
	
	public String getVscScript() {
		return vscScript;
	}
	
	public void setVscId(String vscId) {
		this.vscId = vscId;
	}
	
	public void setVscDialPlanNumber(int vscDialplanNumber) {
		this.vscDialPlanNumber = vscDialplanNumber;
	}
	
	public void setVscName(String vscName) {
		this.vscName = vscName;
	}
	
	public void setVscType(int vscType) {
		this.vscType = vscType;
	}
	
	public void setVscScript(String vscScript) {
		this.vscScript = vscScript;
	}	
}