<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <jsp:useBean id="message" type="bean.Message" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息修改失败</title>
</head>
<body>
<center>个人信息修改失败！请再次确认并检查您的个人信息
<br>
错误信息：<jsp:getProperty property="message" name="message"/></center>
</body>
</html>