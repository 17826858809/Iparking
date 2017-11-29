<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <script>
  	function validate(f){
  		if(!(/^[A-Za-z0-9]{4,40}$/.test(f.mid.value))){
  			alert("用户名必须为数字和字母!");
  			//f.mid.focus();
  			return false;
  		}
  		return true;
  	}
  </script>
  <body style="text-align:center">
 	<form action="/IparkingWeb/sys/Web_AdminServlet" method="post" onSubmit="return validate(this)" style=" border: solid #069 1px; width:300px; height: 150px; margin: 0 auto; margin-top:200px; padding-top: 50px; ">  
  		用户名：<input type="text" name="userName"><br/><br/>
  		密   码：<input type="text" name="pwd"><br/><br/>
  		<input type="submit" value="登录"/>
  	</form>
  </body>
</html>
