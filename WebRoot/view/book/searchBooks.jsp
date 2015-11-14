<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"   %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>书籍信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="css/query1.css">
	<link rel="stylesheet" type="text/css" href="css/query2.css">
	<link rel="stylesheet" type="text/css" href="css/query3.css">
	<link rel="stylesheet" type="text/css" href="css/a.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/b.css">
    <link rel="stylesheet" type="text/css" href="css/c.css">

    <script type="text/javascript" src="css/d.css"></script>
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	<script type="text/javascript" src="js/select.js"> </script>
	
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
			   
				//alert(obj);
				with(document.forms[1]) {  //top里面有了一个form表单了
					currentPage.value = obj;
					submit();
				}
		}
	</script>
  </head>
  
 <body id="book">
	  <!-- top  -->
      <jsp:include page="/view/top.jsp"></jsp:include>
      <!-- top  -->
	<!-- 分类 -->
    <div class="w">
        <div class="breadcrumb">
            <strong><a href="index_UIAction">图书</a></strong>
            <span> &nbsp;&gt;&nbsp;<font size="3"> 新书热淘</font></span>
            <div id="hotkeywords"></div>
        </div>
    </div>
    <!--crumb end-->

<s:form action="search_BookAction" method="post">

	<s:hidden name="currentPage" value="1"></s:hidden>
	<!-- 传递条件查询参数 -->
	<s:hidden id="clientInputInfo" name="queryBookInfo.clientInputInfo" value="%{queryBookInfo.clientInputInfo}"></s:hidden>
	
	<s:hidden id="beginGrade" name="queryBookInfo.beginGrade" value="%{queryBookInfo.beginGrade}"></s:hidden>
	<s:hidden id="endGrade" name="queryBookInfo.endnGrade" value="%{queryBookInfo.endnGrade}"></s:hidden>
	
	<s:hidden id="beginPrice" name="queryBookInfo.beginPrice" value="%{queryBookInfo.beginPrice}"></s:hidden>
	<s:hidden id="endPrice" name="queryBookInfo.endPrice" value="%{queryBookInfo.endPrice}"></s:hidden>
	
	<s:hidden id="beginDiscount" name="queryBookInfo.beginDiscount" value="%{queryBookInfo.beginDiscount}"></s:hidden>
	<s:hidden id="endDiscount" name="queryBookInfo.endDiscount" value="%{queryBookInfo.endDiscount}"></s:hidden>
	
	<!-- 查询条件  -->
	<div align="center">
	    <div id="select-attr" class="m" >
	        <div class="mt" style="margin-left: 180px; width: 80%">
	            <strong style="margin-left: -800px"> 图书筛选   
	            </strong>
	            <!-- 隐藏提交方便回显  -->
	            <s:hidden name="selectDetail" id = "selectDetail" value="%{ selectDetail }"></s:hidden>
	            <strong> <span id="selID" style="color: red; margin-left: 20px">${ selectDetail }</span> </strong>
	        </div>
	         <dl>
	             <dt>客户评分：</dt>
	             <dd>
	             	
	               <div><a class="curr" href="javascript:void(0)" onclick="select('grade', '#',  '#')">不限</a></div>
	               <div><a   href="javascript:void(0)" onclick="select('grade','5', '#')" >5星</a>
	               </div><div><a   href="javascript:void(0)" onclick="select('grade', '4', '#')">4星以上</a>
	               </div><div><a   href="javascript:void(0)" onclick="select('grade', '3', '#')">3星以上</a></div>
	               <div><a   href="javascript:void(0)" onclick="select('grade', '2', '#')">2星以上</a></div>
	               <div><a   href="javascript:void(0)" onclick="select('grade', '1', '#')">1星以上</a></div>                      
	             </dd>
			 </dl>
			 <dl>
	             <dt>价格：</dt>
	             <dd>
	                <div><a class="curr" href="javascript:void(0)" onclick="select('price', '#', '#')">不限</a></div>
	                <div><a   href="javascript:void(0)" onclick="select('price', '0', '19')">0-19元</a></div>
	                <div><a   href="javascript:void(0)" onclick="select('price', '20', '39')">20-39元</a></div>
	                <div><a   href="javascript:void(0)" onclick="select('price', '40', '59')">40-59元</a></div>
	                <div><a   href="javascript:void(0)" onclick="select('price', '60', '#')">60元以上</a></div>                        
	             </dd>
	          </dl>
	          <dl>
	                 <dt>折扣：</dt>
	                 <dd>
	                 <div><a class="curr" href="javascript:void(0)" onclick="select('discount', '#', '#')">不限</a></div>
	                 <div><a   href="javascript:void(0)" onclick="select('discount', '0', '3')">0-3折</a></div>
	                 <div><a   href="javascript:void(0)" onclick="select('discount', '3', '5')">3-5折</a></div>
	                 <div><a   href="javascript:void(0)" onclick="select('discount', '5', '7')">5-7折</a></div>
	                 <div><a   href="javascript:void(0)" onclick="select('discount', '7', '#')">7折以上</a></div>                     
	                </dd>
	           </dl>
	                <div class="clr"></div>
	           <span class="clr"></span>
	    </div>
	    <hr width="80%" align="center">
	    <!--filter end-->
	
	    <script type="text/javascript">
	
	        $("#storeinfo").find("a").click(function(){$("#storeinfo").removeClass("hover");}).end().hoverForIE6();
	        $("#store-selector").hoverForIE6(); $("#store-selector").Jdropdown();
	    </script>
	
	
		<!-- 列举新书    -->
	    <div class="m" id="plist"> 
			<s:iterator var="newBook" value="books">
	        	<div class="item" sku="1055640717">
	            	<dl class="info">
	            	
	                <dt class="p-name">
	                <a target="_blank" href="clientQueryById_BookAction?book.id=${ newBook.id }" title="${ newBook.name }">
	                		${ newBook.name }
	                </a></dt>
	
	                <dd class="p-price">
	                    <div class="dt">汇 书  价：</div>
	                    <div class="dd"><strong>￥<fmt:formatNumber type="number" value="${ newBook.hbPrice }" maxFractionDigits="1"/></strong><span class="discount">(${ newBook.discount }折)</span></div>
	                </dd>
	                <dd class="p-market">
	                    <div class="dt">定&#12288;&#12288;价：</div>
	                    <div class="dd"><del> ￥${ newBook.price }</del></div>
	                </dd>
	                <div class="clr"></div>
	
	            </dl>
	            <ul class="summary">            
	                <li class="summary-author">
	                    <div class="dt">作&#12288;&#12288;者：</div>
	                    <div class="dd">
	           				<c:forEach var="author" items="${newBook.TAuthors}" varStatus="vs">  
		           					<c:if test="${vs['index'] == 0}">
										 <a target="_blank" href="search_BookAction?queryBookInfo.clientInputInfo=${ author.name }" target="_blank"> ${ author.name }  </a>
									</c:if >
									<c:if test="${vs['index'] != 0}">
									　| <a target="_blank" href="search_BookAction?queryBookInfo.clientInputInfo=${ author.name }" target="_blank"> ${ author.name }</a>
									</c:if>
							</c:forEach> 
	                    </div>
	                </li>
	                <li class="summary-press">
	                    <div class="dt">出 版 社：</div>
	                       <div class="dd">
	                         <a target="_blank" href="javascript:void(0)" >${ newBook.publisher }</a>
	                       </div>
	                </li>
	                <li class="summary-time">
	                    <div class="dt">出版时间：</div>
	                    <div class="dd">  <s:date name="#newBook.publishDate" format="yyyy-MM-dd" /></div>
	                </li>
	                <li class="summary-grade">
	                    <div class="dt">顾客评价：</div>
	                    <div class="dd"><span class="star"><span class="star-white"><span class="star-yellow h${ newBook.grade }">&nbsp;</span></span></span><a href="indexEvaluate_EvaluateAction?book.id=${ newBook.id }" target="_blank">(评价)</a></div>
	                </li>
	                                         
	            </ul>       
	
	            <div class="clr"></div>
	
	            <div class="p-img">
	                <a target="_blank" href="clientQueryById_BookAction?book.id=${ newBook.id }" title="${ newBook.name }">                   
	                    <img data-img="1" src="${ newBook.picture }" width="160" height="160" alt="${ newBook.name }">                   
	                </a>
	            </div>
	
	            <div class="btns">
	                <a class="" target="_blank" href="addToCart_CartAction?bookid=${newBook.id}">
	                	<img alt="加入购物车" src="images/addToCart.png">
	                </a>
	            </div>      
	        </div>   
	        </s:iterator>   
		</div>
	</div>
	
	<!-- 分页  -->
	<div align="right" style="margin-right: 200px">
		当前页:  ${currentPage}  &nbsp;&nbsp; 总页数 : ${allPage} 
				       	
	 	<s:if test="%{currentPage > 1}">
			<a href="javascript:f_page(${currentPage-1})"> 上一页 </a> &nbsp;&nbsp;
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
	</div>
	
	</s:form>

<!-- 引入底部标准 -->
	<jsp:include page="/view/bottom.jsp"></jsp:include>   
</body>

</html>

