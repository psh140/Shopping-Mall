package com.shop.auth;

import com.shop.Action;

public class AuthFactory {
	private static AuthFactory instance = null;
	private AuthFactory() {};
	
	public static AuthFactory getInstance() {
		if(instance == null) {
			instance = new AuthFactory();
		}
		
		return instance;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		
		if (cmd.equals("authForm")) {
			action = new AuthForm();
		}else if (cmd.equals("adminAuthForm")) {
			action = new AdminAuthForm();
		}
		return action;
		
	}
}
