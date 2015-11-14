<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


		<!-- 最上边的配置  开始 -->
		
		<div id="shortcut-2013">
			<script type="text/javascript" src="css/e.css" charset="gb2312"></script>
		<script type="text/javascript" src="css/f.css" charset="gb2312"></script>
			<div class="w">
				<ul class="fl lh">
					<li class="fore1 ld"><b></b><a href="javascript:addToFavorite()" rel="nofollow">收藏汇书</a></li>
				</ul>
				<ul class="fr lh">
					<li class="fore1" id="loginbar">您好！欢迎来到汇书网 ！
					 	<s:if test="#session.user != null">
					 		<a href="findById_UserAction?id=${ session.user.id } ">[${session.user.userName}] </a>&nbsp;<a href="logout_LoginAction">[退出]</a>&nbsp; <a href="view/changePassword.jsp">[修改密码]</a>
						</s:if>
						<s:else>
							<a href="login_UIAction">[登录]</a>&nbsp;<a href="register.jsp">[免费注册]</a>
						</s:else>
					</li>
					<li class="fore2 ld">
						<a href="clientQuery_OrderAction?queryOrderInfo.userId=${ session.user.id }&currentPage=1" >我的订单</a>
					</li>
					
					<li class="fore3 ld menu" id="app-jd" data-widget="dropdown" clstag="homepage|keycount|home2013|01d">
						<s></s><i></i><span class="outline"></span><span class="blank"></span>
						<a href="" target="_blank">手机汇书</a><b></b>
						<div class="dd lh">
							<div class="qr-code">
								<img id="app-qrcode-img" data-img="1" trigger-lazy-img="done" class="err-product" width="76" height="76" alt="" src=""><i></i>
								<div class="details"><em>扫描我，即刻下载</em><strong>京东客户端</strong></div>
							</div>
							<div class="app-btn">
								<a href="" target="_blank" class="btn-app-apple"></a>
								<a href="" target="_blank" class="btn-app-android"></a>
							</div>
						</div>
					</li>
					
					
					<li class="fore4 ld menu" id="biz-service" data-widget="dropdown">
						<s></s>
						<span class="outline"></span>
						<span class="blank"></span>
						客户服务
						<b></b>
						<div class="dd">
							<div><a href="" target="_blank">帮助中心</a></div>
							<div><a href="" target="_blank" rel="nofollow">售后服务</a></div>
							<div><a href="" target="_blank" rel="nofollow">在线客服</a></div>
							<div><a href="" target="_blank" rel="nofollow">投诉中心</a></div>
							<div><a href="" target="_blank">客服邮箱</a></div>
						</div>
					</li>
					
					<li class="fore5 ld menu" id="site-nav" data-widget="dropdown">
						<s></s>
						<span class="outline"></span>
						<span class="blank"></span>
						网站导航
						<b></b>
						<div class="dd lh">
							<dl class="item fore1">
								<dt>特色栏目</dt>
								<dd>
									<div><a target="_blank" href="">为我推荐</a></div>
									<div><a target="_blank" href="">视频购物</a></div>
									<div><a target="_blank" href="">京东社区</a></div>
									<div><a target="_blank" href="">校园频道</a></div>
									<div><a target="_blank" href="">在线读书</a></div>
									<div><a target="_blank" href="">装机大师</a></div>
									<div><a target="_blank" href="">京东卡</a></div>
									<div><a target="_blank" href="">家装城</a></div>
									<div><a target="_blank" href="">搭配购</a></div>
									<div><a target="_blank" href="">我喜欢</a></div>
								</dd>
							</dl>
							
						
						</div>
					</li>
				</ul>
				<span class="clr"></span>
			</div>
		</div><!--shortcut end--><!--shortcut end-->
	<!-- 最上边的配置  结束 -->
	
	<!-- 头的配置  开始-->
	
	<div id="o-header-2013">
		<div class="w" id="header-2013">
			<div id="logo-2013" class="ld"><a href="index_UIAction" hidefocus="true"><b></b><img src="images/hbookLogo.png" width="270" height="60" alt="汇书"></a></div>
			<!--logo end-->
			<div id="search-2013">
				<div class="i-search ld">
					<ul id="shelper" class="hide">
					</ul>
					<div class="form">
						<s:form action="search_BookAction" method="post">
							<input type="text" class="text" name="queryBookInfo.clientInputInfo" value="${ queryBookInfo.clientInputInfo }" >
							<input type="submit" value="搜索" class="button" >
						</s:form>
					</div>
				</div>
				<div id="advancesearch" style="width: 70px"><a href="<%=basePath%>view/book/advSearch.jsp" target="_blank">高级搜索</a></div>
				<div id="">热门搜索 :
					
					<c:forEach var="item" items="${ session.hotSearch }" varStatus="vs">
						<c:if test="${vs['index'] == 0}">
							 <a href="clientQueryById_BookAction?book.id=${ item.key }">${ item.value }</a>
						</c:if >
						<c:if test="${vs['index'] != 0}">
						　|  <a href="clientQueryById_BookAction?book.id=${ item.key }">${ item.value }</a>
						</c:if>
					</c:forEach>
					
				</div>
			</div>
			
			<!--search end-->
			<div id="my360buy-2013">
				<dl>
					<dt class="ld"><a href="queryUserOrderDetail_OrderDetailAction?queryOrderDetailInfo.userId=${user.id}" >我的汇书</a><b></b></dt>
					<dd>
						<div class="loading-style1"><b></b>加载中，请稍候...</div>
					</dd>
				</dl>
			</div>
			<!--my360buy end-->
			<div id="settleup-2013" >
				<dl>
					<dt class="ld"><span class="shopping"><span id="shopping-amount"></span></span><a href="gotoMyBuyCart_UIAction" id="settleup-url">去购物车结算</a> <b></b> </dt>
					<dd>
						<div class="prompt">
							<div class="loading-style1"><b></b>加载中，请稍候...</div>
						</div>
					</dd>
				</dl>
			</div>
			<!--settleup end-->
		</div>
	
	<!-- 头的配置  结束-->
	
	<div class="w">
		<div id="nav-2013">
			<div id="categorys-2013">
				<div class="mt ld">
					<h2><a href="getAllCategory_UIAction">全部书籍分类<b></b></a></h2>
				</div>
				<div id="_JD_ALLSORT" class="mc"></div>
			</div>
			<div id="treasure"></div>
			
		
			<ul id="navitems-2013" >
				<li class="fore1" id="nav-home" ><a href="index_UIAction" >首页</a></li>
				<li class="fore2" id="nav-fashion"><a href="newBooks_BookAction">新书热淘</a></li>
				<li class="fore3" id="nav-chaoshi"><a href="hotBooks_BookAction">热销供应</a></li>
				<li class="fore4" id="nav-tuan"><a href="lowBooks_BookAction" >低价市场</a></li>
				<li class="fore5" id="nav-auction"><a href="highBooks_BookAction">高评书籍</a></li>
				<li class="fore6" id="nav-shan"><a href="usedBooks_BookAction">二手市场</a></li>
			</ul>
		 </div>
		</div>
	</div>
	<!-- 头配置结束  -->
  
