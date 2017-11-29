package cn.zy.basic.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import cn.zy.basic.entity.History;
import cn.zy.basic.entity.SpaceState;
import cn.zy.basic.entity.Spaces;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpaceStateService;
import cn.zy.basic.service.SpacesService;

public class RoadWantParkingAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 我要停车（车位使用状态变成不可用、添加纪录、获取车位信息、获取tcppass） 参数：userid、车位号 、fl(0 只查询，1 全部)
	 * out->flag(system,success)、sid、location、what、isblue + hid、text(路边)
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
		System.out.println("RoadWantParking");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		History history = new History();
		//String userName = req.getParameter("userName");
		String userid = req.getParameter("userid");
		String sid = req.getParameter("sid");
		String fl = req.getParameter("fl");
		String flag="success";
							
		//查询车位详情
		Spaces spaces = new Spaces();
		spaces.setSid(Integer.parseInt(sid));
		spaces = new SpacesService().findLocation(spaces);	
		String location = spaces.getSlocation(); 
		String isblue = spaces.getIsblue()+"";	
		String what = spaces.getFlag()+"";
	
		try{
			obj.put("sid", sid);
			obj.put("location", location);
			obj.put("what", what);
			obj.put("isblue",isblue);					
			//查询TCP密码
			String text = new SpacesService().findTcpPass(Integer.parseInt(sid));							
			obj.put("text", text);
			
			if("1".equals(fl)){
				if("1".equals(what)){	//路边车位
					SpaceState ss = new SpaceState();
					ss.setSid(Integer.parseInt(sid));
					//车位可使用信息改为不可用
					int ischanged = new SpaceStateService().changeState(ss);
					System.out.println("ischanged"+ischanged);
					if(ischanged==1){
						System.out.println("车位信息修改成功");
						history.setUserid(Integer.parseInt(userid));
						history.setSid(Integer.parseInt(sid));
						history.setOrdertime(new SimpleDateFormat("HH:mm").format(new Date()));
						//添加使用纪录(money为-的表示用车还没结束)
						int info = new HistoryService().add(history);
						System.out.println("添加使用记录："+info);
						
						if(info>0){	//预约成功，获取到车位的位置和可使用时间
		
							flag = "success";
							System.out.println("预约成功！");
							
							obj.put("hid", info);
							
						}else{
							System.out.println("预约失败！");
							flag = "system";
						}
					}else{
						System.out.println("changed state failed!");
						flag = "system";
					}
		
				}else{	//小区车位
					flag = "success";
					}
				}
			}catch(Exception e){
				throw new RuntimeException(e);
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
