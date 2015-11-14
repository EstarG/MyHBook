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
    
    <title>查询用户信息</title>
    
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
	    //删除选定用户
		function f_dlUser(obj) {
			//alert("aa");
			var x = confirm("确定要删除项目" + obj);
			if (x) {
				window.location.href = "delete_UserAction?id=" + obj;
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
				//alert(document.getElementById("type").value);
				document.getElementById("type").value = "0";
				document.getElementById("manSex").checked="";
				document.getElementById("womenSex").checked="";
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
		
		//查看用户详细信息
	   	function openUserDetail(id) {
	   		//alert("user ID = " + id);
	   		window.open ('queryUserDetail_UserAction? id=' + id,'newwindow','height=300,width=800,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,        resizable=no,location=no, status=no');
	   	}
		
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
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
						<td height="31"><div class="titlebt">查询用户</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
  	 </table>
	
  
  
     <s:form action="queryUsers_UserAction" method="post">
     	<s:hidden name = "currentPage" value="1"></s:hidden>
     	<table class="table table-hover">
     	    <tr>
     	    	<td> 用户名： <input type="text" name = "queryUserInfo.userName" value="${ queryUserInfo.userName }"> </td>
     	    	<td> 真实姓名: <input type="text" name = "queryUserInfo.realName" value="${ queryUserInfo.realName }">
     	    		<td>开始时间: 
	            	 <s:textfield name = "queryUserInfo.beginDate" onfocus="cdr.show(this);" >
	            	 	<s:param name="value"><s:date name="queryUserInfo.beginDate" format="yyyy-MM-dd"/> </s:param>
	            	 </s:textfield>  </td>   
	            	         
	                 <td>结束时间：
	                 	<s:textfield name = "queryUserInfo.endDate"  onfocus="cdr.show(this);" >
	                 		<s:param name = "value"> <s:date name="queryUserInfo.endDate" format="yyyy-MM-dd"  /> </s:param>
	                 </s:textfield> 	
	             </td>
     	    </tr>
     	    
     	   
     	    <tr>
     	    	
     	        <td>地址 : <br/> <input type="text" name = "queryUserInfo.address" value="${ queryUserInfo.address }"> </td>
     			<td>权限：<br/>
     			    <s:select id="type"  name="queryUserInfo.type" list="permissions" listKey="fkey" listValue="fvalue" headerKey="%{ queryUserInfo.type }" >
  					</s:select>
  				</td>
  				<td> 性别: <br/>
  					
  					<s:if test="null == queryUserInfo.sex">
	  					<input type="radio" value="男" name="queryUserInfo.sex" id="manSex"> 男
	  					<input type="radio" value="女" name="queryUserInfo.sex" id="womenSex"> 女
  					</s:if>
  					<s:else>
  						<s:if test='%{ queryUserInfo.sex == "男" }'>
  							<input type="radio" value="男" name="queryUserInfo.sex" id="manSex" checked="checked"> 男
	  						<input type="radio" value="女" name="queryUserInfo.sex" id="womenSex"> 女
  						</s:if>
  						<s:else>
  							<input type="radio" value="男" name="queryUserInfo.sex" id="manSex"> 男
	  						<input type="radio" value="女" name="queryUserInfo.sex" id="womenSex" checked="checked"> 女
  						</s:else>
  					</s:else>
  					
  					
  				</td> 
  				<td> </td>
     	    </tr>
     	    
     	</table>
     	
      
        <input class="btn" type="submit"" value="查询" />
        <input class="btn" type="button" value="重置" onclick="f_clear()"/>&nbsp;
        <div>
			<font face="黑体" size="6"><font color="red">  <s:property value="request.message"/>  </font>  </font>  
		</div>
    
  
  
  		<table class="table table-hover">
  		<tr>
  			<th>用户编号</th> <th>用户名</th> <th>姓名</th> <th>性别</th>  <th>电话</th>  <th>地址</th> <th>权限</th> <th colspan="2" align="right">操作</th>  
  		</tr>
  	     <s:iterator var="user" value="users">
	     	<tr>
	     		<td>  <s:property value="#user.id"/>  </td>
	     		<td>  <s:property value="#user.userName"/>  </td>
		     	<td>  <s:property value="#user.realName"/>  </td>
		     	<td>  <s:property value="#user.sex"/>  </td>
		     	<td>  <s:property value="#user.phone"/>  </td>
		     	<td>  <s:property value="#user.address"/>  </td>
		     	<td>  <s:property value="#user.type"/>  </td>
		     	<td>
						<s:a action="queryById_UserAction">
							<s:param name="id" value="#user.id"></s:param>
							<font color="#000E00">更新</font>
						</s:a>
						<a href="jacascript:void(0)" onclick="openUserDetail(<s:property value="#user.id"/>)">详细</a>
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
