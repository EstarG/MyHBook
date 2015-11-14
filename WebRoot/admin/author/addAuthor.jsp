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
    
    <title>My JSP 'addAuthor.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		 <!-- 引入bootstrap所需css与js -->
 	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-responsive.css">
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link href="admin/images/skin.css" rel="stylesheet" type="text/css" />
	
	
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script language="javascript" src="js/Calendar2.js"></script>
	
	<script type="text/javascript">
	//直接return 是没错的， 不过eclispe10报错
		function check() {
			//alert(document.form1.name.value + " -- " + document.form1.infor.value);
			if (document.form1.name.value == "") {
				alert("作者姓名不能为空");
				document.form1.name.focus();
				event.returnValue = false;
				return ;
			}
			if (document.form1.infor.value.trim() == "") {
				alert("作者简介不能为空");
				document.form1.infor.focus();
				event.returnValue = false;
				return ;
			}
			event.returnValue = true;
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
						<td height="31"><div class="titlebt">添加用户</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
  	 </table>
  
    	<s:form action="add_AuthorAction" id = "form1" onsubmit="check()" method="post">
    		 <font color="red">带*为必填项</font> 
    		<table class="table table-hover">
    			<tr>
    				 <td> 作者姓名：<input id="name" type="text" name="name" /> <font color="red">*</font>  </td> 
    			</tr>
    			<tr>
    				<td colspan="2">
    				        作者信息 ： 
    				 	<textarea id="infor" rows="20%" cols="60%" name="infor">
    				 	
    				 	</textarea>
    				 	<font color="red">*</font>
    				 </td>
    			</tr>
    			
    			<tr>
    				<td>
    					<input type="submit" value="添加" class="btn">
			    		<input type="reset" value="重置" class="btn">
			    		 <font color="red">${ message }</font>
    				</td>
    			</tr>
    		</table>
    		
    		
    	</s:form>
  </body>
</html>
