package com.shop.payment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.Action;

public class AddCartList implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		PrintWriter out = response.getWriter();
		if(m_id == null) {
			out.println("<script>alert('로그인이 필요합니다.'); location.href='./AuthServlet?cmd=authForm';</script>");
			out.close();
		} else {
			int p_code = Integer.parseInt(request.getParameter("p_code"));
			int p_count = Integer.parseInt(request.getParameter("p_count"));
			System.out.println("p_code : " + p_code + " p_count : " + p_count);
			CartDAO cDao = CartDAO.getInstance();
			cDao.insertCart(m_id, p_code, p_count);
			
			String url = "./ProductServlet?cmd=productView&p_code=" + p_code;
			response.sendRedirect(url);
			
			out.println("<script>alert('장바구니에 상품을 담았습니다.'); location.href='./ProductServlet?cmd=productView&p_code=" + p_code + "';</script>");
			out.close();
		}
		
		out.close();
	}
	
}
