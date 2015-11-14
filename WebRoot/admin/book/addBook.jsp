<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addBook.jsp' starting page</title>
    
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
		//日期显示
		var cdr = new Calendar("cdr");
		document.write(cdr);
		cdr.showMoreDay = true;
		//非空判断
		function check(){
			//alert(":xxx");
			if(document.form1.isbn.value==""){
				alert("ISBN不能为空！");
				document.form1.isbn.focus();
				event.returnValue = false;
				return ;
			}
			if(document.form1.name.value==""){
				alert("书名不能为空！");
				document.form1.name.focus();
				event.returnValue = false;
				return ;
			}else if(document.form1.publisher.value==""){
				alert("出版社不能为空！");
				document.form1.publisher.focus();
				event.returnValue = false;
				return ;
			}
			if(document.form1.publishDate.value==""){
				alert("出版时间不能为空！");
				document.form1.publishDate.focus();
				event.returnValue = false;
				return ;
			}
			if(document.form1.price.value==""){
				alert("价格不能为空！");
				document.form1.price.focus();
				event.returnValue = false;
				return ;
			}
			if (isNaN(document.form1.price.value)) {
				alert("价格输入非法");
				document.form1.price.focus();
				event.returnValue = false;
				return ;
			}
			if (document.form1.discount.value == "") {
				alert("折扣不能为空！");
				document.form1.discount.focus();
				event.returnValue = false;
				return ;
			} else if (isNaN(document.form1.discount.value)) {
				alert("折扣输入非法!");
				document.form1.discount.focus();
				event.returnValue = false;
				return ;
			}
			if(document.form1.uploadFile.value==""){
				alert("书籍图片不能为空！");
				document.form1.uploadFile.focus();
				event.returnValue = false;
				return ;
			}
			if (document.form1.authors.value == "") {
				alert("作者不能为空！");
				document.form1.authors.focus();
				event.returnValue = false;
				return ;
			}
			if(document.form1.stockNum.value==""){
				alert("库存量不能为空！");
				document.form1.stockNum.focus();
				event.returnValue = false;
				return ;
			} 
			if (isNaN(document.form1.stockNum.value)) {
				alert("库存量输入非法！");
				document.form1.stockNum.focus();
				event.returnValue = false;
				return ;
			} 
			if (document.form1.faOptions.value == 0) {
				alert("请选择分类");
				document.form1.faOptions.focus();
				event.returnValue = false;
				return ;
			}
			return true;
		}
		//ajax 异步判断ISBN是否存在
		function f_check(obj) {
			//alert(obj);
			 $.post(
				"isExistISBN_BookAction",
				 {"isbn" : obj},
				 c_bJson,
				 'text'
			 );
		} 
		function c_bJson(data) {
			//alert(data);
			mark = eval(data);
			//alert("mark = " + mark);
			if (mark == "0") {
				document.getElementById("isokISBN").innerHTML = "该ISBN书籍已存在";	
				document.getElementById("add").disabled="disabled";  //不合法不能提交
			}  else {
				document.getElementById("isokISBN").innerHTML = "";
				document.getElementById("add").disabled="";  //不合法不能提交
			}
		}
		
		//打开添加作者的界面
		function openwinAuthor() {
			window.open ('admin/book/selectAuthor.jsp','newwindow','height=500,width=600,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,        resizable=no,location=no, status=no') ;
		}
		
		//清空之前添加的作者
		function f_clear_other() {
			//alert("fsdff");
			document.getElementById("authorIds").value = "";
			document.getElementById("authors").value = "";
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
						<td height="31"><div class="titlebt">添加图书</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
   </table>
   
  	<s:form name="form1" action="add_BookAction" enctype="multipart/form-data" onsubmit="check()" method="post">
  		<font color="red" style="margin-left: 23px"> 带*为必填项   ${ message } </font>
  		<table class="table table-hover">
  			<tr>
  				<td> I&nbsp;S&nbsp;B&nbsp;N&nbsp;&nbsp; : &nbsp;<input type="text" name="isbn" onblur="f_check(this.value)"> <font color="red"> * </font> <div id = "isokISBN" style="color: red;"></div></td>
  				<td> 书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名 : <input type="text" name="name"> <font color="red"> * </font>  </td>
  			</tr>
  				
  			<tr>
  				<td> 出&nbsp;版&nbsp;社&nbsp; : <input type="text" name="publisher"> <font color="red"> * </font> </td>
  				<td>
  					 出版时间 :
  			   		<s:textfield name = "publishDate"  onfocus="cdr.show(this);">
	  				</s:textfield>
	  				<font color="red"> * </font> 
	  		 	 </td>
  			</tr>
  			<tr>
  				<td> 定&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价 : <input type="text" name="price"> <font color="red"> * </font>  </td>
  				<td> 折&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;扣 : <input type="text" name="discount"> <font color="red"> * </font>  </td>
  			</tr>
  			<tr>
  			    <td> 图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片 : <input type="file" name="uploadFile"><font color="red" style="margin-left: -65px"> * </font>  </td> 
  				<td>	 作者 : 
  						<div>
	  						<s:hidden name = "queryBookInfo.authorIds" id = "authorIds" value=""> </s:hidden>
						    <s:textarea id="authors" name="authors"   rows="5" cols="50"></s:textarea>   
						    <font color="red"> * </font> 
							<div>
								<input type="button" onclick="openwinAuthor()" value="配置" />  
								<input type="button" value="清空"  onclick="f_clear_other()"/> 
							</div>
							
  						</div>
  				</td>
  			</tr>
  			<tr>
  				<td> 库&nbsp;存&nbsp;量 &nbsp;: <input type="text" name="stockNum"> <font color="red"> * </font> </td>
  				<td  width="60px">
  					<div style="width: 33%; float: left;">
  					 二手 :
  					  <input type="radio" name="used" value="1">是
  					  <input type="radio" name="used" value="0" checked="checked">否
  					 	<font color="red"> * </font> 
  					 </div>
  					 
  					<div style="width: 50%; float: left;">
	  					<div style="width: 40%; float: left; margin-right: 10px">
							<s:select id="faOptions" name="ftype" list="faCategory" listKey="fkey" listValue="fvalue" cssClass="selectWith"  headerKey="0" headerValue="选择分类"></s:select>
		  				</div> 
		  			
			  			<div style="width: 20%; float: left;  margin-left: 10px">
			  				<select id = "chOptions" name = "type" style="width: 100px">
			  					<option value = "0">分类</option>
			  				</select>
						</div> 
  					
  					</div><font color="red"> * </font>
  					 
  				 </td>
  			</tr>
  			<tr>
  				
  			</tr>
  		</table>
  		
  		<table class="table table-hover">
  			<tr>
  				<td> 内容简介: <br/>
  					<textarea rows="20%" cols="70%" name="description.content"></textarea>
  				</td>
  				
  				<td> 作者简介 : <br/>
  					<textarea rows="20%" cols="70%" name="description.authorInfo"></textarea>
  				</td>
  				
  				<td> 书籍目录 : <br/>
  					<textarea rows="20%" cols="70%" name="description.catalogInfo"></textarea>
  				</td>
  			</tr>
  		</table>
  		<input id="add" type="submit" value="添加" class="btn" >
  	</s:form>
    	
  </body>
</html>
