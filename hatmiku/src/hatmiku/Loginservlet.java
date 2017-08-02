package hatmiku;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;

import com.mysql.jdbc.Statement;

public class Loginservlet extends HttpServlet {
	private Connection conn;
	static Statement st;
	public void init() throws ServletException {
		super.init();
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 数据库连接url
			String url = "jdbc:mysql://localhost:3306/hellomysql";
			// 获取数据库连接
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("数据库连接成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");  
        String password = request.getParameter("pwd"); 
        boolean flag =false;
		try {
			String sql = "select * from staff";		
			st = (Statement) conn.createStatement();	
			ResultSet rs = st.executeQuery(sql);	
			while (rs.next()) {	
				String sqlname = rs.getString("name");
				String sqlpassword = rs.getString("password");
				if(sqlname.equals(username)&&sqlpassword.equals(password))
				{
					flag=true;
					break;
				}
			}
			//conn.close();	
		} catch (SQLException e) {
			System.out.println("登录数据错误");
			System.out.println(e.toString());
		}
		if(flag){
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			 response.sendRedirect("/hatmiku/index.jsp"); 
		}
		else {
			/*
			 *保存错误信息到request域，并转发到login.jsp页面
			 */
			request.setAttribute("msg","用户名或密码错误！");
			RequestDispatcher qr = request.getRequestDispatcher("login.jsp");//得到转发器
			qr.forward(request, response);//转发
		}
	
	}
	public void destroy(){
		try {
			System.out.println("=  -  =");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库关闭失败");
		}
	}
}
