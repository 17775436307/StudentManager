package com.example.controller;
import com.example.bean.Student;
import com.example.service.StudentService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 处理学生请求
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 创建业务逻辑对象
	 */
	StudentService stuService=new StudentService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理post请求乱码
		request.setCharacterEncoding("utf-8");
		
		//接收请求标记
		String action=request.getParameter("action");
		if ("register".equals(action)) {//注册
			doRegister(request, response);
		}else if("Zhuxiao".equals(action)) {//学生注销
			doZhuXiao(request, response);
		}else if("searchAllStu".equals(action)) {//查询所有的学生信息
			doSearchAllStu(request, response);
		}else if ("delStu".equals(action)){
			doDelStu(request,response);
		}
	}

	/*
	* 删除学生的方法
	* */
	private void doDelStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("stuName");
		stuService.delStu(name);
		/*调用业务对象处理请求并接收结果*/
		ArrayList<Student> stulist=stuService.searchAllStu();

		/*根据结果选择相应的视图显示*/
		request.setAttribute("stuList", stulist);
		request.getRequestDispatcher("stulist.jsp").forward(request,response);
	}

	/**
	 * 查询所有的学生信息
	 * 方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doSearchAllStu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*调用业务对象处理请求并接收结果*/
		ArrayList<Student> stulist=stuService.searchAllStu();
		
		/*根据结果选择相应的视图显示*/
		request.setAttribute("stuList", stulist);
		request.getRequestDispatcher("stulist.jsp").forward(request, response);
	}

	/**
	 * 学生注销方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doZhuXiao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//移除当前会话中用户
		request.getSession().removeAttribute("uname");
		request.getSession().removeAttribute("pwd");
		request.getSession().removeAttribute("role");
		//跳转到登录页面
		response.sendRedirect("login.jsp");
	}

	/**
	 * 注册方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*1.接收请求和数据*/
		//声明一个学生对象接收用户请求的表单数据
		Student stu=new Student();
		stu.setUname(request.getParameter("uname"));
		stu.setPwd(request.getParameter("pwd"));
		stu.setSex(request.getParameter("sex"));
		stu.setCity(request.getParameter("city"));
		String[] hobbys=request.getParameterValues("hobby");
		if (hobbys==null) {
			stu.setHobby(null);
		}else {
			stu.setHobby(Arrays.toString(hobbys));
		}
		
		/*2.调用相应业务模型层来处理请求并接收结果*/
		boolean flag=stuService.stuRegister(stu);
		
		/*3.根据结果调用相应视图显示*/
		//判断是否注册过
		if (flag==true) {//注册过
			//设置响应的内容类型
			response.setContentType("text/html;charset=utf-8");
			//设置响应信息
			response.getWriter().write("此用户已存在不能再注册，3秒后跳转到注册页面");
			//实现3秒后跳转到登录页面
			response.setHeader("refresh", "3;url=register.html");
		}else {//没注册过
			//设置响应的内容类型
			response.setContentType(" text/html;charset=utf-8");
			//设置响应信息
			response.getWriter().write("注册成功，3秒后跳转到登录页面");
			//实现3秒后跳转到登录页面
			response.setHeader("refresh", "3;url=login.jsp");
		}
	}
}
