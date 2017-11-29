package cn.zy.basic.service;

import java.util.List;

import cn.zy.basic.dao.SpacetimeDao;
import cn.zy.basic.entity.Spacetime;

public class SpacetimeService {
	private SpacetimeDao spacetimeDao=new SpacetimeDao();
	//找到今天所有车位可用时间
	public List<Spacetime> findUsetime(){
		return spacetimeDao.findUsetime();
		
	}
	
	//所有车位时间
	public List<Spacetime> findAll(){
		try {
			return spacetimeDao.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过sid找到车位时间信息
	public List<Spacetime> findBySid(int id){
		try {
			return spacetimeDao.findBySid(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过date找到车位时间信息
	public List<Spacetime> findByDate(String date){
		try {
			return spacetimeDao.findByDate(date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过date+sid找到车位时间信息
	public Spacetime findBySD(int sid,String date){
		try {
			return spacetimeDao.findBySD(sid,date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//修改车位使用时间
	public int update(Spacetime st){
		try {
			return spacetimeDao.update(st);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//删除
	public int del(int sid,String date){
		try {
			return spacetimeDao.del(sid,date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//添加
	public int add(Spacetime st){
		try {
			return spacetimeDao.add(st);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int setTime(Spacetime st){
		try {
			return spacetimeDao.setTime(st);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}








