<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>找回密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-responsive.css">
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
	<link rel="stylesheet" href="css/bootstrap.css">

  <head>
  
 
<script language="javascript">//标题栏显示时间的脚本
	function check() {
		//alert("...." + document.form1.userName.value);
		if (document.form1.userName.value == "") {
			alert("用户名不能为空");
			document.form1.userName.focus();
			event.returnValue = false;
			return ;
		}
		if (document.form1.email.value == "") {
			alert("邮箱不能为空");
			document.form1.email.focus();
			event.returnValue = false;
			return ;
		}
		if (document.form1.phone.value == "") {
			alert("手机号不能为空");
			document.form1.phone.focus();
			event.returnValue = false;
			return ;
		}
		event.returnValue = true;
	}
</script>
</head>
  
  <body>
  
  	<table width="70%" height="20" border="0" bgcolor="" align="center">
     <tr><td align="center"><font family="微软雅黑" size="5"><b> 汇书网找回密码 </b></font></td></tr>
     
    </table>
    <hr size="1" color="#C5a5a7" width="100%" align="center"> 
   
   
  	<form id="form1" name="form1" action="retrive_UIAction" method="post" onsubmit="check()" >
  		<div align="center"><font color="red" >带星为必填项</font> </div> 
	  	<table class="" background="images/whit.jpg"  cellspacing="0" cellpadding="" width="525" height="525"  border="5"  bgColor="#1cf9ee"  align="center">
	  			
	  		<tr>
	  			<td align="center">
	  				用户名：<input id="userName" name="user.userName" type="text" > <font color="red">*</font>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td align="center">
	  				邮&nbsp;&nbsp;&nbsp;&nbsp;箱：<input id="email" name="user.email" type="text" > <font color="red">*</font>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td align="center">
	  				手机号：<input id="phone" name="user.phone" type="text" > <font color="red">*</font>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td align="center">
	  				<input type="submit" value="提交" class="btn">
	  			</td>
	  		</tr>
	  	</table>
	 </form>
	  	 <hr size="1" color="#C5a5a7" width="100%">
     <table width="70%" height="20" border="0" bgcolor="" align="center">
     <tr><td align="center"><font family="微软雅黑" size="2"> &nbsp;&nbsp;Copyright&copy;&nbsp;2012-2013 TNT小组 版权所有 </font></td></tr>
  		
  
  </body>
</html>


