package cn.hbook.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.hbook.bean.TOrder;
import cn.hbook.dao.IOrderDao;
import cn.hbook.form.QueryOrderInfo;

public class OrderDaoImpl extends HibernateDaoSupport implements IOrderDao {
	private int pageSize = 3;
	private int currentPage = 1;
	
	//保存订单
	public boolean save(TOrder order) throws Exception {
		this.getHibernateTemplate().merge(order);
		return true;
	}
	//删除订单
	public boolean delete(TOrder order) throws Exception {
		this.getHibernateTemplate().delete(order);
		return true;
	}
	//更新订单
	public boolean update(TOrder order) throws Exception {
		this.getHibernateTemplate().update(order);
		return true;
	}
	//通过用户ID查找订单信息
	@SuppressWarnings("unchecked")
	public List<TOrder> queryById(Integer userId) throws Exception {
		Query q = this.getSession().createQuery("  from TOrder o where o.TUser.id = ? order by o.orderDate desc  ");
		q.setParameter(0, userId);
		q.setFirstResult(0); q.setMaxResults(pageSize);
		List<TOrder> orders = q.list();
		return orders;
	}
	//查询所有订单
	@SuppressWarnings("unchecked")
	public List<TOrder> queryOrders() throws Exception {
		List<TOrder> orders = this.getHibernateTemplate().find(" from TOrder o order by o.orderDate desc ");
		return orders;
	}
	//按条件查询订单
	@SuppressWarnings("unchecked")
	public List<TOrder> query(QueryOrderInfo queryOrderInfo) throws Exception {
		//枚举查询条件
		final Integer orderId = queryOrderInfo.getOrderId();
		final Integer userId = queryOrderInfo.getUserId();
		final String userName = queryOrderInfo.getUserName();
		final Date beginDate = queryOrderInfo.getBeginDate();
		final Date endDate = queryOrderInfo.getEndDate();
		final Double beginPrice = queryOrderInfo.getBeginPrice();
		final Double endPrice = queryOrderInfo.getEndPrice();
		
		List<TOrder> orders = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
				//HQL查询语句
				StringBuilder hql = new StringBuilder("select o from TOrder o where 1 = 1 ");
				//记录查询参数
				List<Object> params = new ArrayList<Object>();
				//结果集
				List<TOrder> orders = new ArrayList<TOrder>();
				
				//枚举查询条件动态的拼接HQL语句
				if (orderId != null && orderId != 0) {
					hql.append(" and o.id = ? ");
					params.add(orderId);
				}
				if (userId != null && userId != 0) {
					hql.append(" and o.TUser.id = ? ");
					params.add(userId);
				}
				if (userName != null && !"".equals(userName.trim())) {
					hql.append(" and o.TUser.userName like ? ");
					params.add("%" + userName + "%");
				}
				if (beginDate != null) {
					hql.append(" and o.orderDate >= ? ");
					params.add(beginDate);
				}
				if (endDate != null) {
					hql.append(" and o.orderDate <= ? ");
					params.add(endDate);
				}
				if (beginPrice != null) {
					hql.append(" and o.priceTotal >= ? ");
					params.add(beginPrice);
				}
				if (endPrice != null) {
					hql.append(" and o.priceTotal <= ? ");
					params.add(endPrice);
				}
				//按时间排序显示最先的订单
				hql.append(" order by o.orderDate desc ");
				//设置查询参数
				Query  q = s.createQuery(hql.toString());
				for (int i = 0; i < params.size(); ++i) {
					q.setParameter(i, params.get(i));
				}
				// 设置页面
				q.setFirstResult((currentPage - 1) * pageSize);
				q.setMaxResults(pageSize);
				orders = q.list();
				return orders;
			}
		});
		return orders;
	}
	//按条件查询得到的总页数
	@SuppressWarnings("unchecked")
	public Integer getAllPage(QueryOrderInfo queryOrderInfo) throws Exception {
		//枚举查询条件
		final Integer orderId = queryOrderInfo.getOrderId();
		final Integer userId = queryOrderInfo.getUserId();
		final String userName = queryOrderInfo.getUserName();
		final Date beginDate = queryOrderInfo.getBeginDate();
		final Date endDate = queryOrderInfo.getEndDate();
		final Double beginPrice = queryOrderInfo.getBeginPrice();
		final Double endPrice = queryOrderInfo.getEndPrice();
		
		Integer allPage = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
				//HQL查询语句
				StringBuilder hql = new StringBuilder("select count(*) from TOrder o where 1 = 1 ");
				//记录查询参数
				List<Object> params = new ArrayList<Object>();
				
				//枚举查询条件动态的拼接HQL语句
				if (orderId != null && orderId != 0) {
					hql.append(" and o.id = ? ");
					params.add(orderId);
				}
				if (userId != null && userId != 0) {
					hql.append(" and o.TUser.id = ? ");
					params.add(userId);
				}
				if (userName != null && !"".equals(userName.trim())) {
					hql.append(" and o.TUser.userName like ? ");
					params.add("%" + userName + "%");
				}
				if (beginDate != null) {
					hql.append(" and o.orderDate >= ? ");
					params.add(beginDate);
				}
				if (endDate != null) {
					hql.append(" and o.orderDate <= ? ");
					params.add(endDate);
				}
				if (beginPrice != null) {
					hql.append(" and o.priceTotal >= ? ");
					params.add(beginPrice);
				}
				if (endPrice != null) {
					hql.append(" and o.priceTotal <= ? ");
					params.add(endPrice);
				}
				//按时间排序显示最先的订单
				hql.append(" order by o.orderDate desc ");
				//设置查询参数
				Query  q = s.createQuery(hql.toString());
				for (int i = 0; i < params.size(); ++i) {
					q.setParameter(i, params.get(i));
				}
				// 设置页面
				
				Integer allPage = (Integer.parseInt(q.uniqueResult().toString()) + pageSize - 1)/pageSize;
				return allPage;
			}
		});
		return allPage;
	}

	public void setCurrentPage(int currentPage) throws Exception {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	

}
