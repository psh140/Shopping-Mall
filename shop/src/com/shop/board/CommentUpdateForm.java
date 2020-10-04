package com.shop.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class CommentUpdateForm implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "./board/commentUpdate.jsp";
		String b_num = request.getParameter("b_num");
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO boardVo = bDao.selectBoardItem(Integer.parseInt(b_num));
		
		request.setAttribute("view", boardVo);
		
		String c_num = request.getParameter("c_num");
		CommentVO cVo = bDao.selectCommentItem(Integer.parseInt(c_num));
		String c_contents = cVo.getC_contents().replace("<br>", "\r\n");

		cVo.setC_contents(c_contents);
		
		request.setAttribute("comment", cVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
