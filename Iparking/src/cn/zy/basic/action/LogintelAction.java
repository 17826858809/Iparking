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
import cn.zy.basic.entity.History;
import cn.zy.basic.entity.Spaces;
import cn.zy.basic.entity.Wallet;
import cn.zy.basic.service.AdminService;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpacesService;
import cn.zy.basic.service.WalletService;

public class LogintelAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 用户手机登录注册
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
		
		Admin admin = new Admin();
		String phone=req.getParameter("phone");
		admin.setPhone(phone);
		JSONArray json = null;
		JSONObject job=null;
		Wallet wallet = new Wallet();
		try {
			Admin userInfo = new AdminService().loginTel(admin);
			if (userInfo != null) {
				job=new JSONObject();
				//登录成功
				System.out.println("success");
				//req.getSession().setAttribute("userid", userInfo.getId());
				wallet.setUserid(userInfo.getUserid());
				wallet = new WalletService().findMoney(wallet);
				job.put("userid",userInfo.getUserid()).put("username", userInfo.getUserName()).put("money", wallet.getMoney());
				//检查是否有未结束的信息
				History history = new History();
				history.setUserid(userInfo.getUserid());
                history = new HistoryService().findLast(history);
                
                if(history!=null&&history.getMoney()<0){
                	job.put("hid",history.getHid() );
                	job.put("spaceid",history.getSid() );
                	job.put("ordertime",history.getOrdertime() );
                	job.put("when", history.getUsedate());//哪天
                	job.put("date",history.getUsetime() );
                	Spaces spaces = new Spaces();
                	spaces.setSid(history.getSid());
                	spaces = new SpacesService().findLocation(spaces);
                	job.put("isblue", spaces.getIsblue());
                }
                
				//flag=true;
				json = new JSONArray().put(job);
			}else{
				System.out.println("failed");
				return;
			}
			
			System.out.println("1111"+userInfo.getUserid()+"  "+userInfo.getUserName()+"  "+userInfo.getPwd()+"  "+userInfo.getPhone());
			//json = new JSONArray().put(new JSONObject().put("userid",userInfo.getUserid()).put("username", userInfo.getUserName()).put("pwd", userInfo.getPwd()).put("phone", userInfo.getPhone()).put("money", wallet.getMoney()));
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
