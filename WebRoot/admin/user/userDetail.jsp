<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userDetail.jsp' starting page</title>
    
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
    <table class="table table-hover">
    	
    	<tr>
    		 <td>用户编号： ${ user.id }  </td>
    		 <td>真实姓名： ${ user.realName } </td>
        </tr>
    	<tr>
    		 <td>用&nbsp;&nbsp;户&nbsp;&nbsp;名： ${ user.userName }</td>
    		 <td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：${ user.password }</td>
        </tr>
        <tr>
    		 <td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：${ user.sex }  </td>
    		 <td>Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：${ user.email }</td>
        </tr>
        <tr>
    		 <td>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：${ user.phone }</td>
    		 <td>Q&nbsp;&nbsp;&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ： ${ user.userQq } </td>
        </tr>
        <tr>
    		 <td>家庭住址：${ user.address }</td>
    		 <td>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：
    		 <s:date name="%{user.birthday}" format="yyyy-MM-dd"/>
    		 </td>
        </tr>
        <tr>
        	<td colspan="2">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限：
        		<s:select id="type"  name="type" list="permissions" listKey="fkey" listValue="fvalue" value="%{ user.type }" disabled="disabled">
  				</s:select>
        	</td>
        </tr>
    </table>
  </body>
</html>
