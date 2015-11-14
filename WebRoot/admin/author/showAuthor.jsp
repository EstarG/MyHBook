<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showUsers.jsp' starting page</title>
    
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
	
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script language="javascript" src="js/Calendar2.js"></script>
	<script type="text/javascript">
		  
	    //删除选定用户
		function f_dlAuthor(obj) {
			//alert("aa");
			var x = confirm("确定要删除项目" + obj);
			if (x) {
				window.location.href = "delete_AuthorAction?id=" + obj;
			}
		}
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
		//批量删除判断用户是否有选
		   	function isSelected() {
		   		 with(document.forms[0]){
					for (var i = 0; i < ids.length; ++i){
						if (ids[i].checked == true) return true;
					}	   
					return false;
				 }
		   	}
	   	//批量删除
		function  f_dlAllok() {
	   		alert("xxx");
			var selected = document.getElementById("ids");
			if (!isSelected()) {
				 alert("请选择要删除的项");
				 return false;
			} else {
				var x = confirm("确定要删除选中全部");
				
				if (x) {
					return true;
				} else {
					return false;
				}
			}
		}
	   	
	   	//限制字符串长度
		function cutStr(len){ 
	   	  
	   	  var obj=document.getElementById("table1").getElementsByTagName("td"); 
	      for (var i=0;i<obj.length;i++){ 
	    	  if (obj[i].id == "authorInfo") {
		    	  //alert(obj[i].innerHTML);
		          obj[i].innerHTML=obj[i].innerHTML.substring(0,len)+'…'; 
	    	  }
	      } 
		} 
	   	
		//查看作者详细信息
	   	function openAuthorDetail(id) {
	   		//alert("Author ID = " + id);
	   		window.open ('queryAuthorDetail_AuthorAction? id=' + id,'newwindow','height=500,width=800,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,        resizable=no,location=no, status=no');
	   	}
	   	
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
	    
	  //表单校验
   		function check(){
   			if (isNaN(document.form1.id.value)){
				alert("编号不合法");
				document.form1.id.focus();
				event.returnValue = false;
				return ;
			}
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
  
  <body onload="cutStr(45)">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="17" valign="top" background="images/mail_leftbg.gif">
				<img src="admin/images/left-top-right.gif" width="17" height="29" />
			</td>
			<td valign="top" background="admin/images/content-bg.gif">
				<table width="100%" height="31" border="0" cellpadding="0"
					cellspacing="0" class="left_topbg" id="table2">
					<tr>
						<td height="31"><div class="titlebt">查询作者</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
  	 </table>
	
     <s:form action="query_AuthorAction" method="post" id="form1" onsubmit="check()">
     	<s:hidden name = "currentPage" value="1"></s:hidden>
     	<table class="table table-hover" >
     		<tr>
     	    	<td> 编号： <input type="text" name = "id" id="id" value="${ author.id }"> </td>
     	    	<td> 作者名： <input type="text" name = "name" value="${ author.name }"> </td>
     	    </tr>
     	</table>
     	
        <input class="btn" type="submit"" value="查询" />
        <input class="btn" type="button" value="重置" onclick="f_clear()"/>&nbsp;
         <div>
			<font face="黑体" size="6"><font color="red">  <s:property value="request.message"/>  </font>  </font>  
		</div> 
  
  	
  	<table  class="table table-hover" id="table1">
  		<tr>
  			 <th>作者编号</th> <th>作者姓名</th> <th>作者简介</th> <th colspan="2">操作</th> 
  		</tr>
  	     <s:iterator var="author" value="authors">
	     	<tr>
	     		<td>  <s:property value="#author.id"/>  </td>
	     		<td>  <s:property value="#author.name"/>  </td>
		     	<td id="authorInfo">  <s:property value="#author.infor"/> </td>
		     	<td>
						<s:a action="queryById_AuthorAction">
							<s:param name="id" value="#author.id"></s:param>
							<font color="#000E00">更新</font>
						</s:a>
				</td>
				 <td>
						<a href="jacascript:void(0)" onclick="openAuthorDetail(<s:property value="#author.id"/>)">详细</a>
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
