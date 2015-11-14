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
  	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7" />
    <base href="<%=basePath%>">
    
    <title>汇书网</title>
    
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
    
    <script type="text/javascript" src="css/d.css"></script>
    <script type="text/javascript">window.pageConfig = { compatible: true,searchType: 1 }; </script>
	<script type="text/javascript" src="css/e.css" charset="gb2312"></script>
	<script type="text/javascript" src="css/f.css" charset="gb2312"></script>
	<script language="javascript" src="js/Calendar2.js"></script>
	
	<script type="text/javascript">
	
	
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
	            intervalTime = 2500,  // 间隔时间
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
    height:170px;
    width:740px;
    overflow:hidden;  /*--超出容器的所有元素都不可见--*/
    position:relative;  /*--相对定位--*/
    border:10px solid #eee;
    bodrer-radius:5px;
    -webkit-border-radius:5px;
    -moz-border-radius:5px;
    margin-top: 0px;
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
    width:740px;
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

	.open{
            overflow: visible;
        }	
</style>
	
	
	
  </head>
  
  <body id="book">
 
	
    <jsp:include page="top.jsp"></jsp:include>
    
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
                  <div class="mt"> <h2>图书</h2> </div>
         		  <div class="mc" clstag="firstc|keycount|book|daohang">
		          <div class="my_left_category" >
					 <div class="my_left_cat_list" >
					 
					 <!-- 遍历图书分类 -->
					 <s:iterator var="category" value="categorys">
						  <div class="h2_cat" onmouseover="this.className='h2_cat active_cat'" onmouseout="this.className='h2_cat'">
							  <h2><a href="queryByCategory_BookAction?queryBookInfo.type=${category.key.id }"> <s:property value="#category.key.name"/> </a>   </h2>
							   <div class="h3_cat">
							     <div class="shadow">
							     <div class="shadow_border">
								      <ul>
								      	<s:iterator var="subcategory" value="#category.value">	
								         	<li><a href="queryByCategory_BookAction?queryBookInfo.type=${subcategory.id }"> <s:property value="#subcategory.name"/>   </a></li>
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
                <!--  团购实现结束  group end-->
                
            <!-- 加三个广告 -->
            <div class="m">
            	<c:forEach var="entry" items = "${ advertisement }">
            		<div class="m da210x90"><a target="_blank" title="" href="${ entry.key }"><img width="200" height="90" alt="" data-lazyload="${ entry.value }" src="${ entry.value }"></a></div>
            	</c:forEach>
            </div>
<!-- 今日特价实现结束 -->

 <!-- 热门书店 开始-->
					<div class="mt">
                        <h2> 热门书店</h2>
                    </div>
                    
                    <div class="da196x50">
                        <a target="_blank" href="${ hotBookStores[0].url }" title="${ hotBookStores[0].name }">
                        	<img width="196" height="50" src="${ hotBookStores[0].picture }">
                        </a>
	                </div>
	                <div class="da196x50">
                        <a target="_blank" href="${ hotBookStores[1].url }" title="${ hotBookStores[1].name }">
                        	<img width="196" height="50" src="${ hotBookStores[1].picture }">
                        </a>
	                </div>
	               <!-- 新增结构 end-->
	               <div class="mc" id="f_marquee">
                    	<div id="f_marqueeCon1">
	                        <ul>
	                        	<c:forEach var="bookstore" items="${ hotBookStores}" begin="2">
		                        	<li class="">&#183;
		                           		<a title="${ bookstore.name }" target="_blank" href="${ bookstore.url }">${ bookstore.name }</a>
		                           	</li>
	                           	</c:forEach>
	                         </ul>
	                    </div>
	                    <div id="f_marqueeCon2"></div>    
                    </div>
                </div>
                <!--publishers end-->
                
                
            <div ></div>
            </div>
        </div>
<!-- 热门书店 结束-->
        
<!--   slide  start -->
        <div class="g-0 c-0">
            <div class="c-a10">
                <div id="slide" class="m" clstag="firstc|keycount|book|jiaodiantu">
					<div class="imageRotation" >   
					    <div class="imageBox">
					    	<s:iterator value="recommendBooks" var="recommendBook">															
					    		 <a href="clientQueryById_BookAction?book.id=${ recommendBook.id }" target="_blank"><img src="${ recommendBook.picture }" /></a>
					    	</s:iterator>
					    </div>
					    <div class="titleBox">
					    	<p class="active"> ${ recommendBooks[0].name } </p>
					    	<s:iterator value="recommendBooks" var="recommendBook" begin="1">
					    		<p> ${ recommendBook.name } </p>
					    	</s:iterator>
					    </div>
					    <div class="icoBox">
					        <span class="active" rel="1">1</span>
					        <span rel="2">2</span>
					        <span rel="3">3</span>
					        <span rel="4">4</span>
					        <span rel="5">5</span>
					    </div>
					</div>
       			
       </div>
<!--slide end-->

                
                <div id="bulletin" class="m" clstag="firstc|keycount|book|zixun">
                    <div class="mt">
                        <h2 style="color: #B99869">图书资讯</h2>
                    </div>
                    <div class="mc">
                        <ul>
                        	<!-- 图书咨询begin -->
							<c:forEach var="entry" items = "${ bookInformation }">
									<li>&#183;<a href="clientQueryById_BookAction?book.id=${ entry.key }" title="${ entry.value }" target="_blank">${ entry.value }</a></li>
			            	</c:forEach>
							<!-- 图书咨询end -->
						</ul>
                    </div>
                </div>
                <!--bulletin end-->
                
                
                
                <div class="clr"></div>
                <div id="focus" clstag="firstc|keycount|book|yizhongzhumu">
                    <div class="mt">
                    	<h2 style="color: #B99869">新书快递</h2>
                    	<div class="extra" style="margin-right: 20px; margin-top: 5px"><a href="newBooks_BookAction" target="_blank">更多&gt;&gt;</a></div>
                    </div>
                    <div class="mc">
                    <!-- 新书推荐 -->
                        <ul class="spec-list">
	                        <s:iterator var="newBook" value="newBooks">
	                            <li class="current" >
	                        		<a href="clientQueryById_BookAction?book.id=${ newBook.id }" title="${ newBook.name }"  target="_blank">
	                        			<img src="${ newBook.picture }" alt="${ newBook.name }" width="130" height="130">
	                        		</a>
	                        	</li>
	                        	
	                        </s:iterator>
                        </ul>
                      <!-- 新书推荐 -->                    
                       <div class="spec-info recom">
						<div class="p-img">
							<a href="clientQueryById_BookAction?book.id=${ randomNewBook.id }" title="${ randomNewBook.name }" target="_blank">
								<img src="${ randomNewBook.picture }" alt="${ randomNewBook.name }" width="130" height="130">
							</a>
						</div>
						<div class="p-name"><a href="clientQueryById_BookAction?book.id=${ randomNewBook.id }" target="_blank">${ randomNewBook.name }</a></div>
						<div class="p-market">定价：￥${ randomNewBook.price }</div>
						<div class="p-price">汇书价：<strong>￥$ <fmt:formatNumber type="number" value="${ randomNewBook.hbPrice }" maxFractionDigits="1"/></strong>(${ randomNewBook.discount }折)</div>
						<div class="p-info">${ randomNewBook.TDescription.content }</div>
                       </div>
                      </div>
                                          
                </div>
                <!--focus end-->
                
                
		<div id="booking" class="m" clstag="firstc|keycount|book|yushou">
			<div class="mt">
				<h2 style="color: #B99869">套装典藏</h2>
				<div class="extra">
				</div>
         	</div>
         	<div class="mc">
         		<dl>
					<dt class="p-img bookimg">
						<div class="i-img">
							<a title="${ bookSuit[0].name }" target="_blank" href="clientQueryById_BookAction?book.id=${ bookSuit[0].id }">
								<img width="100" height="100" alt="${ bookSuit[0].name }" src="${ bookSuit[0].picture }">
							</a>
						</div>
					</dt>
					<dd>
						<div class="p-name">
							<a title="${ bookSuit[0].name }" target="_blank" href="clientQueryById_BookAction?book.id=${ bookSuit[0].id }">
								${ bookSuit[0].name }
							</a>
						</div>
						<div class="p-price">
							<del>￥${ bookSuit[0].price }</del><strong>￥${ bookSuit[0].hbPrice }</strong>
						</div>
						<div class="p-info">${ bookSuit[0].TDescription.content }</div>
						<div class="extra">
							<a title="${ bookSuit[0].name }" target="_blank" href="clientQueryById_BookAction?book.id=${ bookSuit[0].id }">了解&gt;&gt;</a>
						</div>
					</dd>
				</dl>
				
				<ul>
					<c:forEach var="booksuit" items="${ bookSuit }" begin="1">
						<li>&#183;<a href="clientQueryById_BookAction?book.id=${ booksuit.id }" title="${ booksuit.name }" target="_blank">${ booksuit.TDescription.content }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
        
        <!--booking end-->
        <div class="clr"></div>
        <div id="newbooks" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|xinshusudi">
        	<div class="mt">
            	<h2 style="color: #B99869">热门推荐</h2>
            	<div class="extra" style="margin-right: 20px; margin-top: 5px"><a href="hotBooks_BookAction" target="_blank">更多&gt;&gt;</a></div>
            </div>
            <div class="mc">
            	
				   <ul class="tabcon" data-widget="tab-content">
				   <s:iterator var="hotBook" value="hotBooks">
	                	<li>
		                	<div class="p-img bookimg">
		                		<div class="i-img">
		                			<a target="_blank" href="clientQueryById_BookAction?book.id=${ hotBook.id }" title="${ hotBook.name }">
		                				<img width="130" height="130" src="${ hotBook.picture }">
		                			</a>
		                		</div>
		                	</div>
	                		<div class="p-name">
	                			<a target="_blank" href="clientQueryById_BookAction?book.id=${ hotBook.id }">${ hotBook.name }</a>
	                		</div>
	                		<div class="p-market">
	                			<del>定价￥${ hotBook.price }</del>
	                		</div>
	                		<div class="p-price">
	                			<strong>汇书价￥$ <fmt:formatNumber type="number" value="${ hotBook.hbPrice }" maxFractionDigits="1"/></strong>
	                		</div>
	                	</li>
                	</s:iterator>
                </ul>         
			</div>
			
			<div class="newbooks-ads" style="margin-top: -40px">
            	<a href="newBooks_BookAction"> <img alt="" src="images/adv1.jpg" width="380" height="100" > </a>
            	<a href="highBooks_BookAction"> <img alt="" src="images/adv2.jpg" width="380" height="100" > </a>
            </div>
		</div>
		<!--newbooks end-->
		
		<div id="newbook-top" class="side-m" clstag="firstc|keycount|book|xinshubang">
			<div class="mt">
				<h3 style="color: #B99869">少儿馆</h3> <div class="extra"></div>
			</div>
			<div class="mc">
				<ul>
					<li class="fore1  curr">
               			<span>1</span>
						<div class="p-img">
							<a target="_blank" href="clientQueryById_BookAction?book.id=${ childrenBooks[0].id }">
                            	<img width="50" height="50" data-img="1" alt="${childrenBooks[0].name }" title="${childrenBooks[0].name }" src="${childrenBooks[0].picture }" />
                            </a>
                            </div>
						<div class="p-name"><a target="_blank" href="clientQueryById_BookAction?book.id=${ childrenBooks[0].id }">${childrenBooks[0].name }</a></div>
						<div class="p-market"><del>￥${ childrenBooks[0].price }</del> </div><div class="p-price"><strong>￥${ childrenBooks[0].hbPrice} </strong></div>
					</li>
					
					<c:forEach var="childrenBook" items="${ childrenBooks }" begin="1">
						<li class="fore  fore1">
	               			<span>2</span>
							<div class="p-img">
								<a target="_blank" href="clientQueryById_BookAction?book.id=${ childrenBook.id }">
	                            	<img width="50" height="50" data-img="1" alt="${ childrenBook.name }" title="${ childrenBook.name }" src="${ childrenBook.picture }" />
	                            </a>
	                            </div>
							<div class="p-name"><a target="_blank" href="clientQueryById_BookAction?book.id=${ childrenBook.id }">${ childrenBook.TDescription.content }</a></div>
							<div class="p-market"><del>￥${ childrenBook.price }</del></div><div class="p-price"><strong>￥${ childrenBook.hbPrice }</strong></div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		
		<div class="clr"></div>
		<div id="sort-recommend" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|fenleituijian">
			<div class="mt">
				<h2 style="color: #B99869">特价好书</h2>
				<div class="extra" style="margin-right: 20px; margin-top: 5px"><a href="lowBooks_BookAction" target="_blank">更多&gt;&gt;</a></div>
			</div>
				<div class="mc">
					<div class="clr"></div>
						<div class="tabcon" data-widget="tab-content">
						<div class="hot-total side-m" clstag="firstc|keycount|book|xiaoshoubang">
							<div class="smt">
								<h3 style="color: #B99869">考试专区</h3><div class="extra"></div>
							</div>
							<div class="smc">
								<ul>
									<li class="fore1  curr">
										<span>1</span>
										<div class="p-img">
											<a target="_blank" title="${ examBooks[0].name }" href="clientQueryById_BookAction?book.id=${ examBooks[0].id }">
												<img width="50" height="50" data-img="1" alt="${ examBooks[0].name }" src="${ examBooks[0].picture }" /></a>
										</div>
										<div class="p-name"><a target="_blank" href="clientQueryById_BookAction?book.id=${ examBooks[0].id }">${ examBooks[0].name }</a>
										</div>
										<div class="p-market"><del>￥${ examBooks[0].price }</del>
										</div>
										<div class="p-price"><strong>￥${ examBooks[0].hbPrice }</strong>
										</div>
									</li>
									
									<c:forEach var="examBook" items="${ examBooks }" begin="1">
										<li class="fore fore1 ">
											<span>2</span>
											<div class="p-img">
												<a target="_sblank" title="${examBook.name }" href="clientQueryById_BookAction?book.id=${ examBook.id }">
													<img width="50" height="50" data-img="1" alt="${examBook.name }" src="${examBook.picture }" /></a>
											</div>
											<div class="p-name"><a target="_blank" href="clientQueryById_BookAction?book.id=${ examBook.id }">${examBook.TDescription.content }</a>
											</div>
											<div class="p-market"><del>￥${ examBook.price }</del>
											</div>
											<div class="p-price"><strong>￥${ examBook.hbPrice }</strong>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
								
						<!-- 随机选取显示 -->						
						<div class="main-recom recom" clstag="firstc|keycount|book|shangpin1">
							<div class="p-img">
								<a href="clientQueryById_BookAction?book.id=${ randLowBook.id }" title="${ randLowBook.name }" target="_blank">
								<img alt="" src="${ randLowBook.picture  }" width="130" height="130"></a>
							</div>
							<div class="p-name"><a href="clientQueryById_BookAction?book.id=${ randLowBook.id }" target="_blank">${ randLowBook.name }</a>
							</div>
							<div class="extra"><span class="star"><span class="star-white">
								<span class="star-yellow h${randLowBook.grade} }">&nbsp;</span></span></span>
							</div>
							<div class="p-market">定价：￥${ randLowBook.price }</div>
							<div class="p-price">汇书价：<strong>￥ <fmt:formatNumber type="number" value="${randLowBook.hbPrice }" maxFractionDigits="1"/> </strong>(${ randLowBook.discount }折)</div>
							<div class="p-info" >${ randLowBook.TDescription.content  }</div>
						</div>
						
						<!-- 遍历低价书籍 -->
						<ul class="p-list" clstag="firstc|keycount|book|shangpin2">
							<s:iterator var="lowBook" value="lowBooks">
								<li>
										<div class="p-img bookimg">
											<div class="i-img">
												<a target="_blank" href="clientQueryById_BookAction?book.id=${ lowBook.id }" title="${ lowBook.name }">
													<img width="130" height="130" src="${ lowBook.picture }" width="130" height="130"></a>
											</div>
										</div>
										<div class="p-name">
											<a target="_blank" href="clientQueryById_BookAction?book.id=${ lowBook.id }">${ lowBook.name }</a>
										</div>
										<div class="p-market">
											<del>定价￥${ lowBook.price }</del>
										</div>
										<div class="p-price">
											<strong>汇书价￥  <fmt:formatNumber type="number" value=" ${ lowBook.hbPrice }" maxFractionDigits="1"/> </strong>
										</div>
								</li>
							</s:iterator>
						</ul>
					</div>
					
                </div>
                </div>
                <div>
                </div>
                <!--sort-recommend end-->
                <div class="clr"></div>
                <!--featurebook  start-->
		 <div id="featurebook" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|teseshudian">
     
		
		<!--featurebook  end-->
		<div class="clr"></div>
               
               
               
               
                <div id="children" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|teseguan">
                    <div class="mt">
                        <h2 style="color: #B99869">好评推荐</h2>
                        <div class="extra"><a href="highBooks_BookAction" target="_blank">更多&gt;&gt;</a></div>
                    </div>
                    <div class="mc"><div class="clr"></div>
            	<div class="tabcon " data-widget="tab-content">
            	<!-- 枚举显示高分好评书籍  --> 
				<ul>
					<s:iterator var="highBook" value="highBooks">
	            		<li>
							<div class="p-img bookimg">
								<div class="i-img">
									<a target="_blank" href="clientQueryById_BookAction?book.id=${ highBook.id }">
										<img width="130" height="130" alt="${ highBook.name }" src="${ highBook.picture }">
									</a>
								</div>
							</div>
							<div class="p-name">
								<a target="_blank" href="clientQueryById_BookAction?book.id=${ highBook.id }">${ highBook.name }
								</a>
							</div>
							<div class="p-market"><del>定价￥${ highBook.price } </del></div>
							<div class="p-price"><strong>汇书价￥  <fmt:formatNumber type="number" value="  ${ highBook.hbPrice }" maxFractionDigits="1"/>  </strong></div>
						</li>
					</s:iterator>	
				</ul>
				
				<div class="popular-tags side-m">
					<div class="smt"><h3>热门标签</h3></div>
					<div class="smc">
						<c:forEach var="entry" items = "${ hotTags }">
							<a href="queryByCategory_BookAction?queryBookInfo.type=${ entry.key }" title="${ entry.value }" target="_blank"><span>${ entry.value }</span></a>
	            		</c:forEach>
				   </div>
				</div>
			</div>
			
					</div>
	</div>
	
                <!---->
                
                <div id="special-books" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|tejiahaoshu">
                    <div class="mt">
                        <h2 style="color: #B99869">二手好书</h2>
                        <div class="extra"><a href="usedBooks_BookAction" target="_blank">更多&gt;&gt;</a></div>
                    </div>
                    <div class="mc">
                         <ul class="tabcon " data-widget="tab-content">
                          <s:iterator var="usedBook" value="usedBooks">
                        	 <li>
	                        	<div class="p-img bookimg">
	                            	<div class="i-img"><a target="_blank" href="clientQueryById_BookAction?book.id=${ usedBook.id }">
	                                	<img width="130" height="130" src="${ usedBook.picture }"></a>
	                                </div>
	                            </div>
	                            <div class="p-name"><a target="_blank" href="clientQueryById_BookAction?book.id=${ usedBook.id }">${ usedBook.name }</a></div>
	                            <div class="p-market"><del>定价￥${ usedBook.price }</del></div>
	                            <div class="p-price">
	                            	<strong>汇书价￥    <fmt:formatNumber type="number" value="${ usedBook.hbPrice }" maxFractionDigits="1"/>   
	                            	<s:property />
	                             </strong></div>
	                        	
	                        </li>
	                      </s:iterator>
						</ul>
                  	</div>
                </div>
                <!--special-books end-->
            </div>
        </div>
    </div>
    <div class="clr"></div>
</div>




	
	
	
	
	<!-- 主题内容结束 -->
	

	
	<!-- 引入底部标准 -->
	<jsp:include page="bottom.jsp"></jsp:include>    
  </body>
</html>






