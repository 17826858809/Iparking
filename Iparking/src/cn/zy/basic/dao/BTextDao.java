package cn.zy.basic.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.BluetoothText;

public class BTextDao {
	
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
	
	//删除指定id记录
	public int delText(int id){
		String sql="delete from tb_bluetooth_text where id = ?";
		
		try {
			return JdbcUtils.getQueryRunner().update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//修改指定id状态
	public int altUsed(int id){
		String sql="update tb_bluetooth_text set used = 1-used where id = ?";
		
		try {
			return JdbcUtils.getQueryRunner().update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//找到所有记录
	public List<BluetoothText> findAll(){
		String sql="select * from tb_bluetooth_text";
		
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<BluetoothText>(BluetoothText.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//找到指定sid记录
	public List<BluetoothText> findBySid(int sid){
		String sql="select * from tb_bluetooth_text where sid = ?";
		
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<BluetoothText>(BluetoothText.class),sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//找到未使用记录
	public List<BluetoothText> findByUsed(){
		String sql="select * from tb_bluetooth_text where used = 1";
		
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<BluetoothText>(BluetoothText.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//找到指定sid未使用记录
	public List<BluetoothText> findBySU(int sid){
		String sql="select * from tb_bluetooth_text where sid = ? and used = 1";
		
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<BluetoothText>(BluetoothText.class),sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	//添加纪录
	public int addUsed(BluetoothText bt){
		String sql="insert into tb_bluetooth_text(sid,text,used) values(?,?,1)";
		
		try {
			return JdbcUtils.getQueryRunner().update(sql,bt.getSid(),bt.getText());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}