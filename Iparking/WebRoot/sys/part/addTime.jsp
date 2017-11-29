<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*,cn.zy.basic.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>时间管理</title>
</head>
<%
	String flag = request.getParameter("flag");
	System.out.println(flag); 
	if("0".equals(flag)){
%>
		<script>alert("此车位当天已有记录！");	</script>
<%
	}
%>
<script>
	function fn(){
		window.location="Web_TimeServlet";
	}
	function sub(){
		if(document.form1.date.value==""||document.form1.time.value==""){
			alert("信息不完整！");
		}else{
			var f=document.form1.sid.value+"@@"+document.form1.date.value+"@@"+document.form1.time.value;
			window.location="Web_TimeServlet?time="+f+"&handle=add";
		}
	}
</script>

<body>

	<div id="main">
<br /><br /><!--标题-->
   		<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />添加
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
	    <br/>
	    <div>
	    <form id="form1" name="form1" method="post" action="">
	        <div style="background-color:#FFF; border: 1px solid #91C0E3; padding: 20px 10px 20px 70px; margin: 10px 30px;">
		      <table cellpadding="0" cellspacing="5" class="mainForm">
	            	<tr>
						<td width="10%">车位编号：</td>
						<td>
							<select name="sid" style="width:80px">
<%								
							List<Spaces> list = new SpacesService().findAllIn();	
							Iterator<Spaces> it = list.iterator();
							while(it.hasNext()){
								Spaces ss=it.next();	
 %>
				   				<option value="<%=ss.getSid() %>"><%=ss.getSid() %></option>
<%
							}
 %>
				   			</select> *
				   		</td>
					</tr>
					<tr>
							<td width="20%">日期：</td>
						<td><input name="date" type="date" class="InputStyle" style="width:300px"/> *</td>
					</tr>
					<tr>
							<td width="15%">时间：</td>
							<td><input name="time" type="text" class="InputStyle" style="width:300px"/> *(格式：8:00-12:00 14:00-18:00)</td>
					</tr>
	    		</table>
	          </div>
	            <center>
	              <p>
	                    <input name="提交" type="button" id="button2" onclick="sub()" value="提交" />
	                    <input name="按钮" type="button" id="button" onclick="fn()" value="返回"/>
	              </p>
	          </center>
          </form>
		</div>
    </div>
</body>
</html>