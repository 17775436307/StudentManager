package com.example.controller;

import com.example.dto.UserDto;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.example.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * 处理学生和老师公共功能
 */
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * 创建学生的业务对象
     */
    StudentService stuService=new StudentService();

    /**
     * 创建老师的业务对象
     */
    TeacherService tService=new TeacherService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
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
        if ("login".equals(action)) {//登录
            doLogin(request, response);
        }else if("modifyPwd".equals(action)) {//修改密码
            doModifyPwd(request, response);
        }else if("SearchByName".equals(action)) {//根据用户名查找要修改的用户对象
            doSearchByUname(request, response);
        }else if ("ModifyUser".equals(action)) {//编辑用户信息
            doModifyUser(request, response);
        }

    }

    /**
     * 编辑用户信息方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doModifyUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*1.接收请求和数据*/
        //创建输入模板对象
        UserVo uv=new UserVo();
        uv.setUname(request.getParameter("uname"));
        uv.setCity(request.getParameter("city"));
        uv.setSex(request.getParameter("sex"));
        uv.setHobby(Arrays.toString(request.getParameterValues("hobby")));
        String role=request.getSession().getAttribute("role").toString();

        /*2.根据身份调用相应业务对象处理请求并接收结果*/
        if ("student".equals(role)) {
            stuService.modifyStu(uv);
        }else {
            tService.modifyTeacher(uv);
        }

        /*3.根据结果选择相应的视图显示*/
        request.setAttribute("mess", "编辑个人信息成功");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * 根据用户名查找要修改的用户对象方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doSearchByUname(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    }

    /**
     * 修改密码方法
     * @param request
     * @param response
     * @throws IOException
     */
    private void doModifyPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*1.接收用户请求的数据*/
        String role=request.getSession().getAttribute("role").toString();
        String uname=request.getSession().getAttribute("uname").toString();
        String pwd=request.getSession().getAttribute("pwd").toString();
        String pwd2=request.getParameter("pwd2");

        /*2.根据用户的身份选择相应的业务对象处理请求并接收结果*/
        if ("student".equals(role)) {//学生修改密码
            StudentService.modifyPwd(uname,pwd,pwd2);
        } else {//老师修改密码
            tService.modifyPwd(uname,pwd,pwd2);
        }

        /*3.选择相应的视图显示*/
        //清除Session中的当前用户信息
        //移除当前会话中用户
        request.getSession().removeAttribute("uname");
        request.getSession().removeAttribute("pwd");
        request.getSession().removeAttribute("role");
        //跳转到登录页面
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("密码修改成功，3秒后跳转到登录页面");
        response.setHeader("refresh", "3;url=login.jsp");
    }

    /**
     * 登录方法
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     * @throws ServletException
     * @throws IOException
     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException, ServletException, IOException {
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
    }

}
