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
		window.location="Web_SpaceServlet";
	}
	function sub(){
		if(document.form1.slocation.value==""){
			alert("请输入位置！");
		}else{
			var ss=document.form1.longitude.value;
			if(ss==null||ss==""){
				ss=0;
			}
			var ll=document.form1.latitude.value;
			if(ll==null||ll==""){
				ll=0;
			}
			var users=document.form1.users.value;
			if(users.equals("zhengfu")){
				users=1;
			}else{
				users=2;
			}
			var suo=document.form1.suo.value;
			if(suo.equals("blue")){
				suo=1;
			}else{
				suo=0;
			}
			var f=document.form1.userid.value+"@@"+document.form1.slocation.value+"@@"+document.form1.issure.value+"@@"+ss+"@@"+ll+"@@"+users+"@@"+suo;
			window.location="Web_SpaceServlet?space="+f+"&handle=add";
		}
	}
</script>
<body>
	<div id="main">
<!--标题-->
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
		            	<td width="10%">车位编号:</td>
						<td><input name="sid" type="text" class="InputStyle" style="width:300px" disabled="disabled"/></td>
					</tr>
					<tr>
						<td width="10%">车主编号</td>
						<td>
							<select name="userid" style="width:80px">
<%								
							List<Admin> list = new AdminService().findAllNoSpace();	
							Iterator<Admin> it = list.iterator();
							while(it.hasNext()){
								Admin ss=it.next();	
 %>
				   				<option value="<%=ss.getUserid() %>"><%=ss.getUserid() %></option>
<%
							}
 %>
				   			</select>*
				   		</td>
					</tr>
					<tr>
							<td width="20%">位置</td>
						<td><input name="slocation" type="text" class="InputStyle" style="width:300px"/>*</td>
					</tr>
					<tr>
							<td width="15%">是否安装</td>
						<td>
							<select name="issure" style="width:80px">
				   				<option value="0">未安装</option>
				   				<option value="1">已安装</option>
				   			</select>*
				   		</td>
					</tr>
					<tr>
							<td width="15%">车位经度</td>
							<td><input name="longitude" type="text" class="InputStyle" style="width:300px"/></td>
					</tr>
					<tr>
							<td width="15%">车位纬度</td>
							<td><input name="latitude" type="text" class="InputStyle" style="width:300px"/></td>
					</tr>
					<tr><td>车位所属者</td>
						<td>
							<select name="users">
            					<option value="person" >个人（小区）</option>
            					<option value="zhengfu">政府（路边）</option>	
            				</select>*
            			</td>
            		</tr>
            		<tr>
            			<td>车位锁属性</td>
            			<td>
            				<select name="suo">
            					<option value="blue" >蓝牙锁</option>
            					<option value="wifi">联网锁</option>	
            				</select>*
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