package cn.hbook.web;

import java.util.ArrayList;
import java.util.List;

import cn.hbook.bean.TOrderdetail;
import cn.hbook.form.QueryOrderDetailInfo;
import cn.hbook.service.IOrderDetailService;

import com.opensymphony.xwork2.ActionSupport;

public class OrderDetailAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1677622151731403967L;
	
	private IOrderDetailService orderDetailService; //业务逻辑处理
	private List<TOrderdetail> orderDetails = new ArrayList<TOrderdetail>(); //记录查询结果
	private QueryOrderDetailInfo queryOrderDetailInfo = new QueryOrderDetailInfo();
	
    private Integer currentPage;  //当前页
    private Integer allPage ; //总页数
    
    
    public String query() throws Exception {
    	System.out.println("queryOrderDetailInfo : " + queryOrderDetailInfo.toString());
    	String result = "";
    	//设置当前页
    	if (currentPage == null || currentPage == 0) {
    		currentPage = 1;
    	}
    	this.orderDetailService.setCurrentPage(currentPage);
    	this.orderDetailService.setPageSize(10);
    	//得到总页数
    	this.allPage = this.orderDetailService.getAllPage(queryOrderDetailInfo);
    	//查询结果
    	this.orderDetails = this.orderDetailService.query(queryOrderDetailInfo);
    	result = "query";
    	return result;
    }
    
    public String queryUserOrderDetail() throws Exception {
    	System.out.println("userId = " + queryOrderDetailInfo.getUserId());
    	//设置当前页
    	if (currentPage == null || currentPage == 0) {
    		currentPage = 1;
    	}
    	this.orderDetailService.setCurrentPage(currentPage);
    	this.orderDetailService.setPageSize(8);
    	//得到总页数
    	this.allPage = this.orderDetailService.getAllPage(queryOrderDetailInfo);
    	//查询结果
    	this.orderDetails = this.orderDetailService.query(queryOrderDetailInfo);
    	return "queryUserOrderDetail";
    }
    
    //settter getter
    
    
	public IOrderDetailService getOrderDetailService() {
		return orderDetailService;
	}
	public void setOrderDetailService(IOrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}
	public List<TOrderdetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<TOrderdetail> orderDetails) {
		this.orderDetails = orderDetails;
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

	public QueryOrderDetailInfo getQueryOrderDetailInfo() {
		return queryOrderDetailInfo;
	}

	public void setQueryOrderDetailInfo(QueryOrderDetailInfo queryOrderDetailInfo) {
		this.queryOrderDetailInfo = queryOrderDetailInfo;
	}
	
}
