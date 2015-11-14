<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deleteAuthor.jsp' starting page</title>
    
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
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script language="javascript" src="js/Calendar2.js"></script>
	
  </head>
  
  <body>
  	<!--  
	    <s:form action="deleteAll_AuthorAction">
	  		 输入要删除的作者的编号多个用户用";"隔开 <br/>
	  		 <input  type="text" class="input-medium search-query" name = "inputAuthorIds">
	  		 <input type="submit" value="删除" class="btn">
	   	  	
	   	</s:form>
   	-->
   	
   	<h1 style="color: red;">暂无此功能</h1>
  </body>
</html>
