package cn.zy.basic.service;

import java.util.List;

import cn.zy.basic.dao.BTextDao;
import cn.zy.basic.entity.BluetoothText;

public class BTextService {
	private BTextDao bTextDao=new BTextDao();
	
	//将车位上一条用过的密码改为不可用
	public int changeBluetooth(int sid){
		try {
			return bTextDao.changeBluetooth(sid);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//监听每个车位动态密码是否全部为0
	public int listener(){
		try {
			return bTextDao.listener();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//删除指定id记录
	public int delText(int id){
		try{
			return bTextDao.delText(id);
		}catch(Exception e){throw new RuntimeException(e);}
	}
	
	//修改指定id状态
	public int altUsed(int id){
		try{
			return bTextDao.altUsed(id);
		}catch(Exception e){throw new RuntimeException(e);}
	}
	
	//找到所有记录
	public List<BluetoothText> findAll(){
		try{
			return bTextDao.findAll();
		}catch(Exception e){throw new RuntimeException(e);}
	}
	
	//找到指定sid记录
	public List<BluetoothText> findBySid(int sid){
		try{
			return bTextDao.findBySid(sid);
		}catch(Exception e){throw new RuntimeException(e);}
	}
	
	//找到未使用记录
	public List<BluetoothText> findByUsed(){
		try{
			return bTextDao.findByUsed();
		}catch(Exception e){throw new RuntimeException(e);}
	}
	
	//找到指定sid未使用记录
	public List<BluetoothText> findBySU(int sid){
		try{
			return bTextDao.findBySU(sid);
		}catch(Exception e){throw new RuntimeException(e);}
	}
	
	//添加纪录
	public int addUsed(BluetoothText bt){
		try{
			return bTextDao.addUsed(bt);
		}catch(Exception e){throw new RuntimeException(e);}
	}
}