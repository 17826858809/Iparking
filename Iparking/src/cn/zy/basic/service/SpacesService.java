package cn.zy.basic.service;

import java.util.List;

import cn.zy.basic.dao.SpacesDao;
import cn.zy.basic.entity.Spaces;

public class SpacesService {
	private SpacesDao spacesDao=new SpacesDao();
	//通过车位号找到信息
	public Spaces findLocation(Spaces spaces){
		try {
			return spacesDao.findLocation(spaces);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到所有可用车位
	public List<Spaces> findAll(){
		try{
			return spacesDao.findAll();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//找到用户自己的车位（根据id找到车位）
	public List<Spaces> findSpace(Spaces space){
		try{
			return spacesDao.findSpace(space);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//发布车位
	public Spaces addSpace(Spaces space){
		try{
			synchronized(this){
				return spacesDao.addSpace(space);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//找到所有车位
	public List<Spaces> findAllSpace(){
		try{
			return spacesDao.findAllSpace();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//更新修改
	public int update(Spaces space){
		try{
			return spacesDao.update(space);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	//找到所有已安装的车位
	public List<Spaces> findAllIn(){
		try{
			return spacesDao.findAllIn();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Spaces> findAllByflag(String flag){
		try{
			return spacesDao.findAllByflag(flag);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Spaces> webFindAllByFlag(int flag){
		try{
			return spacesDao.webFindAllByFlag(flag);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public List<Spaces> webFindAllByIsblue(int isblue){
		try{
			return spacesDao.webFindAllByIsblue(isblue);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//找到tcp用户密码
	public String findTcpPass(int sid){
		try{
			return spacesDao.findTcpPass(sid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//修改tcp密码
	public int changeTcpPass(int sid,String pass){
		try{
			return spacesDao.changeTcpPass(sid,pass);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}








