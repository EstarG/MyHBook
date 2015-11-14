package cn.hbook.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
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

import cn.hbook.bean.TAuthor;
import cn.hbook.dao.IAuthorDao;

public class AuthorDaoImpl extends HibernateDaoSupport implements IAuthorDao {
	private int pageSize = 3;
	private int currentPage = 1;
	//添加作者信息
	public boolean save(TAuthor author) throws Exception {
		this.getHibernateTemplate().persist(author);
		return true;
	}
	//删除作者信息
	public boolean delete(TAuthor author) throws Exception {
		this.getHibernateTemplate().delete(author);
		return true;
	}
	//批量删除作者
	public boolean deleteAll(List<String> ids) throws Exception {
		Set<TAuthor> authors = new HashSet<TAuthor>();
		for (String id : ids) {
			authors.add(new TAuthor(Integer.parseInt(id.trim())));
		}
		this.getHibernateTemplate().deleteAll(authors);
		return true;
	}
	//修改作者信息
	public boolean update(TAuthor author) throws Exception {
		this.getHibernateTemplate().merge(author);
		return true;
	}
	//根据作者的ID查询作者信息
	public TAuthor queryById(Integer authorId) throws Exception {
		TAuthor author = this.getHibernateTemplate().get(TAuthor.class, authorId);
		return author;
	}
	//查询出所有作者的信息
	@SuppressWarnings("unchecked")
	public List<TAuthor> queryAuthors() throws Exception {
		List<TAuthor> authors = this.getHibernateTemplate().find("from TAuthor");
		return authors;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryAuthor(Integer id, String name)
			throws Exception {
		List<Map<String, Object>> authorsIdAndNname = new ArrayList<Map<String,Object>>();
		if (null != id && id != 0 && null != name && !"".equals(name)) {
			authorsIdAndNname = this.getHibernateTemplate().find("select new map(a.id as id, a.name as name) from TAuthor as a where a.id = ? and a.name like ? ", id, "%"+name+"%"); 
		} else if (null != id && id != 0) {
			authorsIdAndNname = this.getHibernateTemplate().find("select new map(a.id as id, a.name as name) from TAuthor as a where a.id = ? ", id); 
		} else if (null != name && !"".equals(name)) {
			authorsIdAndNname = this.getHibernateTemplate().find("select new map(a.id as id, a.name as name) from TAuthor as a where a.name like ? ", "%"+name+"%"); 
		}
		return authorsIdAndNname;
	}
	//根据条件查询作者
	@SuppressWarnings("unchecked")
	public List<TAuthor> query(TAuthor author) throws Exception {
		final Integer id = author.getId();
		final String name = author.getName();
		List<TAuthor> authors = (List<TAuthor>)this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
				//查询使用的HQL
				StringBuilder hql = new StringBuilder("from TAuthor where 1 = 1 ");
				//存储查询参数
				List<Object> params = new ArrayList<Object>();
				//枚举 查询条件， 动态的拼接HQL语句
				if (id != null && id != -0) {
					hql.append(" and id = ? ");
					params.add(id);
				}
				if (name != null && !"".equals(name.trim())) {
					hql.append(" and name like ? ");
					params.add("%" + name + "%");
				}
				Query q = s.createQuery(hql.toString());
				for (int i = 0; i < params.size(); ++i) {
					q.setParameter(i, params.get(i));
				}
				// 设置页面
				q.setFirstResult((currentPage - 1) * pageSize);
				q.setMaxResults(pageSize);
				return q.list();
			}
		});
		
		return authors;
	}
	//获得根据条件查询时的总页数
	@SuppressWarnings("unchecked")
	public Integer getAllPage(TAuthor author) throws Exception {
		//获得查询条件
		final Integer id = author.getId();
		final String name = author.getName();
		
		Integer allPage = (Integer)this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session s)
					throws HibernateException, SQLException {
				//查询使用的HQL
				StringBuilder hql = new StringBuilder("select count(*) from TAuthor where 1 = 1 ");
				//存储查询参数
				List<Object> params = new ArrayList<Object>();
				//枚举 查询条件， 动态的拼接HQL语句
				if (id != null && id != -0) {
					hql.append(" and id = ? ");
					params.add(id);
				}
				if (name != null && !"".equals(name.trim())) {
					hql.append(" and name like ? ");
					params.add("%" + name + "%");
				}
				Query q = s.createQuery(hql.toString());
				for (int i = 0; i < params.size(); ++i) {
					q.setParameter(i, params.get(i));
				}
				Integer allPage = (Integer.parseInt(q.uniqueResult().toString()) + pageSize - 1) / pageSize;
				return allPage;
			}
		});
		return allPage;
	}

	//分页设置
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
	
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		IAuthorDao authorDao = (IAuthorDao)ctx.getBean("authorDao");
		List<Map<String, Object>> idandName = authorDao.queryAuthor(11, "鲁");
		if (idandName == null) System.out.println(".....");
		for (Map<String, Object> mp : idandName) {
			System.out.println(mp.get("id") +"  " + mp.get("name"));
		}
	}
}
