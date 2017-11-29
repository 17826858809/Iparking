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

public class FindSpaceIsusedAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 查看车位是否已经结束
	 * IN:hid
	 * out:usetime
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
		System.out.println("FindSpaceIssure");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		String hid = req.getParameter("hid");

		History history = new HistoryService().findByHid(Integer.parseInt(hid));
	
		try{
			obj.put("usetime", history.getUsetime());
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
