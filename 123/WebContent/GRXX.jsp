<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <jsp:useBean id="user" type="bean.Users" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎进入图书管理系统</title>
</head>
<body>
您的ID为
<jsp:getProperty property="id" name="user"/><br>
您的借书权限为
<jsp:getProperty property="num_right" name="user"/><br>
您的借书日期权限为
<jsp:getProperty property="data_right" name="user"/><br>
您目前已借阅
<jsp:getProperty property="borrow_now" name="user"/><br>
您目前罚款为
<jsp:getProperty property="fine" name="user"/><br>
<form action="z2" method="post">
<input type="submit" name="g" value="编辑个人信息"/>
</form>
<form action="start.jsp">
<input type="submit" value="返回">
</form>
</body>
</html>