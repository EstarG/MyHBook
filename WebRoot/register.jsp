<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
  <head>
    <base href="http://localhost:8080/MyHBook/">
    <title>注册汇书</title>
	<meta http-equiv="pragma" content="no-cache">

	<meta http-equiv="cache-control" content="no-cache">

	<meta http-equiv="expires" content="0">    

	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

	<meta http-equiv="description" content="This is my page">

	<!--

	<link rel="stylesheet" type="text/css" href="styles.css">

	-->
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.2.js"></script>
	<link rel="stylesheet" href="css/bootstrap.css">
	
	<script type="text/javascript">
		
			//用户名非空检查
		    function checkUserName() {
		    	if (document.form1.userName.value==""){
					alert("用户名不能为空！");
					document.form1.userName.focus();
					return false;
				}
				return true;
		    }
		    
  		    //表单校验
	   		function check(){
	   			if (!checkUserName()) {
	   				event.returnValue = false;
	   				document.getElementById("add").disabled="disabled";  //不合法不能提交
	   				return ;
	   			}
				if (document.form1.password.value==""){
					alert("密码不能为空！");
					document.form1.password.focus();
					event.returnValue = false;
					return ;
				}
				if (document.form1.repeatPassword.value==""){
					alert("重复密码不能为空！");
					document.form1.repeatPassword.value.focus();
					event.returnValue = false;
					return ;
				}
				if (document.form1.password.value != document.form1.repeatPassword.value){
					alert("两次输入密码不一致");
					document.form1.password.focus();
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
						return;
					}
				}
				event.returnValue = true;
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
				if (!checkUserName()) {
					document.getElementById("add").disabled="disabled"; 
				}
	   		}
	   		
	   		//查看协议
	   		function openProtocol() {
	   			window.open ('<%=basePath%>/protocol.jsp','newwindow','height=700,width=800,top=100,left=300,toolbar=no,menubar=no,scrollbars=no,        resizable=no,location=no, status=no');
	   		}
    </script>

	<style type="text/css">
		#main{
			width: 990px;
			height: 550px;
			margin-left: 180px;
		}
		#reg1{
		}
		#reg2{
			width: 990px;
			height: 500px;
			background-color: #ffffff;
			margin-top: -50px;
			border-bottom-color:#f2f2f2; 
		}

		#form1{
			display: block;
			float: left;
			margin-top: 40px;
			margin-left: 70px;
		}
		#info{
			margin-top: 50px;
			display: block;
			display: block;
		}
	</style>
  </head>
  

  <body  style="background-color:#f5fffa;font-family:'微软雅黑'">
	<table width="80%"  height="80%" border="3" align="center"><tr><td>
	        <fieldset width="300" align="right"> 
		       <legend align="left">欢迎注册</legend>

  		<div id="main" style="margin-left: 30px" >
	    <div id="reg1" align="left">
	    	 <a href="index_UIAction"><font color="#666666" size="6" style=""><b>汇书网</b></font> </a>
	    	 <a href="index_UIAction"><img src="images/toppic5.jpg" alt="汇书网" width="170" height="60"/></a> <b></b>
	    </div>

	    <div  align="right">
        	<span style="text-align: right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></span> <span>我已经注册，现在就&nbsp;<a
                href="login_UIAction"
                class="flk13">登录</a></span>
        </div>

	    
	    <div id="reg2" style="margin-top: 10px">
	    <div align="left" style="margin-left: 10px"> <font size=6 ><b>欢迎加入汇书网</b></font> <br/> <font size="3" color="red" ><b>带*为必填项</b></font>  </div>
	    	
		  <div id="form1">
			<form action="register_UserAction" name="form1" onsubmit="check()" method="post">
				<input type="hidden" name="type" value="85">
						<table>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								
								<tr>
									<td>用&nbsp;户&nbsp;名&nbsp;：</td>
									<td><input type="text" id="userName" name="userName" size=28 class="tb"  onblur="f_checkUserName(this.value)">&nbsp;&nbsp;<font
										color=red>*</font>
										<div id="isokUserName" style="color: red;"></div>
									</td>
								</tr>
								
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;：</td>
									<td><input type="password" size=30 class="tb"
										name="password" id="password">&nbsp;&nbsp;<font color=red>*</font>
									</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>重复密码：</td>
									<td><input type="password" size=30 class="tb"
										name="repeatPassword" id="repeatPassword">&nbsp;&nbsp;<font color=red>*</font>
									</td>
								</tr>

								<tr>

									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>

								<tr>
									<td>邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</td>
									<td><input type="text" size=28 class="tb" name="email" id="email"></td>
								</tr>

								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>

								<tr>
									<td>&nbsp;</td>
									<td>
										<input type="submit" id="add" name="add" value="注册" class="btn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="重置" class="btn">
									</td>
								</tr>
							</table>
						</form>
						 <font size=2>点击注册表示已阅读并同意《<a href="jacascript:void(0)" onclick="openProtocol()">汇书网用户使用协议</a>》</font>
				</div>

				<div id="info" style="margin-left: 120px; float: left">
					<table>
						<tr> 
							<td> <br> 使用合作网站账号快速登录：<br></td>
						</tr>
						<tr>
							<td>
								<br> 
								<a href="login_UIAction"> <img src="images/c_msn.gif"></img> </a> &nbsp;&nbsp;&nbsp;&nbsp;
								<a href="login_UIAction"> <img src="images/c_qq.gif"></img><br> </a>
							</td>
						</tr>

						<tr> 
							<td>
							<br> 
								<a href="login_UIAction"> <img src="images/c_renren.gif"></img> </a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="login_UIAction"> <img src="images/c_sina.gif"></img><br> </a>
							<br>
							<a href="login_UIAction">
								<img src="images/c_sohu.gif"></img>
							</a>
						</td></tr>
					</table>
				</div>
	    </div>
    </div>
  </body>

</html>

