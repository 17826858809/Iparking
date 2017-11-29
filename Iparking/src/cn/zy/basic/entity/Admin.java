package cn.zy.basic.entity;
/**
 * tb_users表 用户重要信息
 */
public class Admin {
	private int userid;
	private String userName;
	private String pwd;
	private String phone;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int id) {
		this.userid = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
