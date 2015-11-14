package cn.hbook.form;

import java.util.Date;

public class QueryOrderInfo {
	private Integer orderId;
	private Integer userId;
	private String userName;
	private Date beginDate;
	private Date endDate;
	private Double beginPrice;
	private Double endPrice;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getBeginPrice() {
		return beginPrice;
	}
	public void setBeginPrice(Double beginPrice) {
		this.beginPrice = beginPrice;
	}
	public Double getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "userName = " + userName + "beginPrice = " + beginPrice + "endPrice = " + endPrice; 
	}
	
	
}