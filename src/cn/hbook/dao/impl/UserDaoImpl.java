package cn.hbook.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.hbook.bean.TBook;
import cn.hbook.bean.TUser;
import cn.hbook.dao.IBookDao;
import cn.hbook.dao.IUserDao;
import cn.hbook.form.QueryUserInfo;

@Transactional
public class UserDaoImpl extends HibernateDaoSupport implements IUserDao {
	private int pageSize = 3;
	private int currentPage = 1;
	
	//添加用户
	public boolean save(TUser user) throws Exception {
		this.getHibernateTemplate().save(user);
		return true;
	}
	//删除用户
	public boolean delete(TUser user) throws Exception {
		this.getHibernateTemplate().delete(user);
		return true;
	}
	//批量删除用户
	public boolean deleteAll(List<String> ids) throws Exception {
		Set<TUser> delUsers = new HashSet<TUser>();
		for (String id : ids) {
			//删除的必须是已经存在的
			if (queryById(Integer.parseInt(id)) != null) {
				delUsers.add(new TUser(Integer.parseInt(id.trim())));
			}
		}
		this.getHibernateTemplate().deleteAll(delUsers);
		return true;
	}
	//更新用户信息
	public boolean update(TUser user) throws Exception {
		this.getHibernateTemplate().merge(user);
		return true;
	}
	//根据ID查找用户
	@SuppressWarnings("unused")
	public TUser queryById(int userid) throws Exception {
		TUser user = this.getHibernateTemplate().get(TUser.class, userid);
		return user;
	}
	
	/**
	 * 根据用户名查找用户
	 * 
	 * @param uaerName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public TUser queryUserByName(String userName) throws Exception {
		System.out.println("userdao  userName = " + userName );
		List<TUser> users = (List<TUser>)this.getHibernateTemplate().find("from TUser u where u.userName = ? ", userName);
		if (null != users && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	//检查用户的合法性
	public TUser checkLegal(String userName, String password) throws Exception {
		List<TUser> users = this.queryUsers();
		TUser legalUser = null;  //代表合法用户
		for (TUser user : users) {
			if (user.getUserName().equals(userName.trim()) && user.getPassword().equals(password.trim())) {
				legalUser = user;
				break;
			}
		}
		return legalUser;
	}

	//获得查询结果的总页数
	@SuppressWarnings("unchecked")
	public int getAllPage(QueryUserInfo queryUserInfo) throws Exception {
		//得到所有查找条件拼接HQL得到总页数
		final String realName = queryUserInfo.getRealName();
		final String userName = queryUserInfo.getUserName();
		final String address = queryUserInfo.getAddress();
		final Integer type = queryUserInfo.getType();
		final String sex = queryUserInfo.getSex();
		final Date beginDate = queryUserInfo.getBeginDate();
		final Date endDate = queryUserInfo.getEndDate();
		
		
		Object allPage = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder("select count(*) from TUser where 1 = 1 ");
				//用来存放查询参数
				List<Object> params = new ArrayList<Object>();
				//动态的拼接hql的同时，记录查询参数
				if (realName != null && !"".equals(realName.trim())) {
					hql.append(" and realName like ? ");
					params.add("%" + realName + "%");
				}
				if (userName != null && !"".equals(userName.trim())) {
					hql.append(" and userName like ? ");
					params.add("%" + userName + "%");
				}
				if (address != null && !"".equals(address.trim()) ){
					hql.append(" and address like ? ");
					params.add("%" + address + "%");
				}
				if (type != null && type != 0) {
					hql.append(" and type = ? ");
					params.add(type);
				}
				if (sex != null && !"".equals(sex.trim())) {
					hql.append(" and sex = ? ");
					params.add(sex);
				}
				if (beginDate != null) {
					hql.append(" and birthday >= ? ");
					params.add(beginDate);
				}
				if (endDate != null) {
					hql.append(" and birthday <= ? ");
					params.add(endDate);
				}
				//利用session 获得Query
				Query q = s.createQuery(hql.toString());
				for (int i = 0; i < params.size(); ++i) {
					q.setParameter(i, params.get(i));
				}
				//计算总页数
				int allPage = (Integer.parseInt(q.uniqueResult().toString()) + pageSize - 1) / pageSize;
				return allPage;
			}
		});
		return (Integer) allPage;
	}

	//按条件查询用户信息 以Map的形式返回
	public List<Map<String, Object>> query(QueryUserInfo queryUserInfo) throws Exception {
		//得到所有查找条件拼接HQL得到总页数
		final String realName = queryUserInfo.getRealName();
		final String userName = queryUserInfo.getUserName();
		final String address = queryUserInfo.getAddress();
		final Integer type = queryUserInfo.getType();
		final String sex = queryUserInfo.getSex();
		final Date beginDate = queryUserInfo.getBeginDate();
		final Date endDate = queryUserInfo.getEndDate();
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> users = (ArrayList<Map<String, Object>>)this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						//存放查找到的用户
						List<Map<String, Object>> users = new ArrayList<Map<String,Object>>();
						//HQL语句
						StringBuilder hql = new StringBuilder("select new map(u.id as id, u.realName as realName, u.userName as userName, u.password as password," 
								     + "u.sex as sex, u.birthday as birthday, u.email as email, u.phone as phone, u.userQq as userQq, u.address as address, s.fvalue as type" +
								      ") from TUser u, TSyscode s where s.fkey = u.type ");
						//用来存放查询参数
						List<Object> params = new ArrayList<Object>();
						//动态的拼接hql的同时，记录查询参数
						if (realName != null && !"".equals(realName.trim())) {
							hql.append(" and realName like ? ");
							params.add("%" + realName + "%");
						}
						if (userName != null && !"".equals(userName.trim())) {
							hql.append(" and userName like ? ");
							params.add("%" + userName + "%");
						}
						if (address != null && !"".equals(address.trim()) ){
							hql.append(" and address like ? ");
							params.add("%" + address + "%");
						}
						if (type != null && type != 0) {
							hql.append(" and type = ? ");
							params.add(type);
						}
						if (sex != null && !"".equals(sex.trim())) {
							hql.append(" and sex = ? ");
							params.add(sex);
						}
						if (beginDate != null) {
							hql.append(" and birthday >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and birthday <= ? ");
							params.add(endDate);
						}
						//利用session 获得Query
						Query q = s.createQuery(hql.toString());
						for (int i = 0; i < params.size(); ++i) {
							System.out.println("参数 : " + params.get(i).toString());
							q.setParameter(i, params.get(i));
						}
						
						//设置分页的
						System.out.println("currentPage　ＤＡＯ" + currentPage);
						q.setFirstResult((currentPage - 1)*pageSize);
						q.setMaxResults(pageSize);
						
						users = q.list();
						System.out.println("查询到" + users.size() + "条数据" + "参数个数为" + params.size());
						return users;
					}
		});
		return users;
	}
	
	//查询所有用户信息
	@SuppressWarnings("unchecked")
	public List<TUser> queryUsers() throws Exception {
		List<TUser> users = this.getHibernateTemplate().find("from TUser");
		return users;
	}
	
	public void setCurrentPage(int currentPage) throws Exception {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("/MyHBook/WebRoot/WEB-INF/applicationContext.xml");
		IUserDao userDao = (IUserDao)ctx.getBean("userDao");
		TUser user = userDao.queryUserByName("zz");
		System.out.println(user.getUserName());*/
	}
}
