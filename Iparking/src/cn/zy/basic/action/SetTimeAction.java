package cn.zy.basic.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

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

public class SetTimeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
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
		System.out.println("SetTimeServlet");
		String sid = req.getParameter("sid");
		String day = req.getParameter("day");
		String time = req.getParameter("time");
		JSONArray json = new JSONArray();
		JSONObject obj = new JSONObject();
		Spacetime st = new Spacetime();
		st.setSid(Integer.parseInt(sid));
		st.setHowtime(time);
		try{
			st.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(day));
		
			int s = new SpacetimeService().setTime(st);
			String flag ;
			if(s>0){
				flag = "success";
			}else{
				flag = "fail";
			}
			obj.put("flag", flag);
			json.put(obj);
			System.out.println(flag);
			
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
