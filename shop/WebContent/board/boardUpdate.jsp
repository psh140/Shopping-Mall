<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="UTF-8">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>게시글 수정</title>

  <!-- Bootstrap core CSS -->
  <link href="./Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="./Resources/css/shop-homepage.css" rel="stylesheet">
  
  <script>
	function goInsert() {
		location.href = './BoardServlet?cmd=boardInsertAction';
	}
</script>

</head>

<body>
<% if (session.getAttribute("m_id") != null) { %>
     <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
    <c:if test="${sessionScope.m_id != null}">
    	<a class="navbar-brand" href="#">${sessionScope.m_id}님 환영합니다.</a>
    </c:if>
      
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" 
      aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <c:choose>
            	<c:when test="${sessionScope.m_id == null}">
            		<a class="nav-link" href="./AuthServlet?cmd=authForm">로그인</a><!-- (로그아웃) (로그인 페이지에 관리자 로그인) -->
            	</c:when>
            	<c:when test="${sessionScope.m_id != null}">
            		<a class="nav-link" href="./AuthServlet?cmd=logoutAction">로그아웃</a>
            	</c:when>
            </c:choose>
              <span class="sr-only">(current)</span>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./AuthServlet?cmd=signInForm">회원가입</a>
          </li>
          <li class="nav-item">
      		<c:choose>
            	<c:when test="${sessionScope.m_type == 'C'}">
            		<a class="nav-link" href="./ProductServlet?cmd=cartList">장바구니</a> <!-- 로그인, 비로그인일시 장바구니 -->
            	</c:when>
            	<c:when test="${sessionScope.m_type == 'A'}">
            		<a class="nav-link" href="./ProductServlet?cmd=addProduct">상품등록</a> <!-- 관리자일시 상품등록 -->
            		
            	</c:when>
            </c:choose>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">공지사항</a>
          </li>
          <!--<li class="nav-item">
            <a class="nav-link" href="#">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
          </li> -->
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">

        <h1 class="my-4">Shop Name</h1>
        <div class="list-group">
        	<c:choose>
        		<c:when test="${sessionScope.m_type eq 'A'}">
        		<a href="./ProductServlet?cmd=adminProductList" class="list-group-item">관리자 상품관리</a>
        		<a href="./BoardServlet?cmd=boardList" class="list-group-item">게시판 관리</a>
        		</c:when>
        		<c:when test="${sessionScope.m_type eq 'C' || sessionScope.m_type eq null}">
        		<a href="./MainServlet?cmd=main" class="list-group-item">상품</a>
        		<a href="./BoardServlet?cmd=boardList" class="list-group-item">게시판</a>
        		</c:when>
        	</c:choose>
        	<c:if test="${sessionScope.m_type eq 'A'}">
        		<a class="list-group-item" href="./ProductServlet?cmd=AdminPayment">결제관리</a> <!-- 관리자일시 상품등록 -->
        	</c:if>
          <a href="#" class="list-group-item">Q&A</a>
        </div>

      </div>
      <!-- /.col-lg-3 -->
      <!-- 바뀔 영역 -->
      <div class="col-lg-9">

        <div class="row">
			
			      <div class="col-lg-8">
		<form action="./BoardServlet?cmd=boardUpdateAction" method="post">
		<input type="hidden" name="b_num" value="${view.b_num}">
		<input type="hidden" name="pageNum" value="${param.pageNum}">
        <!-- Title -->
		<br>
		<h1>수정하기</h1>
        <hr>
		제목 <input type="text" name="b_title" style="width: 540px;" value="${view.b_title}">
        <hr>
        <!-- Post Content -->
                 내용
		<textarea name="b_contents" cols="74" rows="25">${view.b_contents}</textarea>
		
        <hr>
		
		<input type="submit" class="btn btn-primary" value="수정하기">&nbsp;
		<hr>
		</form>
      </div>

        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->
      <!-- .바뀔영역 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="./vendor/jquery/jquery.min.js"></script>
  <script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<%} else { %>
	<script>alert('로그인이 필요합니다.'); location.href='./AuthServlet?cmd=authForm';</script>
<%}%>
</body>

</html>
