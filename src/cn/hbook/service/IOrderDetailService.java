package cn.hbook.service;

import java.util.List;

import cn.hbook.bean.TOrderdetail;
import cn.hbook.form.QueryOrderDetailInfo;

public interface IOrderDetailService {
	//添加
	public boolean save(TOrderdetail orderdetail) throws Exception;
	//删除
	public boolean delete(TOrderdetail orderdetail) throws Exception;
	//修改
	public boolean update(TOrderdetail orderdetail) throws Exception;
	//查询所有
	public List<TOrderdetail> queryOrderDetails() throws Exception;
	//按条件查询
	public List<TOrderdetail> query(QueryOrderDetailInfo queryOrderDetailInfo) throws Exception;
	public Integer getAllPage(QueryOrderDetailInfo queryOrderDetailInfo) throws Exception;
	
	//分页查询 设置当前页
	public void setCurrentPage(int currentPage);
	//设置页面大小
	public void setPageSize(int pageSize);
}
