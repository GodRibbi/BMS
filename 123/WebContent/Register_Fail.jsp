<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <jsp:useBean id="message" type="bean.Message" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册失败</title>
<table> <td><A href="Main_Register.jsp"><font size=2>用户注册
</font></A></td> <td><A href="Main_Login.jsp"><font size=2>用户登录
</font></A></td>
</table>
</head>
<body>
<center>注册失败 ！请再次核对信息！
<br>
错误信息：<jsp:getProperty property="message" name="message"/>
</center>
</body>
</html>