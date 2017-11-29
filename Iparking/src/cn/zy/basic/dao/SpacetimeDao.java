package cn.zy.basic.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zy.basic.dbUtils.JdbcUtils;
import cn.zy.basic.entity.Spacetime;

public class SpacetimeDao {
	//找到今天所有小区车位使用时间
	public List<Spacetime> findUsetime(){
		String sql="select sid,howtime from tb_spacetime where date = date_format(now(),'%y-%m-%d') union select sid,'0' from tb_spaces where flag=2 and sid not in (select sid from tb_spacetime where date = date_format(now(),'%y-%m-%d') )";
		
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Spacetime>(Spacetime.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//所有车位时间
	public List<Spacetime> findAll(){
		String sql="select * from tb_spacetime";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Spacetime>(Spacetime.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过sid找到车位时间信息
	public List<Spacetime> findBySid(int id){
		String sql="select * from tb_spacetime where sid = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Spacetime>(Spacetime.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过date找到车位时间信息
	public List<Spacetime> findByDate(String date){
		String sql="select * from tb_spacetime where date = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Spacetime>(Spacetime.class),date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过date+sid找到车位时间信息
	public Spacetime findBySD(int sid,String date){
		String sql="select * from tb_spacetime where sid = ? and date = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Spacetime>(Spacetime.class),sid,date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int update(Spacetime st){
		String sql="update tb_spacetime set howtime=? where sid = ? and date = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql,st.getHowtime(),st.getSid(),st.getDate());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public int del(int sid,String date){
		String sql="delete from tb_spacetime where sid = ? and date = ?";
		try {
			return JdbcUtils.getQueryRunner().update(sql,sid,date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int add(Spacetime st){
		String sql="insert into tb_spacetime values(?,?,?,?)";
		try {
			return JdbcUtils.getQueryRunner().update(sql,st.getSid(),st.getDate(),st.getHowtime(),st.getHowtime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public int setTime(Spacetime st){
		String sql="call setTime(?,?,?)";
		try {
			return JdbcUtils.getQueryRunner().update(sql,st.getSid(),new SimpleDateFormat("yyyy-MM-dd").format(st.getDate()),st.getHowtime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}