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
/*
 * 登录
 */

public class Web_AdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name=request.getParameter("userName");
		String pwd=request.getParameter("pwd");
		int flag = -1;
		try{
			Admin admin=new Admin();
			admin.setUserName(name);
			admin.setPwd(pwd);
			admin=new AdminService().loginAdmin(admin);
			if(admin==null){
				request.getRequestDispatcher("/sys/login.jsp").forward(request, response);
				
			}else{
				request.getSession().setAttribute("login", true);
				request.getRequestDispatcher("/sys/index.html").forward(request, response);
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
