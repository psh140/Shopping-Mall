package com.shop.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.Action;

public class AdminCheck implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");

		String m_id = request.getParameter("m_id");
		String m_passwd = request.getParameter("m_passwd");
		
		AuthDAO aDao = AuthDAO.getInstance();
		boolean chk = aDao.adminLogin(m_id, m_passwd);
		String url = "";
		
		PrintWriter out = response.getWriter();
		System.out.println("authcheck로 넘어옴");
		if(chk) {
			HttpSession session = request.getSession();
			session.setAttribute("m_id", m_id);
			url = "./MainServlet?cmd=main"; // 성공시 메인페이지
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url); // 비밀번호 확인 후 해당 url로 보내기
			dispatcher.forward(request, response); // 가지고 있는 객체는 싹 다 넘어감.
		} else {
			out.println("<script>alert('로그인에 실패했습니다.'); history.go(-1);</script>'"); // 경고창 다시짜기
//			url = "./AuthServlet?cmd=authForm"; // 실패시 다시 로그인화면
			
		}
		out.close();
	}



}
