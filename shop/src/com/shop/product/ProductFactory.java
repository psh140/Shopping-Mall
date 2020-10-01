package com.shop.product;

import com.shop.Action;


public class ProductFactory {

	private static ProductFactory instance = null;
	private ProductFactory() {}

	public static ProductFactory getInstance() {
		if(instance == null) {
			instance = new ProductFactory();
		}
		
		return instance;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		
		if(cmd.equals("productView")) {
			action = new ProductView();
		} else if (cmd.equals("addProduct")) {
			action = new AddProduct();
		} else if (cmd.equals("addProductAction")) {
			action = new AddProductAction();
		} else if (cmd.equals("adminProductList")) {
			action = new AdminProductList();
		} else if (cmd.equals("adminProductView")) {
			action = new AdminProductView();
		} else if (cmd.equals("updateProduct")) {
			action = new UpdateProduct();
		}
		
		return action;
	}

}
