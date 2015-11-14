<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deleteUser.jsp' starting page</title>
    
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
	<link href="admin/images/skin.css" rel="stylesheet" type="text/css" />
	
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script language="javascript" src="js/Calendar2.js"></script>	
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.2.js"></script>
	
	<script type="text/javascript">
			//显示时间
			var cdr = new Calendar("cdr");
		    document.write(cdr);
		    cdr.showMoreDay = true;
		//表单校验
		function check() {
		//alert("xxx");
			var content = document.getElementById("inputUserIds").value;
			//alert(content);
			var arr = content.split(";");
			for (var i = 0; i < arr.length; ++i) {
				if (isNaN(arr[i])) {
					alert("输入数据非法");
					event.returnValue = false;
					return ;
				}
			}
			return true;
			
		}
	</script>
	<style type="text/css">
			body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #F7F8F9;
		}
		</style>
  </head>
  
  <body>
    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="17" valign="top" background="images/mail_leftbg.gif">
				<img src="admin/images/left-top-right.gif" width="17" height="29" />
			</td>
			<td valign="top" background="admin/images/content-bg.gif">
				<table width="100%" height="31" border="0" cellpadding="0"
					cellspacing="0" class="left_topbg" id="table2">
					<tr>
						<td height="31"><div class="titlebt">删除用户</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
   </table>
   <div style="margin-top: 50px; margin-left: 100px">
	  	<s:form action="deleteAll_UserAction" onsubmit="check()" method="post">
	  		<font color="red" > 输入要删除的用户编号多个用户用";"隔开</font> <br/> 
	   	  	 <input  type="text" class="input-medium search-query" name="inputUserIds" id="inputUserIds">
	 		 <button type="submit" class="btn">删除</button>
	   	</s:form>
   	</div>
  </body>
</html>
