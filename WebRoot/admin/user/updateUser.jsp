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
    
    <title>My JSP 'updateUser.jsp' starting page</title>
    
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
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.2.js"></script>
	
	<script type="text/javascript">
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
	    // 省市二级联动
	   		$(document).ready(
				function(){
					$("#province").change(
						function(){
							var fkey = $(this).val();
							//alert("fkey = " + fkey);
							$.post(
								"queryCitys_UserAction",
								{"fkey":fkey},
								call_back,
								"json"
							);
						}
					);
				}
			);
			function call_back(data){
				$("#city").empty();
				$.each(data,function(indx,obj){
						$("#city").append("<option value='"+obj.fkey+"'>"+obj.fvalue+"</option>");
				});
			}
			
			//表单校验
			function check() {
			
				if (document.form1.password.value==""){
					alert("密码不能为空！");
					document.form1.password.focus();
					event.returnValue = false;
					return ;
				}
				if (document.form1.realName.value==""){
					alert("姓名不能为空！");
					document.form1.realName.focus();
					event.returnValue = false;
					return ;
				}
				if (document.form1.phone.value==""){
					alert("电话不能为空！");
					document.form1.phone.focus();
					event.returnValue = false;
					return ;
				}
				
				if (document.form1.province.value=="0"){
					alert("地址不能为空！");
					document.form1.province.focus();
					event.returnValue = false;
					return ;
				}

				var temp = document.getElementById("email");
				//alert(temp.value);
				//对电子邮件的验证
				var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				if(temp.value!=""){
					if(!myreg.test(temp.value)){
						alert('提示:请输入有效的E_mail！');
						document.form1.email.focus();
						event.returnValue = false;
						return ;
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
						<td height="31"><div class="titlebt">更新用户</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
  	 </table>
	
  
  
     <s:form action="update_UserAction" id="form1" onsubmit="check()" method="post">
     	<font color="red" style="margin-left: 23px"> 带*不能为空  </font>
     	<font color="red">${ message }</font>
     	<s:hidden name="id" value="%{user.id}"></s:hidden>
     	<s:hidden name="userName" value="%{user.userName}"></s:hidden>
     	<table class="table table-hover">
     		
     		<tr>
     			<td> 密码： <input type="text" name="password" value="${user.password}">   <font color="red"> * </font>  </td>
     			<td> 姓名： <input type="text" name="realName" value="${user.realName}">   <font color="red"> * </font>  </td>
     		</tr>
     		
     		<tr>
     			<td> 邮箱： <input type="text" name="email" value="${user.email}">   </td>
     			<td> 电话： <input type="text" name="phone" value="${user.phone}">  <font color="red"> * </font>  </td>
     		</tr>
     		<tr>
     			<td> Q&nbsp;&nbsp;Q： <input type="text" name="userQq" value="${user.userQq}">  </td>
     			<td>
     			  生日：
     				<s:textfield name = "birthday"  onfocus="cdr.show(this);">
						<s:param name = "value"> <s:date name="%{user.birthday}" format="yyyy-MM-dd"/>  </s:param>
					</s:textfield>
     			</td>
     		</tr>
     		<tr>
     		
     			<td> 
     				权限：
  					<s:select name="type" list="permissions" listKey="fkey" listValue="fvalue" value="%{user.type}">
  					</s:select>
  					 <font color="red"> * </font> 
     			 </td>
     			 <td> 性别： 
     				<s:select name="sex" list="#{'男':'男','女':'女'}" listKey="key" listValue="value" value="%{user.sex}">
     				</s:select>
     			</td>
     			
     		</tr>
     		<tr>
     			
     		</tr>
     		<tr>
     			<td colspan="2">
     				地址：
     				 <s:select id="province" name="province" list="provinces" listKey="fkey" listValue="fvalue" value="%{ user.province }" style="width: 120px">
					 </s:select>
					 
					 <select id = "city" name = "city" style="width: 100px">
			  					<option value = "${ user.city }"> ${ cityName } </option>
			  		 </select>
			  		  <font color="red"> * </font> 
     			</td>
     		</tr>
     		
     	</table>
     	<input class="btn" type="submit" value="更新" />
        <input class="btn" type="reset" value="重置"/>&nbsp;
     </s:form>
  </body>
</html>
