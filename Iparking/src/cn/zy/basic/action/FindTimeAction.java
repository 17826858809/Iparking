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

import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.service.SpacetimeService;

public class FindTimeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 找到自己的车位出租时间
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
		System.out.println("FindTimeServlet");
		String sid = req.getParameter("sid");
		String day = req.getParameter("day");
		System.out.println(sid+"  "+day);
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		try {
			Spacetime spacetime = new SpacetimeService().findBySD(Integer.parseInt(sid),day);
			
			if(spacetime!=null&&!"".equals(spacetime)){
				obj.put("time", spacetime.getAlltime());
				json.put(obj);
				//System.out.println(spacetime.getHowtime());
			}else{
				return;
			}
			
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
