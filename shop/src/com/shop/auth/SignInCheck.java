package com.shop.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.Action;

public class SignInCheck implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");

		String m_id = request.getParameter("m_id");
		String m_name = request.getParameter("m_name");
		String m_passwd = request.getParameter("m_passwd");
		String m_phone = request.getParameter("m_phone");
		String m_addr = request.getParameter("m_addr");

		String url = "";
		PrintWriter out = response.getWriter();
		
		AuthDAO aDao = AuthDAO.getInstance();
		int chk;
		
		try {
			chk = aDao.validateCheck(m_id);
			
			if(chk == 0) {
				System.out.println("회원가입 성공");
				aDao.insertID(m_id, m_name, m_passwd, m_phone, m_addr);
				out.println("<script>alert('회원가입을 축하드립니다.'); location.href='./AuthServlet?cmd=authForm';</script>");
			} else {
				System.out.println("회원가입 실패");
				out.println("<script>alert('아이디가 중복입니다.'); history.go(-1);</script>'"); // 경고창 다시짜기	
			}
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		if(chk == 0) {
//			HttpSession session = request.getSession();
//			session.setAttribute("m_id", m_id);
//			url = "./AuthServlet?cmd=main"; // 성공시 메인페이지
//			
//			RequestDispatcher dispatcher = request.getRequestDispatcher(url); // 비밀번호 확인 후 해당 url로 보내기
//			dispatcher.forward(request, response); // 가지고 있는 객체는 싹 다 넘어감.
//		} else {
//			
////			url = "./AuthServlet?cmd=authForm"; // 실패시 다시 로그인화면
//			
//		}
		
	}

}
