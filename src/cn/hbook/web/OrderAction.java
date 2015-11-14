package cn.hbook.web;

import java.util.ArrayList;
import java.util.List;

import cn.hbook.bean.TOrder;
import cn.hbook.form.QueryOrderInfo;
import cn.hbook.service.IOrderService;

import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6117707700341671627L;
	
	private IOrderService orderService; //业务逻辑处理
	private QueryOrderInfo queryOrderInfo = new QueryOrderInfo(); ////收集前端form表单的提交的查询信息
	private List<TOrder> orders = new ArrayList<TOrder>(); //查询结果
	private Integer userId;
	
	
	
	private Integer currentPage;  //当前页
    private Integer allPage ; //总页数
    
    public String query() throws Exception {
    	
    	System.out.println("queryOrderInfo = : " + queryOrderInfo.toString());
    	//System.out.println(queryOrderInfo.getBeginDate() + "：　"+ queryOrderInfo.getEndDate());
    	String result = "";
    	//设置当前页
    	if (currentPage == null || currentPage == 0) {
    		currentPage = 1;
    	}
    	orderService.setCurrentPage(currentPage);
    	orderService.setPageSize(8);
    	//记录总页数
    	allPage = orderService.getAllPage(queryOrderInfo);
    	//记录查询结果
    	orders = orderService.query(queryOrderInfo);
    	result = "query";
    	return result;
    }
    
    //客户端查看我的订单
    public String clientQuery() throws Exception{
    	if (currentPage == null || currentPage == 0) {
    		currentPage = 1;
    	}
    	orderService.setCurrentPage(currentPage);
    	orderService.setPageSize(8);
    	allPage = orderService.getAllPage(queryOrderInfo);
    	
    	System.out.println("userId = " + queryOrderInfo.getUserId());
    	this.orders = orderService.query(queryOrderInfo);
    	return "clientQuery";
    }

    
    //setter getter
	public IOrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public QueryOrderInfo getQueryOrderInfo() {
		return queryOrderInfo;
	}
	public void setQueryOrderInfo(QueryOrderInfo queryOrderInfo) {
		this.queryOrderInfo = queryOrderInfo;
	}
	public List<TOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<TOrder> orders) {
		this.orders = orders;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getAllPage() {
		return allPage;
	}
	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
