package cn.zy.basic.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.Bluetooth;

public class BluetoothDao {
	//找到MAC地址和密码
	public Bluetooth findBluetooth(int sid,int flag){
		String sql;
		if(flag==0){
			sql="select a.sid,a.bluetoothmac,b.text as intext from tb_bluetooth a,tb_bluetooth_text b where a.sid = ? and b.sid = a.sid and b.used = 1 limit 1";
		}else{
			sql="select sid,bluetoothmac,intext from tb_bluetooth where sid = ?";
		}
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Bluetooth>(Bluetooth.class),sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//修改租用密码状态
	public int changeBluetooth(int sid){
		String sql="update tb_bluetooth_text set used = 0 where id = (select id from (select id from tb_bluetooth_text where used = 1 and sid = ? limit 1) s) ";
		try {
			return JdbcUtils.getQueryRunner().update(sql,sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//监听每个车位动态密码是否全部为0
	public int listener(){
		String sql="call mmtext();";
		
		try {
			return JdbcUtils.getQueryRunner().update(sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//修改租用密码状态
	public int update(Bluetooth bt){
		String sql="update tb_bluetooth set bluetoothmac=?,intext=? where sid=?";
		try {
			return JdbcUtils.getQueryRunner().update(sql,bt.getBluetoothmac(),bt.getIntext(),bt.getSid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//添加
	public int add(int sid){
		String sql="insert into tb_bluetooth values(?,0,0)";
		try {
			return JdbcUtils.getQueryRunner().update(sql,sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}