package com.shop.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class BoardView implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "./board/boardView.jsp";
		String b_num = request.getParameter("b_num");
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = bDao.selectBoardItem(Integer.parseInt(b_num));
		
		request.setAttribute("view", bVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
