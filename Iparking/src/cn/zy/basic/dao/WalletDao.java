package cn.zy.basic.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.Wallet;

public class WalletDao {
	//查询余额
	public Wallet findMoney(Wallet wallet){
		String sql="select * from tb_wallet where userid = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Wallet>(Wallet.class),wallet.getUserid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//更新余额
	public int upMoney(Wallet wallet){
			String sql="update tb_wallet set money = money - ? where userid = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql,wallet.getMoney(),wallet.getUserid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//查询all
	public List<Wallet> findAll(){
		String sql="select * from tb_wallet";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Wallet>(Wallet.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过租金查询
	public List<Wallet> findByDeposit(double deposit){
		String sql="select * from tb_wallet where deposit<=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Wallet>(Wallet.class),deposit);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过userid+租金查询
	public Wallet findByUD(int id,double deposit){
		String sql="select * from tb_wallet where userid = ? and deposit<?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Wallet>(Wallet.class),id,deposit);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//修改
	public int update(Wallet wallet){
			String sql="update tb_wallet set money = ?,deposit = ?,tickets = ? where userid = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql,wallet.getMoney(),wallet.getDeposit(),wallet.getTickets(),wallet.getUserid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//添加
	public int add(int userid){
			String sql="insert into tb_wallet(userid,money,deposit,tickets) values(?,0,0,0)";
		try {
			return JdbcUtils.getQueryRunner().update(sql,userid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//充值
	public int charge(int money,int userid){
		String sql = "";
		if(money<300)
			sql="update tb_wallet set money = money + ? where userid = ?";
		else
			sql = "update tb_wallet set deposit = deposit + ? where userid = ?";
	try {
		return JdbcUtils.getQueryRunner().update(sql,money,userid);
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}

}








