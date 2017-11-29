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

public class HistoryAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 找到用户车位使用情况
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
		System.out.println("HistoryServlet");
		String userid = req.getParameter("userid");
		int sid = Integer.parseInt(req.getParameter("sid"));
		History history = new History();
		Admin admin = new Admin();
		JSONArray json = new JSONArray();
		try {
			if(userid!=""){
				//通过userid找到用户自己的所有车位（spaces表）
				Spaces s = new Spaces();
				s.setUserid(Integer.parseInt(userid));
				List<Spaces> spacelist = new SpacesService().findSpace(s);
				Iterator spaceIt = spacelist.iterator();
				s = (Spaces)spaceIt.next();
				sid=s.getSid();
			}
			history.setSid(sid);
			List<History> list = new HistoryService().find(history);
			Iterator it = list.iterator();
			int i = 1;
			history = (History)it.next();
			JSONObject obj = new JSONObject();
			admin.setUserid(history.getUserid());
			admin = new AdminService().findUser(admin);
			obj.put("id", i);
			obj.put("sid", history.getSid());
			obj.put("tel", admin.getPhone());
			
			json.put(obj);
			
			System.out.println(history.getUsetime());
				
			
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
