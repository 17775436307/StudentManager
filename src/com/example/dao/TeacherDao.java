package com.example.dao;

import com.example.bean.Teacher;
import com.example.db.DBCenter;
import com.example.dto.UserDto;
import com.example.vo.UserVo;

/**
 * 老师的数据访问类
 */
public class TeacherDao {

	/**
	 * 根据用户名和密码查询老师对象方法
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public boolean searchTeacherByUnameAndPwd(String uname, String pwd) {
		boolean flag=false;
		for (Teacher t : DBCenter.tlist) {
			if (t.getUname().equals(uname)&&t.getPwd().equals(pwd)) {
				flag=true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 根据用户名和原密码修改密码方法
	 * @param uname
	 * @param pwd
	 * @param pwd2
	 */
	public static void modifyPwdByUname(String uname, String pwd, String pwd2) {
		for (int i = 0; i < DBCenter.tlist.size(); i++) {
			if (DBCenter.tlist.get(i).getUname().equals(uname)&&DBCenter.tlist.get(i).getPwd().equals(pwd)) {
				DBCenter.tlist.get(i).setPwd(pwd2);
				break;
			}
		}
	}

	/**
	 * 根据用户名查找老师对象信息 方法
	 * @param uname
	 * @return
	 */
	public UserDto searchTeacherByUname(String uname) {
		UserDto userDto=null;
		for (Teacher t : DBCenter.tlist) {
			if (t.getUname().equals(uname)) {
				userDto=new UserDto(t.getUname(), t.getSex(), t.getHobby(), t.getCity());
				break;
			}
		}
		return userDto;
	}

	/**
	 * 根据用户名找到用户对象，修改用户对象的信息方法
	 * @param uv
	 */
	public void modifyTeacher(UserVo uv) {
		//遍历用户集合
		for (int i = 0; i < DBCenter.tlist.size(); i++) {
			if (DBCenter.tlist.get(i).getUname().equals(uv.getUname())) {
				DBCenter.tlist.get(i).setCity(uv.getCity());
				DBCenter.tlist.get(i).setHobby(uv.getHobby());
				DBCenter.tlist.get(i).setSex(uv.getSex());
				break;
			}
		}
		
	}

}
