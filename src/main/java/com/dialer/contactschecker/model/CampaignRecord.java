package com.dialer.contactschecker.model;

import java.util.List;

public class CampaignRecord {
	private long calId;
	private String phone;
	private int status;
	private int attempts;
	private int valid;
	private String ivrresult;
	private List<String> otherColumns;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public String getIvrResult() {
		return ivrresult;
	}
	public void setIvrResult(String IvrResult) {
		this.ivrresult = IvrResult;
	}
	public List<String> getOtherColumns() {
		return otherColumns;
	}
	public void setOtherColumns(List<String> otherColumns) {
		this.otherColumns = otherColumns;
	}
	public long getCalId() {
		return calId;
	}
	public void setCalId(long calId) {
		this.calId = calId;
	}
}