<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="css/leftCategory.css">
	<link rel="stylesheet" type="text/css" href="css/a.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/b.css">
    <link rel="stylesheet" type="text/css" href="css/c.css">
    
    <script type="text/javascript" src="css/d.css"></script>
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	<script type="text/javascript" src="css/e.css" charset="gb2312"></script>
	<script type="text/javascript" src="css/f.css" charset="gb2312"></script>
	<script language="javascript" src="js/Calendar2.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/cart1.css">
	<link rel="stylesheet" type="text/css" href="css/cart2.css">
	
	
    <style type="text/css">
    	.breadcrumb{
			margin-left: 70px;
		}
		#div1{
			margin-left: 70px;
			margin-right: 75px;
			
		}
		
		#div2{
			margin-left: 65px;
		}
    </style>
    
   
	<script src="js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		 //删除选定用户
		function f_dlBook(obj) {
			//alert("aa");
			var x = confirm("确定要删除书籍" + obj);
			if (x) {
				window.location.href = "delete_CartAction?isbn=" + obj;
			}
		}
		
		//检查购物车是否为空， 为空不能结算
		function gotoPayment() {
			var tabNode = document.getElementById("table1");
			//alert("tabNode = " + tabNode.rows.length);
			if (tabNode.rows.length <= 1) {
				alert("您的购物车为空，请挑选您喜爱的书籍");
			} else {
				window.location.href = "gotoPayment_PaymentAction";
			}
		}
	</script>
  </head>
  
  <body>
  
    <jsp:include page="/view/top.jsp"></jsp:include>
    
    	<div class="breadcrumb">
    		<strong><a href="<%=basePath %>/view/index.jsp">我的汇书</a></strong>
    		<span>&nbsp;&gt;&nbsp;我的购物车<span></span></span>
   		</div>

		<div id="div1">
			<hr  dir="rtl">
				
			<hr>
		</div>
		
   
    <div id="div2">
    	<div class="breadcrumb">
    		<strong><a href="<%=basePath %>/view/index.jsp">我的购物车</a></strong>
		</div>
		
		
		<div>
				<img alt="" src="images/gouwu01.png" width="243px" height="170px">
				<a href="index_UIAction"> <img alt="继续购买" src="images/continueBuy.png"> </a>
			    <a href="javascript:void(0)" onclick="gotoPayment()"> <img alt="去结算" src="images/toPay.png"> </a>
		</div>
		
    	<table id="table1"  rules="rows" width="1210px" border="1"  border="1" style="border-collapse:collapse; border-color: #808080">
    		<tr bgcolor="#F7F7F7" height="38px"> <th>全选</th>  <th>商品</th> <th>汇书价</th>  <th>数量</th> <th>小计</th>  <th>操作</th> </tr>
    		<s:iterator id="colum" value="#session.cart">
	    		<tr><td colspan="6" bgcolor="#DBEEFD" height="38px">  广告</td></tr>
	    		<tr bgcolor="#FFFDEE" height="80px">
	    			<td> 1 </td>
	    			<td align="center"><a href="clientQueryById_BookAction?book.id=${ value.book.id }"> <img alt="${ value.book.name }" src="${ value.book.picture } " width="70px" height="60px"> </a> <a href="clientQueryById_BookAction?book.id=${ value.book.id }"> <s:property value="value.book.name"/> </a>    </td>
	    			<td align="center"> <fmt:formatNumber type="number" value=" ${ value.book.hbPrice } " maxFractionDigits="1"/>     </td>
	    			<td align="center">  ${ value.num }    </td>
	    			<td align="center">    <fmt:formatNumber type="number" value=" ${ value.price } " maxFractionDigits="1"/>   </td>
	    			<td align="center"> <a href="javascript:f_dlBook('${ value.book.isbn }')" > 删除 </a> </td>
	    		</tr>
    		</s:iterator>
    	
    	</table>
    </div>
     
    <jsp:include page="/view/bottom.jsp"></jsp:include>
  </body>
</html>
