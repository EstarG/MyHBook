package cn.hbook.dao;

import java.util.List;

import cn.hbook.bean.TOrder;
import cn.hbook.form.QueryOrderInfo;

public interface IOrderDao {
	//保存订单
	public boolean save(TOrder order) throws Exception;
	//删除订单
	public boolean delete(TOrder order) throws Exception;
	//更新订单
	public boolean update(TOrder order) throws Exception;
	//通过用户ID查找
	public List<TOrder> queryById(Integer userId) throws Exception; 
	//查询所有订单
	public List<TOrder> queryOrders() throws Exception;
	//按条件查询订单
	public List<TOrder> query(QueryOrderInfo queryOrderInfo) throws Exception;
	//按条件查询的总页数
	public Integer getAllPage(QueryOrderInfo queryOrderInfo) throws Exception;
	//分页查询 设置当前页
	public void setCurrentPage(int currentPage) throws Exception;
	//设置页面大小
	public void setPageSize(int pageSize);
}
