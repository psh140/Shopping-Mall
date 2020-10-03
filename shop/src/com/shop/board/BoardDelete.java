package com.shop.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class BoardDelete implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b_num = request.getParameter("b_num");
		PrintWriter out = response.getWriter();
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		bDao.deleteBoard(Integer.parseUnsignedInt(b_num));
		System.out.println("BoardDelete 호출");
		
		response.sendRedirect("./BoardServlet?cmd=boardList");
//		out.println("<script>alret('삭제되었습니다.'); location.href='./BoardServlet?cmd=boardList';</script>");
//		out.close();
	}

}
