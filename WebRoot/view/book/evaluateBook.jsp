<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${ book.name }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<!-- 左侧书籍分类导航 -->
		<!-- 左侧书籍分类导航 -->
	<link rel="stylesheet" type="text/css" href="css/leftCategory.css">
	<link rel="stylesheet" type="text/css" href="css/a.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/b.css">
    <link rel="stylesheet" type="text/css" href="css/c.css">
    <link rel="stylesheet" type="text/css" href="css/bookDetail3.css">
    

    <script type="text/javascript" src="css/d.css"></script>
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	 <script type="text/javascript" src="css/e.css" charset="gb2312"></script>
	<script type="text/javascript" src="css/f.css" charset="gb2312"></script>
	
	<script type="text/javascript">
		function check() {
			var radioNode = document.getElementsByName("value");
			
			for (var i = 0; i < radioNode.length; ++i) {
				if (radioNode[i].checked) {
					event.returnValue = true;
					return ;
				}
			}
			alert("请选择评分");
			event.returnValue = false;
		}
		
		function shareWeiBo(title,url,picurl){
			//alert(title + " :　" + url + " : " + picurl);
			var sharesinastring='http://v.t.sina.com.cn/share/share.php?title='+title+'&url='+url+'&content=utf-8&sourceUrl='+url+'&pic='+picurl;  
			 window.open(sharesinastring,'newwindow','height=400,width=400,top=100,left=100');
		}
	</script>
	

  </head>
  
  <body id="book">
 
	
    <jsp:include page="/view/top.jsp"></jsp:include>
    
    <!--长条小的简单分类  -->
	<div class="w">
	    <div class="breadcrumb">
	        <span clstag="firstc|keycount|book|mianbaoxie">
	        	<a href="http://e.jd.com/ebook.html" target="_blank">电子书</a>&nbsp;|&nbsp;<a href="http://mvd.jd.com/" target="_blank">音像</a>&nbsp;|&nbsp;<a href="http://read.jd.com/" target="_blank">在线读书</a>&nbsp;|&nbsp;<a href="http://tuan.jd.com/channel/dujiahui-quanguo.html" target="_blank">团购</a>&nbsp;|&nbsp;<a href="http://author.jd.com/user/author/showAuthor.action" target="_blank">版税补贴</a>&nbsp;|&nbsp;<a href="http://www.jd.com/booktop-2-0-1.html" target="_blank">图书榜</a>&nbsp;|&nbsp;<a href="http://www.jd.com/booktop-1-2-0-1.html" target="_blank">新书榜</a>&nbsp;|&nbsp;<a href="http://sale.jd.com/p24145.html" target="_blank">特价</a>&nbsp;|&nbsp;<a href="http://jmall.jd.com/p64928.html" target="_blank">预售</a>&nbsp;|&nbsp;<a href="http://book.jd.com/booksort.html" target="_blank">所有图书分类</a>        </span>
	    </div>
	    <!--breadcrumb end -->
	    <div class="clr"></div>
	</div>	
	<!-- 主体内容   -->
	
<!-- 左侧导航读书分类开始  -->	
<div class="w">
    <div class="g-0">
        <div class="g-0">
            <div class="g-a1 z-2">
              <div id="" class="m">
			  </div>
 		</div> 
 	</div>
 </div>
 <!-- 左侧导航读书分类结束  -->	
 	


      
            
       
	<s:form action="evaluate_EvaluateAction" onsubmit="check()" method="post">	
		<s:hidden name="id" value="%{ book.id }"></s:hidden>
		 <div id="special-books" class="m floor-m" data-widget="tabs" >
             <div class="mt">
                     <h2 style="color: #B99869">书籍详细信息</h2>
             </div>
			 <div id="product-intro">
				 <div id="name" >
				     <h1>${ book.name }<br />
				      </h1>
				 </div>
				 <div class="clearfix" clstag="shangpin|keycount|bookitem|07">
				     <ul id="summary">
				        <li id="summary-market">
				             <div class="dt">定&#12288;&#12288;价：</div>
				             <div class="dd"><del>￥${ book.price }</del></div>
				         </li>
				         <li id="summary-price" clstag="shangpin|keycount|bookitem|0701">
				                <div class="dt">汇 书  价：</div>
				                <div class="dd">
				                    <strong>￥<fmt:formatNumber type="number" value="${ book.hbPrice}" maxFractionDigits="1"/></strong><span>(${ book.discount }折)</span> 
				                </div>              
				          </li>
				
				          <li id="summary-grade" clstag="shangpin|keycount|bookitem|0702">
				
				                <div class="dt">商品评分：</div>
				
				                <div class="dd">
				
				                    <div class="star">
				
				                        <span class="star-white"><span class="star-yellow h${book.grade} "></span></span>
				
				                    </div>  
				
				                    <a href="#comment"></a>
				
				                </div>
				
				          </li>

				          <li id="summary-author">
				              <div class="dt">作&#12288;&#12288;者：</div>
							  <div class="dd" clstag="shangpin|keycount|bookitem|0705">
								  <c:forEach var="author" items="${book.TAuthors}" varStatus="vs">  
			           					<c:if test="${vs['index'] == 0}">
											 <a target="_blank" href="search_BookAction?queryBookInfo.clientInputInfo=${ author.name }" target="_blank"> ${ author.name }  </a>
										</c:if >
										<c:if test="${vs['index'] != 0}">
										　| <a target="_blank" href="search_BookAction?queryBookInfo.clientInputInfo=${ author.name }" target="_blank"> ${ author.name }</a>
										</c:if>
			                        </c:forEach> 
							  </div>
				
				          </li>
				          
				          <li id="summary-ph">
				            <div class="dt">出 版 社：</div>
				            <div class="dd" clstag="shangpin|keycount|bookitem|0706">
				            	<a target="_blank" href="javascript:void(0)" target="_blank">${ book.publisher }</a>
				            </div>
				
				          </li>
						 <li id="summary-published">

								<div class="dt">
									出版时间：
								</div>
								<div class="dd">
									<s:date name=" book.publishDate" format="yyyy-MM-dd" />
								</div>

						</li>
					    <li id="summary-isbn">

								<div class="dt">
									ＩＳＢＮ：
								</div>

								<div class="dd">
									${ book.isbn }
								</div>

					   </li>

									<li id="summary-sortranking" clstag="shangpin|keycount|bookitem|0707">
				
				                <div class="dt">所属分类：</div>
				
				                <div class="dd">
				
				                    <span>
				                        <a target="_blank" href="http://book.jd.com">图书</a>                        
				                    </span>
				
				                </div>
				             </li>
				         </ul>
				        <ul id="choose" clstag="shangpin|keycount|bookitem|09">
				            <li id="choose-amount">
				                <div class="dt">评价分数：</div>
				                <div class="dd" clstag="shangpin|keycount|bookitem|0901">
				                    <input type="radio" name="value" value="1"> 1分
				                    <input type="radio" name="value" value="2"> 2分
				                    <input type="radio" name="value" value="3"> 3分
				                    <input type="radio" name="value" value="4"> 4分
				                    <input type="radio" name="value" value="5"> 5分
				                </div>
				            </li>
				            
				            <li id="choose-btns">
				                <div class="btn" id="choose-btn-append" clstag="shangpin|keycount|bookitem|0902">
				                	<input value="" type="submit"  style="width:140px; height:40px;  border:0; background:url(images/evaluate.png) " /> <font size="5" color="red"> ${ message} </font>
				                </div>
				             </li>
				        </ul>
				    </div>
				
				    <div class="clr"></div>   <!-- 书籍图片 -->
				    <div id="preview" clstag="shangpin|keycount|bookitem|06">
				        <div id="spec-n1" clstag="shangpin|keycount|bookitem|0601">
				            <img width="350" height="350" src="${ book.picture }" alt="${ book.name }" />                    
				        </div>
				
				        <div id="short-share">
				            <div class="share-list" id="share-list" clstag="shangpin|keycount|bookitem|0602">
				                <div class="share-bd">
				                    <em class="share-hd">分享到：</em>
				                    <ul class="share-list-item clearfix share-list-item-all">														
				                        <li><a title="分享到新浪微博" id="site-sina" href="javascript:void(0);" onclick="shareWeiBo('${book.name}','http://192.168.1.105:8080/MyHBook/indexEvaluate_EvaluateAction?book.id='+'${book.id }','192.168.1.105:8080/MyHBook/'+'${book.picture}')">新浪微博</a></li>
				
				                        <li><a title="分享到给QQ好友" id="site-qq" href="javascript:;">QQ</a></li>
				
				                        <li><a title="分享到腾讯微博" id="site-qzone" href="javascript:;">腾讯微博</a></li>
				
				                        <li><a title="分享到人人网" id="site-renren" href="javascript:;">人人网</a></li>
				
				                        <li><a title="分享到开心网" id="site-kaixing" href="javascript:;">开心网</a></li>
				
				                        <li><a title="分享到豆瓣" id="site-douban" href="javascript:;">豆瓣</a></li>
				
				                        <li><a title="分享给MSN好友" id="site-msn" href="javascript:;">MSN</a></li>
				
				                        <li><a title="邮件分享给好友" id="site-email" href="javascript:;">邮件</a></li>
				                    </ul>
				                </div>
				                <div class="share-ft share-ft-open hide"><b></b></div>
				            </div>
				            <div class="clr"></div>
				        </div>
				   
				    </div>
				</div>   
				</div> 
	</s:form>			        <!--product-intro end-->
 </div>
</div>		
</div>
		



	

	
	
	<div class="clr"></div>
	<!-- 主题内容结束 -->
	
	<!-- 引入底部标准 -->
	<jsp:include page="/view/bottom.jsp"></jsp:include> 
  </body>
</html>





