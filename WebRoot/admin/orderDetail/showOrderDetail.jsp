<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showLog.jsp' starting page</title>
    
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
			   
				//alert(obj)
				with(document.forms[0]) {
					currentPage.value = obj;
					submit();
				}
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
						<td height="31"><div class="titlebt">售书历史</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
  	 </table>
	
  
  
    <s:form action="query_OrderDetailAction" method="post">
    	<s:hidden name = "currentPage" value="1"></s:hidden>
    	
    	<table class="table table-hover">
    		<tr>
     	    	<td> 用户名： <input type="text" name = "queryOrderDetailInfo.userName" value="${queryOrderDetailInfo.userName}"> </td>
     	    	<td> 订单号： <input type="text" name = "queryOrderDetailInfo.orderId" value="${queryOrderDetailInfo.orderId}"> </td>
     	    	<td> ISBN： <input type="text" name = "queryOrderDetailInfo.isbn" value="${queryOrderDetailInfo.isbn}"> </td>
     	    		<td>开始时间: 
	            	 <s:textfield name = "queryOrderDetailInfo.beginDate" onfocus="cdr.show(this);" >
	            	 	<s:param name="value"><s:date name="queryOrderDetailInfo.beginDate" format="yyyy-MM-dd"/> </s:param>
	            	 </s:textfield>  </td>   
	            	         
	                 <td>结束时间：
	                 	<s:textfield name = "queryOrderDetailInfo.endDate"  onfocus="cdr.show(this);" >
	                 		<s:param name = "value"> <s:date name="queryOrderDetailInfo.endDate" format="yyyy-MM-dd" /> </s:param>
	                 </s:textfield> 	
	             </td>
     	    </tr>
     	   
    	</table>
    	 <input class="btn" type="submit"" value="查询" />
        <input class="btn" type="button" value="重置" onclick="f_clear()"/>&nbsp;
 	<table class="table table-hover">
  		<tr>
  			<th>编号</th> <th>书籍</th> <th>ISBN</th> <th>订单号</th> <th>用户</th> <th>数量</th>  <th>金额</th>  <th>时间</th>  <th>查询</th>
  		</tr>
  	     <s:iterator var="orderDetail" value="orderDetails">
	     	<tr>
	     		<td>  <s:property value="#orderDetail.id"/>  </td>
	     		<td>  <s:property value="#orderDetail.TBook.name"/> </td>
	     		<td>  <s:property value="#orderDetail.TBook.isbn"/> </td>
	     		<td>  <s:property value="#orderDetail.TOrder.id"/> </td>
	     		<td>  <s:property value="#orderDetail.TUser.userName"/>  </td>
		     	<td>  <s:property value="#orderDetail.num"/>  </td>
		     	<td>  <s:property value="#orderDetail.price"/>  </td>
		     	<td> 
		     		<s:date name="#orderDetail.saleDate" format="yyyy-MM-dd"/>
		     	</td>
		     	<td><a href="jacascript:void(0)" onclick="openBookDetail(<s:property value="#orderDetail.TBook.id"/>)">详细</a></td>
	     	</tr>
    	 </s:iterator>
    </table>
    
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
