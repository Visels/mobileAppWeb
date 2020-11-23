package com.app.ws.ui.Security;

import com.app.ws.SpringApplicationContext;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME = 864000000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/user/create";


	public static String getTokenSecret(){

		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");

		return appProperties.getTokenSecret();
	}

}
