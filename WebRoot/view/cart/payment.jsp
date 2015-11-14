<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请选择支付方式</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
	
		function check() {
		  // alert("aaa");
			var address = document.form1.address;
			if (address.value.trim().length == 0) {
				alert("请输入收获地址");
				address.focus();
				event.returnValue = false;
				return ;
			}
			event.returnValue = true;
		}
	</script>

  </head>
  
  <body>
    <table width="960" border="0" align="center">
  <tr>
    <td width="536" valign="top">
	<form id="form1" action="payment_PaymentAction" method="post" name="form1" onsubmit="check()">
	<!-- 经订单号与金额传过去 -->
	<input type="hidden" value="${ orderToken }">
	<input type="hidden" value="${ amount }">
	<table width="100%" border="0">
      <tr>
        <td height="30" colspan="4"><table width="100%" height="50" border="0" cellpadding="0" cellspacing="1" bgcolor="#A3E0FF">
          <tr>
           
             <td align="center" bgcolor="#F7FEFF"><h3>订单号：${ orderToken }  应付金额：￥${ amount } 元</h3></td>
         	 
         	 <td> <font size="5" color="red"> 收货地址： </font>  <input id="address" type="text" value="${ session.user.address} "> </td>	
          </tr>
        </table></td>
        </tr>
      <tr>
        <td colspan="4">&nbsp;</td>
        </tr>
      <tr>
        <td height="30" colspan="4" bgcolor="#F4F8FF"><span class="STYLE3">请您选择在线支付银行</span> </td>
        </tr>
      <tr>
        <td width="26%" height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="CMBCHINA-NET">招商银行 </td>
        <td width="25%" height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="ICBC-NET">工商银行</td>
        <td width="25%" height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="ABC-NET">农业银行</td>
        <td width="24%" height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="CCB-NET">建设银行 </td>
      </tr>
      <tr>
        <td height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="CMBC-NET">中国民生银行总行</td>
        <td height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="CEB-NET" >光大银行 </td>
        <td height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="BOCO-NET">交通银行</td>
        <td height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="SDB-NET">深圳发展银行</td>
      </tr>
      <tr>
        <td height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="BCCB-NET">北京银行</td>
        <td height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="CIB-NET">兴业银行 </td>
        <td height="25"><INPUT TYPE="radio" NAME="pd_FrpId" value="SPDB-NET">上海浦东发展银行 </td>
        <td ><INPUT TYPE="radio" NAME="pd_FrpId" value="ECITIC-NET">中信银行</td>
      </tr>
      <tr>
        <td colspan="4">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="4" align="center"><input id="submit" name="submit" type="submit" value=" 确认支付 " class="btn"/></td>
        </tr>
    </table>
	</form>	</td>
      
    </table>

  </body>
</html>
