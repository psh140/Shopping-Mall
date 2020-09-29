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
		
		if (cmd.equals("main")) {
			action = new MainForm();
			System.out.println("authfactory·Î ³Ñ¾î¿È");
		} else if (cmd.equals("authForm")) {
			action = new AuthForm();
		} else if (cmd.equals("adminAuthForm")) {
			action = new AdminAuthForm();
		} else if(cmd.equals("adminCheck")) {
			action = new AdminCheck();
		} else if(cmd.equals("authCheck")) {
			action = new AuthCheck();
		} else if(cmd.equals("logoutAction")) {
			action = new LogoutAction();
		} 
		return action;
		
	}
}
