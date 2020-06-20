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
 * Servlet implementation class returnbook
 */
@WebServlet("/returnbook")
public class returnbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnbook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
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
		Users user=new Users();
		user = (Users) request.getSession().getAttribute("user");
//		//判断id是否合法
//		boolean illegal=true;
//		try {
//			Connection ct;
//			ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
//			Statement sm= ct.createStatement();
//			ResultSet rs = sm.executeQuery("select * from Records");
//			while(rs.next()){
//				if(rs.getString(1).equals(request.getParameter("bookid"))) {
//					illegal=false;
//				}
//			}
//			ct.close();
//		} catch (Exception e) {}
//		if(request.getParameter("bookid")==null||request.getParameter("bookid")==""||illegal) {
//			response.sendRedirect("fail.jsp");
//			return;
//		}	
		int id = Integer.parseInt(request.getParameter("bookid"));	
		//int id=1;
		
		
		//检索id所在位置，并获取其数据
		Records record =new Records();
		HttpSession record_session=request.getSession(true);
		record_session.setAttribute("record", record);
		Book book=new Book();
		HttpSession book_session=request.getSession(true);
		book_session.setAttribute("book", book);
		try {
			Connection ct;
			ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
			Statement sm= ct.createStatement();
			
			
			ResultSet rs = sm.executeQuery("select * from RECORDS");
			while(rs.next()){
			if(id==Integer.parseInt(rs.getString(1))) {
				record.setId(id);
				record.setU_id(rs.getString(2));
				record.setB_id(rs.getString(3));
				record.setDate(rs.getDate(4));
				record.setBack_date(rs.getDate(5));
				record.setExit(false);
				record.setReturn_money(0);
				break;
			}
			}
			
			rs = sm.executeQuery("select * from BOOK");
			while(rs.next()){
			if(record.getB_id().equals(rs.getString(1))) {
				book.setId(rs.getString(1));
				book.setNum(rs.getInt(6));
				book.setBorrow_num(rs.getInt(7));
				book.setBorrow_sum(rs.getInt(8));
				break;
			}
			}
			ct.close();
		} catch (Exception e) {}
		
		
		
		//计算罚款
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(new Date());
		Date date=new Date();
		try {
			date =  sdf.parse(s);
		} catch (ParseException e) {}
		int time =reducedtime(date,record.getBack_date());
		if(time>0) {
			record.setReturn_money(time*1f);//一块钱一天
			user.setFine(user.getFine()+record.getReturn_money());
		}
		else {
			record.setReturn_money(0);
		}
		
		
		//更新重要数据
		record.setAlready_back_date(date);
		record.setExit(true);
		user.setBorrow_now(user.getBorrow_now()-1);
		book.setBorrow_num(book.getBorrow_num()-1);
		try {
			Connection ct;
			ct = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
			Statement state=ct.createStatement();   
			
		    String sql1=
		    		"update RECORDS set exit=1, return_money=" + record.getReturn_money() + 
		    		", already_back_date=" + changeDateStr(date) + " where id="+id;
		    state.executeUpdate(sql1);
		    
		    String sql2="update USERS set borrow_now="+user.getBorrow_now()+",fine=" + user.getFine() 
		    +" where id="+user.getId();
		    state.executeUpdate(sql2);
		    
		    String sql3="update BOOK set borrow_num="+book.getBorrow_num()+" where id="+book.getId();
		    state.executeUpdate(sql3);
		    ct.close();
		} catch (Exception e) {}
		
		
		
		//还书成功
		response.sendRedirect("returnsuccess.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public int reducedtime(Date date1,Date date2) {
		long time;
		time=date1.getTime()-date2.getTime();
		return (int)(time/ 24 / 60 / 60 / 1000);//相等
	}
	
	public String changeDateStr(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String str = simpleDateFormat.format(date);
		str= "to_date('"+str+"','yyyy-mm-dd')";
		return str;
	}
}
