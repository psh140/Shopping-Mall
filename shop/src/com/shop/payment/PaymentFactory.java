package com.shop.payment;

import com.shop.Action;

public class PaymentFactory {
	
	private static PaymentFactory instance = null;
	private PaymentFactory() {}
	
	public static PaymentFactory getInstance() {
		if(instance == null) {
			instance = new PaymentFactory();
			
		}
		return instance;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		
		
		return action;	
	}
	
	
}
