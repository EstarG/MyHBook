<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    
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
    <script type="text/javascript" src="css/d.css"></script>
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	<script type="text/javascript" src="css/e.css" charset="gb2312"></script>
	<script type="text/javascript" src="css/f.css" charset="gb2312"></script>
	<script language="javascript" src="js/Calendar2.js"></script>
	
	<script type="text/javascript">
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
	    
	    function check() {
	    	//alert("aaaa");
	    	var password = document.form1.password;
	    	//alert("password = " + password.value.trim().length);
	    	if (password.value.trim().length == 0) {
	    		alert("密码不能为空");
	    		password.focus();
	    		event.returnValue = false;
	    		return ;
	    	}
	    	event.returnValue = true;
	    }
	</script>
	<style type="text/css">
		.breadcrumb{
			margin-left: 70px;
		}
		#div1{
			margin-left: 80px;
			margin-right: 80px;
			
		}
		#div2{
			margin-left: 150px;
			height: 500px;
			display: block;
		}
		#div3{
			display: block;
			float: left;
		}
		
	</style>
 </head>
  
  <body>
  
  	  <!-- top  -->
      <jsp:include page="/view/top.jsp"></jsp:include>
      <!-- top  -->
      
      <!-- content  -->
      	 <div class="breadcrumb">
    		<strong><a href="<%=basePath %>/view/index.jsp">我的汇书</a></strong>
    		<span>&nbsp;&gt;&nbsp;账户信息<span></span></span>
		</div>
		<div id="div1">
			<hr  dir="rtl">
				<h2>账户信息     &nbsp; &nbsp;  &nbsp;  &nbsp;  </h2>
			<hr>
		</div>
		
		<div id="div2"  >
			<s:form action="viewUpdate_UserAction" id="form1" onsubmit="check()" method="post">
			     	<s:hidden name="id" value="%{user.id}"></s:hidden>
			     	<s:hidden name="userName" value="%{user.userName}"></s:hidden>
			     	<s:hidden name="password" value="%{user.password}"></s:hidden>
			     	
			     	<table  height="400px" >
			     		<tr>
			     			<td  width="600px" align="left">
				     			<a href="index_UIAction">	<img alt="" src="<%=basePath %>/images/toppic5.jpg" width="600px" height="300px"> </a>
				     		</td>
			     		
				     		<td width="400px" align="right" >
				     			<table>
						     		
						     		<tr>
						     			<td>  </td>
						     		</tr>
						     		<tr>
						     			<td> 姓名: <input type="text" name="realName" value="${user.realName}" width="400px">  </td>
						     		</tr>
						     		<tr>
						     			<td>  </td>
						     		</tr>
						     		<tr>
						     			<td> 性别: 
						     				<s:select name="sex" list="#{'男':'男','女':'女'}" listKey="key" listValue="value" value="%{user.sex}">
						     				</s:select>
						     			</td>
						     		</tr>
						     		<tr>
						     			<td>  </td>
						     		</tr>
						     		<tr>
						     			<td> 邮箱: <input type="text" name="email" value="${user.email}" width="400px">  </td>
						     		</tr>
						     		<tr>
						     			<td>  </td>
						     		</tr>
						     		<tr>
						     			<td> 电话: <input type="text" name="phone" value="${user.phone}" width="400px">  </td>
						     		</tr>
						     		<tr>
						     			<td>  </td>
						     		</tr>
						     		<tr>
						     			<td> Q&nbsp;&nbsp;Q: <input type="text" name="userQq" value="${user.userQq}" width="400px">  </td>
						     		</tr>
						     		<tr>
						     			<td>  </td>
						     		</tr>
						     		<tr>
						     			<td> 地址: <input type="text" name="address" value="${user.address}" width="400px">  </td>
						     		</tr>
						     		<tr>
						     			<td>  </td>
						     		</tr>
						     		<tr>
						     			<td>
						     				<s:hidden name="type" value="%{user.type}"></s:hidden>
						     				权限: 
						     				<s:if test="%{user.type == 83}">
						     					管理员
						     				</s:if>
						     				<s:if test="%{user.type == 84}">
						     					会员
						     				</s:if>
						     				<s:if test="%{user.type == 85}">
						     					普通用户
						     				</s:if>
						     				
						     			</td>
						     		</tr>
						     		<tr>
						     			<td>  </td>
						     		</tr>
						     		<tr>
						     			<td>
						     			  生日:
						     				<s:textfield name = "birthday"  onfocus="cdr.show(this);" width="200px">
												<s:param name = "value"> <s:date name="%{user.birthday}" format="yyyy-MM-dd"/>  </s:param>
											</s:textfield>
						     			</td>
						     		</tr>
					   			</table>
					   			<input type="submit" value="保存" style="background:url(save.png)"/>
				   			</td>
				     	</tr>
			     	</table>
			     	</s:form>
		</div>
      <!-- content -->
      <!-- end  -->
      <jsp:include page="/view/bottom.jsp"></jsp:include>
      <!-- end  -->
   
  </body>
</html>
