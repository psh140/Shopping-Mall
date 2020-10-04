# Shopping-Mall
Shopping Mall


init commit  


파일 업로드는 무조건 post로  

hidden으로 넘기면 안됨

``` HTML
<form method="post" enctype="multipart/form-data" action="./BoardServlet?cmd=upload">  
	<input type="hidden" name="cmd" value="??">  
</form>  
```

컴포넌트에 영향받지 않고 get방식으로 넘김

9/30
클라이언트 로그인, 회원가입, 관리자 로그인  

10/1  
관리자 상품관리 페이지 완료  
유저 상품 리스트, 상세 뷰 페이지 완료  

10/2  
해야할 것
장바구니 담기, 장바구니 빼기, 결제페이지 넘기기, 결제페이지 완료  
게시판 만들기  

10/3  
게시판 CRUD 완성  