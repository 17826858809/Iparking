package cn.zy.basic.service;

import cn.zy.basic.dao.RepairDao;
import cn.zy.basic.entity.Repair;

public class RepairService {
	private RepairDao repairDao=new RepairDao();
	
	//添加纪录
	public int add(Repair bt){
		try{
			return repairDao.add(bt);
		}catch(Exception e){throw new RuntimeException(e);}
	}
}