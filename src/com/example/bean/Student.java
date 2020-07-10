package com.example.bean;
/**
 * 学生类
 */
public class Student {
	/**
	 * 姓名
	 */
	private String uname;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 爱好
	 */
	private String hobby;
	
	/**
	 * 所在省份
	 */
	private String city;

	public Student() {
		
	}

	public Student(String uname, String pwd) {
		super();
		this.uname = uname;
		this.pwd = pwd;
	}

	public Student(String uname, String pwd, String sex, String hobby, String city) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.sex = sex;
		this.hobby = hobby;
		this.city = city;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
