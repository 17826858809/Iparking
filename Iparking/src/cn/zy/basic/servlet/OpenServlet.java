package cn.zy.basic.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.zy.basic.entity.Admin;
import cn.zy.basic.entity.History;
import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.service.AdminService;
import cn.zy.basic.service.BTextService;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpaceStateService;
import cn.zy.basic.service.SpacetimeService;
/**
 *路边的车位根据howtime改变状态，小区车位不改变
 *分析是否超时
 */
public class OpenServlet implements ServletContextListener{
	private Mythread mythread;
	public void contextInitialized(ServletContextEvent arg0) {
			System.out.println("容器初始化！");
			mythread=new Mythread();
			mythread.start();
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
		mythread.interrupt();
		
	}
}

class Mythread extends Thread {
	@Override
	public void run() {
		while (!this.isInterrupted()) {// 线程未中断执行循环
		
			String flag  = "0";	//时间不可用传入数据库的参数  
			String flag2 = "0";	//时间可用   set canuse = 1
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String now = sdf.format(new Date());
			/*		
			try {
				Date now_date = sdf.parse(now); //当前时、分转换为date便于比较
				//获取今天所有可用车位及时间
				List<Spacetime> listTime = new SpacetimeService().findUsetime();
				Iterator lit=listTime.iterator();
				while(lit.hasNext()){
					Spacetime st = (Spacetime)lit.next();
					String time = st.getHowtime();
					int id = st.getSid();
					if(time.equals("0")){
						flag+=","+id;
					}else{
						//获取到的时间格式：8:00-12:00 13:00-17:00
						String[] how = time.split(" ");//分割得到每一部分可用时间 8:00-12:00
						for(int i=0;i<how.length;i++){
							String[] after = how[i].split("-");//再分割得到开始和结束时间 8:00 12:00
	
							//比较时间
							Date date1 = sdf.parse(after[0]);
							Date date2 = sdf.parse(after[1]);
							if(now_date.compareTo(date1)<0){
								flag+=","+id;
								break;
							}else if(now_date.compareTo(date2)>0){
								if((i+1)>=how.length){
									flag+=","+id;
									break;
								}
								continue;
							}
							flag2+=","+id;
							break;
						}
					}
				}
				new SpaceStateService().changeByOutTime(flag);	//改变车位时间不可用状态
				new SpaceStateService().changeByInTime(flag2);	//改变车位时间可用状态
				
				System.out.println("time"+flag+"==="+flag2);
		*/
			//当天有开放时间的为可用
			List<Spacetime> listTime = new SpacetimeService().findUsetime();
			Iterator lit=listTime.iterator();
			while(lit.hasNext()){
				Spacetime st = (Spacetime)lit.next();
				String time = st.getHowtime();
				int id = st.getSid();
				if(time.equals("0")){
					flag+=","+id;
				}else{
					flag2+=","+id;
				}
			}
			new SpaceStateService().changeByOutTime(flag);	//改变车位时间不可用状态
			new SpaceStateService().changeByInTime(flag2);	//改变车位时间可用状态
			
			System.out.println("time"+flag+"==="+flag2);
	
			
				//监听动态密码是否全为零
				new BTextService().listener();
			try{
				//超时判断
				//找到tb_history中money为空usedate不为空的记录，分析ordertime
				List<History> list = new HistoryService().findOver();
				Iterator it = list.iterator();
				History history = new History();
				String order;
				long remain=0;
				while(it.hasNext()){
					try{
						history = (History)it.next();
						order = history.getOrdertime().split("-")[1];
						remain = (sdf.parse(order).getTime() - sdf.parse(now).getTime()) / 1000 / 60;
		                if(remain == -15){
		                	//TODO:超时15分钟，客服协调
		                	new AdminService().findUserinto(history.getHid(),history.getUserid(),order);
		                	
		                }
					}catch(Exception e){}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(30000); //每隔30s执行一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}

