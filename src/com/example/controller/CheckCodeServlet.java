package com.example.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码的Servlet
 */
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
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
		//声明两个变量存验证码的宽高
		int width=120;
		int height=40;
		
		//创建内存图像对象
		BufferedImage bimg=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//获得图像的画笔
		 Graphics gp=bimg.getGraphics();
		 //设置画笔颜色
		 gp.setColor(Color.blue);
		 //填充图像的背景颜色
		 gp.fillRect(0, 0, width, height);
		 
		 
		 //设置画笔颜色
		 gp.setColor(Color.YELLOW);
		 //设置画笔的字体
		 gp.setFont(new Font("宋体", Font.BOLD, 30));
		 //声明一个数组存可能作为验证码的字符
		 String[] s1= {"1","2","3","a","b","e","我","中","人"};
		 //声明字符串存验证码
		 StringBuffer sb=new StringBuffer();
		 //循环生成四个验证码
		 for (int i = 0; i <4; i++) {
			int index=(int)(Math.random()*s1.length);
			//生成的验证码字母为
			String code1=s1[index];
			//将生成的验证码写入到图像上
			gp.drawString(code1, i*20+10, 30);
			//将每次生成的字母存到sb中
			sb.append(code1);
		}
		 
		 //存储生成的验证码，用Session会话对象存
		 request.getSession().setAttribute("checkcode", sb);
		 
		 //在图像画干扰线
		//设置画笔颜色
		 gp.setColor(Color.red);
		 for (int i = 0; i < 10; i++) {
			gp.drawLine((int)(Math.random()*width), (int)(Math.random()*height), (int)(Math.random()*width), (int)(Math.random()*height));
		}
		 
		 //将生成的验证写到客户端
		 ImageIO.write(bimg, "jpg", response.getOutputStream());

	}

}
