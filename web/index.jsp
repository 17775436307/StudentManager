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
  Object mess=request.getAttribute("mess");
  if(mess!=null){
    out.write("<h2 style='color:green;'>"+mess.toString()+"</h2>");
  }
%>

<%
  //获得当前用户的身份
  String role=session.getAttribute("role").toString();
  if("student".equals(role)){
%>
<h1>欢迎，<%=session.getAttribute("uname").toString() %>进入学生后台管理页面</h1>
<a href="UserServlet?action=SearchByName">编辑个人信息</a><br/>
<a href="modifyPwd.jsp">修改个人密码</a><br/>
<a href="StudentServlet?action=Zhuxiao">注销</a>
<%
}else{
%>
<h1>欢迎，<%=session.getAttribute("uname").toString() %>进入老师后台管理页面</h1>
<a href="UserServlet?action=SearchByName">编辑个人信息</a><br/>
<a href="modifyPwd.jsp">修改个人密码</a><br/>
<a href="StudentServlet?action=searchAllStu">管理学员信息</a>
<%
  }
%>
</body>
</html>