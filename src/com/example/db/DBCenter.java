package com.example.db;

import com.example.bean.Student;
import com.example.bean.Teacher;

import java.util.ArrayList;

/**
 * 存项目数据
 */

public class DBCenter {
	/**
	 * 存学生对象的集合
	 */
	public static ArrayList<Student> stulist=new ArrayList<Student>();
	/**
	 * 存老师对象的集合
	 */
	public static ArrayList<Teacher> tlist=new ArrayList<Teacher>();
	
	//初始化老师和学生对象
	static {
		Student stu1=new Student("小明", "123", "man", "", "nan");
		Student stu2=new Student("小花", "123", "man", "", "hunan");
		Student stu3=new Student("小白", "123", "man", "", "nan");
		stulist.add(stu1);
		stulist.add(stu2);
		stulist.add(stu3);

		Teacher t1=new Teacher("admin", "admin", "woman", "", "hubei");
		Teacher t2=new Teacher("test", "test", "woman", "", "hubei");
		tlist.add(t1);
		tlist.add(t2);
	}
	
}
