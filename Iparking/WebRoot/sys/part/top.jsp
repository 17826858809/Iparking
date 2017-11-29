<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*,cn.zy.basic.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/top.css" />
	<title>top</title>
</head>
<script></script>
<body>
	<div id="head1">
    	<div id="head1_logo">
        	<b>Iparking信息管理平台</b>
        </div>
        <div id="head1_welcome">
        	<img src="style/images/user.gif"/> 您好，<b>管理员</b>
        </div>
        
    </div>
    
    <div id="head2">
    	<div id="head2_position" style="float:left">
			<a href="javascript: window.parent.right.history.back();">
				<img src="style/images/Header_back.gif" width="24" height="24" style="margin-top: -8px;"/>
				<b>后退</b>
			</a>
			<a href="javascript: window.parent.right.history.forward();">
				<img src="style/images/Header_forward.gif" width="24" height="24" style="margin-top: -8px;"/>
				<b>前进</b>		
			</a>
        </div>
        <div id="head2_refresh" style="float:right">
			<a href="javascript: window.parent.right.history.go(0);">
				<img src="style/images/Header_refresh.gif" width="24" height="24" style="margin-top: -8px;"/>
				<b>刷新(IE、Chrome)</b>		
			</a>
			<a href="javascript: window.parent.right.location.reload(true);">
				<img src="style/images/Header_refresh.gif" width="24" height="24" style="margin-top: -8px;"/>
				<b>刷新(Firefox)</b>		
			</a>
          </div>
    </div>
</body>
</html>
