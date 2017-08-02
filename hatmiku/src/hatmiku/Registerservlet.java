package hatmiku;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Registerservlet extends HttpServlet {
	//private static final long serialVersionUID = 1789481329876401944L;
	private Connection conn;
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
			System.out.println("数据库连接失败");
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username"); 
		String password0 = request.getParameter("pwd0");
		String password1 = request.getParameter("pwd1");
		String email = request.getParameter("email");
		String telphone = request.getParameter("tel");
		String sex = request.getParameter("sex");
		
		//返回错误界面
		if(username.equals("")){
			/*
			 * 用户名不能为空！
			 */
			request.setAttribute("regmsg", "用户名不能为空！");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");//得到转发器
			qr.forward(request, response);//转发
		}
		else if(email.equals("")){
			/*
			 *邮箱不能为空
			 */
			request.setAttribute("regmsg", "邮箱不能为空！");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//转发
		}
		else if(password0.equals("")){
			/*
			 *密码不能为空！
			 *转发至request.jsp
			 */
			request.setAttribute("regmsg", "密码为不能空！");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//转发
		}
		else if(!password0.equals(password1)){
			/*
			 *将错误信息存入request域中，然后转发至register.jsp
			 *两次密码输入不一致 
			 */
			request.setAttribute("regmsg", "两次密码输入不一致！");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//转发
		}
		else if(sex.equals("")){
			/*
			 * 性别不能为空
			 */
			request.setAttribute("regmsg", "性别不能为空！");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//转发
		}
		else if(telphone.equals("")){
			/*
			 * 电话号码不能为空
			 */
			request.setAttribute("regmsg", "电话号不能为空！");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//转发
		}
		if (conn != null) {
			try {
				// 插入注册信息的SQL语句(使用?占位符)
				String sql = "insert into staff(name,password,telphone,email,sex) "
						+ "values(?,?,?,?,?)";
				// 创建PreparedStatement对象
				PreparedStatement ps = conn.prepareStatement(sql);
				// 对SQL语句中的参数动态赋值
				ps.setString(1, username);
				ps.setString(2, password0);
				ps.setString(3, telphone);
				ps.setString(4, email);
				ps.setString(5, sex);
				// 执行更新操作
				ps.executeUpdate();
				// 获取PrintWriter对象
				PrintWriter out = response.getWriter();
				// 输出注册结果信息
//				out.print("<h1 aling='center'>");
//				out.print(username + "regist succefully!");
//				out.print("</h1>");
//				out.flush();
//				out.close();
				/*
				 * 重定向
				 */
				response.sendRedirect("/hatmiku/login.jsp"); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 发送数据库连接错误提示信息
			response.sendError(500, "数据库连接错误！");
		}
	}

}
