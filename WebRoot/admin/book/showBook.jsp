<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showBook.jsp' starting page</title>
    
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
	
	
	
		  
		//重置查询条件
		function f_clear(){
	   			var formObj = document.forms[0].elements;
				for (var i = 0; i < formObj.length; ++i) {
					if (formObj[i].type == 'text'){
						formObj[i].value='';
					}
				}
				document.getElementById("faOptions").value="0";
				document.getElementById("chOptions").value="0";
				document.getElementById("used").checked="";
				document.getElementById("unUsed").checked="";
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
		//全选设置
		 function f_slAll(){
		 //alert("1111");
	   		   var s = document.getElementById("selectAll");
			   with(document.forms[0]){
				    if (selectAll.checked){
						for (var i = 0; i < ids.length; ++i) {
							ids[i].checked = true;
						}
					} else {
						for (var i = 0; i < ids.length; ++i){
							ids[i].checked = false;
						}
					}
				}
	   	}
	   	//查看书籍详细信息
	   	function openBookDetail(id) {
	   		//alert("bookid = " + id);
	   		window.open ('queryBookDetail_BookAction? id=' + id,'newwindow','height=700,width=800,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,        resizable=no,location=no, status=no');
	   	}
	   	//书籍分类二级联动
	   	$(document).ready(
				function(){
					$("#faOptions").change(
						function(){
							var fkey = $(this).val();
							//alert("fkey = " + fkey);
							$.post(
								"getSubCategory_BookAction",
								{"fkey":fkey},
								call_back,
								"json"
							);
						}
					);
				}
			);
			function call_back(data){
				$("#chOptions").empty();
				$.each(data,function(indx,obj){
						$("#chOptions").append("<option value='"+obj.fkey+"'>"+obj.fvalue+"</option>");
				});
			}
	   	
	   	function check() {
	   		
	   		if(isNaN(document.getElementById("beginPrice").value) || isNaN(document.getElementById("endPrice").value) || document.getElementById("beginPrice").value > document.getElementById("endPrice").value){
				alert("价格输入非法");
				document.form1.beginPrice.focus();
				event.returnValue = false;
				return ;
			}
	   		if (isNaN(document.getElementById("beginGrade").value) || isNaN(document.getElementById("endGrade").value) || document.getElementById("beginGrade").value > document.getElementById("endGrade").value) {
	   			alert("评分输入非法");
				document.form1.endGrade.focus();
				event.returnValue = false;
				return ;
	   		}
	   		if (isNaN(document.getElementById("beginDiscount").value) || isNaN(document.getElementById("endDiscount").value) || document.getElementById("beginDiscount").value > document.getElementById("endDiscount").value) {
	   			alert("折扣输入非法");
				document.form1.endGrade.focus();
				event.returnValue = false;
				return ;
	   		}
	   		if (isNaN(document.getElementById("beginStockNum").value) || isNaN(document.getElementById("endStockNum").value) || document.getElementById("beginStockNum").value > document.getElementById("endStockNum").value) {
	   			alert("库存量输入非法");
				document.form1.endGrade.focus();
				event.returnValue = false;
				return ;
	   		}
	   		
	   		return true;
	   		
	   	}
	 
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
	</script>
	<style type="text/css">
		.selectWith{
			width: 100px;
		}
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #F7F8F9;
		}
	</style>
  </head>
  
  <body background="">
  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="17" valign="top" background="images/mail_leftbg.gif">
				<img src="admin/images/left-top-right.gif" width="17" height="29" />
			</td>
			<td valign="top" background="admin/images/content-bg.gif">
				<table width="100%" height="31" border="0" cellpadding="0"
					cellspacing="0" class="left_topbg" id="table2">
					<tr>
						<td height="31"><div class="titlebt">查询书籍</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
   </table>
  	<s:form name="form1" action="query_BookAction" onsubmit="check()" method="post">
  	 	<s:hidden name = "currentPage" value="1"></s:hidden>
  	    <table class="table table-hover">
  	    	
  	    	<tr>
  	    		<td> 书名:  <input type="text" name="queryBookInfo.name" value="${ queryBookInfo.name }"></td>
  	    		<td>作者名 ：<input type="text" name="queryBookInfo.authorName" value="${ queryBookInfo.authorName }"></td>
  	    		<td>ISBN: <input type="text" name="queryBookInfo.isbn" value="${ queryBookInfo.isbn }"></td>
  	    		<td>出版社：<input type="text" name="queryBookInfo.publisher" value="${ queryBookInfo.publisher }"> </td>
  	    		<td>
	  	    		<div style="width: 40%; margin-top: 13px; float: left; margin-right: 10px">
						<s:select id="faOptions" name="queryBookInfo.type" list="faCategory" listKey="fkey" listValue="fvalue" cssClass="selectWith"   ></s:select>
	  				</div> 
	  				
  						<div style="width: 20%; float: left; margin-top: 13px; margin-left: 10px">
		  					<s:select id="chOptions" name="queryBookInfo.chType" list="chCategory" listKey="fkey" listValue="fvalue" cssClass="selectWith" ></s:select>
		  				</div> 
  					
  	    		</td>
  	    	</tr>
  	    	
  	    	
  	    	<tr>
  	    		<td>出版时间:
  			   		<div><s:textfield name = "queryBookInfo.beginDate" onfocus="cdr.show(this);" >
	  						<s:param name="value"> <s:date name="queryBookInfo.beginDate" format="yyyy-MM-dd"/> </s:param>
	  				</s:textfield></div>
	  				 
	  				<div><s:textfield name = "queryBookInfo.endDate" value="%{ queryBookInfo.endDate }" onfocus="cdr.show(this);">
	  					<s:param  name="value"> <s:date name="queryBookInfo.endDate" format="yyyy-MM-dd"/> </s:param>
	  				</s:textfield></div>
  	    		</td>
  	    		<td>
  	    			价格：
  	    			<div><input id="beginPrice" type="text" name="queryBookInfo.beginPrice" value="${ queryBookInfo.beginPrice }"></div>
  	    			
  	    			<div><input id="endPrice" type="text" name="queryBookInfo.endPrice" value="${ queryBookInfo.endPrice }"></div>
  	    		</td>
  	    		<td>
  	    			客户评分：
  	    			<div><input id="beginGrade" type="text" name="queryBookInfo.beginGrade" value="${ queryBookInfo.beginGrade }"></div>
  	    			
  	    			<div><input id="endGrade" type="text" name="queryBookInfo.endGrade" value="${ queryBookInfo.endGrade }"></div>
  	    		</td>
  	    		<td>
  	    			折扣：
  	    			<div><input id="beginDiscount" type="text" name="queryBookInfo.beginDiscount" value="${ queryBookInfo.beginDiscount }"></div>
  	    			
  	    			<div><input id="endDiscount" type="text" name="queryBookInfo.endDiscount" value="${ queryBookInfo.endDiscount }"></div>
  	    		</td>
  	    		<td>
  	    			库存量：
  	    			<div><input id="beginStockNum" type="text" name="queryBookInfo.beginStockNum" value="${ queryBookInfo.beginStockNum }"></div>
  	    			
  	    			<div><input id="endStockNum" type="text" name="queryBookInfo.endStockNum" value="${ queryBookInfo.endStockNum }"></div>
  	    		</td>
  	    	</tr>
  	    
  	    </table>
  	   
  	    
        <div> 
		        <input class="btn" type="submit"" value="查询" />
		        <input class="btn" type="button" value="重置" onclick="f_clear()"/>&nbsp;
  	    			二手:  
  	    			<s:if test="null == queryBookInfo.used">
	  	    			<input id="used" type="radio" name="queryBookInfo.used" value="1">是
	  	    			<input id="unUsed" type="radio" name="queryBookInfo.used" value="0">否
  	    			</s:if>
  	    			<s:if test="null != queryBookInfo.used">
  	    				<s:if test="%{ queryBookInfo.used == 1 }">
  	    					<input id="used" type="radio" name="queryBookInfo.used" value="1" checked="checked">是
	  	    				<input id="unUsed" type="radio" name="queryBookInfo.used" value="0">否
  	    				</s:if>
  	    				<s:else>
  	    					<input id="used" type="radio" name="queryBookInfo.used" value="1" >是
	  	    				<input id="unUsed" type="radio" name="queryBookInfo.used" value="0" checked="checked">否
  	    				</s:else>
  	    			</s:if>
  	    </div>
    
  
         <div>
			<font face="黑体" size="6"><font color="red">  <s:property value="request.message"/>  </font>  </font>  
		</div> 
		
  		<table class="table table-hover" >
  		    <tr>  <th>编号</th> <th>ISBN</th> <th>书名</th> <th>出版社</th> <th>出版时间</th> <th>汇书价</th> <th>折扣</th> <th>库存量</th> <th>销售量</th> <th>操作</th> </tr>
	  		<s:iterator var="book" value="books">
	  			<tr>
	  				<td> <s:property value="#book.id"/>   </td>
	  				<td> <s:property value="#book.isbn"/>   </td>
	  				<td> <s:property value="#book.name"/>   </td>
	  				<td> <s:property value="#book.publisher"/>   </td>
	  				<td> <s:date name="#book.publishDate" format="yyyy-MM-dd"/>  </td>
	  				<td> <s:property value="#book.hbPrice"/>   </td>
	  				<td> <s:property value="#book.discount"/>   </td>
	  				<td> <s:property value="#book.stockNum"/>   </td>
	  				<td> <s:property value="#book.saleNum"/>   </td>
	  				<td>
						<s:a action="queryById_BookAction">
							<s:param name="id" value="#book.id"></s:param>
							<font color="#000E00">更新</font>
						</s:a>
						<a href="jacascript:void(0)" onclick="openBookDetail(<s:property value="#book.id"/>)">详细</a>
					 </td>
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
