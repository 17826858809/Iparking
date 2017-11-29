<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*,cn.zy.basic.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>车位管理</title>
</head>
<script language="javascript">
	function fn(){
		window.location="Web_TimeServlet";
	}
	function sub(){
		if(document.form1.time.value==""){
			alert("时间格式不正确！");
		}else{
			var f=document.form1.sid.value+"@@"+document.form1.date.value+"@@"+document.form1.time.value;
			window.location="Web_TimeServlet?time="+f+"&handle=update";
		}
	}
</script>
<body>
	<div id="main">
<!--标题-->
   		<div id="top">
        	<div id="stop">
				<img src="style/images/title_arrow.gif" />修改
			</div>
            <div id="stop2"></div>
            
        </div>
        <div id="white"></div>
	    <br/>
	    <div>
	    <%
	    	String sid = request.getParameter("id"); 
	    	String date = request.getParameter("date"); 
	    	String time = request.getParameter("time"); 
	    %>
        <form id="form1" name="form1" method="post" action="">
	        <div style="background-color:#FFF; border: 1px solid #91C0E3; padding: 20px 10px 20px 70px; margin: 10px 30px;">
		      <table cellpadding="0" cellspacing="5" class="mainForm">
	            	<tr>
		            	<td width="10%">车位编号:</td>
						<td><input name="sid" type="text" class="InputStyle" value="<%=sid %>" style="width:300px" disabled="disabled"/></td>
					</tr>
					<tr>
						<td width="20%">日期：</td>
						<td><input name="date" type="text" class="InputStyle" value="<%=date %>" style="width:300px" disabled="disabled"/></td>
					</tr>
					<tr>
						<td width="20%">时间：</td>
						<td><input name="time" type="text" class="InputStyle" value="<%=time %>" style="width:300px"/> *(格式：8:00-12:00 14:00-18:00)</td>
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