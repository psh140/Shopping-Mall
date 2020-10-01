package com.shop.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;
import com.shop.auth.AuthDAO;

public class AddProductAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		
		String p_name = request.getParameter("p_name");
		int p_price = Integer.parseInt(request.getParameter("p_price"));

		String url = "";
		PrintWriter out = response.getWriter();
		
		ProductDAO pDao = ProductDAO.getInstance();
		
		
		try {
			pDao.insertProduct(p_name, p_price);
			out.println("<script>alert('상품이 등록되었습니다.'); location.href='./ProductServlet?cmd=adminProductList';</script>");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
