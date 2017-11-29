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

import cn.zy.basic.entity.Bluetooth;
import cn.zy.basic.entity.History;
import cn.zy.basic.entity.SpaceState;
import cn.zy.basic.entity.Spaces;
import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.entity.Wallet;
import cn.zy.basic.service.BTextService;
import cn.zy.basic.service.BluetoothService;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpaceStateService;
import cn.zy.basic.service.SpacesService;
import cn.zy.basic.service.SpacetimeService;
import cn.zy.basic.service.WalletService;

public class ChangeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	/**
	 * 修改预约时间、修改历史记录
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
		System.out.println("change");
		JSONArray json = null;
		JSONObject obj = new JSONObject();
		History history = new History();
		int hid = Integer.parseInt(req.getParameter("hid"));
		int sid = Integer.parseInt(req.getParameter("sid"));
		String ordertime = req.getParameter("ordertime");
		
		//将时间减少ordertime
		Spacetime st = new SpacetimeService().findBySD(sid, new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
		String ho = st.getHowtime();
		String sss="";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try{
			Date dd = sdf.parse(ordertime);
			//获取到的时间格式：8:00-12:00 13:00-17:00
			String[] how = ho.split("[ -]");//分割得到每一部分可用时间 8:00 12:00 13:00 17:00
			for(int i=0;i<how.length;i++){
				if(sdf.parse(how[i]).compareTo(dd)>0){
					if(i%2==1){
						how[i-1]=ordertime;
					}
					break;
				}
			}
			for(int i=0;i<how.length;i++){
				if(i%2==0){
					sss+=how[i]+"-";
				}else if(i%2==1&&(i+1)<how.length){
					sss+=how[i]+" ";
				}else{
					sss+=how[i];
				}
			}
			st.setHowtime(sss);
			new SpacetimeService().update(st);
			history.setHid(hid);
			history = new HistoryService().findByHid(hid);
			String before = history.getOrdertime().split("-")[0];
			String all = before+"-"+ordertime;
			history.setOrdertime(all);
			new HistoryService().change(history);
			
			obj.put("flag", "success");
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
