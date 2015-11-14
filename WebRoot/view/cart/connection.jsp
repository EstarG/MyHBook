<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>结算</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <body onload="javascript:document.forms[0].submit()">
  	<!-- http://tech.yeepay.com:8080/robot/debug.action -->
  	<!-- https://www.yeepay.com/app-merchant-proxy/node -->
	<form name="yeepay" action="payment_PaymentAction" method='post'>	
		<input type='hidden' name='p0_Cmd'   value="${paymentRequest.p0_Cmd}"> <!-- 请求命令,在线支付固定为Buy -->
		<input type='hidden' name='p1_MerId' value="${paymentRequest.p1_MerId}"> <!-- 商家ID -->
		<input type="hidden" name="p2_Order" value="${paymentRequest.p2_Order}"> <!-- 商家的交易定单号 -->
		<input type='hidden' name='p3_Amt'   value="${paymentRequest.p3_Amt}"> <!-- 订单金额 -->
		<input type='hidden' name='p4_Cur'   value="${paymentRequest.p4_Cur}"> <!-- 货币单位 -->
		<input type='hidden' name='p5_Pid'   value="${paymentRequest.p5_Pid}"> <!-- 商品ID -->
		<input type='hidden' name='p6_Pcat'  value="${paymentRequest.p6_Pcat}"> <!-- 商品种类 -->
		<input type='hidden' name='p7_Pdesc' value="${paymentRequest.p7_Pdesc}"> <!-- 商品描述 -->
		<input type='hidden' name='p8_Url'   value="${paymentRequest.p8_Url}"> <!-- 交易结果通知地址 -->
		<input type='hidden' name='p9_SAF'   value="${paymentRequest.p9_SAF}"> <!-- 需要填写送货信息 0：不需要 1:需要 -->
		<input type='hidden' name='pa_MP'    value="${paymentRequest.pa_MP}"> <!-- 商家扩展信息 -->
		<input type='hidden' name='pd_FrpId' value="${paymentRequest.pd_FrpId}"> <!-- 银行ID -->
		<!-- 应答机制 为“1”: 需要应答机制;为“0”: 不需要应答机制 -->
		<input type="hidden" name="pr_NeedResponse"  value="${paymentRequest.pr_NeedResponse}">
		<input type='hidden' name='hmac' value="${paymentRequest.hmac}"><!-- MD5-hmac验证码 -->
	</form>
  </body>
</html>
