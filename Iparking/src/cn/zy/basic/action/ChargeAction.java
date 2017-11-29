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

import cn.zy.basic.service.WalletService;

public class ChargeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 充值
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
		int money = Integer.parseInt(req.getParameter("money"));
		int userid = Integer.parseInt(req.getParameter("userid"));
		int x = new WalletService().charge(money, userid);
		String flag="";
		if(x<=0){
			flag = "fail";
		}else{
			flag = "success";
		}
		JSONArray json  = new JSONArray();
		try{
			json.put(new JSONObject().put("flag", flag));
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
