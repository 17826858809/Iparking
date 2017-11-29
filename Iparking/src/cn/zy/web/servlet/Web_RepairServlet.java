package cn.zy.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zy.basic.entity.Admin;
import cn.zy.basic.entity.History;
import cn.zy.basic.service.AdminService;
import cn.zy.basic.service.HistoryService;


public class Web_RepairServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String handle=request.getParameter("handle");
		String userid=request.getParameter("userid");
		String sid=request.getParameter("sid");
		History history = new History();
		try{
			if("del".equals(handle)){
				int id=Integer.parseInt(request.getParameter("hid"));
				new HistoryService().delHistory(id);
			}
			List<History> list=new ArrayList<History>();
			if((userid==null||userid=="")&&(sid==null||sid=="")){	//没有查找，查找所有
				list=new HistoryService().findAllHistory();
			}else if(userid!=null&&userid!=""&&(sid==null||sid=="")){	//查找固定userid的
				history.setUserid(Integer.parseInt(userid));
				list=new HistoryService().findMy(history);
			}else if(sid!=null&&sid!=""&&(userid==null||userid=="")){	//查找固定sid的
				history.setSid(Integer.parseInt(sid));
				list=new HistoryService().find(history);
			}else{														//通过userid和sid查找
				history.setUserid(Integer.parseInt(userid));
				history.setSid(Integer.parseInt(sid));
				list=new HistoryService().findByUS(history);
			}
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("right_history.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}		

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
