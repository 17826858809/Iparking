package cn.zy.basic.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.Admin;

public class AdminDao {
	//管理员登录
	public Admin loginAdmin(Admin admin){
		String sql="select * from administrator where username =? and pwd = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Admin>(Admin.class),admin.getUserName(),admin.getPwd());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//登录
	public Admin login(Admin admin){
		String sql="select * from tb_users where username =? and pwd = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Admin>(Admin.class),admin.getUserName(),admin.getPwd());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//手机登录
	public Admin loginTel(Admin admin){
		String sql="select * from tb_users where phone =?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Admin>(Admin.class),admin.getPhone());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Admin findUser(Admin admin){
		String sql="select * from tb_users where userid =?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Admin>(Admin.class),admin.getUserid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Admin findPhone(Admin admin){
		String sql="select * from tb_users where phone =?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Admin>(Admin.class),admin.getPhone());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Admin findName(Admin admin){
		String sql="select * from tb_users where username =?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Admin>(Admin.class),admin.getUserName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public int register(Admin admin){
		String sql="call register(?,?,?)";
		try {
			return JdbcUtils.getQueryRunner().update(sql, admin.getUserName(),admin.getPwd(),admin.getPhone());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Admin> findAllUser(){
		String sql="select * from tb_users";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Admin>(Admin.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//删除没有车位信息的用户
	public int delUser(int id){
		String sql="call delUser(?,@flag)";
		try {
			return JdbcUtils.getQueryRunner().update(sql,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//修改
	public int update(Admin admin){
		String sql="update tb_users set username=?,pwd=?,phone=? where userid=?";
		try {
			return JdbcUtils.getQueryRunner().update(sql,admin.getUserName(),admin.getPwd(),admin.getPhone(),admin.getUserid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//找到所以没有车位信息的用户
	public List<Admin> findAllNoSpace(){
		String sql="select * from tb_users where userid not in(select userid from tb_spaces)";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Admin>(Admin.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int findUserinto(int hid,int userid,String order){
		String sql="insert into tb_teltoaway values(?,?,?)";
		try {
			return JdbcUtils.getQueryRunner().update(sql, hid,userid,order);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}








