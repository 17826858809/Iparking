package cn.zy.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zy.basic.entity.SpaceState;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpaceStateService;


public class Web_StateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String handle=request.getParameter("handle");
		String sid=request.getParameter("sid");
		SpaceState spaceState = new SpaceState();
		try{
			if("alt".equals(handle)){
				spaceState.setSid(Integer.parseInt(request.getParameter("id")));
				new SpaceStateService().changeState(spaceState);
			}
			List<SpaceState> list=new ArrayList<SpaceState>();
			if(sid==null||sid==""){	//没有查找，查找所有
				list=new SpaceStateService().findAll();
			}
			else{
				spaceState.setSid(Integer.parseInt(sid));
				list.add(new SpaceStateService().findState(spaceState));
			}
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("right_state.jsp").forward(request, response);
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
