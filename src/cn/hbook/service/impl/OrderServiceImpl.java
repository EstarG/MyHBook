package cn.hbook.service.impl;

import java.util.List;
import cn.hbook.bean.TOrder;
import cn.hbook.dao.IOrderDao;
import cn.hbook.form.QueryOrderInfo;
import cn.hbook.service.IOrderService;

public class OrderServiceImpl  implements IOrderService {
	
	private int pageSize = 3;
	private int currentPage = 1;
	private IOrderDao orderDao;
	//保存订单
	public boolean save(TOrder order) throws Exception {
		return orderDao.save(order);
	}
	//删除订单
	public boolean delete(TOrder order) throws Exception {
		return orderDao.delete(order);
	}
	//更新订单
	public boolean update(TOrder order) throws Exception {
		return orderDao.update(order);
	}
	public List<TOrder> queryById(Integer userId) throws Exception {
		return orderDao.queryById(userId);
	}
	//查询所有订单
	public List<TOrder> queryOrders() throws Exception {
		this.orderDao.setCurrentPage(currentPage);
		return orderDao.queryOrders();
	}
	//按条件查询订单
	public List<TOrder> query(QueryOrderInfo queryOrderInfo) throws Exception {
		this.orderDao.setCurrentPage(currentPage);
		this.orderDao.setPageSize(pageSize);
		return orderDao.query(queryOrderInfo);
	}
	//按条件查询的总页数
	public Integer getAllPage(QueryOrderInfo queryOrderInfo) throws Exception {
		this.orderDao.setCurrentPage(currentPage);
		this.orderDao.setPageSize(pageSize);
		return orderDao.getAllPage(queryOrderInfo);
	}
	//分页查询 设置当前页
	public void setCurrentPage(int currentPage) throws Exception {
		this.currentPage = currentPage;

	}
	//设置页面大小
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public IOrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
}
