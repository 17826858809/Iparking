package cn.zy.basic.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.SpaceState;

public class SpaceStateDao {
	//改变车位状态
	public int changeState(SpaceState spaceState){
		String sql="update tb_spacestate set canuse = 1-canuse where sid = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql, spaceState.getSid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//查询车位是否可用
	public SpaceState findState(SpaceState spaceState){
		String sql="select * from tb_spacestate where sid = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<SpaceState>(SpaceState.class), spaceState.getSid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//根据时间改变车位状态（不可用）
	public int changeByOutTime(String s){
		String sql="update tb_spacestate set canuse = -1 where sid in("+s+") and canuse = 1";
		try {
			return JdbcUtils.getQueryRunner().update(sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//根据时间改变车位状态（时间可用）
	public int changeByInTime(String s){
		String sql="update tb_spacestate set canuse = 1 where sid in("+s+") and canuse = -1";
		try {
			return JdbcUtils.getQueryRunner().update(sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<SpaceState> findAll(){
		String sql="select * from tb_spacestate";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<SpaceState>(SpaceState.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public int add(int s){
		String sql="insert into tb_spacestate values(?,-1)";
		try {
			return JdbcUtils.getQueryRunner().update(sql,s);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
