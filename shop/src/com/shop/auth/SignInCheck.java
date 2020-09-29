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
				System.out.println("ȸ������ ����");
				aDao.insertID(m_id, m_name, m_passwd, m_phone, m_addr);
				out.println("<script>alert('ȸ�������� ���ϵ帳�ϴ�.'); location.href='./AuthServlet?cmd=authForm';</script>");
			} else {
				System.out.println("ȸ������ ����");
				out.println("<script>alert('���̵� �ߺ��Դϴ�.'); history.go(-1);</script>'"); // ���â �ٽ�¥��	
			}
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		if(chk == 0) {
//			HttpSession session = request.getSession();
//			session.setAttribute("m_id", m_id);
//			url = "./AuthServlet?cmd=main"; // ������ ����������
//			
//			RequestDispatcher dispatcher = request.getRequestDispatcher(url); // ��й�ȣ Ȯ�� �� �ش� url�� ������
//			dispatcher.forward(request, response); // ������ �ִ� ��ü�� �� �� �Ѿ.
//		} else {
//			
////			url = "./AuthServlet?cmd=authForm"; // ���н� �ٽ� �α���ȭ��
//			
//		}
		
	}

}
