<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书籍详情</title>
</head>
<body>
<CENTER>
<jsp:useBean id="book" class="bean.Book" scope="session"/>
<jsp:useBean id="user" class="bean.Users" scope="session"/>
<%
	String id=request.getParameter("bookid");
	//load driver
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection ct=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
	Statement sm=ct.createStatement();
	ResultSet rs = sm.executeQuery("select * from BOOK");
	while(rs.next()){
	if(id.equals(rs.getString(1))) {
		book.setId(rs.getString(1)); 
		book.setName(rs.getString(2));
		book.setAuthor(rs.getString(3));
		book.setPub(rs.getString(4));
		book.setDescribe(rs.getString(5));
		//书籍总数
		book.setNum(rs.getInt(6));
		//书籍借出数
		book.setBorrow_num(rs.getInt(7));
		//书籍借出总数
		book.setBorrow_sum(rs.getInt(8));
		book.setSort(rs.getString(9));
		book.setRECORD(rs.getDate(10));
		break;
	}
	}
		
	rs.close();
	sm.close();
	ct.close();
%>
<h1>书籍编号：<br><%=book.getId() %></h1><br>
书籍名称：<br><%=book.getName() %><br>
书籍作者：<br><%=book.getAuthor() %><br>
书籍出版社：<br><%=book.getPub() %><br>
书籍描述：<br><%=book.getDescribe() %><br>
书籍类别：<br><%=book.getSort() %><br>
书籍借出总数：<br><%=book.getBorrow_sum() %><br>
书籍余量：<br><%=book.getNum()-book.getBorrow_num() %><br>
<form action="borrow1">
<input type="submit" value="借书">
</form>
</CENTER>
</body>
</html>