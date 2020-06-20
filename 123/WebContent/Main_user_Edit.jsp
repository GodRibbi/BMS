<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
             <jsp:useBean id="user" type="bean.Users" scope="session"></jsp:useBean>
     <jsp:useBean id="edituser" type="bean.Edit_Users" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息编辑</title>
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
<center>
<form action="z3" method="post">
用户编号：<jsp:getProperty property="ID" name="edituser"/>
<input  type="hidden" value="<jsp:getProperty property="ID" name="edituser"/>" name="id0"><br>
用户名：<input type="text" value="<jsp:getProperty property="NAME" name="edituser"/>" name="name1"><br>
学院：<input type="text" value="<jsp:getProperty property="INSTITUTE" name="edituser"/>" name="institute1"><br>
专业/办公室：<input type="text" value="<jsp:getProperty property="PRO" name="edituser"/>" name="pro1"><br>
年级：<input type="text" value="<jsp:getProperty property="GRADE" name="edituser"/>" name="grade1"><br>
联系电话：<input type="text" value="<jsp:getProperty property="PHONE" name="edituser"/>" name="phone1"><br>

密码：<input type="text" value="<jsp:getProperty property="PASSWORD" name="edituser"/>" name="password1"><br>

<input type="submit" name="g" value="提交"/>
</form>

</center>
</body>
</html>