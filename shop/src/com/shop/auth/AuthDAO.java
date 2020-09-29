package com.shop.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shop.DBConnection;

public class AuthDAO {
	private static AuthDAO instance = null;
	
	private AuthDAO() {
		
	}
	
	public static AuthDAO getInstance() {
		if (instance == null) {
			instance = new AuthDAO();
		}
		return instance;
	}
	
	public boolean clientLogin(String m_id, String m_passwd) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean chk = false;
		try {
			conn = DBConnection.getConnection();
			sql = "select count(m_id) from member " +
					"where m_id = ? and m_passwd = ? and m_type = 'C'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_passwd);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			int count = rs.getInt(1);
			
			if (count == 1) {
				chk = true;
			} else {
				chk = false;
			}
			
			rs.close();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
		
		return chk;
	}
}
