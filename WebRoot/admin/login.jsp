<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core"  prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>管理员登陆</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="汇书网">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
<script language="JavaScript">
	
	function changeimg() {
		var myimg = document.getElementById("code");
		now = new Date();
		myimg.src = "image.jsp?code=" + now.getTime();
	}

	function correctPNG() {
		var arVersion = navigator.appVersion.split("MSIE")
		var version = parseFloat(arVersion[1])
		if ((version >= 5.5) && (document.body.filters)) {
			for ( var j = 0; j < document.images.length; j++) {
				var img = document.images[j]
				var imgName = img.src.toUpperCase()
				if (imgName.substring(imgName.length - 3, imgName.length) == "PNG") {
					var imgID = (img.id) ? "id='" + img.id + "' " : ""
					var imgClass = (img.className) ? "class='" + img.className
							+ "' " : ""
					var imgTitle = (img.title) ? "title='" + img.title + "' "
							: "title='" + img.alt + "' "
					var imgStyle = "display:inline-block;" + img.style.cssText
					if (img.align == "left")
						imgStyle = "float:left;" + imgStyle
					if (img.align == "right")
						imgStyle = "float:right;" + imgStyle
					if (img.parentElement.href)
						imgStyle = "cursor:hand;" + imgStyle
					var strNewHTML = "<span "
							+ imgID
							+ imgClass
							+ imgTitle
							+ " style=\""
							+ "width:"
							+ img.width
							+ "px; height:"
							+ img.height
							+ "px;"
							+ imgStyle
							+ ";"
							+ "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
							+ "(src=\'" + img.src
							+ "\', sizingMethod='scale');\"></span>"
					img.outerHTML = strNewHTML
					j = j - 1
				}
			}
		}
	}
	window.attachEvent("onload", correctPNG);
	
	
	function chklogin() {
		//alert("xxxx");
		if (document.form1.userName.value == "") {
			alert("用户名不能为空");
			document.form1.userName.focus();
			event.returnValue = false;
			return ;
		}
		if (document.form1.password.value == "") {
			alert("密码不能为空");
			document.form1.password.focus();
			event.returnValue = false;
			return;
		}
		if (document.form1.verifycode.value == "") {
			alert("验证码不能为空");
			document.form1.verifycode.focus();
			event.returnValue = false;
			return ;
		}
		event.returnValue = true;
		
	}
</script>


<link href="images/skin.css" rel="stylesheet" type="text/css">
<body>
	<table width="100%" height="166" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="42" valign="top">
				<table width="100%" height="42" border="0" cellpadding="0"
					cellspacing="0" class="login_top_bg">
					<tr>
						<td width="1%" height="21">&nbsp;</td>
						<td height="42">&nbsp;</td>
						<td width="17%">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" height="532" border="0" cellpadding="0"
					cellspacing="0" class="login_bg">
					<tr>
						<td width="49%" align="right">
							<table width="91%" height="532" border="0" cellpadding="0"
								cellspacing="0" class="login_bg2">
								<tr>
									<td height="138" valign="top">
										<table width="89%" height="427" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td height="149">&nbsp;</td>
											</tr>
											<tr>
												<td height="80" align="right" valign="top">
												</td>
											</tr>
											<tr>
												<td height="198" align="right" valign="top">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td width="35%">&nbsp;</td>
															<td height="25" colspan="2" class="left_txt">
																<p>1- 书籍的管理查阅...</p>
															</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td height="25" colspan="2" class="left_txt">
																<p>2- 用户的管理，审批...</p>
															</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td height="25" colspan="2" class="left_txt">
																<p>3- 订单的查询...</p>
															</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td width="30%" height="40"><img
																src="images/icon-demo.gif" width="16" height="16"><a
																href="http://www.baidu.com" target="_blank"
																class="left_txt3">使用说明</a></td>
															<td width="35%"><img
																src="images/icon-login-seaver.gif" width="16"
																height="16"><a href="http://www.sdut.edu.cn"
																class="left_txt3">在线客服</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table>
						</td>
						<td width="1%">&nbsp;</td>
						<td width="50%" valign="bottom">
							<table width="100%" height="59" border="0" align="center"
								cellpadding="0" cellspacing="0">
								<tr>
									<td width="4%">&nbsp;</td>
									<td width="96%" height="38"><span class="login_txt_bt">登录汇书网后台管理</span>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td height="21"><table cellSpacing="0" cellPadding="0"
											width="100%" border="0" id="table211" height="328">
											<tr>
												<td height="164" colspan="2" align="middle">
													<form id="form1" name="form1" action="adminLogin_LoginAction" method="post"
														onsubmit="chklogin()">
														<table cellSpacing="0" cellPadding="0" width="100%"
															border="0" height="143" id="table212">
															<%
																String rtnMsg = (String) session.getAttribute("rtnMsg");
																if (rtnMsg != null) {
															%>
															<tr>
																<td></td>
																<td><font size="2" color="#ff0000"><%=rtnMsg%></td>
															</tr>
															<%
																session.removeAttribute("rtnMsg");
																}
															%>
															<tr>
																<td width="13%" height="38" class="top_hui_text">
																	<span class="login_txt">用户名：&nbsp;&nbsp; </span> 
																</td>
																<td height="38" colspan="2" class="top_hui_text">
																<input id="userName" name="userName" class="editbox4" value="" size="20"> <span> <font color="red">${ message }</span>
																</td>
															</tr>
															<tr>
																<td width="13%" height="35" class="top_hui_text"><span
																	class="login_txt"> 密 码： &nbsp;&nbsp; </span>
																</td>
																<td height="35" colspan="2" class="top_hui_text">
																	<input id="password" class="editbox4" type="password" size="20" name="password"> 
																	<img src="images/luck.gif"
																	width="19" height="18"></td>
															</tr>
															<tr>
																<td width="13%" height="35"><span class="login_txt">验证码：</span>
																</td>
																<td height="35" colspan="2" class="top_hui_text"><input
																	class=wenbenkuang name="verifycode" type=text value=""
																	maxLength=4 size=10> <img id="code"
																	src="image.jsp"><a
																	href="javascript:changeimg()">看不清，换一张 </a></td>
															</tr>
															<tr>
																<td height="35">&nbsp;</td>
																<td width="20%" height="35"><input name="Submit"
																	type="submit" class="button" id="Submit" value="登 录">
																</td>
																<td width="67%" class="top_hui_text"><input
																	name="cs" type="reset" class="button" id="cs"
																	value="取 消" onClick="showConfirmMsg1()">
																</td>
															</tr>
														</table>
														<br>
													</form>
												</td>
											</tr>
											<tr>
												<td width="433" height="164" align="right" valign="bottom"><img
													src="images/login-wel.gif" width="242" height="138">
												</td>
												<td width="57" align="right" valign="bottom">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="20"><table width="100%" border="0" cellspacing="0"
					cellpadding="0" class="login-buttom-bg">
					<tr>
						<td align="center"><span class="login-buttom-txt">Copyright
								&copy; 2013-2014 www.hbook.com</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>