package com.dialer.contactschecker.model;

public class User 
{
	private int usr_id=-1;
	private String usr_login;
	private String usr_password;
	private String usr_email;
	private int usr_default_language;
	
	public int getUsr_id() 
	{
		return usr_id;
	}
	
	public void setUsr_id(int usr_id) 
	{
		this.usr_id = usr_id;
	}
	
	public String getUsr_login() 
	{
		return usr_login;
	}
	
	public void setUsr_login(String usr_login) {
		this.usr_login = usr_login;
	}
	
	public String getUsr_password() {
		return usr_password;
	}
	
	public void setUsr_password(String usr_password) {
		this.usr_password = usr_password;
	}
	
	public String getUsr_email() {
		return usr_email;
	}
	
	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}
	
	public int getUsr_default_language() {
		return usr_default_language;
	}
	
	public void setUsr_default_language(int usr_default_language) {
		this.usr_default_language = usr_default_language;
	}	
}
