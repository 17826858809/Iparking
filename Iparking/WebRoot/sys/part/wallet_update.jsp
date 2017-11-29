<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,cn.zy.basic.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style/css/right.css" />
	<title>钱包管理</title>
</head>
<%
	String userid=request.getParameter("id");
	String money=request.getParameter("money");
	String deposit=request.getParameter("deposit");
	String ticket=request.getParameter("ticket");
%>
<script language="javascript">
	function fn(){
		window.location="Web_WalletServlet";
	}
	function update(){
		var f = document.form1.userid.value+"@@"+document.form1.money.value+"@@"+document.form1.deposit.value+"@@"+document.form1.ticket.value;
		window.location="Web_WalletServlet?wallet="+f+"&handle=update";
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
        <form id="form1" name="form1" method="post" action="">
	        <div style="background-color:#FFF; border: 1px solid #91C0E3; padding: 20px 10px 20px 70px; margin: 10px 30px;">
		      <table cellpadding="0" cellspacing="5" class="mainForm">
	            	<tr>
						<td width="10%">用户编号</td>		
						<td><input type="text" name="userid" class="InputStyle" value=<%=userid %> style="width: 300px" disabled="disabled"/> *</td>
	             	</tr>
	                <tr>
						<td width="20%">钱包余额</td>
						<td><input type="text" name="money" class="InputStyle" value=<%=money %> style="width: 300px"/> *</td>
	                </tr>
	                <tr>
						<td width="15%">押金剩余</td>
						<td><input type="text" name="deposit" class="InputStyle" value=<%=deposit %> style="width: 300px"/> *</td>
	                </tr>
	                <tr>
	                	<td width="15%">优惠券</td>
						<td><input type="text" name="ticket" class="InputStyle" value=<%=ticket %> style="width: 300px"/> *</td>
	                
					</tr>
	    		</table>
	          </div>
	            <center>
	              <p>
	                    <input name="提交" type="button" id="button2" onclick="update()" value="修改" />
	                    <input name="按钮" type="button" id="button" onclick="fn()" value="返回"/>
	              </p>
	          </center>
          </form>
		</div>
    </div>
</body>
</html>