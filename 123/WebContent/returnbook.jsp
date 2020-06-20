<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>还书</title>
</head>
<body>


<!-- <form action="returnbook1" method="post"> -->
<!-- 还书编号：<input type="text" name="bookid"><br> -->
<!-- <input type="submit" value="还书"> -->
<jsp:useBean id="user" class="bean.Users" scope="session"/>
<jsp:useBean id="record" class="bean.Records" scope="session"/>
<CENTER>

<table>
<table border="1">
<tr><td>借阅编号</td><td>借阅书籍</td></tr>
<%
boolean isNull=true;
List<String> recordid=new ArrayList<>();
List<String> bookid=new ArrayList<>();
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection ct;
ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
Statement sm= ct.createStatement();
ResultSet rs = sm.executeQuery("select * from RECORDS");
while(rs.next()){
	if(rs.getString(2).equals(user.getId())&&rs.getInt(6)!=1){
		recordid.add(rs.getString(1));
		bookid.add(rs.getString(3));
 		isNull=false;
	}
}
for(int i=0;i<bookid.size();i++){
	rs = sm.executeQuery("select * from BOOK where id=" +"'"+bookid.get(i)+"'");
	rs.next();
		out.println("<tr>");
		out.println("<td>"+recordid.get(i)+"</td>");
		out.println("<td>"+rs.getString(2)+"</td>");
		out.println("<td>");
		out.println("<form action=\"returnbook1\" method=\"post\">");
		out.println("<input type=\"hidden\" name='bookid' value="+recordid.get(i)+">");
		out.println("<input type=\"submit\" value=\"还书\">");
		out.println("</form>");
		out.println("</td>");
		out.println("</tr>");
}
ct.close();
if(isNull){
	out.println("<tr>");
	out.println("<td>"+"空"+"</td>");
	out.println("<td>"+"空"+"</td>");
	out.println("<td>");
}
%>
</table>
<form action="start.jsp">
<input type="submit" value="返回">
</form>
<!-- </form> -->
</CENTER>
</body>
</html>