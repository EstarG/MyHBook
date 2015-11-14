<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的汇书</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="css/a.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/b.css">
    <link rel="stylesheet" type="text/css" href="css/c.css">
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	<script type="text/javascript" src="css/e.css" charset="gb2312"></script>
	<script type="text/javascript" src="css/f.css" charset="gb2312"></script>
	<script language="javascript" src="js/Calendar2.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
		//重置查询条件
		function f_clear(){
	   			var formObj = document.forms[1].elements;
				for (var i = 0; i < formObj.length; ++i) {
					if (formObj[i].type == 'text'){
						formObj[i].value='';
					}
				}
				document.getElementById("status").value = '0';
	   			return true;
	   	}
	   	//上下页切换
	   	function f_page(obj) {
			   
				//alert(obj)
				with(document.form1) {
					currentPage.value = obj;
					submit();
				}
		}
		//查看书籍详细信息
	   	function openBookDetail(id) {
	   		//alert("bookid = " + id);
	   		window.open ('queryBookDetail_BookAction? id=' + id,'newwindow','height=700,width=800,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,        resizable=no,location=no, status=no');
	   	}
	   	
	   	//表单校验
	   	function check() {
	   		var orderId = document.form1.orderId;
	   		var beginPrice = document.form1.beginPrice;
	   		var endPrice = document.form1.endPrice;
	   		if (null != orderId.value && isNaN(orderId.value)) {
	   			alert("订单号输入非法");
	   			orderId.focus();
	   			event.returnValue = false;
	   			return ;
	   		}
	   		if (null != beginPrice.value && isNaN(beginPrice.value)) {
	   			alert("价格输入非法");
	   			beginPrice.focus();
	   			event.returnValue = false;
	   			return ;
	   		}
	   		if (null != endPrice.value && isNaN(endPrice.value)) {
	   			alert("价格输入非法");
	   			endPrice.focus();
	   			event.returnValue = false;
	   			return ;
	   		}
	   		event.returnValue = true;
	   	}
	</script>
	<style type="text/css">
		.breadcrumb{
			margin-left: 65px;
		}
		#div1{
			margin-left: 70px;
			margin-right: 70px;
			
		}
		#div2{
			margin-left: 150px;
		}
	</style>
  </head>
  
  <body>
  	  <!-- top  -->
      <jsp:include page="/view/top.jsp"></jsp:include>
      <!-- top  -->
      
      <div class="breadcrumb">
    		<strong><a href="<%=basePath %>/view/index.jsp">我的汇书</a></strong>
    		<span>&nbsp;&gt;&nbsp;我的足迹<span></span></span>
	 </div>
		
		<div id="div1">
			<hr  dir="rtl">
				<h2>账户信息</h2>
			<hr>
			
			<table width="900px">
				<tr>
					<td> 用户名 :   ${user.userName} </td>
					<td> 姓名:     ${user.realName} </td>
					<td> 电话 :  ${user.phone}</td>	
				</tr>
				<tr>
					
					<td>邮箱：${user.email }</td>
					<td>权限: 
						<s:if test="%{#session.user.type == 83}">
							管理员
						</s:if>
						<s:if test="%{#session.user.type == 84}">
							会员
						</s:if>
						<s:if test="%{#session.user.type == 85}">
							普通用户
						</s:if>
					
					</td>
					<td> 地址:  ${user.address} </td>	
					<td> <a href="findById_UserAction?id=${ user.id }">更改个人信息</a> </td>
				</tr>
			</table>
		</div>
		<br/>

	 <div id="div1">
			<hr  dir="rtl">
				<h2>购买过的书籍</h2>
			<hr>
			
			<!-- 条件查询 -->
			
	<form action="queryUserOrderDetail_OrderDetailAction" name="form1" onsubmit="check()" method="post">
		<s:hidden name = "currentPage" value="1"></s:hidden>
		<s:hidden name="queryOrderDetailInfo.userId" value="%{queryOrderDetailInfo.userId}"></s:hidden>
    	
    	<table>
    	  <tr>
     	    	<td> 订&nbsp;&nbsp;单&nbsp;&nbsp;号： <input id="orderId" type="text" name = "queryOrderDetailInfo.orderId" value="${ queryOrderDetailInfo.orderId }">
     	    		I&nbsp;&nbsp;S&nbsp;&nbsp;B&nbsp;&nbsp;N&nbsp;： <input type="text" name = "queryOrderDetailInfo.isbn" value="${ queryOrderDetailInfo.isbn }"> 
     	    	 </td>
     	    	<td>开始时间: 
	            	 <s:textfield name = "queryOrderDetailInfo.beginDate" onfocus="cdr.show(this);"  >
	            	 	<s:param name="value"><s:date name="queryOrderDetailInfo.beginDate" format="yyyy-MM-dd"/> </s:param>
	            	 </s:textfield>  </td>   
	            	         
	              <td>结束时间：
	                 <s:textfield name = "queryOrderDetailInfo.endDate"  onfocus="cdr.show(this);" >
	                 		<s:param name = "value"> <s:date name="queryOrderDetailInfo.endDate" format="yyyy-MM-dd" /> </s:param>
	                 </s:textfield> 	
	             </td>
     	    </tr>
     	    <tr>
     	    	<td>
     	    		起始价格： <input id="beginPrice" type="text" name="queryOrderDetailInfo.beginPrice" value="${ queryOrderDetailInfo.beginPrice }">
     	    		最高价格： <input id="endPrice" type="text" name="queryOrderDetailInfo.endPrice" value="${ queryOrderDetailInfo.endPrice }">
     	    	</td>
     	    	<td>
        			<input type="submit" value="查询" style="background:url(images/find.png)"/>   
		     	    <input type="button" value="重置" style="background:url(images/reset.png)" onclick="f_clear()"/>
     	    	</td>
     	    </tr>
    	</table>
    	
    	<hr/> <br/>
		
		
		
		<!--  显示购买过的书籍  -->
		<s:iterator var="orderDetail" value="orderDetails">
	     	<table width="1100px" border="1" style="border-collapse:collapse; border-color: #808080">
	     	<tr> <th colspan="3">订单号: <s:property value="#orderDetail.TOrder.id"/></th> <th colspan="3"> 成交时间: <s:date name="#orderDetail.saleDate" format="yyyy-MM-dd"/>    </th> </tr>
				<tr><th>书籍</th> <th>单价</th> <th>折扣</th> <th>数量</th> <th>合计</th> <th>交易</th> </tr>
			    <tr>
			     	<td>  
			     		<div style="width: 270; height: 150">
				     		<div style="display: block; float: left">
				     			<a href="clientQueryById_BookAction?book.id=${orderDetail.TBook.id}"> <img alt="" src="${ orderDetail.TBook.picture }" width="90px" height="130"> </a>
				     			
				     		</div>
				     		<div style="display: block; float: left; margin-top: 40px">
				     			书名: &nbsp;&nbsp; <a href="clientQueryById_BookAction?book.id=${orderDetail.TBook.id}">  <s:property value="#orderDetail.TBook.name"/> </a> <br/> 
				     			ISBN:&nbsp;&nbsp; <s:property value="#orderDetail.TBook.isbn"/>
				     		</div>
			     		</div> 
			     		
			     	</td>
			     	<td align="center">  <s:property value="#orderDetail.TBook.price"/> </td>
			     	<td align="center">  <s:property value="#orderDetail.TBook.discount"/> </td>
				    <td align="center">  <s:property value="#orderDetail.num"/>  </td>
				    <td align="center">  <s:property value="#orderDetail.price"/>  </td>
				    <td align="center"><a href="clientQueryById_BookAction?book.id=${orderDetail.TBook.id}">查看商品详情</a></td>
			     </tr>
			    </table>
	     		<br/>
	     	</s:iterator>
	     	
		  <div align="right" style="margin-right: 130px">
			当前页: ${currentPage} &nbsp;&nbsp; 总页数 : ${allPage}

			<s:if test="%{currentPage > 1}">
				<a href="javascript:f_page(${currentPage-1})"> 上一页 </a> &nbsp;&nbsp;
			       	</s:if>
			<s:if test="%{currentPage <= 1}">
			       		上一页
			       	</s:if>
			<s:if test="%{currentPage < allPage}">
				<a href="javascript:f_page(${currentPage+1})"> 下一页</a>
			</s:if>
			<s:if test="%{currentPage >= allPage}">
						下一页
					</s:if>
			</div>
		</form>
		
		</div>
      
      <!-- end  -->
      <jsp:include page="/view/bottom.jsp"></jsp:include>
      <!-- end  -->
  </body>
</html>
