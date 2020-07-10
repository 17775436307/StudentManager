<%@page import="com.example.bean.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//验证原密码
	function ispwd1() {
		//获得登录的原密码
		var oldpwd=document.getElementById("oldpwd").value;
		//输入的原密码
		var pwd1=document.getElementById("pwd1").value;
		if (oldpwd==pwd1) {
			document.getElementById("pwd11").innerHTML="√";
			document.getElementById("pwd11").style.color="green"
			return true;
		}else{
			document.getElementById("pwd11").innerHTML="原密码输入有误！";
			document.getElementById("pwd11").style.color="red";
			return false;
		}
	}
	
	//验证新密码
	function ispwd2() {
		//输入的原密码
		var pwd1=document.getElementById("pwd1").value;
		//新密码
		var pwd2=document.getElementById("pwd2").value;
		if (pwd2=="") {
			document.getElementById("pwd21").innerHTML="新密码不能为空！";
			document.getElementById("pwd21").style.color="red";
			return false;
		}else if(pwd2==pwd1){
			document.getElementById("pwd21").innerHTML="新密码不能和原密码一样！";
			document.getElementById("pwd21").style.color="red";
			return false;
		}else{
			document.getElementById("pwd21").innerHTML="√";
			document.getElementById("pwd21").style.color="green";
			return true;
		}
	}
	
	//确认密码
	function ispwd3() {
		//新密码
		var pwd2=document.getElementById("pwd2").value;
		//确认新密码
		var pwd3=document.getElementById("pwd3").value;
		if (pwd2==pwd3) {
			document.getElementById("pwd31").innerHTML="√";
			document.getElementById("pwd31").style.color="green";
			return true;
		} else {
			document.getElementById("pwd31").innerHTML="两次密码输入不一致";
			document.getElementById("pwd31").style.color="red";
			return false;
		}
	}
	
	//验证表单是否能正常
	function  checkFrom() {
		return ispwd1()&&ispwd2()&&ispwd3();
	}
</script>
</head>
<body>
	<form method="post" action="UserServlet" onsubmit="return checkFrom()">
		<!-- 给当前作一个标记 -->
		<input type="hidden" name="action" value="modifyPwd"/>
		<table>
			<tr>
				<td></td>
				<td><h1>修改学生密码</h1></td>
				<td><input type="hidden" name="oldpwd" id="oldpwd" value="<%=session.getAttribute("pwd").toString() %>" /></td>
			</tr>
			<tr>
				<td>原密码：</td>
				<td><input type="password" name="pwd1" id="pwd1" onblur="ispwd1()" /></td>
				<td id="pwd11"></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" name="pwd2" id="pwd2" onblur="ispwd2()" /></td>
				<td id="pwd21"></td>
			</tr>
			<tr>
				<td>确认新密码：</td>
				<td><input type="password" name="pwd3" id="pwd3" onblur="ispwd3()" /></td>
				<td id="pwd31"></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="修改密码"/>
					<input type="reset" value="重置" />
				</td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>