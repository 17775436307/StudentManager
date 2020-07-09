package com.example.dao;

import com.example.bean.Student;
import com.example.db.DBCenter;
import com.example.dto.UserDto;
import com.example.vo.UserVo;

import java.util.ArrayList;

/**
 * 学生的数据访问类
 */
public class StudentDao {

	/**
	 * 根据用户名查询学生是否存在方法
	 * @param stu
	 * @return
	 */
	public static boolean searchStuByName(Student stu) {
		//声明一个变量作标记
		boolean flag=false;//没注册过
		//遍历用户集合
		for (Student s : DBCenter.stulist) {
			if (s.getUname().equals(stu.getUname())) {
				flag=true;
				break;
			}
		}
		return flag;
	}

	/**
	 *添加学生的方法
	 * @param stu
	 */
	public void addStu(Student stu) {
		//注册用户，将用户存入到集合中
		DBCenter.stulist.add(stu);
		
	}
	
	/**
	 * 根据用户和密码查询用户是否存在方法
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public boolean searchStuByUnameAndPwd(String uname, String pwd) {
		//声明一个变量作标记
		boolean flag=false;//没注册过
		//遍历用户集合
		for (Student s : DBCenter.stulist) {
			if (s.getUname().equals(uname)&&s.getPwd().equals(pwd)) {
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
		for (int i = 0; i < DBCenter.stulist.size(); i++) {
			if (DBCenter.stulist.get(i).getUname().equals(uname)&&DBCenter.stulist.get(i).getPwd().equals(pwd)) {
				DBCenter.stulist.get(i).setPwd(pwd2);
				break;
			}
		}
	}
	
	/**
	 * 根据用户名查找用户信息方法
	 * @param uname
	 * @return
	 */
	public UserDto searchStuByUname(String uname) {
		UserDto userDto=null;
		//遍历用户集合
		for (Student s : DBCenter.stulist) {
			if (s.getUname().equals(uname)) {
				userDto=new UserDto(s.getUname(), s.getSex(), s.getHobby(), s.getCity());
				break;
			}
		}
		return userDto;
	}

	/**
	 * 根据用户名找到用户对象，修改用户对象的信息方法
	 * @param uv
	 */
	public void modifyStu(UserVo uv) {
		//遍历用户集合
		for (int i = 0; i < DBCenter.stulist.size(); i++) {
			if (DBCenter.stulist.get(i).getUname().equals(uv.getUname())) {
				DBCenter.stulist.get(i).setCity(uv.getCity());
				DBCenter.stulist.get(i).setHobby(uv.getHobby());
				DBCenter.stulist.get(i).setSex(uv.getSex());
				break;
			}
		}
		
	}

	/**
	 * 查询所有的学生信息方法
	 * @return
	 */
	public ArrayList<Student> searchAllStu() {
		// TODO Auto-generated method stub
		return DBCenter.stulist;
	}

	//删除学生
    public void delStu(String name) {
		for (int i = 0; i < DBCenter.stulist.size(); i++) {
			if (DBCenter.stulist.get(i).getUname().equals(name)) {
				DBCenter.stulist.remove(i);
				break;
			}
		}
    }
}
