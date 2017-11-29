package cn.zy.basic.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import cn.zy.basic.entity.Bluetooth;
import cn.zy.basic.entity.Spaces;
import cn.zy.basic.service.BluetoothService;
import cn.zy.basic.service.SpacesService;

public class MySpaceAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 找到用户自己的车位
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
		System.out.println("MySpaceServlet");
		String userid = req.getParameter("userid");
		System.out.println("userid="+userid);
		JSONArray json = new JSONArray();
		Bluetooth bt;
		try {
			//通过userid找到用户自己的所有车位（spaces表）
			Spaces s = new Spaces();
			s.setUserid(Integer.parseInt(userid));
			List<Spaces> spacelist = new SpacesService().findSpace(s);
			Iterator spaceIt = spacelist.iterator();
			while(spaceIt.hasNext()){
				s = (Spaces)spaceIt.next();
				JSONObject obj = new JSONObject();
				if(s.getIsblue()==1){
					bt = new BluetoothService().findBluetooth(s.getSid(), 1);
					obj.put("mac", bt.getBluetoothmac());
					obj.put("text",bt.getIntext());
				}else if(s.getIsblue()==0){
					obj.put("tcp", new SpacesService().findTcpPass(s.getSid()));
				}
				obj.put("sid", s.getSid());
				obj.put("issure", s.getIssure());
				obj.put("slocation", s.getSlocation());
				obj.put("longitude", s.getLongitude());
				obj.put("latitude", s.getLatitude());
				obj.put("isblue", s.getIsblue());
				json.put(obj);
				System.out.println(s.getSid()+"=="+obj.get("sid"));
				
			}
			if(json.length()==0){return;}
			// 返回信息到客户端
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
	}
}
