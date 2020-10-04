package com.shop.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class BoardDelete implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b_num = request.getParameter("b_num");
//		PrintWriter out = response.getWriter();
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.deleteCommentAll(Integer.parseInt(b_num));
		bDao.deleteBoard(Integer.parseInt(b_num));
		
		response.sendRedirect("./BoardServlet?cmd=boardList");
//		out.println("<script>alret('삭제되었습니다.'); location.href='./BoardServlet?cmd=boardList';</script>");
//		out.close();
	}

}
