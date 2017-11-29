package cn.zy.web.servlet;

import java.io.IOException;
import java.net.URLDecoder;
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


public class Web_SpaceServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String handle=request.getParameter("handle");
		String sid=request.getParameter("sid");
		String userid=request.getParameter("userid");
		String issure=request.getParameter("issure");	//on、null
		String users=request.getParameter("users");
		String suo=request.getParameter("suo");
		System.out.println(sid+" "+userid+" "+issure+" "+users+" "+suo);
		Spaces space = new Spaces();
		try{
			if("update".equals(handle)){
				//sid,userid,slocation,issure,longitude,latitude,
				String s=request.getParameter("space");
				String[] value=s.split("@@");
				Spaces f=new Spaces();
				f.setSid(Integer.parseInt(value[0]));
				f.setUserid(Integer.parseInt(value[1]));
			//	String slocation = new String(request.getParameter("slocation").getBytes("iso8859-1"), "UTF-8");
		    	
				f.setSlocation(new String(value[2].getBytes("iso8859-1"),"UTF-8"));
				f.setIssure(Integer.parseInt(value[3]));
				f.setLongitude(Double.parseDouble(value[4]));
				f.setLatitude(Double.parseDouble(value[5]));
				f.setFlag(Integer.parseInt(value[6]));
				f.setIsblue(Integer.parseInt(value[7]));
				
				new SpacesService().update(f);
			}else if("add".equals(handle)){
				//userid,slocation,issure,longitude,latitude,
				String s=request.getParameter("space");
				String[] value=s.split("@@");
				Spaces f=new Spaces();
				f.setUserid(Integer.parseInt(value[0]));
				f.setSlocation(new String(value[1].getBytes("iso8859-1"),"utf-8"));
				f.setIssure(Integer.parseInt(value[2]));
				f.setLongitude(Double.parseDouble(value[3]));
				f.setLatitude(Double.parseDouble(value[4]));
				f.setFlag(Integer.parseInt(value[5]));
				f.setIsblue(Integer.parseInt(value[6]));
				
				new SpacesService().addSpace(f);
			}else if("upbt".equals(handle)){
				//sid,mac,pwd
				String s=request.getParameter("bt");
				String[] value=s.split("@@");
				if(value[1].equals("null")){
					//tcp密码修改
					new SpacesService().changeTcpPass(Integer.parseInt(value[0]),value[2]);
				}else{
					Bluetooth f=new Bluetooth();
					f.setSid(Integer.parseInt(value[0]));
					f.setBluetoothmac(value[1]);
					f.setIntext(value[2]);
					new BluetoothService().update(f);
				}
			}
			List<Spaces> list=new ArrayList<Spaces>();
			//条件：sid是否为空，userid是否为空，issure（是否已安装）是否勾选，users(路边zhengfu、小区person、全部alluser)，suo（蓝牙blue，联网wifi，全部allclock）
			if((sid==null||sid=="")&&(userid==null||userid=="")&&(issure==null||issure=="")&&(users==null||users.equals("zhengfu"))&&(suo==null||suo.equals("allclock"))){	//找到所有的
				list=new SpacesService().findAllSpace();
			}else if(sid!=null&&sid!=""){	//根据sid查找
				space.setSid(Integer.parseInt(sid));
				space=new SpacesService().findLocation(space);
				list.add(space);
			}else if(userid!=null&&userid!=""){	//根据userid
				space.setUserid(Integer.parseInt(userid));
				list=new SpacesService().findSpace(space);
			}else if(issure!=null&&issure!=""){	//根据issure
				list=new SpacesService().findAllIn();
			}else if(users.equals("zhengfu")){	//根据users(路边)
				list=new SpacesService().webFindAllByFlag(1);
			}else if(users.equals("person")){	//根据users(小区)
				list=new SpacesService().webFindAllByFlag(2);
			}else if(suo.equals("blue")){	//根据锁（蓝牙，联网）
				list=new SpacesService().webFindAllByIsblue(1);
			}else if(suo.equals("wifi")){	//根据锁（蓝牙，联网）
				list=new SpacesService().webFindAllByIsblue(0);
			}
		//	request.setAttribute("flag", flag);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("right_space.jsp").forward(request, response);
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
