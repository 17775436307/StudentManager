package com.example.service;

import com.example.bean.Student;
import com.example.dao.StudentDao;
import com.example.dto.UserDto;
import com.example.vo.UserVo;

import java.util.ArrayList;

/**
 * 学生的业务逻辑
 */
public class StudentService {
	/**
	 * 创建数据访问对象
	 */
	StudentDao stuDao=new StudentDao();

	/**
	 * 注册方法
	 * @param stu
	 * @return
	 */
	public boolean stuRegister(Student stu) {
		//调用数据访问对象的方法查询用户是否存在
		boolean flag=StudentDao.searchStuByName(stu);
		//调用数据访问对象的方法添加用户
		if (flag==false) {
			stuDao.addStu(stu);
		}
		return flag;
	}

	/**
	 * 登录方法
	 * @param uname
	 * @param pwd
	 * @return boolean
	 */
	public boolean stuLogin(String uname, String pwd) {
		boolean flag=stuDao.searchStuByUnameAndPwd(uname,pwd);
		return flag;
	}

	/**
	 * 修改学生密码方法
	 * @param uname
	 * @param pwd
	 * @param pwd2
	 */
	public static void modifyPwd(String uname, String pwd, String pwd2) {
		StudentDao.modifyPwdByUname(uname,pwd,pwd2);
	}

	/**
	 * 根据用户名查找用户信息方法
	 * @param uname
	 * @return
	 */
	public UserDto searchStuByUname(String uname) {
		UserDto userDto=stuDao.searchStuByUname(uname);
		return userDto;
	}

	/**
	 * 根据用户名找到用户对象，修改用户对象的信息方法
	 * @param uv
	 */
	public void modifyStu(UserVo uv) {
		stuDao.modifyStu(uv);
	}

	/**
	 * 查询所有的学生信息方法
	 * @return
	 */
	public ArrayList<Student> searchAllStu() {
		// TODO Auto-generated method stub
		return stuDao.searchAllStu();
	}

	//删除学生
    public void delStu(String name) {
		stuDao.delStu(name);
    }
}
