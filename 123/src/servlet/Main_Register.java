package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Message;
import bean.Users;

/**
 * Servlet implementation class Main_Register
 */
@WebServlet("/Main_Register")
public class Main_Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main_Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//1.加载JDBC数据库驱动程序
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e) {
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Connection ct;
		String id=request.getParameter("id0"),
				name=request.getParameter("name0"),
				institute=request.getParameter("institute0"),
				pro=request.getParameter("pro0"),
				phone=request.getParameter("phone0"),
				sort=request.getParameter("sort0"),
				password=request.getParameter("password0");
		int grade=Integer.parseInt(request.getParameter("grade0"));
		int num_right1,date_right1;
		num_right1=date_right1=0;
		if(sort.equals("老师"))
		{
			num_right1=10;
			date_right1=90;
		}
		if(sort.equals("学生"))
		{
			num_right1=2;
			date_right1=30;
		}

		try {
			ct=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
			String sql="insert into users values (?,?,?,?,?,?,?,?,?,?,0,0.0,1)";
			PreparedStatement stat = ct.prepareStatement(sql);
			stat.setString(1,id);
			stat.setString(2,name);
			stat.setString(3,institute);
			stat.setString(4,pro);
			stat.setInt(5,grade);
			stat.setString(6,phone);
			stat.setString(7,sort);
			stat.setString(8,password);
			stat.setInt(9,num_right1);
			stat.setInt(10,date_right1);

			int line = stat.executeUpdate();
			RequestDispatcher dispatcher=request.getRequestDispatcher("Register_Success.jsp");//转发
			dispatcher.forward(request,response);
		}
		catch(SQLException e)
		{
			Message message=new Message();
			message.setMessage(""+e);
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("Register_Fail.jsp");//转发
			dispatcher.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
