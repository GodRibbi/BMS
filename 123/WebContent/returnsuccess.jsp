<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>还书成功</title>
</head>
<body>
<CENTER>
<jsp:useBean id="record" class="bean.Records" scope="session"/>
还书成功！！<br>
<% 
if(record.getReturn_money()>0){
	out.println("您已经超出天数限制，欠款"+record.getReturn_money()+"元！");
	out.println("<br>");
}
%>
感谢您的支持！
<form action="start.jsp">
<input type="submit" value="返回">
</form>
</CENTER>
</body>
</html>