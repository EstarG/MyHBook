<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateUser.jsp' starting page</title>
    
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
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script language="javascript" src="js/Calendar2.js"></script>	
	<script type="text/javascript">
			//显示时间
		var cdr = new Calendar("cdr");
	    document.write(cdr);
	    cdr.showMoreDay = true;
	</script>
	<style type="text/css">
		.selectWith{
			width: 100px;
		}
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #F7F8F9;
		}
	</style>
  </head>
  
  
  		<table class="table table-hover">
  			<tr>
  				<td>  编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号 ：${book.id }   </td>
  				<td>  I&nbsp;S&nbsp;B&nbsp;N&nbsp;&nbsp; : ${ book.isbn}  </td>
  				<td rowspan="7"> 
  					<img alt="${ book.name }" src="${ book.picture }" width="300" height="200">
  				</td>
  			</tr>
  			
  			<tr>
  				<td> 书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名 : ${ book.name } </td>
  				<td>  出&nbsp;版&nbsp;社&nbsp;: ${ book.publisher } </td>
  			    
  			</tr>
  			<tr>
  				<td>
  					 出版时间 :
  			   		 <s:date name="%{book.publishDate}" format="yyyy-MM-dd"/> 
	  		 	</td>
	  		 	<td> 作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者 :
  					<c:forEach var="author" items="${ book.TAuthors }">
							  	${ author.name }  
					</c:forEach>
  				 </td>
  			</tr>
  			<tr>
  				<td> 定&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价 : ${ book.price } </td>
  				<td> 折&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;扣 : ${ book.discount } </td>
  			</tr>
  				
  			<tr>
  				<td> 库&nbsp;存&nbsp;量 &nbsp;: ${ book.stockNum }  </td>
  				<td> 销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：${ book.saleNum }</td>
  			</tr>
  			<tr>
  				<td> 评&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分 : ${ book.grade } </td>
  				<td> 汇&nbsp;书&nbsp;价: ${ book.hbPrice } </td>
  			</tr>
  			<tr>
  				<td>
  					  二手书 :
  					 <s:if test="%{ book.used == 1 }">
  					 	是
  					 </s:if>
  					 <s:else>
  					 	 否
  					 </s:else>
  				</td>
  				<td>	
  					分类  : 
  					
						<s:select id="faOptions" list="faCategory" listKey="fkey" listValue="fvalue" cssClass="selectWith"  value="%{ book.ftype }"></s:select>
	  				
	  					<s:select id="chOptions" list="chCategory" listKey="fkey" listValue="fvalue" cssClass="selectWith"  value="%{book.type}" ></s:select>
	  				
  				</td>
  			</tr>
  		</table>
  		<table class="table table-hover">
  			<tr>
  				
  				<td> 内容简介: <br/>
  					<textarea rows="20%" cols="70%" name="description.content" >
  						${ book.TDescription.content }
  					</textarea>
  				</td>
  				<td> 作者简介 : <br/>
  					<textarea rows="20%" cols="70%" name="description.authorInfo">
  						${ book.TDescription.authorInfo }
  					</textarea>
  				</td>
  				<td> 书籍目录 : <br/>
  					<textarea rows="20%" cols="70%" name="description.catalogInfo">
  						${ book.TDescription.catalogInfo }
  					</textarea>
  				</td>
  			</tr>
  		</table>
  		
  		
  </body>
</html>
