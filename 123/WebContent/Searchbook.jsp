<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="java.util.*,java.sql.*" %>
          <jsp:useBean id="user" class="bean.Users" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书搜索</title>
</head>
<body>
欢迎：
<%=user.getId() %>
登录！<br>

<CENTER>
<table>
<tr><td>
<form action="search0" method="post">
<input type="text" name="Book_name" placeholder="请输入要搜索的图书编号或书名" style="width:300px;"/>
<input type="submit" name="g1" value="搜索"/>    
</form>
</td>
</tr></table>
<br>
<table border="1">
<tr><td>图书编号</td><td>书名</td><td>书籍总数</td><td>书籍目前借出数</td>
</tr>
<%
	String x=request.getParameter("Book_name");
	//load driver
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection ct=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
	Statement sm=ct.createStatement();
	String uri="select * from BOOK where (name like '%"+x+"%' or id like '%"+x+"%')";
	ResultSet rs=sm.executeQuery(uri);
	while(rs.next())
	{
		out.println("<tr>");
		out.println("<td>"+rs.getString(1)+"</td>");
		out.println("<td>"+rs.getString(2)+"</td>");
		out.println("<td>"+rs.getInt(6)+"</td>");
		out.println("<td>"+rs.getInt(7)+"</td>");
		out.println("<td><form action=\"borrowbook.jsp\" method=\"post\">");
		out.println("<input type=\"hidden\" name='bookid' value="+rs.getString(1)+">");
		out.println("<input type=\"submit\" value=\"借阅\">");
		out.println("</form></td>");
		out.println("</tr>");
	}
	rs.close();
	sm.close();
	ct.close();
%>
</table>
<form action="start.jsp">
<input type="submit" value="返回">
</form>
</CENTER>
</body>
</html>