package cn.hbook.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.hbook.bean.TLog;
import cn.hbook.dao.ILogDao;
import cn.hbook.form.QueryLogInfo;

@Transactional
public class LogDaoImpl extends HibernateDaoSupport implements ILogDao {
	private int pageSize = 3;
	private int currentPage = 1;
	
	//记录日志
	public boolean save(TLog log) throws Exception {
		this.getHibernateTemplate().persist(log);
		return true;
	}
	//查询所有的日志
	@SuppressWarnings("unchecked")
	public List<TLog> queryAll() throws Exception {
		List<TLog> logs = this.getHibernateTemplate().find("from TLog");
		return logs;
	}
    //按条件查看日志信息
	@SuppressWarnings("unchecked")
	public List<TLog> query(QueryLogInfo queryLogInfo) throws Exception {
		// 枚举出查询条件
		final String userName = queryLogInfo.getUserName(); //按用户名查看日志 
		final String userIp = queryLogInfo.getUserIp(); //按IP查看日志
		final Date beginDate = queryLogInfo.getBeginDate(); // 	起止时间
		final Date endDate = queryLogInfo.getEndDate();
		final String info = queryLogInfo.getInfo(); // 按具体操作查询
		System.out.println("userName : " + userName);
		
		List<TLog> logs = this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
				//hql语句
				StringBuilder hql = new StringBuilder("select log from TLog log join log.TUser u where 1 = 1 ");
				//记录查询参数
				List<Object> params = new ArrayList<Object>();
				//记录查询出来的记录
				List<TLog> logs = new ArrayList<TLog>();
				
				if (userName != null && !"".equals(userName.trim())) {
					hql.append(" and u.userName like ?");
					params.add("%" + userName + "%");
				}
				if (userIp != null && !"".equals(userIp.trim())) {
					hql.append(" and log.userip = ? ");
					params.add(userIp);
				}
				if (info != null && !"".equals(info.trim())) {
					hql.append(" and log.info like ? ");
					params.add("%" + info + "%");
				}
				
				if (beginDate != null) {
					hql.append(" and log.logdate >= ? ");
					params.add(beginDate);
				}
				if (endDate != null) {
					hql.append(" and log.logdate <= ? ");
					params.add(endDate);
				}
				hql.append(" order by log.logdate desc ");
				//设置查询参数
				Query  q = s.createQuery(hql.toString());
				for (int i = 0; i < params.size(); ++i) {
					q.setParameter(i, params.get(i));
				}
				// 设置页面
				q.setFirstResult((currentPage - 1) * pageSize);
				q.setMaxResults(pageSize);
				logs = q.list();
				return logs;
			}
			
		});
		return logs;
	}

	//获得那条件查询时所得的总页数
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer getAllPage(QueryLogInfo queryLogInfo) throws Exception {
		// 枚举出查询条件
		final String userName = queryLogInfo.getUserName(); //按用户名查看日志 
		final String userIp = queryLogInfo.getUserIp(); //按IP查看日志
		final Date beginDate = queryLogInfo.getBeginDate(); // 	起止时间
		final Date endDate = queryLogInfo.getEndDate();
		final String info = queryLogInfo.getInfo(); // 按具体操作查询
		
		
		
		Integer allPage = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						StringBuilder hql = new StringBuilder("select count(*) from TLog log join log.TUser u where 1 = 1 ");
						List<Object> params = new ArrayList<Object>();
						
						if (userName != null && !"".equals(userName.trim())) {
							hql.append(" and u.userName like ? ");
							params.add("%" + userName + "%");
						}
						if (userIp != null && !"".equals(userIp.trim())) {
							hql.append(" and log.userip = ? ");
							params.add(userIp);
						}
						if (info != null && !"".equals(info.trim())) {
							hql.append(" and log.info like ? ");
							params.add("%" + info + "%");
						}
						
						if (beginDate != null) {
							hql.append(" and log.logdate >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and log.logdate <= ? ");
							params.add(endDate);
						}
						//设置查询参数
						Query  q = s.createQuery(hql.toString());
						for (int i = 0; i < params.size(); ++i) {
							q.setParameter(i, params.get(i));
						}	
						Integer allPage = (Integer.parseInt(q.uniqueResult().toString()) + pageSize - 1) / pageSize;
						return allPage;
					}
					
				}
		);
		return allPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	//测试我们在多段查询满足一端的一些信息时必须只能通过一段的主键才可以，也即多段依赖一端的那个键
	@SuppressWarnings("unchecked")
	public List<TLog> test() {
		Configuration cfg = new Configuration();
		cfg.configure();
		Session s = cfg.buildSessionFactory().openSession();
		StringBuilder hql = new StringBuilder(
				"from TLog log where log.TUser.id = ? ");
		Query q = s.createQuery(hql.toString());
		q.setInteger(0, 1);
		
		return q.list();
	}
	
	public static void main(String[] args) {
		
		List<TLog> logs = new LogDaoImpl().test();
		for (TLog log : logs) {
			System.out.println(log.getInfo() + " : " + log.getTUser().getRealName());
		}
	}
	
}
