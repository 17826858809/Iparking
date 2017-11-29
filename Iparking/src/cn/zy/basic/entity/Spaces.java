package cn.zy.basic.entity;

/**
 * tb_spaces 车位表，用户发布车位需确认才能使用
 */
public class Spaces {
	private int sid;
	private int userid;
	private String slocation;
	private int issure; //用户发布车位后有无确认和安装
	private double longitude;	//经度
	private double latitude;	//纬度
	private int flag;
	private int isblue;
	public int getIsblue() {
		return isblue;
	}
	public void setIsblue(int isblue) {
		this.isblue = isblue;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getSlocation() {
		return slocation;
	}
	public void setSlocation(String slocation) {
		this.slocation = slocation;
	}
	public int getIssure() {
		return issure;
	}
	public void setIssure(int issure) {
		this.issure = issure;
	}

}
