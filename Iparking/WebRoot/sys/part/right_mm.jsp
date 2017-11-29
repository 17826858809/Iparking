<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>租用密码管理</title>
</head>
<body>
	<div id="main">
<!--标题-->
    	<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />租用密码列表
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
<!--搜索区-->
      <div id="search">
        	<form action="Web_MMServlet" method="post">
            	车位编号:<input name="sid" type="text"/>
            	<input name="used" type="checkbox" />未使用
                <input name="searchkey" type="submit" value="搜索" />
          </form>
      </div>
<!--表体-->
<%
	List<BluetoothText> list=(List<BluetoothText>)request.getAttribute("list");
	if(list!=null){
		Iterator<BluetoothText> iter=list.iterator();
 %>
        <div id="table">
        	<div id="real">
            	<table width="100%" cellpadding="0" cellspacing="0" class="mainTable">
        <!-- 表头-->
        			<thead>
            			<tr align="center" valign="middle" id="TableTitle">
							<td width="10%">编号</td>
							<td width="10%">车位号</td>
							<td width="20%">密码</td>
							<td width="15%">是否使用</td>
							<td width="25%">操作</td>
						</tr>
					</thead>	
		<!--显示数据列表 -->
        			<tbody id="TableData">
<%
						while(iter.hasNext()){
							BluetoothText tt=iter.next();
							request.setAttribute("isused",tt.getUsed());
 %>
						<tr>
							<td align="center"><%=tt.getId() %></td>
							<td align="center"><%=tt.getSid()%></td>
							<td align="center"><%=tt.getText() %></td>
							<td align="center"><c:choose><c:when test="${isused==1}">未使用</c:when><c:otherwise>已使用</c:otherwise></c:choose>
				</td>
							<td>
								<a href="Web_MMServlet?handle=del&id=<%=tt.getId()%>" class="FunctionButton">删除</a>									
								<a href="Web_MMServlet?handle=alt&id=<%=tt.getId()%>" class="FunctionButton">修改状态</a>									
							</td>
						</tr>
<% 
						}
%>
        			</tbody>
    			</table>
            </div>
            <div id="add" align="center" > 
            	<div class="FunctionButton"><a href="addMM.jsp">添加</a></div>
            </div>
        </div>
<%	}
 %>
    </div>
</body>
</html>