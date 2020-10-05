package com.shop.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shop.DBConnection;

public class CartDAO {

	private static CartDAO instance = null;
	
	private CartDAO() {
		
	}
	
	public static CartDAO getInstance() {
		if (instance == null) {
			instance = new CartDAO();
		}
		return instance;
	}
	
	public void insertCart(String m_id, int p_code, int p_count) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cr_id = 0;
		
		try {
			conn = DBConnection.getConnection();
			sql = "select count(cr_id) from cart_main where m_id = ?"; // cart_main에 해당 m_id가 장바구니를 가지고 있는지 판단
			
			pstmt = conn.prepareStatement(sql);
	 		pstmt.setString(1, m_id);
	 		rs = pstmt.executeQuery();
	 		rs.next();
	 		
	 		int count_cr_id = rs.getInt(1);
	 		System.out.println("장바구니 존재 여부 판단 : " + count_cr_id);
	 		if (count_cr_id == 1) { // 장바구니가 있으면 해당 장바구니의 cr_id를 가져옴
		 		rs.close();
	 			pstmt.close();
	 			sql = "select * from cart_main where m_id = ?";
	 			pstmt = conn.prepareStatement(sql);
	 			pstmt.setString(1, m_id);
	 			rs = pstmt.executeQuery();
	 			rs.next();
	 			
	 			cr_id = rs.getInt("cr_id");
	 			System.out.println("if문 장바구니 존재 여부 판단 : " + cr_id);
	 			rs.close();
	 			pstmt.close();
	 		} else { // 장바구니가 없으면 cart_main에 장바구니 생성. 즉 cr_id를 생성 후 cr_id를 가져옴
	 			rs.close();
	 			pstmt.close();
	 			sql = "insert into cart_main values(seq_cr_id.nextval, ?)";
	 			pstmt = conn.prepareStatement(sql);
	 			pstmt.setString(1, m_id);
	 			pstmt.executeUpdate();
	 			pstmt.close();
	 			
	 			sql = "select * from cart_main where m_id = ?";
	 			pstmt = conn.prepareStatement(sql);
	 			pstmt.setString(1, m_id);
	 			rs = pstmt.executeQuery();
	 			rs.next();
	 			
	 			cr_id = rs.getInt("cr_id");
	 			System.out.println("else문 장바구니 존재 여부 판단 : " + cr_id);
	 			rs.close();
	 			pstmt.close();
	 		}
	 		
	 		sql = "select count(crs_id) from cart_sub where cr_id = ? and p_code = ?"; // 해당 상품이 cart_main에서 받아온 crs_id에 존재하는지 판단
	 		pstmt = conn.prepareStatement(sql);
	 		pstmt.setInt(1, cr_id);
	 		pstmt.setInt(2, p_code);
			rs = pstmt.executeQuery();
			rs.next();
			int count_crs_id = rs.getInt(1); // 해당 상품이 cart_sub에 존재하는지 여부 판단
			rs.close();
			pstmt.close();
			System.out.println("cart_sub 존재 여부 판단 : " + count_crs_id);
			if (count_crs_id == 1) { // 존재하면 해당 cr_id, p_code를 가진 p_count를 업데이트
				sql = "update cart_sub set p_count = p_count + ? where cr_id = ? and p_code = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, p_count);
				pstmt.setInt(2, cr_id);
				pstmt.setInt(3, p_code);
				System.out.println("if문 cart_sub 존재 여부 판단 : " + count_crs_id);
				pstmt.executeUpdate();
				pstmt.close();
			} else { //존재하지 않으면 해당 상품을 추가
				pstmt.close();
				sql = "insert into cart_sub values(SEQ_CRS_ID.nextval, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				System.out.println("cr_id : " + cr_id + " p_code : " + p_code + " p_count : " + p_count);
				pstmt.setInt(1, cr_id);
				pstmt.setInt(2, p_code);
				pstmt.setInt(3, p_count);
				System.out.println("else 문 cart_sub 존재 여부 판단 : " + count_crs_id);
				pstmt.executeUpdate();
				pstmt.close();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
	}
}
