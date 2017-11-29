package cn.zy.basic.service;

import java.math.BigInteger;
import java.util.List;

import cn.zy.basic.dao.HistoryDao;
import cn.zy.basic.entity.History;

public class HistoryService {
	private HistoryDao historyDao=new HistoryDao();
	//增加预约记录
	public int add(History history){
		try {
			return historyDao.add(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//增加后添加使用记录
	public int up(History history){
		try {
			return historyDao.up(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到某车位的租用记录
	public List<History> find(History history){
		try {
			return historyDao.find(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到当前用户使用记录
	public List<History> findMy(History history){
		try {
			return historyDao.findMy(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//找到所有使用记录
	public List<History> findAllHistory(){
		try {
			return historyDao.findAllHistory();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过userid和sid找到使用记录
	public List<History> findByUS(History history){
		try {
			return historyDao.findByUS(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//delete记录
	public int delHistory(int history){
		try {
			return historyDao.delHistory(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//延时记录
	public int change(History history){
		try {
			return historyDao.change(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//添加金额
	public int addMoney(History history){
		try {
			return historyDao.addMoney(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//结束、计费
	public int overtime(int hid,double mon,String use,int flag){
		try {
			return historyDao.overtime(hid,mon,use,flag);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public History findByHid(int hid){
		try {
			return historyDao.findByHid(hid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public int changeDate(History history){
		try {
			return historyDao.changeDate(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public List<History> findOver(){
		try {
			return historyDao.findOver();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public History findLast(History history){
		try {
			return historyDao.findLast(history);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}








