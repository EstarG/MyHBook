<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ce.jsp' starting page</title>
    
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
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.2.js"></script>
	
	<script type="text/javascript">
		function queryAuthor() {
			//alert("xxx");
			var id = document.getElementById("id").value;
			if (isNaN(id)) {
				alert("输入作者的编号非法, 请检查后重新输入");
				document.getElementById("id").focus();
				return ;
			}
			var name = document.getElementById("name").value;
			//alert(id  + "  " + name);
			$.post(
					"queryAuthor_AuthorAction",
					 {
						"id" : id,
						"name" : name
					 },
					 c_bJson,
					 'text'
				 );
		}
		 
	    function c_bJson(data) {
	    	//alert(data);
	    	var c = document.getElementById("author");
	    	var str = "";
	    	$.each(eval(data),
	    		function(idx, obj) {
	    			//alert("idx = " + idx + "obj = " + obj.name  + " " + obj.id);
	   			    str = str + "<input type=\"checkbox\" name =\"authorIds\"  id = " + obj.name + "  value=\"" + obj.id + "\" " + "  '//'>"+obj.name+"<br'/'>";	 
	   			   	c.innerHTML = str;   
	    		}
	    	);
	    	
	    	if(str == "") {
	    		c.innerHTML = "<font color='red'>你查询的作者不存在</font>";
	    	}
	    }
	    
	   
		function f_close() {
			//alert("xxx");
			//获得父窗口
			var prtW = window.opener;
			var authors = document.getElementsByName("authorIds");
			var length = authors.length;
			//获得已选员工的编号
			var slAuthors = prtW.document.getElementById("authorIds").value
					.split(";");
			var length2 = slAuthors.length;
			//如果从未选过就	清空
			if (length2 == 1) {
				prtW.document.getElementById("authorIds").value = "";
				prtW.document.getElementById("authors").value = "";
			}
			for ( var i = 0; i < length; ++i) {
				//alert(emps[i].value + " : " + emps[i].checked + " : " +  emps[i].id) ;
				if (authors[i].checked == true) {
					//1表示没有选过2表示已经选过
					var flag = 1;
					for ( var j = 0; j < length2; ++j) {
						if (authors[i].value == slAuthors[j]) {
							flag = 2;
							break;
						}
					}
					if (flag == 1) {
						prtW.document.getElementById("authorIds").value += authors[i].value
								+ ";";
						prtW.document.getElementById("authors").value += authors[i].id
								+ ";";
					}
				}
			}
			//关闭窗口
			window.close();
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
	
  </head >
  
  <body >
  
  	 <table class="table table-hover">
  	 	<tr>
  	 		<td> 作者姓名：<input id="name" name="name" type="text"> </td>
  	 		<td> 作者编号：<input id="id" name="id" type="text" > </td>
  	 		<td>  <input type="button" value="查询" style="margin-top: 12px" class="btn" onclick="queryAuthor()">    </td>
  	 	</tr>
  	 	<tr>
  	 		<td colspan="3" height="300px">
  	 			<div id = "author" name="author">
  	 			
  	 			</div>
  	 		</td>
  	 	</tr>
  	 	<tr>
  	 		<td>
		 	 <input class="btn" type="button" onclick="f_close()" value="添   加" />
		  	</td>
  	 	</tr>
  	 </table>
  	 
  	 
  	 
  </body>
 </html>