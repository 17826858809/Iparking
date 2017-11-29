<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>用户管理</title>
</head>
<body>
	<div id="main">
<!--标题-->
    	<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />用户列表
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
<!--搜索区-->
      <div id="search">
        	<form action="Web_AllUserServlet" method="post">
            	手机号:<input name="key" type="text"/>
                <input name="searchkey" type="submit" value="搜索" />
          </form>
      </div>
<!--表体-->
<%
	int flag = (Integer)request.getAttribute("flag");
	List<Admin> list=(List<Admin>)request.getAttribute("list");
	if(flag==0){
	%>
		<script type="text/javascript" language="javascript">
			alert("无法删除，用户是车位主！");          // 弹出错误信息
		</script>	
<%
	}else if(flag == 1){
	%>
		<script type="text/javascript" language="javascript">
			alert("删除成功！");                   // 弹出错误信息
		</script>	
<%
	}
	
 %>
        <div id="table">
        	<div id="real">
            	<table width="100%" cellpadding="0" cellspacing="0" class="mainTable">
        <!-- 表头-->
        			<thead>
            			<tr align="center" valign="middle" id="TableTitle">
							<td width="9%">用户编号</td>
							<td width="14%">用户名</td>
							<td width="11%">密码</td>
							<td width="41%">手机号</td>
							<td width="25%">操作</td>
						</tr>
					</thead>	
		<!--显示数据列表 -->
        			<tbody id="TableData">
<%
					if(list!=null){
						Iterator<Admin> iter=list.iterator();
						while(iter.hasNext()){
							Admin tt=iter.next();
 %>
						<tr>
							<td align="center"><%=tt.getUserid() %></td>
							<td align="center"><%=tt.getUserName() %></td>
							<td align="center"><%=tt.getPwd() %></td>
							<td align="center"><%=tt.getPhone() %></td>
							
							<td>
								<a href="user_update.jsp?userid=<%=tt.getUserid() %>&username=<%=tt.getUserName()%>&pwd=<%=tt.getPwd() %>&phone=<%=tt.getPhone()%>" class="FunctionButton">修改</a>				
							</td>
						</tr>
<% 
						}
					}
%>
        			</tbody>
    			</table>
            </div>
            <div id="add" align="center">
	            <div class="FunctionButton"><a href="addUser.jsp">添加</a></div>
            </div>
        </div>

    </div>
</body>
</html>