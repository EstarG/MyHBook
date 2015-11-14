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
    
    <title>My JSP 'searchBook.jsp' starting page</title>
    
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
			//查看书籍详细信息
	   	function openBookDetail(id) {
	   		//alert("bookid = " + id);
	   		window.open ('queryBookDetail_BookAction? id=' + id,'newwindow','height=700,width=800,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,        resizable=no,location=no, status=no');
	   	}
	   	//表单非空校验
	   	function check(){
	   		//alert("xxx");
	   		var id = document.getElementById("id");	 
	   		var isbn = document.getElementById("isbn");	 
	   		var name = document.getElementById("name");
	   		//alert(id.value + " " + isbn.value + " " + name.value);
	   		if (id.value == "" && isbn.value == "" && name.value == "") {
	   			alert("表单不能为空");
	   			event.returnValue = false;
	   			return ;
	   		}	   	
	   		if (isNaN(id.value)) {
	   			alert("书籍编号非法");
	   			id.focus();
	   			event.returnValue = false;
	   			return ;
	   		}	
	   		return true;
	   	}
	   	
	   	//重置查询条件
		function f_clear(){
	   			var formObj = document.forms[0].elements;
				for (var i = 0; i < formObj.length; ++i) {
					if (formObj[i].type == 'text'){
						formObj[i].value='';
					}
				}
	   			return true;
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
						<td height="31"><div class="titlebt">修改查询</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
   </table>
   <br/>
  	<br><s:form action="updateQuery_BookAction" onsubmit="check()" id="form1" method="post">
  		<table class="table table-hover">
  			<tr>
  				<td>书籍编号：<input type="text" name="queryBookInfo.id" id="id" value="${ queryBookInfo.id }"> </td>
  				<td>ISBN: <input type="text" name="queryBookInfo.isbn" id="isbn" value="${ queryBookInfo.isbn }"> </td>
  				<td>书名： <input type="text" name="queryBookInfo.name" id="name" value="${ queryBookInfo.name }"></td>
  				<td>
  					<input type="submit" value="查询" class="btn" >
  					<input type="reset" value="重置" class="btn" onclick="f_clear()">
  				</td>
  			</tr>
  			
  			
  		</table>
  		
  		<table  class="table table-hover">
  			<tr>  <th>编号</th> <th>ISBN</th> <th>书名</th> <th>出版社</th> <th>出版时间</th>  <th>操作</th> </tr>
	  		<s:iterator var="book" value="books">
	  			<tr>
	  				<td>  <s:property value="#book.id"/>   </td>
	  				<td>  <s:property value="#book.isbn"/>    </td>
	  				<td>  <s:property value="#book.name"/>    </td>
	  				<td>  <s:property value="#book.publisher"/>   </td>
	  				<td> <s:date name="#book.publishDate" format="yyyy-MM-dd"/>  </td>
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
  		
  	</s:form>
  	
  </body>
</html>
