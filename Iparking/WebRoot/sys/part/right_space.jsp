<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>车位管理</title>
</head>
<body>
	<div id="main">
<!--标题-->
    	<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />车位列表
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
<!--搜索区-->
      <div id="search">
        	<form action="Web_SpaceServlet" method="post">
            	车位号:<input name="sid" type="text"/>
            	车主编号:<input name="userid" type="text"/>
            	
            	车位所属者:<select name="users">
            				<option value="alluser" selected="selected">全部</option>
            				<option value="person" >个人（小区）</option>
            				<option value="zhengfu">政府（路边）</option>	
            			</select>
            	车位锁属性:<select name="suo">
            				<option value="allclock" selected="selected">全部</option>
            				<option value="blue" >蓝牙锁</option>
            				<option value="wifi">联网锁</option>	
            			</select>
            	是否安装:<input name="issure" type="checkbox"/>
                <input name="searchkey" type="submit" value="搜索" />
          </form>
      </div>
<!--表体-->
<%
	List<Spaces> list=(List<Spaces>)request.getAttribute("list");
	if(list!=null){
		Iterator<Spaces> iter=list.iterator();
 %>
        <div id="table">
        	<div id="real">
            	<table width="100%" cellpadding="0" cellspacing="0" class="mainTable">
        <!-- 表头-->
        			<thead>
            			<tr align="center" valign="middle" id="TableTitle">
							<td width="5%">车位编号</td>
							<td width="5%">车主编号</td>
							<td width="20%">位置</td>
							<td width="5%">是否安装</td>
							<td width="32%">车位经纬度</td>
							<td width="10%">个人/路边</td>
							<td width="10%">是否为蓝牙车位</td>
							<td width="15%">操作</td>
						</tr>
					</thead>	
		<!--显示数据列表 -->
        			<tbody id="TableData">
<%
						while(iter.hasNext()){
							Spaces tt=iter.next();
 %>
						<tr>
							<td align="center"><%=tt.getSid() %></td>
							<td align="center"><%=tt.getUserid() %></td>
							<td align="center"><%=tt.getSlocation() %></td>
							<td align="center"><%=tt.getIssure()%></td>
							<td align="center"><%=tt.getLatitude()+" , "+tt.getLongitude() %></td>
							<td align="center"><%if(tt.getFlag()==1){ %>路边<%}else{%>小区<%} %></td>
							<td align="center"><%if(tt.getIsblue()==1){ %>蓝牙<%}else{ %>联网<%} %></td>
							<td>
								<a href="space_update.jsp?id=<%=tt.getSid()%>&userid=<%=tt.getUserid() %>&slocation=<%=tt.getSlocation() %>&issure=<%=tt.getIssure() %>&lat=<%=tt.getLatitude()%>&long=<%=tt.getLongitude() %>" class="FunctionButton">修改</a>									
								<a href="space_detial.jsp?id=<%=tt.getSid()%>&blue=<%=tt.getIsblue() %>" class="FunctionButton">详情</a>									
							</td>
						</tr>
<% 
						}
%>
        			</tbody>
    			</table>
            </div>
            <div id="add" align="center">
	            <div class="FunctionButton"><a href="addSpace.jsp">添加</a></div>
            </div>
        </div>
<%	}
 %>
    </div>
</body>
</html>