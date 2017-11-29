<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*,cn.zy.basic.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>修改用户</title>
</head>
<script language="javascript">
	function fn(){
		window.location="Web_AllUserServlet";
	}
	function sub(){
		var pattern=new RegExp("^[0-9]*[a-z]*[A-Z]*$");
		var pattern2=new RegExp("^1[0-9]{10}$");
		if(pattern.test(document.form1.username.value)&&pattern.test(document.form1.pwd.value)&&pattern2.test(document.form1.phone.value)){
			var f=document.form1.userid.value+"@@"+document.form1.username.value+"@@"+document.form1.pwd.value+"@@"+document.form1.phone.value;
			window.location="Web_AllUserServlet?user="+f+"&handle=update";
		}else{
			document.getElementById("errorspan").innerHTML="<span>用户信息不符合规范（用户名、密码由数字和字母组成），请检查！</span>";
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
			//userid,username,pwd,phone
			String userid=request.getParameter("userid");
			String username=request.getParameter("username");
			String pwd=request.getParameter("pwd");
			String phone=request.getParameter("phone");
			String re=request.getParameter("re");
			if("phone".equals(re)){
		%>
				<script>alert("手机号重复！");</script>
		<%
			}else if("name".equals(re)){
		%>
				<script>alert("用户名重复！");</script>
		<%
			}
 		%>

        <form id="form1" name="form1" method="post" action="">
	        <div style="background-color:#FFF; border: 1px solid #91C0E3; padding: 20px 10px 20px 70px; margin: 10px 30px;">
		      <table cellpadding="0" cellspacing="5" class="mainForm">
	            	<tr>
		            	<td width="10%">用户编号：</td>
						<td><input name="userid" type="text" class="InputStyle" value="<%=userid %>" style="width:300px" disabled="disabled"/></td>
					</tr>
					<tr>
						<td width="10%">用户名：</td>
						<td><input name="username" type="text" class="InputStyle" value="<%=username %>" style="width:300px" />*</td>
					</tr>
					<tr>
							<td width="20%">密码：</td>
						<td><input name="pwd" type="text" class="InputStyle" value="<%=pwd %>" style="width:300px"/>*</td>
					</tr>
					<tr>
							<td width="15%">手机号：</td>
							<td><input name="phone" type="text" class="InputStyle" value="<%=phone %>" style="width:300px" />*</td>
					</tr>
					<tr><td></td>
						<td>
							<span id="errorspan"></span>
						</td>
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