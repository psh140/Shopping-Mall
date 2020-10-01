package com.shop.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class UpdateProduct implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");
		
		String p_name = request.getParameter("p_name");
		int p_price = Integer.parseInt(request.getParameter("p_price"));
		String p_stat = request.getParameter("p_stat");
		int p_code = Integer.parseInt(request.getParameter("p_code"));
		ProductVO pVo = new ProductVO();
		pVo.setP_name(p_name);
		pVo.setP_price(p_price);
		pVo.setP_stat(p_stat);
		pVo.setP_code(p_code);
		
		ProductDAO pDao = ProductDAO.getInstance();
		pDao.updateProduct(pVo);
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>alert('상품이 수정되었습니다.'); location.href='./ProductServlet?cmd=adminProductList&p_code=" + p_code + "';</script>");
		out.close();
	}
}
