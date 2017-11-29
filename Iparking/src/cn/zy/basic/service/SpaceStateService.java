package cn.zy.basic.service;

import java.util.List;

import cn.zy.basic.dao.SpaceStateDao;
import cn.zy.basic.entity.SpaceState;

public class SpaceStateService {
	private SpaceStateDao ssDao=new SpaceStateDao();
	public int changeState(SpaceState ss){
		try {
			return ssDao.changeState(ss);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public SpaceState findState(SpaceState ss){
		try {
			return ssDao.findState(ss);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//根据时间更新车位状态（不可用）
	public int changeByOutTime(String ss){
		try {
			return ssDao.changeByOutTime(ss);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//根据时间更新车位状态（时间可用）
	public int changeByInTime(String ss){
		try {
			return ssDao.changeByInTime(ss);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<SpaceState> findAll(){
		try {
			return ssDao.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//添加状态
	public int add(int ss){
		try {
			return ssDao.add(ss);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}








