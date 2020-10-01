package com.shop.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class ProductView implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "./product/productView.jsp";
		String p_code = request.getParameter("p_code");
		System.out.println(p_code);
		ProductDAO pDao = ProductDAO.getInstance();
		ProductVO pVo = pDao.selectProductItem(Integer.parseInt(p_code));
		
		request.setAttribute("view", pVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
