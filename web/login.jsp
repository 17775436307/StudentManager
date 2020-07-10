<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeImg() {
		//因为浏览器会自动将第一次请求的验证码的内存图片保存下来，如果第二次与第一次发送的是同一个请求，浏览器不会发送第二次请求，而是将第一次请求缓存的图片返回给客户端
		document.getElementById("img1").src="CheckCodeServlet2?r="+new Date();
	}
</script>
</head>
<body>
	<%
		//记住我第三步
		//声明一个变量存记住的用户名；
		String uname="";
		//获取cookies
		Cookie[] cs=request.getCookies();
		 if(cs!=null){
			 for(Cookie c:cs){
					if(c.getName().equals("uname")){
						//将Cookie中值解码
						uname=URLDecoder.decode(c.getValue(), "utf-8");
						break;
					}
			}
		 }
		
		//将记住的用户名在广本框中显示
	%>

	<%--用小脚本将登录失败提示信息获取 --%>
	<%
		Object mess=request.getAttribute("mess");
		if(mess!=null){//从登录Servlet跳转过来
			//可以直接将信息打印在当前页面
			//response.getWriter().write(mess.toString());
			out.write(mess.toString());
		}
	%>
	
	<form method="post" action="UserServlet">
		<!-- 给当前作一个标记 -->
		<input type="hidden" name="action" value="login"/>
		<table>
			<tr>
				<td></td>
				<td><h1>登录信息</h1></td>
				<td></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="uname" name="uname" value="<%=uname %>" /></td>
				<td id="uname1"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" id="pwd" name="pwd" /></td>
				<td id="pwd1"></td>
			</tr>
			<tr>
				<td>登录身份：</td>
				<td>
					<input type="radio" name="role" value="student" checked="checked"/>学生
					<input type="radio" name="role" value="teacher"/>老师
				</td>
				<td id="pwd1"></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><input type="text" name="code"/></td>
				<td><img id="img1" src="CheckCodeServlet2" alt="验证码" onclick="changeImg()"/><a onclick="changeImg()">看不清</a></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="checkbox" name="rememberMe"/>记住我</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="登录" />
					<input type="reset" value="重置" />
				</td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>