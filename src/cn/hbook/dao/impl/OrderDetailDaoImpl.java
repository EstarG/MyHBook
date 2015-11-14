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

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

import cn.hbook.bean.TOrderdetail;
import cn.hbook.dao.IOrderDetailDao;
import cn.hbook.form.QueryOrderDetailInfo;

public class OrderDetailDaoImpl extends HibernateDaoSupport implements IOrderDetailDao {

	private int pageSize = 3;
	private int currentPage = 1;
	
	//添加订单细节
	public boolean save(TOrderdetail orderdetail) throws Exception {
		this.getHibernateTemplate().persist(orderdetail);
		return true;
	}
	//删除订单细节
	public boolean delete(TOrderdetail orderdetail) throws Exception {
		this.getHibernateTemplate().delete(orderdetail);
		return true;
	}
    //更新订单细节
	public boolean update(TOrderdetail orderdetail) throws Exception {
		this.getHibernateTemplate().merge(orderdetail);
		return true;
	}
   //查询所有订单细节， 并按时间排序
	@SuppressWarnings("unchecked")
	public List<TOrderdetail> queryOrderDetails() throws Exception {
		List<TOrderdetail> orderdetails = this.getHibernateTemplate().find("from TOrderdetail od order by od.saleDate desc");
		return orderdetails;
	}

	@SuppressWarnings("unchecked")
	public List<TOrderdetail> query(QueryOrderDetailInfo queryOrderDetailInfo) throws Exception {
		//枚举查询条件
		final Integer userId = queryOrderDetailInfo.getUserId();
		final String userName = queryOrderDetailInfo.getUserName(); //用户名
		final String isbn = queryOrderDetailInfo.getIsbn(); // 书的ISBN
		final Integer orderId = queryOrderDetailInfo.getOrderId(); // 订单号
		final Date beginDate = queryOrderDetailInfo.getBeginDate(); //时间段
		final Date endDate = queryOrderDetailInfo.getEndDate();
		
		final Double beginPrice = queryOrderDetailInfo.getBeginPrice(); //价格段
		final Double endPrice = queryOrderDetailInfo.getEndPrice();
		
		final Integer beginNum = queryOrderDetailInfo.getBeginNum(); //数量段
		final Integer endNum = queryOrderDetailInfo.getEndNum();
		
		
		List<TOrderdetail> orderDetails = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
				
				//hql查询语句
				StringBuilder hql = new StringBuilder("select od from TOrderdetail od where 1 = 1");
				//存放查询参数
				List<Object> params = new ArrayList<Object>();
				//存放查询结果
				List<TOrderdetail> orderdetails = new ArrayList<TOrderdetail>();
				
				//枚举查询参数， 动态拼接hql语句
				if (userId != null && userId != 0) {
					hql.append(" and od.TUser.id = ? ");
					params.add(userId);
				}
				if (userName != null && !"".equals(userName.trim())) {
					hql.append(" and od.TUser.userName like ? ");
					params.add("%" + userName + "%");
				}
				if (isbn != null && !"".equals(isbn.trim())) {
					hql.append(" and od.TBook.isbn = ? ");
					params.add(isbn);
				}
				if (orderId != null && orderId != 0) {
					hql.append(" and od.TOrder.id = ? ");
					params.add(orderId);
				}
				if (beginDate != null) {
					hql.append(" and od.saleDate >= ? ");
					params.add(beginDate);
				}
				if (endDate != null) {
					hql.append(" and od.saleDate <= ? ");
					params.add(endDate);
				}
				if (beginPrice != null) {
					hql.append(" and od.price >= ? ");
					params.add(beginPrice);
				}
				if (endPrice != null) {
					hql.append(" and od.price <= ? ");
					params.add(endPrice);
				}
				if (beginNum != null) {
					hql.append("and od.num >= ? ");
					params.add(beginNum);
				}
				if (endNum != null) {
					hql.append(" and od.num <= ? ");
					params.add(endNum);
				}
				
				//按时间排序
				hql.append(" order by od.saleDate desc ");
				
				//得到Query设置查询参数
				Query  q = s.createQuery(hql.toString());
				for (int i = 0; i < params.size(); ++i) {
					q.setParameter(i, params.get(i));
				}	
				// 设置页面
				q.setFirstResult((currentPage - 1) * pageSize);
				q.setMaxResults(pageSize);
				orderdetails = q.list();
				return orderdetails;
			}
				
		});
		return orderDetails;
	}
	//得到按条件查询的总页数
	@SuppressWarnings("unchecked")
	public Integer getAllPage(QueryOrderDetailInfo queryOrderDetailInfo) throws Exception{
		//枚举查询条件
		final Integer userId = queryOrderDetailInfo.getUserId();
		final String userName = queryOrderDetailInfo.getUserName(); //用户名
		final String isbn = queryOrderDetailInfo.getIsbn(); // 书的ISBN
		final Integer orderId = queryOrderDetailInfo.getOrderId(); // 订单号
		final Date beginDate = queryOrderDetailInfo.getBeginDate(); //时间段
		final Date endDate = queryOrderDetailInfo.getEndDate();
		
		final Double beginPrice = queryOrderDetailInfo.getBeginPrice(); //价格段
		final Double endPrice = queryOrderDetailInfo.getEndPrice();
		
		final Integer beginNum = queryOrderDetailInfo.getBeginNum(); //数量段
		final Integer endNum = queryOrderDetailInfo.getEndNum();
		
		Integer allPage = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
				//hql查询语句
				StringBuilder hql = new StringBuilder("select count(*) from TOrderdetail od where 1 = 1");
				//存放查询参数
				List<Object> params = new ArrayList<Object>();
				//枚举查询参数， 动态拼接hql语句
				if (userId != null && userId != 0) {
					hql.append(" and od.TUser.id = ? ");
					params.add(userId);
				}
				if (userName != null && !"".equals(userName.trim())) {
					hql.append(" and od.TUser.userName like ? ");
					params.add("%" + userName + "%");
				}
				if (isbn != null && !"".equals(isbn.trim())) {
					hql.append(" and od.TBook.isbn = ? ");
					params.add(isbn);
				}
				if (orderId != null && orderId != 0) {
					hql.append(" and od.TOrder.id = ? ");
					params.add(orderId);
				}
				if (beginDate != null) {
					hql.append(" and od.saleDate >= ? ");
					params.add(beginDate);
				}
				if (endDate != null) {
					hql.append(" and od.saleDate <= ? ");
					params.add(endDate);
				}
				if (beginPrice != null) {
					hql.append(" and od.price >= ? ");
					params.add(beginPrice);
				}
				if (endPrice != null) {
					hql.append(" and od.price <= ? ");
					params.add(endPrice);
				}
				if (beginNum != null) {
					hql.append("and od.num >= ? ");
					params.add(beginNum);
				}
				if (endNum != null) {
					hql.append(" and od.num <= ? ");
					params.add(endNum);
				}
				//得到Query设置查询参数
				Query  q = s.createQuery(hql.toString());
				for (int i = 0; i < params.size(); ++i) {
					q.setParameter(i, params.get(i));
				}	
				Integer allPage = (Integer.parseInt(q.uniqueResult().toString()) + pageSize - 1) / pageSize;
				return allPage;
			}
		});
		return allPage;
	}

	public void setCurrentPage(int currentPage)  {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
