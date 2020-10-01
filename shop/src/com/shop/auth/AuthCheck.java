package com.shop.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.Action;

public class AuthCheck implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");

		String m_id = request.getParameter("m_id");
		String m_passwd = request.getParameter("m_passwd");
		
		AuthDAO aDao = AuthDAO.getInstance();
		boolean chk = aDao.clientLogin(m_id, m_passwd);
		String url = "";
		
		PrintWriter out = response.getWriter();
		
		if(chk) {
			HttpSession session = request.getSession();
			session.setAttribute("m_id", m_id);
			session.setAttribute("m_type", "C");
			url = "./MainServlet?cmd=main"; // ������ ����������
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url); // ��й�ȣ Ȯ�� �� �ش� url�� ������
			dispatcher.forward(request, response); // ������ �ִ� ��ü�� �� �� �Ѿ.
		} else {
			out.println("<script>alert('로그인에 실패했습니다.'); history.go(-1);</script>'"); // ���â �ٽ�¥��
//			url = "./AuthServlet?cmd=authForm"; // ���н� �ٽ� �α���ȭ��
			
		}
		out.close();


	}
	
}
