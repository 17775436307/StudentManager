<%@page import="java.util.ArrayList"%>
<%@page import="com.example.bean.Student"%>
<%@ page import="com.example.bean.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1px" cellspacing="0px">
		<tr>
			<td>姓名</td>
			<td>性别</td>
			<td>爱好</td>
			<td>省份</td>
			<td>操作</td>
		</tr>
		<%
		ArrayList<Student> stulist=(ArrayList<Student>)request.getAttribute("stuList");
		for(Student s:stulist){
		%>
		<tr>
			<td><%=s.getUname() %></td>
			<td><%=s.getSex() %></td>
			<td><%=s.getHobby()!=""?s.getHobby().substring(1, s.getHobby().length()-1):"" %></td>
			<td><%=s.getCity() %></td>
			<td>
				<a href="StudentServlet?action=delStu&stuName=<%=s.getUname()%>">删除</a>
			</td>
		</tr>
		<% 
		}
		%>
	</table>
</body>
</html>