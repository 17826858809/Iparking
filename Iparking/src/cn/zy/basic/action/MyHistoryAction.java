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

import cn.zy.basic.entity.Admin;
import cn.zy.basic.entity.History;
import cn.zy.basic.entity.Spaces;
import cn.zy.basic.service.AdminService;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpacesService;

public class MyHistoryAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 找到此用户使用情况
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
		System.out.println("MyHistoryServlet");
		String userid = req.getParameter("userid");
		History history = new History();
		history.setUserid(Integer.parseInt(userid));
		JSONArray json = new JSONArray();
		try {
			List<History> list = new HistoryService().findMy(history);
			Iterator it = list.iterator();
			int i = 1;
			while(it.hasNext()){
				JSONObject obj = new JSONObject();
				history = (History)it.next();
				obj.put("id", i);
				obj.put("sid", history.getSid());
				obj.put("ordertime", history.getOrdertime());
				obj.put("usedate", (history.getUsedate()==null||"".equals(history.getUsedate()))?"null":history.getUsedate());
				obj.put("usetime", (history.getUsetime()==null||"".equals(history.getUsetime()))?"null":history.getUsetime());
				obj.put("money", ("".equals(history.getMoney()))?"0":history.getMoney());
				json.put(obj);
				i++;
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
