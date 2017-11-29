package cn.zy.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.zy.basic.entity.Admin;
import cn.zy.basic.entity.BluetoothText;
import cn.zy.basic.service.AdminService;
import cn.zy.basic.service.BTextService;

public class Web_MMServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String handle=request.getParameter("handle");
		String sid=request.getParameter("sid");
		String used=request.getParameter("used");
		try{
			if("del".equals(handle)){
				int id=Integer.parseInt(request.getParameter("id"));
				new BTextService().delText(id);
				
			}else if("alt".equals(handle)){
				int id=Integer.parseInt(request.getParameter("id"));
				new BTextService().altUsed(id);
				
			}else if("add".equals(handle)){
				String s=request.getParameter("mm");
				String[] value=s.split("@@");
				BluetoothText f=new BluetoothText();
				f.setSid(Integer.parseInt(value[0]));
				f.setText(value[1]);
				new BTextService().addUsed(f);
	
			}
			List<BluetoothText> list=new ArrayList<BluetoothText>();
			if((sid==null||sid=="")&&(used==null||used=="")){
				list=new BTextService().findAll();
			}else if((sid!=null&&sid!="")&&(used==null||used=="")){
				list=new BTextService().findBySid(Integer.parseInt(sid));
			}else if((sid==null||sid=="")&&(used!=null&&used!="")){
				list=new BTextService().findByUsed();
			}else{
				list=new BTextService().findBySU(Integer.parseInt(sid));
			}
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("right_mm.jsp").forward(request, response);
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
