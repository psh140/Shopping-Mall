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