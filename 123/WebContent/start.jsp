<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Date" %>
    <%@ page import="java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>初始化</title>
</head> 
<body>
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
<font size="30">热门书目！</font> 
<br>
<table border="1">
<tr><td>书名</td><td>图书简述</td><td>书籍借出总数</td></tr>
<%
	//load driver
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection ct=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
	Statement sm=ct.createStatement();
	String uri="SELECT * FROM BOOK ORDER BY BORROW_SUM DESC";
	ResultSet rs=sm.executeQuery(uri);
	int a=0;
	while(rs.next() && a<=10)
	{
		a++;
		out.println("<tr>");
		out.println("<td>"+rs.getString(2)+"</td>");
		out.println("<td>"+rs.getString(5)+"</td>");
		out.println("<td>"+rs.getInt(8)+"</td>");
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
<br>
<font size="30">还书</font> 
<br>
<form action="returnbook.jsp">
<input type="submit" value="还书">
</form>
<br>
<br>
<font size="30">个人中心</font> 
<br>
<form action="GRXX.jsp">
<input type="submit" value="进入">
</form>
<br>
<font size="30">分类</font> 
<br>
<table border="1">
<tr><td>分类</td><td>分类名称</td></tr>
<%
	//load driver
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection ct1=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
	Statement sm1=ct1.createStatement();
	String uri1="SELECT * FROM BOOK_SORT";
	ResultSet rs1=sm1.executeQuery(uri1);
	while(rs1.next())
	{
		out.println("<tr>");
		out.println("<td>"+rs1.getString(1)+"</td>");
		out.println("<td>"+rs1.getString(2)+"</td>");
		out.println("<td><form action=\"booksort.jsp\" method=\"post\">");
		out.println("<input type=\"hidden\" name='booksort' value="+rs1.getString(1)+">");
		out.println("<input type=\"submit\" value=\"确定\">");
		out.println("</form></td>");
		out.println("</tr>");
	}
	rs1.close();
	sm1.close();
	ct1.close();
%>
</table>
</CENTER>
</body>
</html>