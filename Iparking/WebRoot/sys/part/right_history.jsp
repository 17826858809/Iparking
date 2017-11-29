<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>租用历史管理</title>
</head>
<body>
	<div id="main">
<!--标题-->
    	<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />用户历史租用列表
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
<!--搜索区-->
      <div id="search">
        	<form action="Web_AllHistoryServlet" method="post">
            	用户编号:<input name="userid" type="text"/>
            	车位号:<input name="sid" type="text" />
                <input name="searchkey" type="submit" value="搜索" />
          </form>
      </div>
<!--表体-->
<%
	List<History> list=(List<History>)request.getAttribute("list");
	if(list!=null){
		Iterator<History> iter=list.iterator();
 %>
        <div id="table">
        	<div id="real">
            	<table width="100%" cellpadding="0" cellspacing="0" class="mainTable">
        <!-- 表头-->
        			<thead>
            			<tr align="center" valign="middle" id="TableTitle">
							<td width="7%">订单编号</td>
							<td width="7%">用户编号</td>
							<td width="7%">车位号</td>
							<td width="20%">预订时间</td>
							<td width="15%">使用日期</td>
							<td width="15%">真实使用</td>
							<td width="10%">花费</td>
							<td width="14%">操作</td>
						</tr>
					</thead>	
		<!--显示数据列表 -->
        			<tbody id="TableData">
<%
						while(iter.hasNext()){
							History tt=iter.next();
 %>
						<tr>
							<td align="center"><%=tt.getHid() %></td>
							<td align="center"><%=tt.getUserid() %></td>
							<td align="center"><%=tt.getSid() %></td>
							<td align="center"><%=tt.getOrdertime() %></td>
							<td align="center"><%=tt.getUsedate() %></td>
							<td align="center"><%=tt.getUsetime() %></td>
							<td align="center"><%=tt.getMoney() %></td>
							
							<td>
								<a href="Web_AllHistoryServlet?handle=del&hid=<%=tt.getHid() %>" class="FunctionButton">删除</a>				
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