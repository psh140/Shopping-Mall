package com.shop.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.Action;

public class CommentInsert implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String m_id = (String) session.getAttribute("m_id");
		String b_num = request.getParameter("b_num");
		String c_contents = request.getParameter("c_contents");
		String pageNum = request.getParameter("pageNum");
		
		String c_ip = request.getRemoteAddr(); // ip 불러오기
		CommentVO cVo = new CommentVO();
		cVo.setB_num(Integer.parseInt(b_num));
		cVo.setM_id(m_id);
		cVo.setC_contents(c_contents);
		cVo.setC_ip(c_ip);
		
		PrintWriter out = response.getWriter();
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertComment(cVo);
		
		out.println("<script>alert('댓글이 작성되었습니다.'); location.href='./BoardServlet?cmd=boardView&b_num=" + b_num + "&pageNum="+ pageNum + "';</script>");
		out.close();
		
	}

}
