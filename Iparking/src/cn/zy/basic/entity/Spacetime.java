package cn.zy.basic.entity;

import java.util.Date;

/**
 * tb_spacetime 车位可使用的时间
 */
public class Spacetime {
	private int sid;
	private Date date;
	private String howtime;
	private String alltime;
	public String getAlltime() {
		return alltime;
	}
	public void setAlltime(String alltime) {
		this.alltime = alltime;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHowtime() {
		return howtime;
	}
	public void setHowtime(String howtime) {
		this.howtime = howtime;
	}
}
