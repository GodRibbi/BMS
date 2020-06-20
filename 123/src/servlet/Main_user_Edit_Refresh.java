package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Message;

/**
 * Servlet implementation class Main_user_Edit_Refresh
 */
@WebServlet("/Main_user_Edit_Refresh")
public class Main_user_Edit_Refresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main_user_Edit_Refresh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		Statement sm;
		int rs;
		String id0=request.getParameter("id0");

		String name=request.getParameter("name1");
		String institute=request.getParameter("institute1");
		String pro=request.getParameter("pro1");
		int grade=Integer.parseInt(request.getParameter("grade1"));
		String phone=request.getParameter("phone1");

		String password=request.getParameter("password1");


		
		try {
			ct=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
			
			String sql="update users set name=?,institute=?,pro=?,grade=?,phone=?,password=?"
					+ " where id=?";
			PreparedStatement stat = ct.prepareStatement(sql);

			stat.setString(1,name);
			stat.setString(2,institute);
			stat.setString(3,pro);
			stat.setInt(4,grade);
			stat.setString(5,phone);

			stat.setString(6,password);

			stat.setString(7,id0);
			int line = stat.executeUpdate();
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("user_edit_Success.jsp");//转发
			dispatcher.forward(request,response);
		}
		catch(SQLException e)
		{
			Message message=new Message();
			message.setMessage(""+e);
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("user_edit_Fail.jsp");//转发
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
