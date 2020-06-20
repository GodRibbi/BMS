package servlet;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.*;
/**
 * Servlet implementation class borrow
 */
@WebServlet("/borrow")
public class borrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrow() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	//初始化数据库
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Users user=new Users();
		user = (Users) request.getSession().getAttribute("user");
		Book book=new Book();
		book = (Book) request.getSession().getAttribute("book");
		
		//对用户的条件判断
		
		
		//1.判断用户信用
		if(user.getFine()>=20) {
			response.sendRedirect("fail.jsp");
			return;
		}
		//2.判断用户借书本数限制
		if(user.getNum_right()<user.getBorrow_now()) {
			response.sendRedirect("fail.jsp");
			return;
		}
		//3.判断书的余量
		if(book.getNum()<=book.getBorrow_num()) {
			response.sendRedirect("fail.jsp");
			return;
		}
		
		//判断可以借出
		//1.编辑record
		Records record =new Records();
		HttpSession record_session=request.getSession(true);
		record_session.setAttribute("record", record);
		
		//获取表中行数
		int count = 0;
		try {
			Connection ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
			String sql="select count(*) from RECORDS";
			PreparedStatement preparedStatement = ct.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
			ct.close();
		} catch (Exception e) {}
		//response.getWriter().println(count);
	    

		record.setId(count+1);
		record.setU_id(user.getId());
		record.setB_id(book.getId());
		//舍弃时分秒
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(new Date());
		Date date=new Date();
		try {
			date =  sdf.parse(s);
		} catch (ParseException e) {}
		
		record.setDate(date);
		record.setBack_date(subtractDays(date,user.getData_right()));
		record.setExit(false);
		record.setReturn_money(0);
		//更新数据
		user.setBorrow_now(user.getBorrow_now()+1);
		book.setBorrow_num(book.getBorrow_num()+1);
		book.setBorrow_sum(book.getBorrow_sum()+1);
		//导入数据库中
		try {
			Connection ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
			Statement state=ct.createStatement(); 
			
			//更新record
			String sql="INSERT INTO RECORDS VALUES('"+record.getId()+"','"+record.getU_id()+"','"+record.getB_id()+"',"
					+ changeDateStr(record.getDate())+","+changeDateStr(record.getBack_date())+",0,0,NULL)";
			state.executeUpdate(sql);
			//response.getWriter().println(sql);
			
			//更新users
			    String sql1="update USERS set borrow_now="+user.getBorrow_now()+" where id="+user.getId();
			    state.executeUpdate(sql1);
			//更新book
			    String sql2="update BOOK set borrow_num="+book.getBorrow_num()+",borrow_sum="
			    +book.getBorrow_sum()+" where id="+book.getId();
			    state.executeUpdate(sql2);
			
//			int i = preparedStatement.executeUpdate();
//			response.getWriter().println(i);
			ct.close();
		} catch (Exception e) {}
		
		//response.getWriter().println("Succeed!");
		//response.getWriter().println("记得在"+record.getBack_date()+"前及时归还书籍");
		
		response.sendRedirect("Success.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	//日期加减函数
	public Date subtractDays(Date dateTime/*待处理的日期*/,int n/*加减天数*/){ 
	     return new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L); 
	   } 
	//日期转换为str
	public String changeDateStr(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String str = simpleDateFormat.format(date);
		str= "to_date('"+str+"','yyyy-mm-dd')";
		return str;
	}
}
