package cn.zy.basic.entity;
/**
 * tb_users表 用户重要信息
 */
public class Bluetooth {
	private int sid;
	private String bluetoothmac;
	private String intext;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getBluetoothmac() {
		return bluetoothmac;
	}
	public void setBluetoothmac(String bluetoothmac) {
		this.bluetoothmac = bluetoothmac;
	}
	public String getIntext() {
		return intext;
	}
	public void setIntext(String intext) {
		this.intext = intext;
	}
	
}
