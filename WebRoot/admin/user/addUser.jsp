<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'addUser.jsp' starting page</title>

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
			//显示时间
			var cdr = new Calendar("cdr");
		    document.write(cdr);
		    cdr.showMoreDay = true;
		   
	   		//表单校验
	   		function check(){
	   			if (document.form1.userName.value==""){
					alert("用户名不能为空！");
					document.form1.userName.focus();
					event.returnValue = false;
					return ;
				}
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
	   		
	   		//Ajax 异步请求判断用户名是都存在
	   		function f_checkUserName(obj) {
	   			//alert(obj);
	   			 $.post(
					"isExistUserName_UserAction",
					 {"userName" : obj},
					 c_bUserName,
					 'text'
				 );
	   		}
	   		function c_bUserName(data) {
	   			mark = eval(data);
	   			//alert(mark);
				if (mark == "1") {
					document.getElementById("isokUserName").innerHTML = "该用户名已存在";	
					document.getElementById("add").disabled="disabled";  //不合法不能提交
				}  else {
					document.getElementById("isokUserName").innerHTML = "";
					document.getElementById("add").disabled="";  //不合法不能提交
				}
	   		}
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
						<td height="31"><div class="titlebt">添加用户</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
  	 </table>
	
	
		<s:form id="form1" action="add_UserAction" class="form-search" onsubmit="check()" method="post">
			<font color="red" style="margin-left: 23px"> 带*为必填项   </font>
			<table class="table table-hover">
				<tr>
					<td>
						用户名:
						<input  type="text" name="userName" onblur="f_checkUserName(this.value)"> <font color="red"> * </font>
						<div id="isokUserName" style="color: red;">
							
						</div>
					</td>
					<td>
						密码:
						<input type="password" name="password"> <font color="red"> * </font>
					</td>
				</tr>
				<tr>
					<td>
						姓&nbsp;&nbsp;&nbsp;&nbsp;名:
						<input type="text" name="realName"> <font color="red"> * </font>
					</td>
					<td>
						电话:
						<input type="text" name="phone"> <font color="red"> * </font>
					</td>
				</tr>



				<tr>
					<td>
						邮&nbsp;&nbsp;&nbsp;&nbsp;箱:
						<input type="text" id="email" name="email" >
					</td>
					<td>
						Q&nbsp;&nbsp;Q:
						<input type="text" name="userQq">
					</td>
				</tr>
				<tr>
					<td>
						生&nbsp;&nbsp;&nbsp;&nbsp;日:
						<s:textfield name="birthday" onfocus="cdr.show(this);">
							<s:param name="value">
								<s:date name="%{emp.graddate}" format="yyyy-MM-dd" />
							</s:param>
						</s:textfield>
					</td>
					<td>
						性别:&nbsp;&nbsp;
						<input type="radio" " name="sex" value="男" checked="checked">
						男
						<input type="radio" " name="sex" value="女">
						女
					</td>

				</tr>
				<tr>
					<td>
						权&nbsp;&nbsp;&nbsp;&nbsp;限:
						<s:select id="type"  name="type" list="permissions" listKey="fkey" listValue="fvalue" value="85" >
  						</s:select>
  						<font color="red"> * </font>
					</td>

					<td>
						地址:

						<s:select id="province" name="province" list="provinces" listKey="fkey" listValue="fvalue" headerKey="0" headerValue="选择省份" style="width: 120px">
						</s:select>
						<select id = "city" name = "city" style="width: 120px">
		  						<option value = "0"></option>
		  				</select>
						<font color="red"> * </font>
					</td>

				</tr>
				<tr>
					<td>
						<input id="add" type="submit" value="添加" class="btn">
					</td>
					<td>
					    <input type="reset" value="重置" class="btn">
					</td>

				</tr>
				
				<tr>
					<td>
						<font color="red">${ message } </font>
					</td>
					<td></td>
				</tr>
			</table>

		</s:form>
	</body>
</html>
