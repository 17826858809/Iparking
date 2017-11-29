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

public class SpaceAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 找到所有车位
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
		System.out.println("SpaceServlet");
		String flag = req.getParameter("flag");
		System.out.println(flag);
		Spaces space = new Spaces();
		Spacetime st = new Spacetime();
		JSONArray json = new JSONArray();
		if(flag.equals("1")||flag.equals("2")){
			try {
				List<Spaces> list = new SpacesService().findAllByflag(flag);	//所有可用车位
				List<Spacetime> timelist = new SpacetimeService().findUsetime();	//所有车位今天的可用时间
				Iterator it = list.iterator();
				int i = 0;
				while(it.hasNext()){
					JSONObject obj = new JSONObject();
					space = (Spaces)it.next();
					int id = space.getSid();
					obj.put("sid", id);
					obj.put("longitude", space.getLongitude());
					obj.put("latitude", space.getLatitude());
					obj.put("location", space.getSlocation());
					obj.put("isblue", space.getIssure());
					Iterator timeIt = timelist.iterator();
					if(flag.equals("2")){
						while(timeIt.hasNext()){
							st = (Spacetime)timeIt.next();
							if(id==st.getSid()){
								obj.put("time", st.getHowtime());
								break;
							}
						}
						System.out.println(st.getHowtime());
					}
					json.put(i,obj);
					i++;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{return;}
		// 返回信息到客户端
		try{
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(json.toString());
			out.flush();
			out.close();
		}catch(Exception e){}
	}
	
}
