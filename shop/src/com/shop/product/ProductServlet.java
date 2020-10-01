package com.shop.product;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.shop.Action;
import com.shop.DBConnection;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String cmd = request.getParameter("cmd");
		ProductFactory pf = ProductFactory.getInstance();
		Action action = pf.getAction(cmd);
		if(action != null) {
			action.execute(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		System.out.println(cmd);
		String p_code = request.getParameter("p_code");
		System.out.println(p_code);
		if (cmd.equals("uploadIamge")) {
			uploadOk(request, response, p_code);
			String url = "./ProductServlet?cmd=adminProductList&p_code=" + p_code;
			response.sendRedirect(url);
		} else {
			doGet(request, response);	
		}
	}
	
	
	
	protected void uploadOk(HttpServletRequest request, HttpServletResponse response, String p_code) throws ServletException, IOException  {
		String path = request.getSession().getServletContext().getRealPath("product/images");
		System.out.println(path);
		DiskFileUpload upload = new DiskFileUpload();
		
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection(); 
			List items = upload.parseRequest(request);
			Iterator params = items.iterator();
			FileItem item = null;
			
			params.hasNext(); // 파일 폼
			item = (FileItem)params.next();
			String fileName = item.getName();
			
			System.out.println("fileName : "+fileName);
			String imgFile = path + "\\" + p_code + ".png";
			System.out.println("imgFile : "+imgFile);
			File file = new File(imgFile);
			item.write(file);
			
			PreparedStatement pstmt = null;
			String sql = "";
			
			sql = "update product set p_image = ? where p_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_code);
			pstmt.setInt(2, Integer.parseInt(p_code));
			pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
	}

}
