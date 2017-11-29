package cn.zy.basic.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.History;

public class HistoryDao {
	//插入记录
	public int add(History history){
		String sql="call addhistory(?,?,?)";
		try {
			synchronized(this){
				List<Integer> list = JdbcUtils.getQueryRunner().query(sql,new ColumnListHandler<Integer>(1), history.getUserid(),history.getSid(),history.getOrdertime());
				return list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//结束、计费
	public int overtime(int hid,double mon,String use,int flag){
		String sql="call oversomething(?,?,?,?)";
		try {
			return (JdbcUtils.getQueryRunner().query(sql,new ColumnListHandler<Integer>(1), hid,mon,use,flag)).get(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//更新记录
	public int up(History history){
		String sql="update tb_history set usedate = ? ,usetime = ?,money = ? where hid = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql, history.getUsedate(),history.getUsetime(),history.getMoney(),history.getHid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到某车位的租用记录
	public List<History> find(History history){
		String sql="select * from tb_history where sid = ? and usedate is not null order by usedate desc,usetime desc";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<History>(History.class),history.getSid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到某用户的租用记录
	public List<History> findMy(History history){
		String sql="select * from tb_history where userid = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<History>(History.class),history.getUserid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到所有记录
	public List<History> findAllHistory(){
		String sql="select * from tb_history";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<History>(History.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//userid+sid租用记录
	public List<History> findByUS(History history){
		String sql="select * from tb_history where userid = ? and sid = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<History>(History.class),history.getUserid(),history.getSid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//del记录
	public int delHistory(int history){
		String sql="delete from tb_history where hid = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql, history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//延时记录
	public int change(History history){
		String sql="update tb_history set ordertime = ? where hid = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql, history.getOrdertime(),history.getHid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//金额
	public int addMoney(History history){
		String sql="update tb_history set money = ? where hid = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql, history.getMoney(),history.getHid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public History findByHid(int hid){
		String sql="select * from tb_history where hid=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<History>(History.class),hid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int changeDate(History history){
		String sql="update tb_history set usedate = ?,usetime = ?,money=-1 where hid = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql, history.getUsedate(),history.getUsetime(),history.getHid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<History> findOver(){
		String sql="select * from tb_history where money is null and usedate is not null";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<History>(History.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到最后一条记录，查看是否结束
	public History findLast(History history){
		String sql="select * from tb_history where userid = ? order by hid desc limit 1";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<History>(History.class),history.getUserid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}








