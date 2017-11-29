<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>车位可用时间管理</title>
</head>
<body>
	<div id="main">
<!--标题-->
    	<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />车位可用时间管理
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
<!--搜索区-->
      <div id="search">
        	<form action="Web_TimeServlet" method="post">
            	车位号：<input name="sid" type="text" />
            	日期:<input name="date" type="date"/>
                <input name="searchkey" type="submit" value="搜索" />
          </form>
      </div>
<!--表体-->
<%
	List<Spacetime> list=(List<Spacetime>)request.getAttribute("list");
	if(list!=null){
		Iterator<Spacetime> iter=list.iterator();
 %>
        <div id="table">
        	<div id="real">
            	<table width="100%" cellpadding="0" cellspacing="0" class="mainTable">
        <!-- 表头-->
        			<thead>
            			<tr align="center" valign="middle" id="TableTitle">
							<td width="10%">车位号</td>
							<td width="30%">日期</td>
							<td width="15%">可使用时间</td>
							<td width="25%">操作</td>
						</tr>
					</thead>	
		<!--显示数据列表 -->
        			<tbody id="TableData">
<%
						while(iter.hasNext()){
							Spacetime tt=iter.next();
 %>
						<tr>
							<td align="center"><%=tt.getSid() %></td>
							<td align="center"><%=tt.getDate() %></td>							
							<td align="center"><%=tt.getHowtime() %></td>
							<td>
								<a href="time_update.jsp?id=<%=tt.getSid()%>&date=<%=tt.getDate()%>&time=<%=tt.getHowtime() %>" class="FunctionButton">修改</a>
								<a href="Web_TimeServlet?handle=del&sid=<%=tt.getSid()%>&date=<%=tt.getDate()%>" class="FunctionButton">删除</a>	
							</td>
						</tr>
<% 
						}
%>
        			</tbody>
    			</table>
            </div>
            <div id="add" align="center">
	            <div class="FunctionButton"><a href="addTime.jsp">添加</a></div>
            </div>
        </div>
<%	}
 %>
    </div>
</body>
</html>