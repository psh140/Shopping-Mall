package com.shop.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.Action;

public class BoardInsertForm implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		String url = "./board/boardInsert.jsp";
		PrintWriter out = response.getWriter();
		
		if(m_id == null) {
			out.println("<script>alert('로그인이 필요합니다.'); location.href='./AuthServlet?cmd=authForm';</script>");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		out.close();
	}
}
