package cn.zy.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zy.basic.entity.Admin;
import cn.zy.basic.entity.Spacetime;
import cn.zy.basic.entity.Wallet;
import cn.zy.basic.service.AdminService;
import cn.zy.basic.service.HistoryService;
import cn.zy.basic.service.SpacetimeService;
import cn.zy.basic.service.WalletService;


public class Web_WalletServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String handle=request.getParameter("handle");
		String userid=request.getParameter("userid");
		String deposit = request.getParameter("deposit");
		try{
			if("del".equals(handle)){
				int id=Integer.parseInt(request.getParameter("id"));
				new AdminService().delUser(id);
			}if("update".equals(handle)){
				String s=request.getParameter("wallet");
				String[] value=s.split("@@");
				Wallet f=new Wallet();
				f.setUserid(Integer.parseInt(value[0]));
				f.setMoney(Double.parseDouble(value[1]));
				f.setDeposit(Double.parseDouble(value[2]));
				f.setTickets(Double.parseDouble(value[3]));
				new WalletService().update(f);
			}
			List<Wallet> list=new ArrayList<Wallet>();
			Wallet wallet = new Wallet();
			if((userid==null||userid=="")&&(deposit==null||deposit=="")){	//没有查找，查找所有
				list=new WalletService().findAll();
			}else if(userid!=null&&userid!=""&&(deposit==null||deposit=="")){	//查找固定userid的
				wallet.setUserid(Integer.parseInt(userid));
				wallet=new WalletService().findMoney(wallet);
				list.add(wallet);
			}else if(deposit!=null&&deposit!=""&&(userid==null||userid=="")){	//查找押金低于xx的
				try{
					list=new WalletService().findByDeposit(Double.parseDouble(deposit));
				}catch(Exception e){}
			}else{														//通过userid和余额查找
				wallet=new WalletService().findByUD(Integer.parseInt(userid),Double.parseDouble(deposit));
				list.add(wallet);
			}
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("right_wallet.jsp").forward(request, response);
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
