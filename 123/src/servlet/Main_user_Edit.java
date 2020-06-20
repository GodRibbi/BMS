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

import bean.Edit_Users;

import bean.Users;

/**
 * Servlet implementation class Main_user_Edit
 */
@WebServlet("/Main_user_Edit")
public class Main_user_Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main_user_Edit() {
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
		HttpSession session=request.getSession();
		String User_ID=((Users)session.getAttribute("user")).getId();
		Connection ct;
		Statement sm;
		ResultSet rs;
		Edit_Users edituser=new Edit_Users();
		request.setAttribute("edituser", edituser);
		try {
			ct=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","system","123456");
			sm=ct.createStatement();
			String uri="select id,name,institute,pro,grade,phone,password from users where id='"+User_ID+"'";
			rs=sm.executeQuery(uri);
			if(rs.next()) {
				edituser.setID(rs.getString(1));
				edituser.setNAME(rs.getString(2));
				edituser.setINSTITUTE(rs.getString(3));
				edituser.setPRO(rs.getString(4));
				edituser.setGRADE(rs.getInt(5));
				edituser.setPHONE(rs.getString(6));
				edituser.setPASSWORD(rs.getString(7));

				
			}
			//editbook.setID(rs.getString(1));
			rs.close();
			sm.close();
			ct.close();
			RequestDispatcher dispatcher=request.getRequestDispatcher("Main_user_Edit.jsp");//转发
			dispatcher.forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
