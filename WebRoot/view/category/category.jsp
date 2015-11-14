<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>书籍分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<link rel="stylesheet" type="text/css" href="css/category1.css">
    <link rel="stylesheet" type="text/css" href="css/category2.css">
	<link rel="stylesheet" type="text/css" href="css/category3.css">
	<link rel="stylesheet" type="text/css" href="css/a.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/b.css">
    <link rel="stylesheet" type="text/css" href="css/c.css">

    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	<script type="text/javascript" src="js/select.js"> </script>
	
	

  </head>
  
  <body id="book">
	<jsp:include page="/view/top.jsp"></jsp:include>
     <div class="w">
		<div class="breadcrumb">
		    <strong><a href="index_UIAction">图书</a></strong>&nbsp;&gt;&nbsp;<span><a href="getAllCategory_UIAction">图书全部分类</a></span>
		    <div id="hotkeywords"></div>
		</div>
	</div><!--crumb end-->
	<div class="w main">
		<div class="m m0" id="booksort">
			<div class="mt">
				<h2>图书全部分类</h2>
			</div>
			<div class="mc">
                <dl>
                	<s:iterator var="category" value="categorys">
	                	<dt><a href="queryByCategory_BookAction?queryBookInfo.type=${category.key.id }"> <s:property value="#category.key.name"/> </a><b></b></dt>
	                	<dd>
	                		<s:iterator var="subcategory" value="#category.value">	
	                			<em><a href="queryByCategory_BookAction?queryBookInfo.type=${subcategory.id }"><s:property value="#subcategory.name"/>  </a></em>
	                		</s:iterator>
	                	</dd>
                	</s:iterator>
				</dl>
			</div>
		</div><!--booksort end-->
	</div><!--main end-->
    
   <jsp:include page="/view/bottom.jsp"></jsp:include>
	
  </body>
</html>





