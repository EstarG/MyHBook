<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
 
  <script type="text/javascript">
	function loadimage(){
		document.getElementById("randImage").src="image.jsp?"+Math.random();
	}
	function check(){
	   // alert("aaa");
	    //alert(" == " + document.loginForm.userName.value);
		if(document.loginForm.userName.value==""){
				alert("用户名不能为空！");
				document.loginForm.userName.focus();
				event.returnValue = false; 
				return ;
		}if(document.loginForm.password.value==""){
				alert("密码不能为空！");
				document.loginForm.password.focus();
				event.returnValue = false;
				return;
		}if(document.loginForm.verifycode.value==""){
				alert("验证码不能为空！");
				document.loginForm.verifycode.focus();
				event.returnValue = false;
				return;
		}else {
			event.returnValue = true;	
		}
	}
	
	
  
  		$(document).ready(function() {
   		$(".imageRotation").each(function(){
        // 获取有关参数
        var imageRotation = this,  // 图片轮换容器
            imageBox = $(imageRotation).children(".imageBox")[0],  // 图片容器
            titleBox = $(imageRotation).children(".titleBox")[0],  // 标题容器
            titleArr = $(titleBox).children(),  // 所有标题（数组）
            icoBox = $(imageRotation).children(".icoBox")[0],  // 图标容器
            icoArr = $(icoBox).children(),  // 所有图标（数组）
            imageWidth = $(imageRotation).width(),  // 图片宽度
            imageNum = $(imageBox).children().size(),  // 图片数量
            imageReelWidth = imageWidth*imageNum,  // 图片容器宽度
            activeID = parseInt($($(icoBox).children(".active")[0]).attr("rel")),  // 当前图片ID
            nextID = 0,  // 下张图片ID
            setIntervalID,  // setInterval() 函数ID
            intervalTime = 4000,  // 间隔时间
            imageSpeed =500,  // 图片动画执行速度
            titleSpeed =250;  // 标题动画执行速度
        // 设置 图片容器 的宽度
        $(imageBox).css({'width' : imageReelWidth + "px"});
        // 图片轮换函数
        var rotate=function(clickID){
            if(clickID){ nextID = clickID; }
            else{ nextID=activeID<=3 ? activeID+1 : 1; }
            // 交换图标
            $(icoArr[activeID-1]).removeClass("active");
            $(icoArr[nextID-1]).addClass("active");
            // 交换标题
            $(titleArr[activeID-1]).animate(
                {bottom:"-40px"},
                titleSpeed,
                function(){
                    $(titleArr[nextID-1]).animate({bottom:"0px"} , titleSpeed);
                }
            );
            // 交换图片
            $(imageBox).animate({left:"-"+(nextID-1)*imageWidth+"px"} , imageSpeed);
            // 交换IP
            activeID = nextID;
        }
        setIntervalID=setInterval(rotate,intervalTime);
        $(imageBox).hover(
            function(){ clearInterval(setIntervalID); },
            function(){ setIntervalID=setInterval(rotate,intervalTime); }
        );   
        $(icoArr).click(function(){
            clearInterval(setIntervalID);
            var clickID = parseInt($(this).attr("rel"));
            rotate(clickID);
            setIntervalID=setInterval(rotate,intervalTime);
        });
    });
});
  
  
  
  </script>
  <style type="text/css">
.imageRotation{
    height:270px;
    width:570px;
    overflow:hidden;  /*--超出容器的所有元素都不可见--*/
    position:relative;  /*--相对定位--*/
    border:10px solid #eee;
    bodrer-radius:5px;
    -webkit-border-radius:5px;
    -moz-border-radius:5px;
    margin-top: 15px;
    margin-bottom: 15px;
    }
/*-------------图片容器---------------*/
.imageBox{
    position:absolute;  /*--固定定位--*/
    height:270px;
    top:0px;
    left:0px;
    overflow:hidden;
    }
.imageBox img {
    display:block;
    height:270px;
    width:570px;
    float:left;
    border:none;
    }
/*-------------标题容器---------------*/
.titleBox{
    position:absolute;  /*--固定定位--*/
    bottom:0px;
    width:570px;
    height:40px;
    overflow:hidden;
    }
.titleBox p{
    position:absolute;   /*--固定定位--*/
    bottom:-40px;
    width:550px;
    height:40px;
    margin:0px;
    padding:0px 10px;
    line-height:40px;
    z-index:1;
    background-color:#000;
    color:#fff;
    font-family:"微软雅黑","yahei";
    opacity:0.5;
    -moz-opacity:0.5;
    -webkit-opacity:0.5;
    filter:alpha(opacity=50);
    }
.titleBox p span{
    opacity:1;
    -moz-opacity:1;
    -webkit-opacity:1;
    filter:alpha(opacity=100);
    }
.titleBox p.active{
    bottom:0px;
    }
/*-------------图标容器---------------*/
.icoBox{
    position:absolute;  /*--固定定位--*/
    bottom:14px;
    right:15px;
    width:76px;
    height:12px;
    text-align:center;
    line-height:40px;
    z-index:2;
    }
.icoBox span{
    display:block;
    float:left;
    height:12px;
    width:12px;
    margin-left:3px;
    overflow:hidden;
    background:url("images/ico.png") 0px 0px no-repeat;
    cursor:pointer;
    }
.icoBox span.active {
    background-position:0px -12px;
    cursor:default;
    }
	
		
	</style>
  <script type="text/javascript">
  		function show(){
	    	window.open ("jump_LoginAction", "newwindow", "height=260, width=400, toolbar=no, menubar=no, scrollbars=no scrollbars=no, resizable=no,location=no, status=no,top = 250,left = 300");
	    }
	    
	    //Ajax 异步请求判断验证码是都正确
	   		function f_checkCode(obj) {
	   			//alert(obj);
	   			 $.post(
					"isOkCode_LoginAction",
					 {"verifycode" : obj},
					 c_bVerifycode,
					 'text'
				 );
	   		}
	   		function c_bVerifycode(data) {
	   			mark = eval(data);
	   			//alert(mark);
				if (mark == "1") {
					document.getElementById("isOkCode").innerHTML = "验证码不正确";	
				}  else {
					document.getElementById("isOkCode").innerHTML = "";
				}
				
	   		}
	    
  </script>
   </head>
  <body style="background-color:#f5fffa;font-family:'微软雅黑'">
  <!-- 登陆页面 -->
  <br/>
    <table width="80%"  height="80%" border="3" align="center"><tr><td>
    	<center><img  alt = "head" src = "images/hbookLogo.png" width = "1000" height = "150"/><br/></center>
	   
	    <form action="login_LoginAction" method="post" id="loginForm" name="loginForm" onsubmit="check()" >
	       <fieldset width="300" align="right">  <legend align="left">欢迎登录</legend>
	       
			<div class="imageRotation" >   
			
			    <div class="imageBox">
			        <a href="newBooks_BookAction" target="_blank"><img src="images/show1.jpg" /></a>
			        <a href="hotBooks_BookAction" target="_blank"><img src="images/show2.jpg" /></a>
			        <a href="lowBooks_BookAction" target="_blank"><img src="images/show3.jpg" /></a>
			        <a href="highBooks_BookAction" target="_blank"><img src="images/show4.jpg" /></a>
			        <a href="usedBooks_BookAction" target="_blank"><img src="images/show5.jpg" /></a>
			    </div>
			    <div class="titleBox">
			        <p class="active"><span>新书热淘</span></p>
			        <p>热销供应</p>
			        <p>低价市场</p>
			        <p>高评书籍</p>
			        <p>二手市场</p>
			    </div>
			    <div class="icoBox">
			        <span class="active" rel="1">1</span>
			        <span rel="2">2</span>
			        <span rel="3">3</span>
			        <span rel="4">4</span>
			        <span rel="5">5</span>
			    </div>
			</div>


	<div style="float: left;margin-top: 10px;position: relative;position: fixed;left: 780px;top: 260px;" >
		<p align="right">
		         		用户名：<input type="text" name="userName" size="20"/>
		         		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
		         		
		         		密  &nbsp;&nbsp;码：<input type="password" name="password" style="width:156"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
		         		
		         		 验证码：<input type="text" name="verifycode" size="20" >&nbsp;&nbsp;
		         		
		         		<img alt="code"  name="randCode" id="randImage" src="image.jsp" width="60" height="20" border="1" align="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
		         	   	<div id="isOkCode" style="color: red;"></div>  
		         	   	
		         	   	<a href="view/retrievepassword.jsp"><font face="微软雅黑" size=2> 忘记密码 </font></a>&nbsp;&nbsp;
		         	   	<a href="javascript:loadimage();">
		                <font face="微软雅黑" size=2>看不清点我</font></a>&nbsp;&nbsp;
			         	 <img alt = "head" src = "images/headpic1.jpg" width = "30" height = "15"/>
		         	   
		    		<p align="right"><font color = "red"><s:property value = "#request.message"/></font>
		         	<input type="submit" id="login" value="登录" style="margin-right: 90px">
		         	
		         	<!--  
		         	<input type="button" value="忘记密码"/>&nbsp;&nbsp;&nbsp; &nbsp;<br/><br/>
		         	-->
			</div>
		    </fieldset>
		  </form>
		       
		   </td></tr></table>

  </body>
</html>
