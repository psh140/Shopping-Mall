package com.shop.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class CommentUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String c_num = request.getParameter("c_num");
		String b_num = request.getParameter("b_num");
		String c_contents = request.getParameter("c_contents");
		String pageNum = request.getParameter("pageNum");
		
		CommentVO cVo = new CommentVO();
		cVo.setB_num(Integer.parseInt(b_num));
		cVo.setC_num(Integer.parseInt(c_num));
		cVo.setC_contents(c_contents);
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateComment(cVo);
		
		String url = "./BoardServlet?cmd=boardView&b_num=" + b_num + "&pageNum=" + pageNum;
		response.sendRedirect(url);
	}

}
