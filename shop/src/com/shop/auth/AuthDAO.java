package com.shop.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shop.DBConnection;
import com.shop.MyDate;

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
	
	public boolean adminLogin(String m_id, String m_passwd) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean chk = false;
		try {
			conn = DBConnection.getConnection();
			sql = "select count(m_id) from member " +
					"where m_id = ? and m_passwd = ? and m_type = 'A'";
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
	
	public int validateCheck(String m_id) throws SQLException { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int cnt = 1; // 0일시 성공 1일시 실패
		
		try {
			conn = DBConnection.getConnection();
			sql = "select count(m_id) from member where m_id = ?";  //null값을 받아내지 않기 위해 count
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1); 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			DBConnection.close(conn);
		}
		
		return cnt;
	}
	
	public void insertID(String m_id, String m_name, String m_passwd, String m_phone, String m_addr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		String m_date = MyDate.getDate();
		
		try {
			conn = DBConnection.getConnection();
			sql = "insert into member values(?, ?, ?, ?, ?, ?, 'C') ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_name);
			pstmt.setString(3, m_passwd);
			pstmt.setString(4, m_phone);
			pstmt.setString(5, m_addr);
			pstmt.setString(6, m_date);
			
			pstmt.executeUpdate(); 
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
		
	}
}
