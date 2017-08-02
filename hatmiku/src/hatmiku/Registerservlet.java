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
			// ��������
			
			Class.forName("com.mysql.jdbc.Driver");
			// ���ݿ�����url
			String url = "jdbc:mysql://localhost:3306/hellomysql";
			// ��ȡ���ݿ�����
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("���ݿ����ӳɹ�!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
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
		
		//���ش������
		if(username.equals("")){
			/*
			 * �û�������Ϊ�գ�
			 */
			request.setAttribute("regmsg", "�û�������Ϊ�գ�");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");//�õ�ת����
			qr.forward(request, response);//ת��
		}
		else if(email.equals("")){
			/*
			 *���䲻��Ϊ��
			 */
			request.setAttribute("regmsg", "���䲻��Ϊ�գ�");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//ת��
		}
		else if(password0.equals("")){
			/*
			 *���벻��Ϊ�գ�
			 *ת����request.jsp
			 */
			request.setAttribute("regmsg", "����Ϊ���ܿգ�");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//ת��
		}
		else if(!password0.equals(password1)){
			/*
			 *��������Ϣ����request���У�Ȼ��ת����register.jsp
			 *�����������벻һ�� 
			 */
			request.setAttribute("regmsg", "�����������벻һ�£�");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//ת��
		}
		else if(sex.equals("")){
			/*
			 * �Ա���Ϊ��
			 */
			request.setAttribute("regmsg", "�Ա���Ϊ�գ�");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//ת��
		}
		else if(telphone.equals("")){
			/*
			 * �绰���벻��Ϊ��
			 */
			request.setAttribute("regmsg", "�绰�Ų���Ϊ�գ�");
			RequestDispatcher qr = request.getRequestDispatcher("register.jsp");
			qr.forward(request, response);//ת��
		}
		if (conn != null) {
			try {
				// ����ע����Ϣ��SQL���(ʹ��?ռλ��)
				String sql = "insert into staff(name,password,telphone,email,sex) "
						+ "values(?,?,?,?,?)";
				// ����PreparedStatement����
				PreparedStatement ps = conn.prepareStatement(sql);
				// ��SQL����еĲ�����̬��ֵ
				ps.setString(1, username);
				ps.setString(2, password0);
				ps.setString(3, telphone);
				ps.setString(4, email);
				ps.setString(5, sex);
				// ִ�и��²���
				ps.executeUpdate();
				// ��ȡPrintWriter����
				PrintWriter out = response.getWriter();
				// ���ע������Ϣ
//				out.print("<h1 aling='center'>");
//				out.print(username + "regist succefully!");
//				out.print("</h1>");
//				out.flush();
//				out.close();
				/*
				 * �ض���
				 */
				response.sendRedirect("/hatmiku/login.jsp"); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// �������ݿ����Ӵ�����ʾ��Ϣ
			response.sendError(500, "���ݿ����Ӵ���");
		}
	}

}
