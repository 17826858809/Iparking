package cn.zy.basic.entity;

/**
 * tb_wallet 钱包
 */
public class Wallet {
	private int wid;
	private int userid;
	private double money;
	private double deposit;//押金
	private double tickets;//券
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getTickets() {
		return tickets;
	}
	public void setTickets(double tickets) {
		this.tickets = tickets;
	}

}
