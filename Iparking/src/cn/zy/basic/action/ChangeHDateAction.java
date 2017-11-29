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
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpacetimeService;

public class ChangeHDateAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 修改history的usedate,usetime,money="-1",用来标注出使用了或者正在使用的记录
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
		System.out.println("changeHDate");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		History history = new History();
		int hid = Integer.parseInt(req.getParameter("hid"));
		String date = req.getParameter("date");
		String time = req.getParameter("time");
		try{
			Date usedate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			history.setUsedate(usedate);
			history.setHid(hid);
			history.setUsetime(time);
			int i = new HistoryService().changeDate(history);
			if(i>0){
				obj.put("flag", "success");
			}else obj.put("flag", "fail");
			
			json = new JSONArray().put(obj);
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(json.toString());
			out.flush();
			out.close();
			
		}catch(Exception e){}
		// 返回信息到客户端
	}
}
