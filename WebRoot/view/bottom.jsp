<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"   %>

		<!-- 显示最近浏览，以及推荐猜你喜欢 -->
	<div class="w" style="margin-top: 95px">
			<div id="product-track">
				<div class="right">
					<div id="maybe-like" class="m m2" data-widget="tabs">
						<div class="mt">
							<h2>根据浏览猜你喜欢</h2>
						</div>
						<div id="special-books" class="m floor-m" data-widget="tabs" clstag="firstc|keycount|book|tejiahaoshu">
						<div class="mc">
                         <ul class="tabcon " data-widget="tab-content">
	                          <c:forEach var="likeBook" items="${ session.likeBooks  }">
		                        	 <li>
			                        	<div class="p-img bookimg">
			                            	<div class="i-img"><a target="_blank" href="clientQueryById_BookAction?book.id=${ likeBook.id }">
			                                	<img width="130" height="130" src="${ likeBook.picture }"></a>
			                                </div>
			                            </div>
			                            <div class="p-name"><a target="_blank" href="clientQueryById_BookAction?book.id=${ likeBook.id }">${ likeBook.name }</a></div>
			                            <div class="p-market"><del>定价￥${ likeBook.price }</del></div>
			                            <div class="p-price">
			                            	<strong>汇书价￥    <fmt:formatNumber type="number" value="${ likeBook.hbPrice }" maxFractionDigits="2"/>   
			                            	<s:property />
			                             </strong></div>
			                        	
			                        </li>
		                      </c:forEach>
						</ul>
                  	</div>
					</div>
				</div> </div> <!--maybe-like-->
				<!-- right -->		
				<div class="left">
					<div id="recent-view-track" class="m m2">
						<div class="mt">
							<h2>最近浏览</h2>
						</div>
						<div class="mc" style="margin-top: 20px">
	                         <ul class="tabcon " data-widget="tab-content">
		                          <s:iterator var="lookedBook" value="%{#session.lookedBooks}">
		                        	 <li>
			                        	<div class="p-img bookimg">
			                            	<div class="i-img"><a target="_blank" href="clientQueryById_BookAction?book.id=${ lookedBook.id }">
			                                	<img alt="${ lookedBook.name }" width="60%" height="50%" src="${ lookedBook.picture }"></a>
			                                </div>
			                            </div>
			                            <div class="p-name"><a target="_blank" href="clientQueryById_BookAction?book.id=${ lookedBook.id }">${ lookedBook.name }</a></div>
			                        	 <div class="p-price">
			                            	<strong>汇书价￥    <fmt:formatNumber type="number" value="${ lookedBook.hbPrice }" maxFractionDigits="2"/>   
			                            	<s:property />
			                             </strong></div>
			                        </li>
			                      </s:iterator>
							</ul>
                  		</div>
						
							
						</div>
					</div><!--recent-view-track end-->
				</div><!-- left -->		
				<span class="clr"></span>		
			</div>
		</div>

	    <div class="w">
		<div id="service-2013">
			<dl class="fore1">
				<dt><b></b><strong>购物指南</strong></dt>
				<dd>
					<div><a href="" target="_blank" rel="nofollow">购物流程</a></div>
					<div><a href="" target="_blank" rel="nofollow">会员介绍</a></div>
					<div><a href="" target="_blank" rel="nofollow">团购/机票</a></div>
					<div><a href="" target="_blank" rel="nofollow">常见问题</a></div>
					<div><a href="" target="_blank" rel="nofollow">大家电</a></div>
					<div><a href="" target="_blank" rel="nofollow">联系客服</a></div>
				</dd>
			</dl>
			<dl class="fore2">		
				<dt><b></b><strong>配送方式</strong></dt>
				<dd>
					<div><a href="" target="_blank" rel="nofollow">上门自提</a></div>
					<div><a href="" target="_blank" rel="nofollow">211限时达</a></div>
					<div><a href="" target="_blank" rel="nofollow">配送服务查询</a></div>
					<div><a href="" target="_blank" rel="nofollow">配送费收取标准</a></div>
					<div><a href="" target="_blank" rel="nofollow">如何送礼</a></div>
					<div><a href="" target="_blank">Global Shipping</a></div>
				</dd>
			</dl>
			<dl class="fore3">
				<dt><b></b><strong>支付方式</strong></dt>
				<dd>
					<div><a href="" target="_blank" rel="nofollow">货到付款</a></div>
					<div><a href="" target="_blank" rel="nofollow">在线支付</a></div>
					<div><a href="" target="_blank" rel="nofollow">分期付款</a></div>
					<div><a href="" target="_blank" rel="nofollow">邮局汇款</a></div>
					<div><a href="" target="_blank" rel="nofollow">公司转账</a></div>
				</dd>
			</dl>
			<dl class="fore4">		
				<dt><b></b><strong>售后服务</strong></dt>
				<dd>
					<div><a href="" target="_blank" rel="nofollow">售后政策</a></div>
					<div><a href="" target="_blank" rel="nofollow">价格保护</a></div>
					<div><a href="" target="_blank" rel="nofollow">退款说明</a></div>
					<div><a href="" target="_blank" rel="nofollow">返修/退换货</a></div>
					<div><a href="" target="_blank" rel="nofollow">取消订单</a></div>
				</dd>
			</dl>
			<dl class="fore5">
				<dt><b></b><strong>特色服务</strong></dt>
				<dd>			
					<div><a href="" target="_blank">夺宝岛</a></div>
					<div><a href="" target="_blank">DIY装机</a></div>
					<div><a href="" target="_blank" rel="nofollow">延保服务</a></div>
					<div><a href="" target="_blank" rel="nofollow">汇书卡</a></div>
					<div><a href="" target="_blank" rel="nofollow">节能补贴</a></div>
				</dd>
			</dl>
			<span class="clr"></span>
		</div>
	</div>
	<!-- service end --><!-- service end -->
	
	
	<div class="w">
		<div id="footer-2013">
			<div class="links"><a href="" target="_blank" rel="nofollow">关于我们</a>|<a href="" target="_blank" rel="nofollow">联系我们</a>|<a href="" target="_blank" rel="nofollow">人才招聘</a>|<a href="" target="_blank" rel="nofollow">商家入驻</a>|<a href="http://sale.jd.com/act/y3surX7qpM.html" target="_blank" rel="nofollow">广告服务</a>|<a href="" target="_blank" rel="nofollow">手机汇书</a>|<a href="" target="_blank">友情链接</a>|<a href="" target="_blank">销售联盟</a>|<a target="_blank" href="">汇书社区</a>|<a target="_blank" href="">汇书公益</a></div>
			<div class="copyright">北京市公安局朝阳分局备案编号110105014669&nbsp;&nbsp;|&nbsp;&nbsp;京ICP证070359号&nbsp;&nbsp;|&nbsp;&nbsp;互联网药品信息服务资格证编号(京)-非经营性-2011-0034<br><a target="_blank" href="" rel="nofollow">音像制品经营许可证苏宿批005号</a>&nbsp;&nbsp;|&nbsp;&nbsp;出版物经营许可证编号新出发(苏)批字第N-012号&nbsp;&nbsp;|&nbsp;&nbsp;互联网出版许可证编号新出网证(京)字150号<br><a target="_blank" href="">网络文化经营许可证京网文[2011]0168-061号</a>&nbsp;&nbsp;Copyright&nbsp;&copy;&nbsp;2004-2014&nbsp;&nbsp;汇书&nbsp;版权所有<br />汇书旗下网站：<a href="" target="_blank">360TOP</a>&nbsp;&nbsp;<a href="" target="_blank">迷你挑</a>&nbsp;&nbsp;<a target="_blank" href="">English Site</a></div>
		</div>
	</div>
	<!-- footer end -->