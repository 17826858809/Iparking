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

import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.service.SpacetimeService;

public class findTimeLengthAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 查找可延长的时间
	 * IN->sid,ordertime
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
		System.out.println("findTimeLengthServlet");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		int sid = Integer.parseInt(req.getParameter("sid"));
		String ordertime = req.getParameter("ordertime");
		String re=null;
		
		Spacetime st = new SpacetimeService().findBySD(sid,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		String all = st.getHowtime();
		String[] str = all.split("[ -]");//用空格和短杠分割
		SimpleDateFormat sdf = new SimpleDateFormat("HH-mm");
		try{
			for(int i=0;i<str.length;i++){
				if(sdf.parse(str[i]).compareTo(sdf.parse(sdf.format(new Date())))>0){
					if(i%2==1){	//每一小段的后一个（截止）
						re=str[i];
					}
					break;		
				}
			}
		
			obj.put("re", re);
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
