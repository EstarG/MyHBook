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
    
    <title>我的订单</title>
    
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
    <script type="text/javascript" src="css/d.css"></script>
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	<script type="text/javascript" src="css/e.css" charset="gb2312"></script>
	<script type="text/javascript" src="css/f.css" charset="gb2312"></script>
	
	<script language="javascript" src="js/Calendar2.js"></script>
	
	<script type="text/javascript">
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
		//重置查询条件
		function f_clear(){
	   			var formObj = document.form1.elements;
				for (var i = 0; i < formObj.length; ++i) {
				//	alert("type = " + formObj[i].type + "value = " + formObj[i].value);
					if (formObj[i].type == "text"){
						formObj[i].value="";
					}
				}
	   	}
	   	//上下页切换
	   	function f_page(obj) {
			//alert(obj)
			with(document.form1) {
				currentPage.value = obj;
				submit();
			}
		}
		//表单校验
		function check() {
			var node = document.form1.orderId;
			if (node.value != null && isNaN(node.value)) {
				alert("订单号非法");
				node.focus();
				event.returnValue = false;
				return ;
			}
		 	var beginNode = document.form1.beginPrice;
			var endNode = document.form1.endPrice;
			
			if (isNaN(beginNode.value)) {
				alert("起始价格非法");
				beginNode.focus();
				event.returnValue = false;
				return ;
			}
			if (isNaN(endNode.value)) {
				alert("最高价格非法");
				endNode.focus();
				event.returnValue = false;
				return ;
			}
			
			event.returnValue = true;
		}
	</script>
	
	<style type="text/css">
		.breadcrumb{
			margin-left: 70px;
		}
		#div1{
			margin-left: 80px;
			margin-right: 80px;
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
    		<span>&nbsp;&gt;&nbsp; <a href="clientQuery_OrderAction?queryOrderInfo.userId=${ user.id }&currentPage=1">我的订单 </a><span></span></span>
		</div>
		<div id="div1">
			<hr  dir="rtl">
				<h2>订单查询</h2>
			<hr>
		</div>
      	
      	<div id="div2">
      	
		     <s:form action="clientQuery_OrderAction" name="form1" onsubmit="check()" method="post">
		      	<s:hidden name="currentPage" value="1"></s:hidden>
		   		<s:hidden name="queryOrderInfo.userId" value="%{queryOrderInfo.userId}"></s:hidden>
		     	<table width="800px" height="100px">
		     		<tr>
		     			<td>订&nbsp;&nbsp;单&nbsp;&nbsp;号 : <input type="text" id="orderId" name="queryOrderInfo.orderId" value="${ queryOrderInfo.orderId }"> </td>
		     	    	<td> 开始时间: 
			            	 <s:textfield name = "queryOrderInfo.beginDate" onfocus="cdr.show(this);" >
			            	 	<s:param name="value"><s:date name="queryOrderInfo.beginDate" format="yyyy-MM-dd"/> </s:param>
			            	 </s:textfield>  </td>   
			            	         
			                 <td>结束时间：
			                 	<s:textfield name = "queryOrderInfo.endDate"  onfocus="cdr.show(this);" >
			                 		<s:param name = "value"> <s:date name="queryOrderInfo.endDate" format="yyyy-MM-dd" /> </s:param>
			                 </s:textfield> 	
			             </td>
		     	    </tr>
		     	    <tr>
		     	    	<td>起始价格 : <input type="text" id="beginPrice" name="queryOrderInfo.beginPrice" value="${ queryOrderInfo.beginPrice }"> </td>
		     	    	<td>最高价格 : <input type="text" id="endPrice" name="queryOrderInfo.endPrice" value="${ queryOrderInfo.endPrice }"> </td>
		     	    	<td>
		     	    		<input type="submit" value="查询" style="background:url(images/find.png)"/>   
		     	    		<input type="button" value="重置" style="background:url(images/reset.png)" onclick="f_clear()"/></td>
		     	    </tr>
		     	</table>
		     	
		  	     <s:iterator var="order" value="orders">
		  	     	<table border="1" width="1000px" height="250px" border="1" style="border-collapse:collapse; border-color: #808080" >
				  		<tr>
				  			<th colspan="3" align="left">订单编号: <s:property value="#order.id"/></th> <th colspan="3" align="left">姓名 :<s:property value="#order.TUser.userName"/></th> 
				  		</tr>
				     	
				     	<tr><th>书名</th> <th>ISBN</th> <th>单价</th> <th>折扣</th> <th>数量</th> <th>价格</th>  </tr>
				     	<s:iterator var="orderDetail" value="#order.TOrderdetails">
				     		<tr>
					     		<td> <a href="clientQueryById_BookAction?book.id=${ orderDetail.TBook.id }"> <s:property value="#orderDetail.TBook.name"/> </a> </td>
					     		<td>  <s:property value="#orderDetail.TBook.isbn"/> </td>
					     		<td>  <s:property value="#orderDetail.TBook.price"/> </td>
						     	<td>  <s:property value="#orderDetail.TBook.discount"/>折 </td>
						     	<td>  <s:property value="#orderDetail.num"/>  </td>
						     	<td>  <s:property value="#orderDetail.price"/>  </td>
			     			</tr>
				     	</s:iterator>
				     	<tr>
				     		<td colspan="2">时间 :  <s:date name="#order.orderDate" format="yyyy-MM-dd"/> </td>
				     		<td colspan="2">总数: <s:property value="#order.numTotal"/> </td>
				     		<td colspan="2">小计 : <s:property value="#order.priceTotal"/> </td>
				     	</tr>
				     	
				     </table>
			     
			     	<hr width="85%"  style="margin-left: -5px;"/>
		    	 </s:iterator>
		    
		    	 <div align="right" style="margin-right: 200px">
		     		当前页:  ${currentPage}  &nbsp;&nbsp; 总页数 : ${allPage} 
			       	
			       	<s:if test="%{currentPage > 1}">
			       		<a href="javascript:f_page(${currentPage-1})"> 上一页  </a> &nbsp;&nbsp;
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
		     </s:form>
      </div>	
      	
      <!-- end  -->
      <jsp:include page="/view/bottom.jsp"></jsp:include>
      <!-- end  -->
  </body>
</html>
