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
import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.entity.Wallet;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpacetimeService;
import cn.zy.basic.service.WalletService;

public class CancelAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 取消预约 
	 *  增加剩余时间，修改history记录增加金额,扣费   
	 * IN->sid,uid,hid,ordertime,day,money,OUT->flag(fail,success)
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
		System.out.println("CancelServlet");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		String sid = req.getParameter("sid");
		String uid = req.getParameter("uid");
		String hid = req.getParameter("hid");
		String ordertime = req.getParameter("ordertime");
		String day = req.getParameter("day");
		double money = Double.parseDouble(req.getParameter("money"));
		String flag=null;
		int is = 0;
		String strs = "";
		
		if(day.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
			//增加剩余时间
			Spacetime st = new SpacetimeService().findBySD(Integer.parseInt(sid), day);
			String ho = st.getHowtime();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			try{
				String[] od = ordertime.split("-");
				Date od1 = sdf.parse(od[0]);
				Date od2 = sdf.parse(od[1]);
						
				//获取到的时间格式：8:00-12:00 13:00-17:00
				String[] how = ho.split(" ");//分割得到每一部分可用时间 8:00-12:00
				for(int i=0;i<how.length;i++){
					String[] after = how[i].split("-");//再分割得到开始和结束时间 8:00 12:00
					//比较时间
					Date date1 = sdf.parse(after[0]);
					Date date2 = sdf.parse(after[1]);
					if(od1.compareTo(date1)<0){		//在之前
						if(od2.compareTo(date1)<0){		//在之前，断开
							if(is == 0){
								strs+=ordertime;
							}else{
								strs+=od[1] ;
							}
							for(int x=i;x<how.length;x++){
								strs+=" "+how[x];
							}
	
							break;
						}else{	//等于，合在一起
							if(is == 0){
								strs+=od[0]+"-"+after[1];
							}else{
								strs+=after[1];
							}
							for(int x=i+1;x<how.length;x++){
								strs+=" "+how[x];
							}
							break;						
						}
					}else{	//第一段之后	
						if((i+1)>=how.length){	//总后
							if(od1.compareTo(date2)>0){
								strs=ho+" "+ordertime;
							}else{	//等于，合在一起
								for(int y=0;y<i;y++){
									strs+=how[y]+" ";
								}
								strs+=after[0]+"-"+od[1];
							}
							break;
						}else{
							for(int y=0;y<i;y++){
								strs+=how[y]+" ";
							}
							if(od1.compareTo(date2)==0){
								strs+=after[0]+"-";
								is = 1;
							}
							continue;
						}
					}
				}
					
			}catch(Exception e){
				throw new RuntimeException(e);
			}
	
			st.setHowtime(strs);
			new SpacetimeService().update(st);
		}
		//修改history记录
		History his=new History();
		his.setHid(Integer.parseInt(hid));
		his.setMoney(money);
		int info = new HistoryService().addMoney(his);
		if(info>0){
			//扣费
			Wallet wa = new Wallet();
			wa.setMoney(money);
			wa.setUserid(Integer.parseInt(uid));
			int up = new WalletService().upMoney(wa);
			if(up>0){
				flag = "success";
			}else{
				flag = "fail";
			}
		}else{
			flag = "fail";
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
