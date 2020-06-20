<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Date" %>
        <%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成功</title>
</head>
<body>
<CENTER>
<jsp:useBean id="record" class="bean.Records" scope="session"/>
您的还书编号<br>
<%=record.getId() %><br>
<%
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
out.println("记得在"+df.format(record.getBack_date())+"前及时归还书籍"); 
%>
<form action="start.jsp">
<input type="submit" value="返回">
</form>
</CENTER>
</body>
</html>