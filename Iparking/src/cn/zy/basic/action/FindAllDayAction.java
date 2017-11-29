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

import cn.zy.basic.entity.Spaces;
import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.service.SpacesService;
import cn.zy.basic.service.SpacetimeService;

public class FindAllDayAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
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
		System.out.println("FindAllDayServlet");
		String uid = req.getParameter("userid");
		
		JSONArray json = new JSONArray();
		try {
			Spaces ss = new Spaces();
			ss.setUserid(Integer.parseInt(uid));
			List<Spaces> l = new SpacesService().findSpace(ss);
			ss = l.iterator().next();
			json.put(new JSONObject().put("sid", ss.getSid()));
			List<Spacetime> list = new SpacetimeService().findBySid(ss.getSid());
			System.out.println(list.size());
			Iterator it = list.iterator();
			while(it.hasNext()){
				Spacetime st = (Spacetime)it.next();
				JSONObject obj = new JSONObject();
				obj.put("date", st.getDate());
				json.put(obj);
				System.out.println(obj.getString("date"));
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
