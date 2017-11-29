package cn.zy.basic.service;

import java.util.List;

import cn.zy.basic.dao.AdminDao;
import cn.zy.basic.entity.Admin;
import cn.zy.basic.entity.History;

public class AdminService {
	private AdminDao adminDao=new AdminDao();
	//管理员登录
	public Admin loginAdmin(Admin admin){
		try {
			return adminDao.loginAdmin(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Admin login(Admin admin){
		try {
			return adminDao.login(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Admin loginTel(Admin admin){
		try {
			return adminDao.loginTel(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过id查找
	public Admin findUser(Admin admin){
		try {
			return adminDao.findUser(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过手机号查询
	public Admin findPhone(Admin admin){
		try {
			return adminDao.findPhone(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过用户名查询
	public Admin findName(Admin admin){
		try {
			return adminDao.findName(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//注册
	public int register(Admin admin){
		try {
			synchronized(this){
				return adminDao.register(admin);
				
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//查询所有用户信息
	public List<Admin> findAllUser(){
		try {
			return adminDao.findAllUser();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//删除用户信息
	public int delUser(int id){
		try {
			return adminDao.delUser(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//更新
	public int update(Admin admin){
		try {
			return adminDao.update(admin);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//查询所有没有车位的用户
	public List<Admin> findAllNoSpace(){
		try {
			return adminDao.findAllNoSpace();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int findUserinto(int hid,int userid,String order){
		try {
			return adminDao.findUserinto(hid,userid,order);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}








