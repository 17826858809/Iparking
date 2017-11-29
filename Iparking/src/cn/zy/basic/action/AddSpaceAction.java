package cn.zy.basic.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import cn.zy.basic.entity.Spaces;
import cn.zy.basic.service.BluetoothService;
import cn.zy.basic.service.SpaceStateService;
import cn.zy.basic.service.SpacesService;

public class AddSpaceAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 发布车位
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
		System.out.println("AddSpaceServlet");
		String userid = req.getParameter("userid");
		String location = req.getParameter("slocation");
		System.out.println(userid);
		JSONArray json = new JSONArray();
		try {
			Spaces s = new Spaces();
			s.setUserid(Integer.parseInt(userid));
			s.setSlocation(new String(location.getBytes("iso8859-1"),"utf-8"));
			s.setIssure(0);
			s.setLatitude(0);
			s.setLongitude(0);
			synchronized(this){
				s = new SpacesService().addSpace(s);
				//修改状态 -1
				new SpaceStateService().add(s.getSid());
				//添加蓝牙信息
				new BluetoothService().add(s.getSid());
			}
			JSONObject obj = new JSONObject();
			obj.put("sid", s.getSid());
			obj.put("userid", s.getUserid());
			obj.put("slocation", s.getSlocation());
			obj.put("issure", s.getIssure());	
			obj.put("longitude", s.getLongitude());
			obj.put("latitude", s.getLatitude());
			json.put(obj);
			
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
