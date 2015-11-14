<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>- 管理页面</title>
<script language=JavaScript>
	function logout() {
		if (confirm("您确定要退出控制面板吗？"))
			//跳到action清空session
			top.location = "adminLogout_LoginAction";
		return false;
	}
	function login() {
		
		top.location = "loginIndex_UIAction";
		return false;
	}
</script>
<script language=JavaScript1.2>
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="车到山前,旅游,旅游导航">
<meta http-equiv="description" content="This is my page">
<script language=JavaScript1.2>
	
</script>
<base target="main">
<link href="images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
	<table width="100%" height="64" border="0" cellpadding="0"
		cellspacing="0" class="admin_topbg">
		<tr>
			<td width="61%" height="64"></td>
			<td width="39%" valign="top"><table width="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						
						
						 <s:if test="%{#session.admin == null}">
						 	<td width="74%" height="38" class="admin_txt"></td>
				          	<td width="22%">
				          	 	<input type="button" onclick="login();" value="登录"> 
				          	 </td>
							<td width="4%">&nbsp;</td>
				         </s:if>
				         <s:else>
				         	<td width="74%" height="38" class="admin_txt">管理员：<b>${ admin.userName }</b></td>
				         	<td width="22%"><a href="#" target="_self" onClick="logout();">
							<img src="images/out.gif" alt="安全退出"
								width="46" height="20" border="0"> </a></td>
							<td width="4%">&nbsp;</td>
				         </s:else>
						
					</tr>
					<tr>
						<td height="19" colspan="3">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
