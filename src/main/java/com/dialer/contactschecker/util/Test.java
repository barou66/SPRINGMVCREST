package com.dialer.contactschecker.util;

public class Test {

	public static void main(String[] args) {
		AppEncrypt ff=new AppEncrypt();
		System.out.println(ff.encrypt("admin"));
		System.out.println(ff.decrypt("/oqKX0FyLVA="));
	}

}
