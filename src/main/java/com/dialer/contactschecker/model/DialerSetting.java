package com.dialer.contactschecker.model;
public class DialerSetting
{
	/*private String dstOptionName;
	private String dstValue;*/
	private int maxCall; 
	private int maxNumber; 
	private int waitDuration; 
	private boolean amdDetection;
	public int getMaxCall() {
		return maxCall;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public int getWaitDuration() {
		return waitDuration;
	}
	public boolean isAmdDetection() {
		return amdDetection;
	}
	public void setMaxCall(int maxCall) {
		this.maxCall = maxCall;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public void setWaitDuration(int waitDuration) {
		this.waitDuration = waitDuration;
	}
	public void setAmdDetection(boolean amdDetection) {
		this.amdDetection = amdDetection;
	}
	
	/*public String getDstOptionName() {
		return dstOptionName;
	}
	public String getDstValue() {
		return dstValue;
	}
	public void setDstOptionName(String dstOptionName) {
		this.dstOptionName = dstOptionName;
	}
	public void setDstValue(String dstValue) {
		this.dstValue = dstValue;
	}*/
	
		
	
}
