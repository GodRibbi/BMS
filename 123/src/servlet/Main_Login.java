package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import bean.Users;

/**
 * Servlet implementation class Main_Login
 */
@WebServlet("/Main_Login")
public class Main_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main_Login() {
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
		Connection con;
		Statement sql;
		ResultSet rs;
		Users reg=new Users();
		HttpSession session=request.getSession();
		session.setAttribute("user", reg);
		String loginame=request.getParameter("loginame").trim(),	 
		password=request.getParameter("password").trim();
		
		
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
			sql=con.createStatement();
			String a="select * from USERS where id='"+loginame+"' and password ='"+password+"' and state_now=1";
			rs=sql.executeQuery(a);
			reg.setId(loginame);
			//reg.setAdmin_password(rs.getString(4));
			
			
			if(rs.next())
			{
			reg.setNum_right(rs.getInt(9));
			reg.setData_right(rs.getInt(10));
			reg.setFine(rs.getFloat(12));
			reg.setBorrow_now(rs.getInt(11));
			
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("start.jsp");//转发
			dispatcher.forward(request,response);
			rs.close();
			sql.close();
			con.close();
			}
			else
			{
				
			RequestDispatcher dispatcher=request.getRequestDispatcher("Login_false.jsp");//转发
			dispatcher.forward(request,response);
			rs.close();
			sql.close();
			con.close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//RequestDispatcher dispatcher=request.getRequestDispatcher("Login_false.jsp");//转发
				//dispatcher.forward(request,response);
				e.printStackTrace();
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
