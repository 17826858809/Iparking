package cn.zy.basic.dao;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.Repair;

public class RepairDao {
	
	//添加
	public int add(Repair bt){
		String sql="call addrepair(?,?)";
		
		try {
			return JdbcUtils.getQueryRunner().update(sql,bt.getSid(),bt.getReason());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}