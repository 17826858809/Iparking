<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>用户钱包管理</title>
</head>
<body>
	<div id="main">
<!--标题-->
    	<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />用户资金列表
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
<!--搜索区-->
      <div id="search">
        	<form action="Web_WalletServlet" method="post">
            	用户编号：<input name="userid" type="text"/>
            	押金 < <input name="deposit" type="text" />
                <input name="searchkey" type="submit" value="搜索" />
          </form>
      </div>
<!--表体-->
<%
	List<Wallet> list=(List<Wallet>)request.getAttribute("list");
	if(list!=null){
		Iterator<Wallet> iter=list.iterator();
 %>
        <div id="table">
        	<div id="real">
            	<table width="100%" cellpadding="0" cellspacing="0" class="mainTable">
        <!-- 表头-->
        			<thead>
            			<tr align="center" valign="middle" id="TableTitle">
							<td width="10%">用户编号</td>
							<td width="20%">钱包余额</td>
							<td width="15%">押金剩余</td>
							<td width="15%">优惠券</td>
							<td width="25%">操作</td>
						</tr>
					</thead>	
		<!--显示数据列表 -->
        			<tbody id="TableData">
<%
						while(iter.hasNext()){
							Wallet tt=iter.next();
 %>
						<tr>
							<td align="center"><%=tt.getUserid() %></td>
							<td align="center"><%=tt.getMoney()%></td>
							<td align="center"><%=tt.getDeposit() %></td>
							<td align="center"><%=tt.getTickets() %></td>
							<td>
								<a href="wallet_update.jsp?id=<%=tt.getUserid()%>&money=<%=tt.getMoney() %>&deposit=<%=tt.getDeposit() %>&ticket=<%=tt.getTickets() %>" class="FunctionButton">修改</a>									
							</td>
						</tr>
<% 
						}
%>
        			</tbody>
    			</table>
            </div>
        </div>
<%	}
 %>
    </div>
</body>
</html>