package cn.hbook.service.impl;

import java.util.List;

import cn.hbook.bean.TOrderdetail;
import cn.hbook.dao.IOrderDetailDao;
import cn.hbook.form.QueryOrderDetailInfo;
import cn.hbook.service.IOrderDetailService;

public class OrderDetailServiceImpl implements IOrderDetailService {
	private int pageSize = 3;
	private int currentPage = 1;
	private IOrderDetailDao orderDetailDao;
	
	public boolean save(TOrderdetail orderdetail) throws Exception {
		return orderDetailDao.save(orderdetail);
	}

	public boolean delete(TOrderdetail orderdetail) throws Exception {
		return orderDetailDao.delete(orderdetail);
	}

	public boolean update(TOrderdetail orderdetail) throws Exception {
		return orderDetailDao.update(orderdetail);
	}

	public List<TOrderdetail> queryOrderDetails() throws Exception {
		this.orderDetailDao.setCurrentPage(currentPage);
		return orderDetailDao.queryOrderDetails();
	}

	public List<TOrderdetail> query(QueryOrderDetailInfo queryOrderDetailInfo)
			throws Exception {
		this.orderDetailDao.setCurrentPage(currentPage);
		this.orderDetailDao.setPageSize(pageSize);
		return orderDetailDao.query(queryOrderDetailInfo);
	}

	public Integer getAllPage(QueryOrderDetailInfo queryOrderDetailInfo) throws Exception{
		this.orderDetailDao.setCurrentPage(currentPage);
		this.orderDetailDao.setPageSize(pageSize);
		return orderDetailDao.getAllPage(queryOrderDetailInfo);
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public IOrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}

	public void setOrderDetailDao(IOrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	

}
