<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>车位状态管理</title>
</head>
<body>
	<div id="main">
<!--标题-->
    	<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />车位状态管理
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
<!--搜索区-->
      <div id="search">
        	<form action="Web_StateServlet" method="post">
            	车位号：<input name="sid" type="text" />
                <input name="searchkey" type="submit" value="搜索" />
          </form>
      </div>
<!--表体-->
<%
	List<SpaceState> list=(List<SpaceState>)request.getAttribute("list");
	if(list!=null){
		Iterator<SpaceState> iter=list.iterator();
 %>
        <div id="table">
        	<div id="real">
            	<table width="100%" cellpadding="0" cellspacing="0" class="mainTable">
        <!-- 表头-->
        			<thead>
            			<tr align="center" valign="middle" id="TableTitle">
							<td width="10%">车位号</td>
							<td width="15%">使用状态</td>
							<td width="25%">操作</td>
						</tr>
					</thead>	
		<!--显示数据列表 -->
        			<tbody id="TableData">
<%
						while(iter.hasNext()){
							SpaceState tt=iter.next();
							request.setAttribute("canuse",tt.getCanuse());
 %>
						<tr>
							<td align="center"><%=tt.getSid() %></td>
							<td align="center"><c:choose><c:when test="${canuse==1}">未使用</c:when><c:when test="${canuse==0}">正在使用</c:when><c:otherwise>规定时间之外</c:otherwise></c:choose>
							</td>
							<td><c:choose><c:when test="${canuse==0||canuse==1}"><a href="Web_StateServlet?handle=alt&id=<%=tt.getSid()%>" class="FunctionButton">修改为正在/未使用</a></c:when></c:choose></td>
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