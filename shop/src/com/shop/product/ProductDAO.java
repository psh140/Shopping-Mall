package com.shop.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.DBConnection;

public class ProductDAO {
	private static ProductDAO instance = null;
	
	private ProductDAO() {
		
	}
	
	public static ProductDAO getInstance() {
		if (instance == null) {
			instance = new ProductDAO();
		}
		return instance;
	}
	
	public List<ProductVO> selectAdminList() {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		try {
			conn = DBConnection.getConnection();
			sql = "select * from product order by p_code desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setP_code(rs.getInt("p_code"));
				pVo.setP_name(rs.getString("p_name"));
				pVo.setP_price(rs.getInt("p_price"));
				pVo.setP_image(rs.getString("p_image"));
				pVo.setP_stat(rs.getString("p_stat"));
				
				list.add(pVo);
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBConnection.close(conn);
		}
		
		return list;
	}
	
	public List<ProductVO> selectProductList() {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		try {
			conn = DBConnection.getConnection();
			sql = "select * from product where p_stat = 'Y' order by p_code desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setP_code(rs.getInt("p_code"));
				pVo.setP_name(rs.getString("p_name"));
				pVo.setP_price(rs.getInt("p_price"));
				pVo.setP_image(rs.getString("p_image"));
				pVo.setP_stat(rs.getString("p_stat"));
				
				list.add(pVo);
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBConnection.close(conn);
		}
		
		return list;
	}
	
	public ProductVO selectProductItem(int p_code) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pVo = new ProductVO();
		
		try {
			conn = DBConnection.getConnection(); // 쿼리문 작성
			sql = "select * from product where p_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_code);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			pVo.setP_code(rs.getInt("p_code"));
			pVo.setP_name(rs.getString("p_name"));
			pVo.setP_price(rs.getInt("p_price"));
			pVo.setP_image(rs.getString("p_image"));
			pVo.setP_stat(rs.getString("p_stat"));

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
		
		return pVo;
	}
	
	public void insertProduct(String p_name, int p_price) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			conn = DBConnection.getConnection();
			sql = "insert into product values(seq_p_code.nextval, ?, ?, 'default', 'N')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_name);
			pstmt.setInt(2, p_price);
			
			pstmt.executeUpdate();
			pstmt.close();
		}  catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
	}
	
	public void updateProduct(ProductVO pVo) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			sql = "update product set p_name = ?, p_price = ?, p_stat = ? " +
					"where p_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getP_name());
			pstmt.setInt(2, pVo.getP_price());
			pstmt.setString(3, pVo.getP_stat());
			pstmt.setInt(4, pVo.getP_code());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
	}
}
