package com.example.bean;
/**
 * 老师类
 */
public class Teacher {
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
	
	/**
	 * 老师类无参构造方法
	 */
	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 老师类全参 构造方法
	 * @param uname
	 * @param pwd
	 * @param sex
	 * @param hobby
	 * @param city
	 */
	public Teacher(String uname, String pwd, String sex, String hobby, String city) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.sex = sex;
		this.hobby = hobby;
		this.city = city;
	}

	/**
	 * 获得姓名方法
	 * @return String
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * 设置姓名方法
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * 获得密码方法
	 * @return String
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * 设置密码方法
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 获得性别方法
	 * @return String
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置性别方法
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获得爱好方法
	 * @return String
	 */
	public String getHobby() {
		return hobby;
	}

	/**
	 * 设置爱好方法
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	/**
	 * 获得省份方法
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置省份方法
	 */
	public void setCity(String city) {
		this.city = city;
	}
}
