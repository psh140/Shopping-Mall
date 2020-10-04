package com.shop.payment;

public class CartVO {
	int cr_id;
	String m_id;
	
	int crs_id;
	int p_code;
	int p_count;
	
	public int getCr_id() {
		return cr_id;
	}
	public void setCr_id(int cr_id) {
		this.cr_id = cr_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getCrs_id() {
		return crs_id;
	}
	public void setCrs_id(int crs_id) {
		this.crs_id = crs_id;
	}
	public int getP_code() {
		return p_code;
	}
	public void setP_code(int p_code) {
		this.p_code = p_code;
	}
	public int getP_count() {
		return p_count;
	}
	public void setP_count(int p_count) {
		this.p_count = p_count;
	}
	
	
}
