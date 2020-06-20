
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.*;

/**
 * Servlet implementation class start
 */
@WebServlet("/start")
public class start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		//包含所有的初始化
		//这里到时后去同步book和user的数据就行了
		//偷懒没获取数据库的内容了
		
		
		Book book=new Book();
		HttpSession book_session=request.getSession(true);
		book_session.setAttribute("book", book);
		
		book.setId("123"); 
		//书籍总数
		book.setNum(10);
		//书籍借出数
		book.setBorrow_num(1);
		//书籍借出总数
		book.setBorrow_sum(1);
		
		
		
		Users user=new Users();
		HttpSession user_session=request.getSession(true);
		user_session.setAttribute("user", user);
		
		user.setId("123"); 
		//能借几本
		user.setNum_right(10); 
		//能借几天
		user.setData_right(14); 
		//已借本数
		user.setBorrow_now(0); 
		//总罚款数
		user.setFine(4); 
		
		
		response.sendRedirect("start.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
