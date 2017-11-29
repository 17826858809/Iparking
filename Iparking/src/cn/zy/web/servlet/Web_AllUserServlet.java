package cn.zy.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zy.basic.entity.Admin;
import cn.zy.basic.entity.Spaces;
import cn.zy.basic.service.AdminService;
import cn.zy.basic.service.SpacesService;


public class Web_AllUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String handle=request.getParameter("handle");
		String key=request.getParameter("key");
		int flag = -1;
		String str = "right_user.jsp";
		try{
			if("update".equals(handle)){
				String s=request.getParameter("user");
				String[] value=s.split("@@");
				Admin f=new Admin();
				f.setUserid(Integer.parseInt(value[0]));
				f.setUserName(value[1]);
				f.setPwd(value[2]);
				f.setPhone(value[3]);
				Admin aa = new AdminService().findPhone(f);
				Admin bb = new AdminService().findName(f);
				if(aa!=null&&aa.getUserid()!=f.getUserid()){
					str = "user_update.jsp";
					request.getRequestDispatcher("user_update.jsp?re=phone&userid="+f.getUserid()+"&username="+f.getUserName()+"&pwd="+f.getPwd()+"&phone="+f.getPhone()).forward(request, response);
				}else if(bb!=null&&bb.getUserid()!=f.getUserid()){
					str = "user_update.jsp";
					request.getRequestDispatcher("user_update.jsp?re=name&userid="+f.getUserid()+"&username="+f.getUserName()+"&pwd="+f.getPwd()+"&phone="+f.getPhone()).forward(request, response);
				}else{
					new AdminService().update(f);
				}
			}else if("add".equals(handle)){
				String s=request.getParameter("user");
				String[] value=s.split("@@");
				Admin f=new Admin();
				f.setUserName(value[0]);
				f.setPwd(value[1]);
				f.setPhone(value[2]);
				if(new AdminService().findPhone(f)!=null){
					request.setAttribute("re", "phone");
					str="addUser.jsp";
					request.getRequestDispatcher("addUser.jsp").forward(request, response);
				}else if(new AdminService().findName(f)!=null){
					request.setAttribute("re", "name");
					str="addUser.jsp";
					request.getRequestDispatcher("addUser.jsp").forward(request, response);
				}else{
					new AdminService().register(f);
				}
			}
			if("right_user.jsp".equals(str)){
				List<Admin> list=new ArrayList<Admin>();
				if(key==null||key==""){
					list=new AdminService().findAllUser();
				}else{
					Admin admin = new Admin();
					admin.setPhone(key);
					admin=new AdminService().findPhone(admin);
					list.clear();
					if(admin==null){
						list=null;
					}else{
						list.add(admin);
					}
				}
				request.setAttribute("flag", flag);
				request.setAttribute("list", list);
				
				request.getRequestDispatcher(str).forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
