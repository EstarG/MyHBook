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
    
    <title>高级搜索</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/a.css" media="all">

	<link rel="stylesheet" type="text/css" href="css/b.css">

    <link rel="stylesheet" type="text/css" href="css/c.css">
    <link rel="stylesheet" type="text/css" href="css/advSearch1.css">
    <link rel="stylesheet" type="text/css" href="css/advSearch2.css">
    
    <script type="text/javascript" src="css/d.css"></script>
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	 <script type="text/javascript" src="css/e.css" charset="gb2312"></script>
	<script type="text/javascript" src="css/f.css" charset="gb2312"></script>
	<script language="javascript" src="js/Calendar2.js"></script>	
	<script type="text/javascript">
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
		//表单校验
		function check() {
			var beginPrice = document.form1.beginPrice.value;
			var endPrice = document.form1.endPrice.value;
			if (beginPrice != null && isNaN(beginPrice)) {
				alert("请输入合法的价格");
				document.form1.beginPrice.focus();
				event.returnValue = false;
				return ;
			}
			if (endPrice != null && isNaN(endPrice)) {
				alert("请输入合法的价格");
				document.form1.endPrice.focus();
				event.returnValue = false;
				return ;
			}
			
			var beginDiscount = document.form1.beginDiscount.value;
			var endDiscount = document.form1.endDiscount.value;
			if (beginDiscount != null && isNaN(beginDiscount)) {
				alert("请输入合法的价格");
				document.form1.beginDiscount.focus();
				event.returnValue = false;
				return ;
			}
			if (endDiscount != null && isNaN(endDiscount)) {
				alert("请输入合法的价格");
				document.form1.endDiscount.focus();
				event.returnValue = false;
				return ;
			}
			event.returnValue = true;
		}
	 
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
	</script>
  </head>
  
  <body>
  	<jsp:include page="/view/top.jsp"/> 
    
   <div class="w">
        <div class="breadcrumb">
            <strong><a href="http://book.jd.com/">图书</a></strong>
            <span> &nbsp;&gt;&nbsp;<font size="3"> 高级搜索</font></span>
            <div id="hotkeywords"></div>
        </div>
    </div>

	<div class="w" id="advance">

		<div class="mt">
			<h2><b></b>高级搜索</h2>
			<ul class="tab">
				<li>图 书</li>
			</ul>
		</div>
		<div class="mc form">

	<s:form id="form1" action="advSearch_BookAction" onsubmit="check()" method="post">
			<div class="item">
				<span class="label label1">书　　名：</span>
				<input  type="text" class="text text1" name="queryBookInfo.name" />
			</div>
			<div class="item">
				<span class="label label1">作　　者：</span>
				<input  type="text" class="text text1" name="queryBookInfo.authorName"/>
			</div>

			<div class="item">
				<span class="label label1">出 版 社：</span>
				<input  type="text" class="text text1" name="queryBookInfo.publisher"/>
			</div>

			<div class="item">

				<span class="label label1">ＩＳＢＮ：</span>
				<input  type="text" class="text text1" name="queryBookInfo.isbn"/>
			</div>

			<div class="item">

				<span class="label label1">折　　扣：</span>
				<div>
				<input id="beginDiscount" type="text" class="text text2"  name="queryBookInfo.beginDiscount" width="50px">
				<span class="label label2">至</span>
				<input id="endDiscount" type="text" class="text text2"  name="queryBookInfo.endDiscount" width="50px">
				</div>
			</div>

			<div class="item">
				<span class="label label1">汇 书 价：</span>
				<input id="beginPrice" name="queryBookInfo.beginPrice" type="text" class="text text2" />
				<span class="label label2">至</span>
				<input id="endPrice" name="queryBookInfo.endPrice" type="text" class="text text2" />
			</div>
			<div class="item">
				<span class="label label1">出版时间：</span>
				<div><s:textfield id="beginDate" name = "queryBookInfo.beginDate"  onfocus="cdr.show(this);">
	  				</s:textfield></div>  
	  				 <span class="label label2">至</span>
	  				<div><s:textfield id="endDate" name = "queryBookInfo.endDate"  onfocus="cdr.show(this);">
	  				</s:textfield></div>

			</div>
			<div class="btns">
				<input type="submit" value="搜 索" class="btn-search" />
			</div>
			 </s:form>
		</div>
		<span class="clr"></span>
	</div>
	
    <jsp:include page="/view/bottom.jsp"/>
  </body>
</html>
