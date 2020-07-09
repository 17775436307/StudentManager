package com.example.service;
/**
 * 老师业务逻辑类
 */

import com.example.dao.TeacherDao;
import com.example.dto.UserDto;
import com.example.vo.UserVo;

public class TeacherService {
	/**
	 * 创建老师数据访问对象
	 */
	TeacherDao tDao=new TeacherDao();

	/**
	 * 老师登录方法
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public boolean tLogin(String uname, String pwd) {
		boolean flag=tDao.searchTeacherByUnameAndPwd(uname,pwd);
		return flag;
	}

	/**
	 * 修改老师的密码方法
	 * @param uname
	 * @param pwd
	 * @param pwd2
	 */
	public void modifyPwd(String uname, String pwd, String pwd2) {
		tDao.modifyPwdByUname(uname,pwd,pwd2);
		
	}

	/**
	 * 根据用户名查找老师对象信息 方法
	 * @param uname
	 * @return
	 */
	public UserDto searchTeacherByUname(String uname) {
		UserDto userDto=tDao.searchTeacherByUname(uname);
		return userDto;
	}

	/**
	 * 根据用户名找到用户对象，修改用户对象的信息方法
	 * @param uv
	 */
	public void modifyTeacher(UserVo uv) {
		tDao.modifyTeacher(uv);
		
	}

}
