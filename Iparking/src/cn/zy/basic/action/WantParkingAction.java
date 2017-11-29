package cn.zy.basic.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.zy.basic.entity.Bluetooth;
import cn.zy.basic.entity.History;
import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.service.BTextService;
import cn.zy.basic.service.BluetoothService;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpacesService;
import cn.zy.basic.service.SpacetimeService;

import com.opensymphony.xwork2.ActionSupport;

public class WantParkingAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 我要停车2 (IN->userid,sid,ordertime,isblue,f,OUT->flag(timeover,success,system,wrong),hid,(mac,text)|text
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest req;
    HttpServletResponse resp;

    public void setServletRequest(HttpServletRequest request) {
     this.req=request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.resp=response;
    }
    public void servlet(){
		System.out.println("WantParkingServlet");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		History history = new History();
		//String userName = req.getParameter("userName");
		String userid = req.getParameter("userid");
		String sid = req.getParameter("sid");
		String ordertime = req.getParameter("ordertime");
		String isblue = req.getParameter("isblue");
		String f = req.getParameter("f");	//1为路边，2为小区
		String flag="wrong";
		Bluetooth blue=null;
		
		//将时间减少ordertime
		if(f.equals("2")){	///小区
			Spacetime st = new SpacetimeService().findBySD(Integer.parseInt(sid), new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
			if(st==null||"".equals(st)){
				flag="timeover";
			}else{
				String ho = st.getHowtime();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				try{
					String[] od = ordertime.split("-");
					Date od1 = sdf.parse(od[0]);
					Date od2 = sdf.parse(od[1]);
					
					//获取到的时间格式：8:00-12:00 13:00-17:00
					String[] how = ho.split(" ");//分割得到每一部分可用时间 8:00-12:00
					for(int i=0;i<how.length;i++){
						String[] after = how[i].split("-");//再分割得到开始和结束时间 8:00 12:00
						//比较时间
						Date date1 = sdf.parse(after[0]);
						Date date2 = sdf.parse(after[1]);
						if(od1.compareTo(date1)<0){	//od1在8：00之前
							flag="timeover";
							break;
						}else if(od1.compareTo(date2)>0){	//od1在date2之后
							if((i+1)>=how.length){
								flag="timeover";
								break;
							}
							continue;
						}else if(od2.compareTo(date2)>0){	//od1在date1和date2之间，od2在date2之后
							flag = "timeover";
							break;
						}
						
						//修改时间
						String strs = "";
						for(int j = 0;j<i;j++){
							strs+=how[j]+" ";
						}
						if(!od[0].equals(after[0])){
							strs+=after[0]+"-"+od[0]+" ";
						}
						if(!od[1].equals(after[1])){
							strs+=od[1]+"-"+after[1]+" ";
						}
						for(int j = i+1;j<how.length;j++){
							strs+=how[j]+" ";
						}
						st.setHowtime(strs);
						new SpacetimeService().update(st);
						
						//插入记录
						history.setUserid(Integer.parseInt(userid));
						history.setSid(Integer.parseInt(sid));
						history.setOrdertime(ordertime);
							
						int info = new HistoryService().add(history);
							
						if(info>0){	//预约成功，获取到车位的位置和可使用时间
							System.out.println("预约成功！");
							flag = "success";
							
							try{
								obj.put("hid", info);
								
								if(isblue.equals("1")){
									//找到用户订单的车位蓝牙或者车位主的蓝牙mac和text
								    blue = new BluetoothService().findBluetooth(Integer.parseInt(sid),0);
									new BTextService().changeBluetooth(Integer.parseInt(sid));
									obj.put("mac", blue.getBluetoothmac());
									obj.put("text", blue.getIntext());									
								}else{
									//查询TCP密码
									String text = new SpacesService().findTcpPass(Integer.parseInt(sid));									
									obj.put("text", text);
								}
							}catch(Exception e){throw e;}
							System.out.println(obj);
						}else{
							System.out.println("预约失败！");
							flag = "system";
						}
											
						break;
					}
				}catch(Exception e){}
			}
			
		}else{
			flag = "wrong";
		}
		try{
			obj.put("flag", flag);
			json = new JSONArray().put(obj);
		
			// 返回信息到客户端
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(json.toString());
			out.flush();
			out.close();
		}catch(Exception e){}
	}
	
}
