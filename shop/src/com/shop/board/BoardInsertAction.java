package com.shop.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.Action;

public class BoardInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		String b_title = request.getParameter("b_title");
		String b_contents = request.getParameter("b_contents");
		String b_ip = request.getRemoteAddr(); // ip 불러오기
		
		PrintWriter out = response.getWriter();
		
		BoardVO bVo = new BoardVO();
		bVo.setB_title(b_title);
		bVo.setB_contents(b_contents);
		bVo.setM_id(m_id);
		bVo.setB_ip(b_ip);
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertBoard(bVo);
		out.println("<script>alert('글 작성되었습니다.'); location.href='./BoardServlet?cmd=boardList';</script>");
		out.close();
	}

}
