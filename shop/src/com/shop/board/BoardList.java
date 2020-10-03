package com.shop.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.Action;

public class BoardList implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "./board/boardList.jsp";
		String pageNum = request.getParameter("pageNum");
		
		int pgNum = 1; // 페이지 번호 초기화
		if (pageNum == null) {
			pgNum = 1;
		} else {
			pgNum = Integer.parseInt(pageNum);
		}
		BoardDAO bDao = BoardDAO.getInstance(); //singleton 생성
		List<BoardVO> list = bDao.selectAll(pgNum, 5); // 페이지 시작위치와 페이지 사이즈
		PagingVO pVo = bDao.pagingBoard(pgNum, 5, 3); // 페이징 사이즈
		
		request.setAttribute("list", list); // list 데이터를 "list" 로 설정하여 view 로 전송
		request.setAttribute("pagedata", pVo); // pVo클래스의 객체를 "pagedata" 로 넘김. view 에 paging 정보 전송
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
