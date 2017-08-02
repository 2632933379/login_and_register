<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Register</title>
    
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
        <h2 align="center">注册Hatmiku</h2>
        <%
        	
         %>
        <form name=loginForm action="register" method=post>
        <table align="center">
            <tr>
                <td>用户名：</td><td><input type=text name=username /></td>
            </tr>      
			<tr>
                <td>邮箱：</td><td><input type=text name=email /></td>
            <tr/> 
            <tr>
                <td>登陆密码：</td><td><input type=password name=pwd0 /></td>
            <tr/> 
			<tr>
                <td>重复密码：</td><td><input type=password name=pwd1 /></td>
            <tr/> 
             <tr>
                <td>性别：</td><td><input type=text name=sex /></td>
            <tr/> 
            <tr>
                <td>电话：</td><td><input type=text name=tel /></td>
            <tr/> 
            <tr>
            <td colspan="2",align="center">
                <input type="submit" value="提交" />
                <input type="reset" value="重置" />
            </td>
            </tr>        
        </table>
        <%
        	String regmsg = (String)request.getAttribute("regmsg");
        	String message = "";     
        	if(regmsg!=null){
        	message = regmsg;
        	}
         %>
         <%=message  %>
        </form>
    </body>
</html>
