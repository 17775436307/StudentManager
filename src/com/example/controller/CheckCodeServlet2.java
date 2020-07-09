package com.example.controller;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 第二种生成验证码的方式
 */
public class CheckCodeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet2() {
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

	}

}
