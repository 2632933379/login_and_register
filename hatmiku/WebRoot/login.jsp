<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body background="image\login.jpg">
        <!-- <p1>test!</p1> -->
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
        <h1 align="center">登录Hatmiku</h1>
        <%
 			 String message="";
  			String msg = (String)request.getAttribute("msg");//获取request 域中名为msg的属性
  			if(msg!=null)
  			{
  			message=msg;
  			}
  		 %>
        <form name=loginForm action="login" method=post>
        <table align="center">
            <tr>
                <td>用户名：</td><td><input type=text name=username /></td>
            </tr>    
            <tr>
                <td>密码：</td><td><input type=password name=pwd /></td>
            <tr/>            
            <tr>
            <td colspan="2",align="center">
                <input type="submit" value="登录" />
              <input type="button" value="没有账号?点我注册!" onclick="location='register.jsp'"/>
            </td>
            </tr>        
        </table>
        </form>
        <%=message
         %>
    </body>
</html>
