package com.dialer.contactschecker.util;

public class AppConstants {
	public static final String CLIENT_ORIGIN = "http://localhost:4200";

	// Phone number status:
	public static final int DIAL_STATUS_UNDIALED = 99;
	public static final int DIAL_STATUS_RINGING = 3;
	public static final int DIAL_STATUS_UNKNOWN = -1;
	public static final int DIAL_STATUS_FAILURE = 0;
	public static final int DIAL_STATUS_HANGUP = 1;
	public static final int DIAL_STATUS_CONGESTION = 8;
	public static final int DIAL_STATUS_EXPIRED = 41;
	public static final int DIAL_STATUS_ANSWERED = 4;
	public static final int DIAL_STATUS_ANSWERING_MACHINE = 40;
	public static final int DIAL_STATUS_BUSY = 5;

	// delimiters
	public static final char DELIMITER_TAB = '	';
	public static final char DELIMITER_COMMA = ',';
	public static final char DELIMITER_SEMICOLON = ';';
	public static final char DELEMITER_PIPE = '|';

}
