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
	<link rel="stylesheet" type="text/css" href="css/leftCategory.css">
	<link rel="stylesheet" type="text/css" href="css/a.css" media="all">
	<link rel="stylesheet" type="text/css" href="css/b.css">
    <link rel="stylesheet" type="text/css" href="css/c.css">
    <link rel="stylesheet" type="text/css" href="css/bookDetail3.css">
    

    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	
	<script type="text/javascript">
		function check(){
			//alert("aaaa");
			var num = document.form1.buyNum;
			if (num.value.trim().length == 0 || isNaN(num.value)) {
				alert("请输入合法的购买数量");
				num.focus();
				event.returnValue = false;
				return ;
			}
			event.returnValue = true;
		}
		var t_url = "http://192.168.1.105:8080/MyHBook/";
		
		function convertURL(url) {
			return t_url + url;
		}
		function convertPicUrl(picurl) {
			return t_url + picurl;
		}
		//分享到新新浪微博
		function shareWeiBo(title,url,picurl){
			//alert(">>>");
			//alert(title + " : " + url + " : " + picurl);
			url = convertURL(url);
			picurl = convertPicUrl(picurl);
			//alert(url + " : " + picurl);
			var sharesinastring='http://v.t.sina.com.cn/share/share.php?title='+title+'&url='+url+'&content=utf-8&sourceUrl='+url+'&pic='+picurl;  
			 window.open(sharesinastring,'newwindow','height=400,width=400,top=100,left=100');
		}
		//分享到腾讯微博
		function shareTCWeiBo(title,url,picurl){
			//alert(">>>");
			//alert(title + " : " + url + " : " + picurl);
			 url = convertURL(url);
			 picurl = convertPicUrl(picurl);
			var shareqqstring='http://v.t.qq.com/share/share.php?title='+title+'&url='+url+'&pic='+picurl;  
			 window.open(shareqqstring,'newwindow','height=400,width=400,top=100,left=100');  
		}
		//分享到QQ空间  
		function shareQZone(title,url,picurl)  { 
			 url = convertURL(url);
			 picurl = convertPicUrl(picurl);
			 //alert(url + " : " + picurl);
			 var shareqqzonestring='http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?summary='+title+'&url='+url+'&pics='+picurl;  
			 window.open(shareqqzonestring,'newwindow','height=400,width=400,top=100,left=100');  
		}  
	</script>

  </head>
  
  <body id="book">
 
	
    <jsp:include page="/view/top.jsp"></jsp:include>
    
    <!--长条小的简单分类  -->
	<div class="w">
	    <div class="breadcrumb">
	        <span clstag="firstc|keycount|book|mianbaoxie">
	        <c:forEach var="item" items="${ session.shortKey }">
	        		<a href="queryByCategory_BookAction?queryBookInfo.type=${item.key }" target="_blank">${ item.value }</a>&nbsp;|&nbsp;
	        	</c:forEach>
	        	
	        	
	        	
	        	<a href="highBooks_BookAction" target="_blank">图书榜</a>&nbsp;|&nbsp;
	        	<a href="newBooks_BookAction" target="_blank">新书榜</a>&nbsp;|&nbsp;
	        	<a href="clientQueryById_BookAction?book.id=${ specialbook.id }" target="_blank">特价</a>&nbsp;|&nbsp;
	        	<a href="getAllCategory_UIAction" target="_blank">所有图书分类</a>   
	        </span>
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
              <div id="sortlist" class="m">
                    <div class="mt">
                        <h2>图书</h2>
                    </div>
         <div class="mc" clstag="firstc|keycount|book|daohang">
		          <div class="my_left_category" >
					 <div class="my_left_cat_list" >
					 <!-- 遍历图书分类 -->
					 <s:iterator var="category" value="categorys">
						  <div class="h2_cat" onmouseover="this.className='h2_cat active_cat'" onmouseout="this.className='h2_cat'">
						   <h2><a href="queryByCategory_BookAction?queryBookInfo.type=${category.key.id }"> <s:property value="#category.key.name"/> </a></h2>
						   <div class="h3_cat">
						     <div class="shadow">
						     <div class="shadow_border">
						      <ul>
						      	<s:iterator var="subcategory" value="#category.value">	
						         	<li><a href="queryByCategory_BookAction?queryBookInfo.type=${subcategory.id }"><s:property value="#subcategory.name"/>  </a></li>
						         </s:iterator>
						      </ul>
						     </div>
						     </div>
						   </div>  
						  </div> 
					  </s:iterator>
					 
					  <div class="extra"><a href="getAllCategory_UIAction" title="全部图书分类" target="_blank">全部图书分类&gt;&gt;</a></div>
			<!--测试复制end-->  
			  </div>
 		</div> 
 	</div>
 </div>
 <!-- 左侧导航读书分类结束  -->	

<!--今日特价实现开始 -->
        <div id="group" class="m" clstag="firstc|keycount|book|tuangou">
                    <div class="mt"><h2>今日特价</h2></div>
                    <div class="mc">
                        <ul>
                            <li>
								<div class="p-img"><a href="clientQueryById_BookAction?book.id=${ specialbook.id }" target="_blank"><img data-src="${ specialbook.name }" width="100" height="100" alt="${ specialbook.name }" src="${ specialbook.picture } "></a></div>
								<div class="p-name"><a href="clientQueryById_BookAction?book.id=${ specialbook.id }" target="_blank" title="${ specialbook.name }">${ specialbook.name }</a> <a href="clientQueryById_BookAction?book.id=${ specialbook.id }" target="_blank" title="查看详情" style="margin-left: 110px">详情</a></div>
								<div class="p-market">原价：￥<del>${ specialbook.price }</del></div>
								<div class="p-price">特价：<strong style="color: red">￥ <fmt:formatNumber type="number" value="${ specialbook.hbPrice }" maxFractionDigits="1"/></strong> </div>
							</li>
                        </ul>
                        <div class="clr"></div>
                        <div id="prev" class="btns disabled"></div>
                        <div id="next" class="btns"></div>
                    </div>
                </div>
 <!--  今日特价结束  group end-->
                
                <!-- 加三个广告 -->
            <div class="m">
				<c:forEach var="entry" items = "${ advertisement }">
            		<div class="m da210x90"><a target="_blank" title="" href="${ entry.key }"><img width="200" height="90" alt="" data-lazyload="${ entry.value }" src="${ entry.value }"></a></div>
            	</c:forEach>
            </div>
            
                
            <div class="m da210x90" ></div>
						<div class="m da210x90" ></div>
						<div class="m da210x90" ></div>
						<div class="m da210x90" style="margin-bottom:0;"></div>
            </div>
        </div>
        
        
        <!--   slide  start -->
        <div class="g-0 c-0">
            <div class="c-a10">
                <div  class="m" clstag="firstc|keycount|book|jiaodiantu">
            </div>

            
       
	<s:form id="form1" action="addToCart_CartAction" onsubmit="check()" method="post"> 	
		<s:hidden name="bookid" value="%{ book.id }"></s:hidden>
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
				
				                        <span class="star-white"><span class="star-yellow h${book.grade} }"></span></span> <span><a href="indexEvaluate_EvaluateAction?book.id=${ book.id }" target="_blank">(评价)</a></span>
				
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
				                <div class="dt">购买数量：</div>
				                <div class="dd" clstag="shangpin|keycount|bookitem|0901">
				                    <div class="wrap-input">
				                        <input id="buyNum" type="text" name="buyNum" value="1" id="buy-num" class="text" height="30px">
				                    </div>
				                </div>
				            </li>
				            
				            <li id="choose-btns">
				                <div class="btn" id="choose-btn-append" clstag="shangpin|keycount|bookitem|0902">
				                	<input value="" type="submit"  style="width:140px; height:40px;  border:0; background:url(images/addToCart.png) " />
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
				                        <li><a title="分享到新浪微博" id="site-sina" href="javascript:void(0);" onclick="shareWeiBo('${ book.name }', 'clientQueryById_BookAction?book.id=' + '${ book.id }', '${ book.picture }')">新浪微博</a></li>
				
				                        <li><a title="分享到给QQ好友" id="site-qq" href="javascript:void(0);" onclick="shareQZone('${ book.name }', 'clientQueryById_BookAction?book.id=' + '${ book.id }', '${ book.picture }')">QQ</a></li>
				
				                        <li><a title="分享到腾讯微博" id="site-qzone" href="javascript:void(0);" onclick="shareTCWeiBo('${ book.name }', 'clientQueryById_BookAction?book.id=' + '${ book.id }', '${ book.picture }')">腾讯微博</a></li>
				
				                        <li><a title="分享到人人网" id="site-renren" href="javascript:void(0);" onclick="shareWeiBo('${ book.name }', 'clientQueryById_BookAction?book.id=' + '${ book.id }', '${ book.picture }')">人人网</a></li>
				
				                        <li><a title="分享到豆瓣" id="site-douban" href="javascript:;">豆瓣</a></li>
				                      
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
		
		
                <div id="special-books" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|tejiahaoshu" style="width: 1050px; height: 400px; margin-left: 220px">
                    <div class="mt">
                        <h2 style="color: #B99869">作者简介</h2>
                    </div>
                    <div class="mc">
                         ${book.TDescription.authorInfo }
                  	</div>
                </div>
                
                 <div id="special-books" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|tejiahaoshu" style="width: 1050px; height: 400px; margin-left: 220px">
                    <div class="mt">
                        <h2 style="color: #B99869">内容简介</h2>
                    </div>
                    <div class="mc">
                    
                         ${book.TDescription.content }
                  	</div>
                </div>
                
                
                 <div id="special-books" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|tejiahaoshu" style="width: 1050px; height: 400px; margin-left: 220px">
                    <div class="mt">
                        <h2 style="color: #B99869">书籍目录</h2>
                    </div>
                    <div class="mc">
                    	<pre>
${book.TDescription.catalogInfo}  	
                    	</pre>
                  	</div>
                </div>
		
		
            </div>
        </div>
    </div>





	
	
	<div class="clr"></div>
	<!-- 主题内容结束 -->
	
	<!-- 引入底部标准 -->
	<jsp:include page="/view/bottom.jsp"></jsp:include> 
  </body>
</html>





