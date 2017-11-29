package cn.zy.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.service.SpacetimeService;


public class Web_TimeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid=request.getParameter("sid");
		String date=request.getParameter("date");
		String handle=request.getParameter("handle");
		Spacetime spacetime = new Spacetime();
		int flag=1;
		try{	
			if("del".equals(handle)){
				new SpacetimeService().del(Integer.parseInt(sid),date);
				sid=null;
				date=null;
			}else if("update".equals(handle)){
				String s=request.getParameter("time");
				String[] value=s.split("@@");
				Spacetime f=new Spacetime();
				f.setSid(Integer.parseInt(value[0]));
				f.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(value[1]));
				f.setHowtime(value[2]);
				new SpacetimeService().update(f);
			}else if("add".equals(handle)){
				String s=request.getParameter("time");
				String[] value=s.split("@@");
				Spacetime f=new Spacetime();
				f.setSid(Integer.parseInt(value[0]));
				f.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(value[1].replaceAll("/", "-")));
				f.setHowtime(value[2]);
				try{
					new SpacetimeService().add(f);
				}catch(Exception e){
					flag=0;
				}
			}
			if(flag==0){
				request.getRequestDispatcher("addTime.jsp?flag=0").forward(request, response);
			}else{
				List<Spacetime> list=new ArrayList<Spacetime>();
				if((sid==null||sid=="")&&(date==null||date=="")){	//没有查找，查找所有
					list=new SpacetimeService().findAll();
				}else if((sid!=null&&sid!="")&&(date==null||date=="")){
					list=new SpacetimeService().findBySid(Integer.parseInt(sid));
				}else if((sid==null||sid=="")&&(date!=null&&date!="")){
					date.replaceAll("/", "-");
					list=new SpacetimeService().findByDate(date);
				}else{
					date.replaceAll("/", "-");
					list.add(new SpacetimeService().findBySD(Integer.parseInt(sid),date));
				}
				request.setAttribute("list", list);
				
				request.getRequestDispatcher("right_time.jsp").forward(request, response);
			}
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
