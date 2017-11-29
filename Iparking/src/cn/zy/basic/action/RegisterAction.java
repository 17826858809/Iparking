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

import cn.zy.basic.entity.Admin;
import cn.zy.basic.service.AdminService;

public class RegisterAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 用户注册
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
    	try {
			System.out.println("RegisterServlet");
			Admin admin = new Admin();
			String username=req.getParameter("username");
			String pwd=req.getParameter("pwd");
			String phone = req.getParameter("phone");
			admin.setUserName(new String(username.getBytes("iso8859-1"),"utf-8"));
			admin.setPwd(pwd);
			admin.setPhone(phone);
			System.out.println(admin.getUserName());
			String flag = "fail";
			JSONArray json = null;
			Admin findId = new AdminService().findPhone(admin);
			if(findId!=null){
				flag = "phone";
			}else{
				Admin findName = new AdminService().findName(admin);
				if(findName!=null){
					flag="name";
				}else{
					int ff = new AdminService().register(admin);
					if (ff > 0) {
						//成功
						flag="success";
					}else{
						flag="fail";
					}
				}
			}
			System.out.println(username);
			System.out.println(pwd);
			System.out.println(phone);
			System.out.println(flag);
			json = new JSONArray().put(new JSONObject().put("flag",flag));
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
