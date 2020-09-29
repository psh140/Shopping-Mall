<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>index</title>
</head>
<body>
<h1>hi</h1>
	<%
  	Connection conn = null;
  	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
  	String user = "shop_admin";
  	String password = "admin";
  	Class.forName("oracle.jdbc.driver.OracleDriver");
  	conn = DriverManager.getConnection(url, user, password);
%>
<%
	PreparedStatement pstmt = null;
	String sql = "";
	ResultSet rs = null;
	
	sql = "select * from member";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next()) {%>
		ID : <%=rs.getString(1) %> <br>
		Name : <%=rs.getString(2) %><br>
		Password : <%=rs.getString(3) %><br>
		Phone : <%=rs.getString(4) %><br>
		Address : <%=rs.getString(5) %><br>
		Date : <%=rs.getString(6) %><br>
		Type : <%=rs.getString(7) %><br>
	<%} 
	
	rs.close();
	%>


</body>
</html>