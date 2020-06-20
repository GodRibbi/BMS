<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection ct;
ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");

// Statement state=ct.createStatement();   //容器
// String sql=
// "update BOOK set borrow_num=1 where id=123 ";   //SQL语句
// state.executeUpdate(sql);         //将sql语句上传至数据库执行

Statement sm= ct.createStatement();
ResultSet rs = sm.executeQuery("select * from BOOK");
while(rs.next()){
	out.println(rs.getInt(4));
	out.println("<br>");
}
%>
</body>
</html>