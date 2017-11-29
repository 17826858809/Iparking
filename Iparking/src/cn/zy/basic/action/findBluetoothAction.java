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

public class findBluetoothAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 找到用户订单的车位蓝牙或者车位主的蓝牙
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
		System.out.println("findBluetoothServlet");
		String sid = req.getParameter("sid");
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		//找到自己的蓝牙
		Bluetooth blue = new BluetoothService().findBluetooth(Integer.parseInt(sid),1);
		try{
			System.out.println(blue.getBluetoothmac());
			obj.put("mac", blue.getBluetoothmac());
			obj.put("text", blue.getIntext());
			json.put(obj);
			// 返回信息到客户端
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(json.toString());
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
