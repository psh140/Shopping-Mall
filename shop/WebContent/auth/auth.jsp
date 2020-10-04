<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" 
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	 <!-- Custom styles for this template -->
	 <link href="./Resources/css/shop-homepage.css" rel="stylesheet">

    <title>로그인 폼</title>


  </head>

  <body cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="500" height="500">

	<div class="card align-middle" style="width:20rem; border-radius:20px;">
		<div class="card-title" style="margin-top:30px;">
			<h2 class="card-title text-center" style="color:#113366;">로그인 폼</h2>
		</div>
		<div class="card-body">
      <form class="form-signin" method="POST" onSubmit="logincall();return false" action="AuthServlet?cmd=authCheck">
        <h5 class="form-signin-heading">로그인 정보를 입력하세요</h5>
        <label for="inputEmail" class="sr-only">Your ID</label>
        <input type="text" id="uid" class="form-control" placeholder="Your ID" name="m_id" required autofocus ><BR>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="upw" class="form-control" placeholder="Password" name="m_passwd" required ><br>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit">로 그 인</button>
        <a href="./AuthServlet?cmd=adminAuthForm">관리자 로그인</a>
      </form>
      
		</div>
	</div>

	<div class="modal">
	</div>
  </body>
</html>