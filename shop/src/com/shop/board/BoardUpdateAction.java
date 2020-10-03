package com.shop.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class BoardUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b_num = request.getParameter("b_num");
		String b_title = request.getParameter("b_title");
		String b_contents = request.getParameter("b_contents");
		PrintWriter out = response.getWriter();
		
		BoardVO bVo = new BoardVO();
		bVo.setB_num(Integer.parseUnsignedInt(b_num));
		bVo.setB_title(b_title);
		bVo.setB_contents(b_contents);
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateBoard(bVo);
		out.println("<script>alert('글 수정되었습니다.'); location.href='./BoardServlet?cmd=boardView&b_num=" + b_num + "';</script>");
		out.close();
	}

}
