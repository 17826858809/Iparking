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

import cn.zy.basic.entity.Repair;
import cn.zy.basic.service.RepairService;

public class RepairAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 报修
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
		System.out.println("RepairServlet");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		String sid = req.getParameter("sid");
		String text = req.getParameter("reason");
		Repair re = new Repair();
		re.setSid(Integer.parseInt(sid));
		re.setReason(text);
		new RepairService().add(re);
		try{
			obj.put("flag", "success");
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
