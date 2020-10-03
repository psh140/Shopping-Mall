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

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <link href="./Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="./Resources/css/shop-homepage.css" rel="stylesheet">
  
  <script>
	function goList(num) {
		location.href = './BoardServlet?cmd=boardList&pageNum=' + num;
	}
	
	function goUpdate(num) {
		location.href = './BoardServlet?cmd=boardUpdate&b_num=' + num;
	}
	
	function Delete(num) {
		if(confirm('정말 삭제하시겠습니까?') == true) {
			location.href = './BoardServlet?cmd=boardDelete&b_num=' + num;
			return true;
		} else {
			return false;
		}
		
	}
	
	
</script>

</head>

<body>

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
            	<c:when test="${sessionScope.m_type == null}">
            		<a class="nav-link" href="./ProductServlet?cmd=cartList">장바구니</a> <!-- 비로그인이면 장바구니 -->
            	</c:when>
            	<c:when test="${sessionScope.m_type == 'C'}">
            		<a class="nav-link" href="./ProductServlet?cmd=cartList">장바구니</a> <!-- 로그인일시 장바구니 -->
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
          <a href="#" class="list-group-item">Q&A</a>
        </div>

      </div>
      <!-- /.col-lg-3 -->
      <!-- 바뀔 영역 -->
      <div class="col-lg-9">

        <div class="row">
			
			      <div class="col-lg-8">

        <!-- Title -->
        <h1 class="mt-4">${view.b_title}</h1>

        <!-- Author -->
        <p class="lead">
          by
          <a href="#">${view.m_id}</a><br>
          ${view.b_ip }
        </p>

        <hr>

        <!-- Date/Time -->
        <p>${view.b_date} 에 작성</p>

        <hr>

        <p>글번호 : ${view.b_num}</p>
        <hr>

        <!-- Post Content -->
		${view.b_contents}
        <hr>
		
		<input type="button" class="btn btn-primary" value="리스트" onclick="goList(${param.pageNum});">&nbsp;

		<c:if test="${sessionScope.m_id == view.m_id}">
			<input type="button" class="btn btn-primary" value="수정" onclick="goUpdate(${view.b_num});">&nbsp;
		</c:if>
		<c:if test="${sessionScope.m_id == view.m_id || sessionScope.m_type == 'A'}">
			<input type="button" class="btn btn-primary" value="삭제" onclick="Delete(${view.b_num});">	
		</c:if>
		

        <!-- Comments Form -->
        <div class="card my-4">
          <h5 class="card-header">댓글:</h5>
          <div class="card-body">
            <form>
              <div class="form-group">
                <textarea class="form-control" rows="3"></textarea>
              </div>
              <button type="submit" class="btn btn-primary">댓글작성</button>
            </form>
          </div>
        </div>

        <!-- Single Comment -->
        <div class="media mb-4">
          
          <div class="media-body">
            <h5 class="mt-0">댓글 작성자 이름</h5>
            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
          </div>
        </div>

        <!-- Comment with nested comments -->
        <div class="media mb-4">
          <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
          <div class="media-body">
            <h5 class="mt-0">Commenter Name</h5>
            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.

            <div class="media mt-4">
              <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
              <div class="media-body">
                <h5 class="mt-0">Commenter Name</h5>
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
              </div>
            </div>

            <div class="media mt-4">
              <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
              <div class="media-body">
                <h5 class="mt-0">Commenter Name</h5>
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
              </div>
            </div>

          </div>
        </div>

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

</body>

</html>
