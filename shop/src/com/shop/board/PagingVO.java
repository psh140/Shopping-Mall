package com.shop.board;

public class PagingVO {
//	거의 고정된 값	
	int pageNum; // 현재 페이지
	int pageSize; // 페이징 단위
	int groupSize; // 페이지 리스트 단위
//	넘겨받을 값	
	int startPage; // 해당그룹에서의 마지막 페이지
	int endPage;  // 해당 그룹에서의 마지막 페이지
	int lastPage; // 총 레코드의 마지막 페이지
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getGroupSize() {
		return groupSize;
	}
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
}
