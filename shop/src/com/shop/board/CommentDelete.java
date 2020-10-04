package com.shop.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class CommentDelete implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b_num = request.getParameter("b_num");
		String c_num = request.getParameter("c_num");
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		bDao.deleteComment(Integer.parseInt(c_num), Integer.parseInt(b_num));
		
		String url = "./BoardServlet?cmd=boardView&b_num=" + b_num + "&pageNum=" + pageNum;
		response.sendRedirect(url);
	}
}
