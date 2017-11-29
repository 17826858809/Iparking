package cn.zy.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zy.basic.entity.Bluetooth;
import cn.zy.basic.entity.Spaces;
import cn.zy.basic.service.BluetoothService;
import cn.zy.basic.service.SpacesService;


public class Web_BluetoothServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String handle=request.getParameter("handle");
		int sid=Integer.parseInt(request.getParameter("id"));
		Bluetooth space = new Bluetooth();
		try{
			if("update".equals(handle)){
				//sid,userid,slocation,issure,longitude,latitude,
				String s=request.getParameter("space");
				String[] value=s.split("@@");
				Spaces f=new Spaces();
				f.setSid(Integer.parseInt(value[0]));
				f.setUserid(Integer.parseInt(value[1]));
				f.setSlocation(value[2]);
				f.setIssure(Integer.parseInt(value[3]));
				f.setLongitude(Double.parseDouble(value[4]));
				f.setLatitude(Double.parseDouble(value[5]));
				new SpacesService().update(f);
			}else if("add".equals(handle)){
				//userid,slocation,issure,longitude,latitude,
				String s=request.getParameter("space");
				String[] value=s.split("@@");
				Spaces f=new Spaces();
				f.setUserid(Integer.parseInt(value[0]));
				f.setSlocation(value[1]);
				f.setIssure(Integer.parseInt(value[2]));
				f.setLongitude(Double.parseDouble(value[3]));
				f.setLatitude(Double.parseDouble(value[4]));
				new SpacesService().addSpace(f);
			}
			Bluetooth list=new BluetoothService().findBluetooth(sid, 1);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("space_detial.jsp").forward(request, response);
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
