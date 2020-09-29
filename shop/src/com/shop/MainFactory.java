package com.shop;

public class MainFactory {
	private static MainFactory instance = null;
	private MainFactory() {}

	public static MainFactory getInstance() {
		if(instance == null) {
			instance = new MainFactory();
		}
		
		return instance;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		
		if(cmd.equals("main")) {
			action = new MainForm();
		}
		
		return action;
	}
}
