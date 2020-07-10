<%@ page import="com.example.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//获得要修改的用户信息
		UserDto s=(UserDto)request.getAttribute("user");
	%>
	<form method="get" action="UserServlet">
		<!-- 给当前作一个标记 -->
		<input type="hidden" name="action" value="ModifyUser"/>
		<table>
			<tr>
				<td></td>
				<td><h1>编辑个人信息页面</h1></td>
				<td></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="uname" name="uname" readonly="readonly" value="<%=s.getUname() %>" /></td>
				<td id="uname1"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<input type="radio" name="sex" value="man" <% if(s.getSex().equals("man")){%> checked="checked" <%} %>  />男
					<input type="radio" name="sex" value="woman" <% if(s.getSex().equals("woman")){%> checked="checked" <%} %> />女
				</td>
				<td></td>
			</tr>
			<tr>
				<td>爱好：</td>
				<td>
					<input type="checkbox" name="hobby" value="sleep" <% if(s.getHobby().contains("sleep")){%> checked="checked" <%} %> />sleep
					<input type="checkbox" name="hobby" value="eat" <% if(s.getHobby().contains("eat")){%> checked="checked" <%} %> />eat
					<input type="checkbox" name="hobby" value="game" <% if(s.getHobby().contains("game")){%> checked="checked" <%} %> />game
					<input type="checkbox" name="hobby" value="drink" <% if(s.getHobby().contains("drink")){%> checked="checked" <%} %> />drink
				</td>
				<td></td>
			</tr>
			<tr>
				<td>出生省份：</td>
				<td>
					<select name="city">
						<option value="hubei" <% if(s.getCity().equals("hubei")){%> selected="selected" <%} %> >湖北</option>
						<option value="hunan" <% if(s.getCity().equals("hunan")){%> selected="selected" <%} %> >湖南</option>
						<option value="guangxi" <% if(s.getCity().equals("guangxi")){%> selected="selected" <%} %> >广西</option>
						<option value="guangdong" <% if(s.getCity().equals("guangdong")){%> selected="selected" <%} %> >广东</option>
						<option value="jiangxi" <% if(s.getCity().equals("jiangxi")){%> selected="selected" <%} %> >江西</option>
					</select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="修改" />
					<input type="reset" value="重置" />
				</td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>