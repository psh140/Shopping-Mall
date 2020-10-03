package com.shop.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.DBConnection;
import com.shop.MyDate;


public class BoardDAO {
	private static BoardDAO instance = null;
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	public List<BoardVO> selectAll(int pageNum, int pageSize) { // 전체 레코드셋 저장
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = DBConnection.getConnection();
			sql = "select * from " + // 게시글 페이징
    				"(select * from " +
    				"(select rownum as SEQ, b_num, m_id, b_title, b_contents, b_date, b_ip from " + // 글정보
    				"(select * from board order by b_num desc)" + // 게시글 나열
    				") where SEQ >= ?" + // 페이징 시작 위치 지정. 페이지 번호
    				") where rownum <= ?"; // pagenum번부터 시작해서 pageSize개의 글 페이지 단위(상수)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum - 1) * pageSize + 1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setB_num(rs.getInt("b_num"));
				bVo.setM_id(rs.getString("m_id"));
				bVo.setB_title(rs.getString("b_title"));
				bVo.setB_contents(rs.getString("b_contents"));
				bVo.setB_date(rs.getString("b_date"));
				bVo.setB_ip(rs.getString("b_ip"));
				
				System.out.println(bVo.getB_num());
				list.add(bVo);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
		System.out.println("selectAll 호출");
		
		return list;
	}
	
	public PagingVO pagingBoard(int pageNum, int pageSize, int groupSize) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PagingVO pVo = null;
		int startPage = 1;
		int endPage = 0;
		int  lastPage = 0;
		
		try {
			conn = DBConnection.getConnection();
			sql = "select count(b_num) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int total = rs.getInt(1);
			
			rs.close();
			pstmt.close();
			
			lastPage = (total - 1) / pageSize + 1;
    		startPage = (pageNum - 1) / groupSize * groupSize + 1;
			endPage = (pageNum -1) / groupSize * groupSize + groupSize;   
			
			pVo = new PagingVO();
			pVo.setPageNum(pageNum);
			pVo.setPageSize(pageSize);
			pVo.setGroupSize(groupSize);
			pVo.setStartPage(startPage);
			pVo.setEndPage(endPage);
			pVo.setLastPage(lastPage);
			
			System.out.println("pVo출력");
			System.out.println(pVo.getPageNum());
			System.out.println(pVo.getPageSize());
			System.out.println(pVo.getGroupSize());
			System.out.println(pVo.getStartPage());
			System.out.println(pVo.getEndPage());
			System.out.println(pVo.getLastPage());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
		System.out.println("pagingBoard 호출");
		return pVo;
	}
	
	public BoardVO selectBoardItem(int b_num) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO bVo = null;
		
		try {
			conn = DBConnection.getConnection();
			sql = "select * from board where b_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			rs.next();
			
			bVo = new BoardVO();
			bVo.setB_num(rs.getInt("b_num"));
			bVo.setM_id(rs.getString("m_id"));
			bVo.setB_title(rs.getString("b_title"));
			bVo.setB_contents(rs.getString("b_contents").replace("\r\n", "<br>"));
			bVo.setB_date(rs.getString("b_date"));
			bVo.setB_ip(rs.getString("b_ip"));
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
		return bVo;
	}
	
	public void insertBoard(BoardVO bVo) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			sql = "insert into board values(" +
					"seq_b_num.nextval, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getM_id());
			pstmt.setString(2, bVo.getB_title());
			pstmt.setString(3, bVo.getB_contents());
			pstmt.setString(4, MyDate.getDate());
			pstmt.setString(5, bVo.getB_ip());
			pstmt.executeUpdate(); //sql문 실행
			System.out.println("insertBoard 실행");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
	}
	
	public void updateBoard(BoardVO bVo) {
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			sql = "update board set b_title = ?, b_contents = ? where b_num = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bVo.getB_title());
			pstmt.setString(2, bVo.getB_contents());
			pstmt.setInt(3, bVo.getB_num());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn);
		}
	}
	
	public void deleteBoard(int b_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			conn = DBConnection.getConnection();
			
			sql = "delete from board where b_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBConnection.close(conn);
		}
		
	}
}
