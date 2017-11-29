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

import cn.zy.basic.entity.History;
import cn.zy.basic.entity.SpaceState;
import cn.zy.basic.entity.Spaces;
import cn.zy.basic.entity.Wallet;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpaceStateService;
import cn.zy.basic.service.SpacesService;
import cn.zy.basic.service.WalletService;

public class SpaceFlagAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 我要停车前传-->查询（查询余额、判断车位使用状态、获取车位信息、获取tcppass） 参数：userid、车位号 
	 * out->flag(wrongnumber,system,nomoney,success)、sid、location、what、isblue
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
		System.out.println("SpaceFlagServlet");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		History history = new History();
		//String userName = req.getParameter("userName");
		String userid = req.getParameter("uid");
		String sid = req.getParameter("sid");
		String flag=null;
		//先查询用户余额
		Wallet wallet = new Wallet();
		wallet.setUserid(Integer.parseInt(userid));
		wallet = new WalletService().findMoney(wallet);
		Double money = wallet.getMoney();
		System.out.println("find wallet,"+money);
		if(money>0){
			//如果用户余额大于0，查询车位号sid是否存在
			SpaceState ss = new SpaceState();
			ss.setSid(Integer.parseInt(sid));
			ss = new SpaceStateService().findState(ss);
			if(ss==null){
				flag = "wrongnumber";
				System.out.println("wrongnumber");
			}else{
				int state = ss.getCanuse();
				System.out.println(state);
				if(state!=1){
					flag = "wrongnumber";
				}else{
					
					//查询车位详情
					Spaces spaces = new Spaces();
					spaces.setSid(Integer.parseInt(sid));
					spaces = new SpacesService().findLocation(spaces);	
					String location = spaces.getSlocation(); 
					String isblue = spaces.getIsblue()+"";	
					String what = spaces.getFlag()+"";
				
					try{
						obj.put("sid", sid);
						obj.put("location", location);
						obj.put("what", what);
						obj.put("isblue",isblue);					
				
						flag = "success";
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}
			}
		}else{
			System.out.println("wallet获得余额小于0！");
			flag = "nomoney";
		}
		try{
			obj.put("flag", flag);
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
