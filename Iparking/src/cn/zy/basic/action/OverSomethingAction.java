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
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpacetimeService;

public class OverSomethingAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 *  车离开
	 *  修改数据库车位状态,计算金额，修改记录，开放剩下的时间 IN->hid
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
		System.out.println("OverSomethingServlet");
		Date dd1s=new Date(),dd2s=new Date();
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		int hid = Integer.parseInt(req.getParameter("hid"));
		int ff = Integer.parseInt(req.getParameter("ff"));	//路边结算还是小区结算
		String use = req.getParameter("order");	//实际使用时间段
		double mon=0;
		int flag=1;
		
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		//计算金额
		try{
			String[] ht=use.split("-");
			double lo=(sdf.parse(ht[1]).getTime()-sdf.parse(ht[2]).getTime())*1.0/(1000*60);
			mon=lo*0.06;
		}catch(Exception e){}
		
		int s=0;
		if(ff==1){	//路边
			flag = 1;
		}else if(ff==2){	//小区
			//开放剩下的时间
			History hh = new HistoryService().findByHid(hid);
			String[] str = hh.getOrdertime().split("-");
			String[] safter = use.split("-");
			try{
				dd1s = new SimpleDateFormat("HH:mm").parse(str[1]);
				dd2s = new SimpleDateFormat("HH:mm").parse(safter[1]);
			}catch(Exception e){}
			if(dd1s.compareTo(dd2s)>0){
				int sid = hh.getSid();
				String strs = "";
				int is=0;
				//增加剩余时间
				Spacetime st = new SpacetimeService().findBySD(sid, new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
				String ho = st.getHowtime();
			    sdf = new SimpleDateFormat("HH:mm");
				try{
					String ordertime=safter[1]+"-"+str[1];
					Date od1 = sdf.parse(safter[1]);
					Date od2 = sdf.parse(str[1]);
							
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
									strs+=str[1] ;
								}
								for(int x=i;x<how.length;x++){
									strs+=" "+how[x];
								}

								break;
							}else{	//等于，合在一起
								if(is == 0){
									strs+=safter[1]+"-"+after[1];
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
									strs+=after[0]+"-"+str[1];
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
			flag=1;			
		}else{	//ff=3,表示状态已经被锁改变，只扣费
			flag=2;
		}
		s = new HistoryService().overtime(hid, mon ,use,flag);	//修改状态，超时扣费，history修改 or 扣费，修改history
		
		try{
			obj.put("money", mon);
			obj.put("overmoney", s);
			obj.put("flag", "success");
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
