<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showLog.jsp' starting page</title>
    
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
		
		//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
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
						<td height="31"><div class="titlebt">系统日志</div>
						</td>
					</tr>
				</table>
			</td>
			<td width="16" valign="top" background="admin/images/mail_rightbg.gif">
				<img src="admin/images/nav-right-bg.gif" width="16" height="29" />
			</td>
		</tr>
  	 </table>
  
    <s:form action="query_LogAction" method="post">
    	<s:hidden name = "currentPage" value="1"></s:hidden>
    	<table class="table table-hover">
    		<tr>
     	    	<td> 用户名： <input type="text" name = "queryLogInfo.userName" value="${ queryLogInfo.userName }"> </td>
     	    	<td> I&nbsp;P&nbsp;地&nbsp;&nbsp;址： <input type="text" name = "queryLogInfo.userIp" value="${ queryLogInfo.userIp }"> </td>
     	    	 <td>用户行为 : <input type="text" name = "queryLogInfo.info" value="${ queryLogInfo.info }"> </td>
     	    	
     	    </tr>
     	    
     	   
     	    <tr>
     	       <td>开始时间: 
	            	 <s:textfield name = "queryLogInfo.beginDate" onfocus="cdr.show(this);" >
	            	 	<s:param name="value"><s:date name="queryLogInfo.beginDate" format="yyyy-MM-dd"/> </s:param>
	            	 </s:textfield>  
	           </td>   
	            	         
	            <td>结束时间：
	                 	<s:textfield name = "queryLogInfo.endDate"  onfocus="cdr.show(this);" >
	                 		<s:param name = "value"> <s:date name="queryLogInfo.endDate" format="yyyy-MM-dd" /> </s:param>
	                 </s:textfield> 	
	             </td>
	             <td></td>
     	    </tr>
    	</table >
    	
        <input class="btn" type="submit"" value="查询" />
        <input class="btn" type="button" value="重置" onclick="f_clear()"/>&nbsp;
    
  
    	
 	<table class="table table-hover">
  		<tr>
  			<th>日志编号</th> <th>用户名</th> <th>IP地址</th> <th>时间</th> <th>具体操作</th> 
  		</tr>
  	     <s:iterator var="log" value="logs">
	     	<tr>
	     		<td>  <s:property value="#log.id"/>  </td>
	     		<td>  <s:property value="#log.TUser.userName"/>  </td>
		     	<td>  <s:property value="#log.userip"/> </td>
		     	<td>  
		     		  <s:date name="#log.logdate" format="yyyy-MM-dd" />
		     	</td>
		     	<td>  <s:property value="#log.info"/>  </td>
	     	</tr>
    	 </s:iterator>
    </table>
    
     		当前页:  ${currentPage}  &nbsp;&nbsp; 总页数 : ${allPage} 
	       	
	       	<s:if test="%{currentPage > 1}">
	       		<a href="javascript:f_page(${currentPage-1})">上一页 </a> &nbsp;&nbsp;
	       	</s:if>
	       	<s:if test="%{currentPage <= 1}">
	       		上一页
	       	</s:if>
            <s:if test="%{currentPage < allPage}">
	       		<a href="javascript:f_page(${currentPage+1})"> 下一页</a>
			</s:if>
			<s:if test="%{currentPage >= allPage}">
				下一页
			</s:if>       
         
    </s:form>
    	
  </body>
</html>
