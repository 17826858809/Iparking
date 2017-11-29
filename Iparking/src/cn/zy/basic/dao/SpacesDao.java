package cn.zy.basic.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.Spaces;

public class SpacesDao {
	//通过车位号找到地址信息
	public Spaces findLocation(Spaces spaces){
		String sql="select * from tb_spaces where sid=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Spaces>(Spaces.class), spaces.getSid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//所有可用车位信息
	public List<Spaces> findAll(){
		String sql="select * from tb_spaces where sid in(select sid from tb_spacestate where canuse=1)";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Spaces>(Spaces.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//根据用户id找到车位
	public List<Spaces> findSpace(Spaces spaces){
		String sql="select * from tb_spaces where userid = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Spaces>(Spaces.class),spaces.getUserid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//发布车位
	public Spaces addSpace(Spaces spaces){
		String sql;
		if(spaces.getFlag()==0) sql="insert into tb_spaces(userid,slocation,issure,longitude,latitude,flag,isblue) values(?,?,?,?,?,2,2)";
		else sql="insert into tb_spaces(userid,slocation,issure,longitude,latitude,flag,isblue) values(?,?,?,?,?,"+spaces.getFlag()+","+spaces.getIsblue()+")";
		String sql2="select * from tb_spaces ORDER BY sid DESC limit 1";
		try{
			JdbcUtils.getQueryRunner().update(sql, spaces.getUserid(),spaces.getSlocation(),spaces.getIssure(),spaces.getLongitude(),spaces.getLatitude());
			return JdbcUtils.getQueryRunner().query(sql2,new BeanHandler<Spaces>(Spaces.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到所有车位
	public List<Spaces> findAllSpace(){
		String sql="select * from tb_spaces";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Spaces>(Spaces.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//更新修改
	public int update(Spaces space){
		String sql="update tb_spaces set userid=? , slocation=? , issure=? , longitude=? , latitude=? , flag=? , isblue=? where sid=?";
		try {
			return JdbcUtils.getQueryRunner().update(sql, space.getUserid(),space.getSlocation(),space.getIssure(),space.getLongitude(),space.getLatitude(),space.getFlag(),space.getIsblue(),space.getSid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到所有安装了的车位
	public List<Spaces> findAllIn(){
		String sql="select * from tb_spaces where issure = 1";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Spaces>(Spaces.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Spaces> findAllByflag(String flag){
		String sql="select * from tb_spaces where sid in(select sid from tb_spacestate where canuse=1) and flag=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Spaces>(Spaces.class),flag);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Spaces> webFindAllByFlag(int flag){
		String sql="select * from tb_spaces where flag=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new  BeanListHandler<Spaces>(Spaces.class), flag);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<Spaces> webFindAllByIsblue(int blue){
		String sql="select * from tb_spaces where isblue=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new  BeanListHandler<Spaces>(Spaces.class), blue);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String findTcpPass(int sid){
		String sql="select * from tcppass where sid=?";
		try {
			List<String> list = JdbcUtils.getQueryRunner().query(sql,new ColumnListHandler<String>(2), sid);
			return list.get(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public int changeTcpPass(int sid,String pass){
		String sql="update tcppass set text=? where sid=?";
		try {
			return JdbcUtils.getQueryRunner().update(sql,pass, sid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}