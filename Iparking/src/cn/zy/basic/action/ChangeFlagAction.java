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

import cn.zy.basic.entity.SpaceState;
import cn.zy.basic.service.SpaceStateService;

public class ChangeFlagAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 将车位状态修改
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
		System.out.println("ChangeFlagServlet");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		String sid = req.getParameter("sid");
		SpaceState ss = new SpaceState();
		ss.setSid(Integer.parseInt(sid));
		int flag = new SpaceStateService().changeState(ss);
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
