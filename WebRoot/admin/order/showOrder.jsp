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
    
    <title>My JSP 'showOrder.jsp' starting page</title>
    
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
	
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.js"></script>
	<script language="javascript" src="js/Calendar2.js"></script>
	
	<script type="text/javascript">	  
		//重置查询条件
		function f_clear(){
	   			var formObj = document.forms[0].elements;
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
			with(document.form1) {
				//alert(obj);
				currentPage.value = obj;
				submit();
			}
		}
		
		//价格非数字检查
		function check() {
			if (isNaN(document.form1.beginPrice.value)) {
				alert("小计输入非法!");
				document.form1.beginPrice.focus();
				event.returnValue = false;
				return ;
			}
			if (isNaN(document.form1.endPrice.value)) {
				alert("小计输入非法!");
				document.form1.endPrice.focus();
				event.returnValue = false;
				return ;
			}
			return true;
		}
		
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
	   
	    //查看书籍详细信息
	   	function openBookDetail(id) {
	   		//alert("bookid = " + id);
	   		window.open ('queryBookDetail_BookAction? id=' + id,'newwindow','height=700,width=800,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,        resizable=no,location=no, status=no');
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
						<td height="31"><div class="titlebt">订单查询</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
  	 </table>
	
  
  	
  
     <s:form action="query_OrderAction" id="form1" name="form1" onsubmit="check()" method="post">
   		<s:hidden name = "currentPage" value="1"></s:hidden>
     	<table class="table table-hover">
     	
     		<tr>
     	    	<td> 用&nbsp;&nbsp;户&nbsp;&nbsp;名 : <input type="text" name = "queryOrderInfo.userName" value="${ queryOrderInfo.userName }"> </td>
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
     	    	<td>最低小计 : <input id="beginPrice" type="text" name="queryOrderInfo.beginPrice" value="${ queryOrderInfo.beginPrice }"> </td>
     	    	<td>最高小计 : <input id="endPrice" type="text" name="queryOrderInfo.endPrice" value="${ queryOrderInfo.endPrice }"> </td>
     	    </tr>
     	</table>
     	 <input class="btn" type="submit"" value="查询" />
        <input class="btn" type="button" value="重置" onclick="f_clear()"/>&nbsp;
     	
  	     <s:iterator var="order" value="orders">
  	     	<table class="table" border="0.5">
		  		<tr>
		  			<th colspan="2">订单号编号 :  <s:property value="#order.id"/>  </th> <th colspan="3">时间： <s:date name="#order.orderDate" format="yyyy-MM-dd"/> </th>     
		  		</tr>
		     	
		     	<tr><th style="color: green;">书名</th> <th style="color: green;">ISBN</th> <th style="color: green;">数量</th> <th style="color: green;">价格</th> <th style="color: green;">查询 </th> </tr>
		     	<s:iterator var="orderDetail" value="#order.TOrderdetails">
		     		<tr>
			     		<td>  <s:property value="#orderDetail.TBook.name"/> </td>
			     		<td>  <s:property value="#orderDetail.TBook.isbn"/> </td>
				     	<td>  <s:property value="#orderDetail.num"/>  </td>
				     	<td>  <s:property value="#orderDetail.price"/>  </td>
				     	<td><a href="jacascript:void(0)" onclick="openBookDetail(<s:property value="#orderDetail.TBook.id"/>)">详细</a></td>
	     			</tr>
		     	</s:iterator>
		     	
		     	
		     	<tr> <th colspan="2">小计：<s:property value="#order.priceTotal"/> </th>  <th colspan="2">数量：<s:property value="#order.numTotal"/></th>    <th>用户 ：<s:property value="#order.TUser.userName"/> </th> </tr>
		     	
		     </table>
	     
	     	<hr/>
    	 </s:iterator>
    
    
     		当前页:  ${currentPage}  &nbsp;&nbsp; 总页数 : ${allPage} 
	       	
	       	<s:if test="%{currentPage > 1}">
	       		<a href="javascript:f_page(${currentPage-1})">上一页 </a> &nbsp;&nbsp;
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
     </s:form>
  </body>
</html>
