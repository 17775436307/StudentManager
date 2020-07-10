# 学生管理系统设计

## 项目的背景
通过网上视频的学习，自己慢慢搭建出了这个简易的学生管理系统

## 项目的介绍
该学生管理系统主要分为老师跟学生不同用户登录，通过对用户的控制，实现不同用户登录后，显示的功能不一样。

## 主要的功能
老师模块：
登录功能、修改个人信息、修改密码、管理学员信息等功能
学生模块：
注册功能、登录功能、修改个人信息、修改密码等功能

## 用到的技术
servlet+jsp+jstl

## 涉及到的部分算法

1、验证码
//通过引入验证码第三方jar包的方式，实现登录验证码的功能
//声明变量存储验证码宽度，高度，验证码的字母个数，验证码的干扰线的条数
		int width=120;
		int height=40;
		int count=4;
		int lineCount=20;
		//创建验证码对象
		ValidateCode vc=new ValidateCode(width, height, count, lineCount);
		//获取验证码
		String code1=vc.getCode();
		//System.out.println("生成的验证码："+code1);
		//将生成的验证码存到Session会话
		request.getSession().setAttribute("checkcode", code1);
		
		//将生成的验证码返回给客户端
		vc.write(response.getOutputStream());
    
    
 2、学生注册
 //通过接收前端的注册信息，实现学生注册功能
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
    
  
 3、登录
 //学生和老师共用登录页面，通过角色role来判断不同的角色登录，进行不同的业务处理
  /*1.接收请求和数据*/
        //接收登录请求的表单数据
        String code=request.getParameter("code");
        //获得系统生成的验证码
        String checkcode=request.getSession().getAttribute("checkcode").toString();
        //先判断验证码
        if (checkcode.equalsIgnoreCase(code)) {//验证码输入正确
            //接收登录身份,用户名和密码
            String role=request.getParameter("role");
            String uname=request.getParameter("uname");
            String pwd=request.getParameter("pwd");

            /*2.调用业务逻辑对象来处理请求并返回结果*/
            boolean result=false;
            if ("student".equals(role)) {//学生
                result=stuService.stuLogin(uname,pwd);
            } else {//老师
                result=tService.tLogin(uname,pwd);
            }

            /*3.根据结果选择相应的视图显示*/
            //判断用户是否登录成功
            if (result==true) {//登录成功
                /*记住我*/
                //判断是否选中记住我
                String rememberMe=request.getParameter("rememberMe");
                if ("on".equals(rememberMe)) {
                    //用Cookie记住用户名,cookie存值是按ascii码存2个字节存，中文按Unicode码4个字节存值，直接将中文存入cookie存不进去
                    //想用Cookie存中文，存时将值编码，获取Cookie中的值要解码
                    Cookie c1=new Cookie("uname",URLEncoder.encode(uname, "utf-8") );
                    //设置cookie的有效期
                    c1.setMaxAge(60*5);
                    //将cookie写入到客户端
                    response.addCookie(c1);
                }

                //登录成功，将用户信息存下来，供后面操作使用
                request.getSession().setAttribute("uname", uname);
                request.getSession().setAttribute("pwd", pwd);
                request.getSession().setAttribute("role", role);

                //转发
                request.getRequestDispatcher("index.jsp").forward(request, response);

            }else {//登录失败
                request.setAttribute("mess", "登录失败");
                //转发，url不变
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }else {//验证码输入有误
            request.setAttribute("mess", "验证码输入有误");
            //转发，url不变
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }


4、修改个人信息
    /*1.接收请求数据*/
    String role=request.getSession().getAttribute("role").toString();
    String uname=request.getSession().getAttribute("uname").toString();

    /*2.根据身份和用户名调用相应业务对象处理请求并接收结果*/
    UserDto userdto=null;
    if ("student".equals(role)) {
        userdto=stuService.searchStuByUname(uname);
    } else {
        userdto=tService.searchTeacherByUname(uname);
    }
    /*3.选择相应的视图显示*/
    request.setAttribute("user", userdto);
    request.getRequestDispatcher("modifyUser.jsp").forward(request, response);
    
/*1.接收请求数据*/
        String role=request.getSession().getAttribute("role").toString();
        String uname=request.getSession().getAttribute("uname").toString();

        /*2.根据身份和用户名调用相应业务对象处理请求并接收结果*/
        UserDto userdto=null;
        if ("student".equals(role)) {
            userdto=stuService.searchStuByUname(uname);
        } else {
            userdto=tService.searchTeacherByUname(uname);
        }

        /*3.选择相应的视图显示*/
        request.setAttribute("user", userdto);
        request.getRequestDispatcher("modifyUser.jsp").forward(request, response);
   
  
 ## 总结   该系统所设计的java核心要点：
1.java核心编程技术
2.第三方工具类的使用
3.数据库技术
4. JSP/Servlet开发基础
5.web前端优化(HTML5）
6.软件工程git/GitHub和文档标价
  
