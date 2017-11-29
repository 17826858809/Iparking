package cn.zy.basic.service;

import cn.zy.basic.dao.BluetoothDao;
import cn.zy.basic.entity.Bluetooth;

public class BluetoothService {
	private BluetoothDao bluetoothDao=new BluetoothDao();
	//找到MAC地址和密码（flag==1 车位主  flag==0 租用者 找到第一条密码）
	public Bluetooth findBluetooth(int sid,int flag){
		try {
			return bluetoothDao.findBluetooth(sid,flag);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//将车位上一条用过的密码改为不可用
	public int changeBluetooth(int sid){
		try {
			return bluetoothDao.changeBluetooth(sid);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//监听每个车位动态密码是否全部为0
	public int listener(){
		try {
			return bluetoothDao.listener();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//修改车位信息
	public int update(Bluetooth bt){
		try {
			return bluetoothDao.update(bt);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//添加蓝牙信息
	public int add(int sid){
		try {
			return bluetoothDao.add(sid);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}