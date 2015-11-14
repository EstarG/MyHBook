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
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.hbook.bean.TBook;
import cn.hbook.dao.IBookDao;
import cn.hbook.form.QueryBookInfo;

@Transactional
public class BookDaoImpl extends HibernateDaoSupport implements IBookDao {
	private int pageSize = 8;
	private int currentPage = 1;
	
	//添加书籍
	public boolean save(TBook book) throws Exception {
		System.out.println("dao -  svae book");
		//this.getSession().clear();
		this.getHibernateTemplate().merge(book); //这样可以在添加书的时候同时更新他的作者
		return true;
	}
	//删除书籍
	public boolean delete(TBook book) throws Exception {
		this.getHibernateTemplate().delete(book);
		return true;
	}
	//批量删除
	public boolean deleteAll(List<String> ids) throws Exception {
		Set<TBook> delBooks = new HashSet<TBook>();
		for (String id : ids) {
			delBooks.add(new TBook(Integer.parseInt(id.trim())));
		}
		this.getHibernateTemplate().deleteAll(delBooks);
		return true;
	}
	//更新书籍信息
	public boolean update(TBook book) throws Exception {
		this.getHibernateTemplate().merge(book);
		return true;
	}
	//根据id查询数据
	public TBook queryById(Integer bookid) throws Exception {
		TBook book = this.getHibernateTemplate().get(TBook.class, bookid);
		return book;
	}
	//根据ISBN查找书籍
	public TBook queryBookByIsbn(String isbn) throws Exception {
		@SuppressWarnings("unchecked")
		List<TBook> books = this.getHibernateTemplate().find("from TBook b where b.isbn = ? ", isbn);
		if (null != books && books.size() > 0) {
			return books.get(0);
		} 
		return null;
	}
	
	
	
	//查询所有书籍
	@SuppressWarnings("unchecked")
	public List<TBook> queryBooks() throws Exception {
		List<TBook> books = this.getHibernateTemplate().find("from TBook");
		return books;
	}
	
	//根据类型查找书籍
	@SuppressWarnings("unchecked")
	public List<TBook> queryBooksByCategory(QueryBookInfo queryBookInfo) throws Exception {
		
		final Integer type = queryBookInfo.getType();
		//分段查询
		final Date beginDate = queryBookInfo.getBeginDate();   //出版时间段
		final Date endDate = queryBookInfo.getEndDate();
		
		final Double beginPrice = queryBookInfo.getBeginPrice(); //价格段查询
		final Double endPrice = queryBookInfo.getEndPrice();
		
		final Integer beginGrade = queryBookInfo.getBeginGrade(); // 按客户评分
		final Integer endGrade = queryBookInfo.getEndGrade();
		
		final Double beginDiscount = queryBookInfo.getBeginDiscount(); //按折扣查
		final Double endDiscount = queryBookInfo.getEndDiscount();
		
		final Integer beginStockNum = queryBookInfo.getBeginStockNum(); //库存量
		final Integer endStockNum = queryBookInfo.getEndStockNum();
		
		//排序条件
		final String orderByPrice = queryBookInfo.getOrderByPrice(); //价格排序
		final String orderByDate = queryBookInfo.getOrderByDate(); //时间排序 
		final String orderBySaleNum = queryBookInfo.getOrderBySaleNum(); //销量排序 
		final String orderByGrade = queryBookInfo.getOrderByGrade(); //按用户评分
		final String orderByDiscount = queryBookInfo.getOrderByDiscount(); //按折扣
		
		List<TBook> books = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						
						//HQL语句
		
						StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where 1 = 1 and (b.type = ? or b.ftype = ? ) ");
						
						// 查询结果
						List<TBook> books = new ArrayList<TBook>();
						//存放查询参数
						List<Object> params = new ArrayList<Object>();
						params.add(type); 
						params.add(type);  //首先将参数几放入
						//动态的拼接hql语句
						//时间段
						if (beginDate != null) {
							hql.append(" and b.publishDate >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and b.publishDate <= ? ");
							params.add(endDate);
						}
						//价格段
						if (beginPrice != null) {
							hql.append(" and b.hbPrice >= ? ");
							params.add(beginPrice);
						}
						if (endPrice != null) {
							hql.append(" and b.hbPrice <= ? ");
							params.add(endPrice);
						}
						//评分段
						if(beginGrade != null) {
							hql.append(" and b.grade >= ? ");
							params.add(beginGrade);
						}
						if (endGrade != null) {
							hql.append(" and b.grade <= ? ");
							params.add(endGrade);
						}
						
						//折扣段
						if (beginDiscount != null) {
							hql.append(" and b.discount >= ? ");
							params.add(beginDiscount);
						}
						if (endDiscount != null) {
							hql.append(" and b.discount <= ? ");
							params.add(endDiscount);
						}
						//库存量
						if (beginStockNum != null) {
							hql.append(" and b.stockNum >= ? ");
							params.add(beginStockNum);
						}
						if (endStockNum != null) {
							hql.append(" and b.stockNum <= ? ");
							params.add(endStockNum);
						}
						//是否已经有order
						boolean isOrder = false;
						//按价格排序
						if (orderByPrice != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByPrice.equals("1")) {
								hql.append(" b.hbPrice asc , ");
							} else {
								hql.append(" b.hbPrice desc , ");
							}
							isOrder = true;
						}
						//时间排序 
						if (orderByDate != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByDate.equals("1")) {
								hql.append(" b.publishDate asc , ");
							} else {
								hql.append(" b.publishDate desc , ");
							}
							isOrder = true;
						}
						//销量排序 
						if (orderBySaleNum != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderBySaleNum.equals("1")) {
								hql.append(" b.saleNum asc , ");
							} else {
								hql.append(" b.saleNum desc , ");
							}
							isOrder = true;
						}
						//按评分排序
						if (orderByGrade != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByGrade.equals("1")) {
								hql.append(" b.grade asc , ");
							} else {
								hql.append(" b.grade desc, ");
							}
							isOrder = true;
						}
						//按折扣查询
						if (orderByDiscount != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByDiscount.equals("1")) {
								hql.append(" b.discount asc , ");
							} else {
								hql.append(" b.discount desc , ");
							}
							isOrder = true;
						}
						//去掉最后边的","
						String result = "";
						if (isOrder == true) {
							result = hql.substring(0, hql.length() - 1);
						} else {
							result = hql.toString();
						}
						//设置查询参数
						System.out.println("Category dao hql = " + hql );
						Query  q = s.createQuery(result);
						for (int i = 0; i < params.size(); ++i) {
							q.setParameter(i, params.get(i));
							System.out.println("参数 " + i + " = " + params.get(i));
						}
						// 设置页面
						q.setFirstResult((currentPage - 1) * pageSize);
						q.setMaxResults(pageSize);
						books = q.list();
						return books;
					}
				}
			);
		
		return books;
	}
	
	
	//按时间排续 查出新书
	@SuppressWarnings("unchecked")
	public List<TBook> queryBooksOrderByDate(QueryBookInfo queryBookInfo) throws Exception{
		//没有目的性的查询不用枚举细节信息
		//分段查询
		final Date beginDate = queryBookInfo.getBeginDate();   //出版时间段
		final Date endDate = queryBookInfo.getEndDate();
		
		final Double beginPrice = queryBookInfo.getBeginPrice(); //价格段查询
		final Double endPrice = queryBookInfo.getEndPrice();
		
		final Integer beginGrade = queryBookInfo.getBeginGrade(); // 按客户评分
		final Integer endGrade = queryBookInfo.getEndGrade();
		
		final Double beginDiscount = queryBookInfo.getBeginDiscount(); //按折扣查
		final Double endDiscount = queryBookInfo.getEndDiscount();
		
		final Integer beginStockNum = queryBookInfo.getBeginStockNum(); //库存量
		final Integer endStockNum = queryBookInfo.getEndStockNum();
		
		//排序条件
		final String orderByPrice = queryBookInfo.getOrderByPrice(); //价格排序
		final String orderBySaleNum = queryBookInfo.getOrderBySaleNum(); //销量排序 
		final String orderByGrade = queryBookInfo.getOrderByGrade(); //按用户评分
		final String orderByDiscount = queryBookInfo.getOrderByDiscount(); //按折扣
		

		List<TBook> books = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						
						//HQL语句
		
						StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where b.stockNum > b.saleNum ");
						// 查询结果
						List<TBook> books = new ArrayList<TBook>();
						//存放查询参数
						List<Object> params = new ArrayList<Object>();
						
						//动态的拼接hql语句
						
						//时间段
						if (beginDate != null) {
							hql.append(" and b.publishDate >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and b.publishDate <= ? ");
							params.add(endDate);
						}
						//价格段
						if (beginPrice != null) {
							hql.append(" and b.hbPrice >= ? ");
							params.add(beginPrice);
						}
						if (endPrice != null) {
							hql.append(" and b.hbPrice <= ? ");
							params.add(endPrice);
						}
						//评分段
						if(beginGrade != null) {
							hql.append(" and b.grade >= ? ");
							params.add(beginGrade);
						}
						if (endGrade != null) {
							hql.append(" and b.grade <= ? ");
							params.add(endGrade);
						}
						
						//折扣段
						if (beginDiscount != null) {
							hql.append(" and b.discount >= ? ");
							params.add(beginDiscount);
						}
						if (endDiscount != null) {
							hql.append(" and b.discount <= ? ");
							params.add(endDiscount);
						}
						//库存量
						if (beginStockNum != null) {
							hql.append(" and b.stockNum >= ? ");
							params.add(beginStockNum);
						}
						if (endStockNum != null) {
							hql.append(" and b.stockNum <= ? ");
							params.add(endStockNum);
						}
						
						hql.append(" order by b.publishDate desc, ");
						//按价格排序
						if (orderByPrice != null) {
							if (orderByPrice.equals("1")) {
								hql.append(" b.hbPrice asc , ");
							} else {
								hql.append(" b.hbPrice desc , ");
							}
						}
						//销量排序 
						if (orderBySaleNum != null) {
							if (orderBySaleNum.equals("1")) {
								hql.append(" b.saleNum asc , ");
							} else {
								hql.append(" b.saleNum desc , ");
							}
						}
						//按评分排序
						if (orderByGrade != null) {
							if (orderByGrade.equals("1")) {
								hql.append(" b.grade asc , ");
							} else {
								hql.append(" b.grade desc, ");
							}
						}
						//按折扣查询
						if (orderByDiscount != null) {
							if (orderByDiscount.equals("1")) {
								hql.append(" b.discount asc , ");
							} else {
								hql.append(" b.discount desc , ");
							}
						}
						//去掉最后边的","
						String result = hql.substring(0, hql.length() - 2) ;
						//设置查询参数
						System.out.println("hql = "+ hql + "\n" + "result = " + result );
					
						Query  q = s.createQuery(result);
						for (int i = 0; i < params.size(); ++i) {
							q.setParameter(i, params.get(i));
							System.out.println("参数 I" + params.get(i));
						}
						// 设置页面
						q.setFirstResult((currentPage - 1) * pageSize);
						q.setMaxResults(pageSize);
						books = q.list();
						return books;
					}
				}
			);
		return books;
	}
	
	//按销量排续。 查热卖书籍
	@SuppressWarnings("unchecked")
	public List<TBook> queryBooksOrderBySaleNum(QueryBookInfo queryBookInfo) throws Exception{
		//按条件查询的条件
		
		//分段查询
		final Date beginDate = queryBookInfo.getBeginDate();   //出版时间段
		final Date endDate = queryBookInfo.getEndDate();
		
		final Double beginPrice = queryBookInfo.getBeginPrice(); //价格段查询
		final Double endPrice = queryBookInfo.getEndPrice();
		
		final Integer beginGrade = queryBookInfo.getBeginGrade(); // 按客户评分
		final Integer endGrade = queryBookInfo.getEndGrade();
		
		final Double beginDiscount = queryBookInfo.getBeginDiscount(); //按折扣查
		final Double endDiscount = queryBookInfo.getEndDiscount();
		
		final Integer beginStockNum = queryBookInfo.getBeginStockNum(); //库存量
		final Integer endStockNum = queryBookInfo.getEndStockNum();
		
		//排序条件
		final String orderByPrice = queryBookInfo.getOrderByPrice(); //价格排序
		final String orderByDate = queryBookInfo.getOrderByDate(); //时间排序 
		final String orderByGrade = queryBookInfo.getOrderByGrade(); //按用户评分
		final String orderByDiscount = queryBookInfo.getOrderByDiscount(); //按折扣
		
		
		
		
		List<TBook> books = this.getHibernateTemplate().execute(
				new HibernateCallback() {
		
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						
						//HQL语句
		
						StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where b.stockNum > b.saleNum ");
						// 查询结果
						List<TBook> books = new ArrayList<TBook>();
						//存放查询参数
						List<Object> params = new ArrayList<Object>();
						
						//动态的拼接hql语句
						
						//时间段
						if (beginDate != null) {
							hql.append(" and b.publishDate >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and b.publishDate <= ? ");
							params.add(endDate);
						}
						//价格段
						if (beginPrice != null) {
							hql.append(" and b.hbPrice >= ? ");
							params.add(beginPrice);
						}
						if (endPrice != null) {
							hql.append(" and b.hbPrice <= ? ");
							params.add(endPrice);
						}
						//评分段
						if(beginGrade != null) {
							hql.append(" and b.grade >= ? ");
							params.add(beginGrade);
						}
						if (endGrade != null) {
							hql.append(" and b.grade <= ? ");
							params.add(endGrade);
						}
						
						//折扣段
						if (beginDiscount != null) {
							hql.append(" and b.discount >= ? ");
							params.add(beginDiscount);
						}
						if (endDiscount != null) {
							hql.append(" and b.discount <= ? ");
							params.add(endDiscount);
						}
						//库存量
						if (beginStockNum != null) {
							hql.append(" and b.stockNum >= ? ");
							params.add(beginStockNum);
						}
						if (endStockNum != null) {
							hql.append(" and b.stockNum <= ? ");
							params.add(endStockNum);
						}
						
						hql.append(" order by b.saleNum desc, ");
						//按价格排序
						if (orderByPrice != null) {
							if (orderByPrice.equals("1")) {
								hql.append(" b.hbPrice asc , ");
							} else {
								hql.append(" b.hbPrice desc , ");
							}
						}
						//时间排序 
						if (orderByDate != null) {
							if (orderByDate.equals("1")) {
								hql.append(" b.publishDate asc , ");
							} else {
								hql.append(" b.publishDate desc , ");
							}
						}
						//按评分排序
						if (orderByGrade != null) {
							if (orderByGrade.equals("1")) {
								hql.append(" b.grade asc , ");
							} else {
								hql.append(" b.grade desc, ");
							}
						}
						//按折扣查询
						if (orderByDiscount != null) {
							if (orderByDiscount.equals("1")) {
								hql.append(" b.discount asc , ");
							} else {
								hql.append(" b.discount desc , ");
							}
						}
						//去掉最后边的","
						String result = hql.substring(0, hql.length() - 2);
						//设置查询参数
						System.out.println("hql = "+ hql + "\n" + "result = " + result );
					
						Query  q = s.createQuery(result);
						for (int i = 0; i < params.size(); ++i) {
							q.setParameter(i, params.get(i));
							System.out.println("参数 I" + params.get(i));
						}
						// 设置页面
						q.setFirstResult((currentPage - 1) * pageSize);
						q.setMaxResults(pageSize);
						books = q.list();
						return books;
					}
				}
			);
		
		return books;
	}
	
	//按价格排续., 查低价书籍
	@SuppressWarnings("unchecked")
	public List<TBook> queryBooksOrderByPrice(QueryBookInfo queryBookInfo) throws Exception{
		//按条件查询的条件
		
		//分段查询
		final Date beginDate = queryBookInfo.getBeginDate();   //出版时间段
		final Date endDate = queryBookInfo.getEndDate();
		
		final Double beginPrice = queryBookInfo.getBeginPrice(); //价格段查询
		final Double endPrice = queryBookInfo.getEndPrice();
		
		final Integer beginGrade = queryBookInfo.getBeginGrade(); // 按客户评分
		final Integer endGrade = queryBookInfo.getEndGrade();
		
		final Double beginDiscount = queryBookInfo.getBeginDiscount(); //按折扣查
		final Double endDiscount = queryBookInfo.getEndDiscount();
		
		final Integer beginStockNum = queryBookInfo.getBeginStockNum(); //库存量
		final Integer endStockNum = queryBookInfo.getEndStockNum();
		
		//排序条件
		final String orderByDate = queryBookInfo.getOrderByDate(); //时间排序 
		final String orderBySaleNum = queryBookInfo.getOrderBySaleNum(); //销量排序 
		final String orderByGrade = queryBookInfo.getOrderByGrade(); //按用户评分
		final String orderByDiscount = queryBookInfo.getOrderByDiscount(); //按折扣
		
		
		
		
		List<TBook> books = this.getHibernateTemplate().execute(
				new HibernateCallback() {
		
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						
						//HQL语句
		
						StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where b.stockNum > b.saleNum ");
						// 查询结果
						List<TBook> books = new ArrayList<TBook>();
						//存放查询参数
						List<Object> params = new ArrayList<Object>();
						
						//动态的拼接hql语句
						
						//时间段
						if (beginDate != null) {
							hql.append(" and b.publishDate >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and b.publishDate <= ? ");
							params.add(endDate);
						}
						//价格段
						if (beginPrice != null) {
							hql.append(" and b.hbPrice >= ? ");
							params.add(beginPrice);
						}
						if (endPrice != null) {
							hql.append(" and b.hbPrice <= ? ");
							params.add(endPrice);
						}
						//评分段
						if(beginGrade != null) {
							hql.append(" and b.grade >= ? ");
							params.add(beginGrade);
						}
						if (endGrade != null) {
							hql.append(" and b.grade <= ? ");
							params.add(endGrade);
						}
						
						//折扣段
						if (beginDiscount != null) {
							hql.append(" and b.discount >= ? ");
							params.add(beginDiscount);
						}
						if (endDiscount != null) {
							hql.append(" and b.discount <= ? ");
							params.add(endDiscount);
						}
						//库存量
						if (beginStockNum != null) {
							hql.append(" and b.stockNum >= ? ");
							params.add(beginStockNum);
						}
						if (endStockNum != null) {
							hql.append(" and b.stockNum <= ? ");
							params.add(endStockNum);
						}
						
						hql.append(" order by b.hbPrice asc, ");
						//时间排序 
						if (orderByDate != null) {
							if (orderByDate.equals("1")) {
								hql.append(" b.publishDate asc , ");
							} else {
								hql.append(" b.publishDate desc , ");
							}
						}
						//销量排序 
						if (orderBySaleNum != null) {
							if (orderBySaleNum.equals("1")) {
								hql.append(" b.saleNum asc , ");
							} else {
								hql.append(" b.saleNum desc , ");
							}
						}
						//按评分排序
						if (orderByGrade != null) {
							if (orderByGrade.equals("1")) {
								hql.append(" b.grade asc , ");
							} else {
								hql.append(" b.grade desc, ");
							}
						}
						//按折扣查询
						if (orderByDiscount != null) {
							if (orderByDiscount.equals("1")) {
								hql.append(" b.discount asc , ");
							} else {
								hql.append(" b.discount desc , ");
							}
						}
						
						//去掉最后边的","
						String result = hql.substring(0, hql.length() - 2);
						//设置查询参数
						System.out.println("hql = "+ hql + "\n" + "result = " + result );
					
						Query  q = s.createQuery(result);
						for (int i = 0; i < params.size(); ++i) {
							q.setParameter(i, params.get(i));
							System.out.println("参数 I" + params.get(i));
						}
						// 设置页面
						q.setFirstResult((currentPage - 1) * pageSize);
						q.setMaxResults(pageSize);
						books = q.list();
						return books;
					}
				}
			);
		
		return books;
	}
	
	//按评分排续., 查高分书籍
	@SuppressWarnings("unchecked")
	public List<TBook> queryBooksOrderByGrade(QueryBookInfo queryBookInfo) throws Exception{
		//按条件查询的条件
		
		//分段查询
		final Date beginDate = queryBookInfo.getBeginDate();   //出版时间段
		final Date endDate = queryBookInfo.getEndDate();
		
		final Double beginPrice = queryBookInfo.getBeginPrice(); //价格段查询
		final Double endPrice = queryBookInfo.getEndPrice();
		
		final Integer beginGrade = queryBookInfo.getBeginGrade(); // 按客户评分
		final Integer endGrade = queryBookInfo.getEndGrade();
		
		final Double beginDiscount = queryBookInfo.getBeginDiscount(); //按折扣查
		final Double endDiscount = queryBookInfo.getEndDiscount();
		
		final Integer beginStockNum = queryBookInfo.getBeginStockNum(); //库存量
		final Integer endStockNum = queryBookInfo.getEndStockNum();
		
		//排序条件
		final String orderByPrice = queryBookInfo.getOrderByPrice(); //价格排序
		final String orderByDate = queryBookInfo.getOrderByDate(); //时间排序 
		final String orderBySaleNum = queryBookInfo.getOrderBySaleNum(); //销量排序 
		final String orderByDiscount = queryBookInfo.getOrderByDiscount(); //按折扣
		
		List<TBook> books = this.getHibernateTemplate().execute(
				new HibernateCallback() {
		
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						
						//HQL语句
		
						StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where b.stockNum > b.saleNum ");
						// 查询结果
						List<TBook> books = new ArrayList<TBook>();
						//存放查询参数
						List<Object> params = new ArrayList<Object>();
						
						//动态的拼接hql语句
						
						//时间段
						if (beginDate != null) {
							hql.append(" and b.publishDate >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and b.publishDate <= ? ");
							params.add(endDate);
						}
						//价格段
						if (beginPrice != null) {
							hql.append(" and b.hbPrice >= ? ");
							params.add(beginPrice);
						}
						if (endPrice != null) {
							hql.append(" and b.hbPrice <= ? ");
							params.add(endPrice);
						}
						//评分段
						if(beginGrade != null) {
							hql.append(" and b.grade >= ? ");
							params.add(beginGrade);
						}
						if (endGrade != null) {
							hql.append(" and b.grade <= ? ");
							params.add(endGrade);
						}
						
						//折扣段
						if (beginDiscount != null) {
							hql.append(" and b.discount >= ? ");
							params.add(beginDiscount);
						}
						if (endDiscount != null) {
							hql.append(" and b.discount <= ? ");
							params.add(endDiscount);
						}
						//库存量
						if (beginStockNum != null) {
							hql.append(" and b.stockNum >= ? ");
							params.add(beginStockNum);
						}
						if (endStockNum != null) {
							hql.append(" and b.stockNum <= ? ");
							params.add(endStockNum);
						}
						
						hql.append(" order by b.grade desc, ");
						//时间排序 
						if (orderByDate != null) {
							if (orderByDate.equals("1")) {
								hql.append(" b.publishDate asc , ");
							} else {
								hql.append(" b.publishDate desc , ");
							}
						}
						//销量排序 
						if (orderBySaleNum != null) {
							if (orderBySaleNum.equals("1")) {
								hql.append(" b.saleNum asc , ");
							} else {
								hql.append(" b.saleNum desc , ");
							}
						}
						
						
						//按价格排序
						if (orderByPrice != null) {
							if (orderByPrice.equals("1")) {
								hql.append(" b.hbPrice asc , ");
							} else {
								hql.append(" b.hbPrice desc , ");
							}
						}
						//时间排序 
						if (orderByDate != null) {
							if (orderByDate.equals("1")) {
								hql.append(" b.publishDate asc , ");
							} else {
								hql.append(" b.publishDate desc , ");
							}
						}
						//销量排序 
						if (orderBySaleNum != null) {
							if (orderBySaleNum.equals("1")) {
								hql.append(" b.saleNum asc , ");
							} else {
								hql.append(" b.saleNum desc , ");
							}
						}
						//按折扣查询
						if (orderByDiscount != null) {
							if (orderByDiscount.equals("1")) {
								hql.append(" b.discount asc , ");
							} else {
								hql.append(" b.discount desc , ");
							}
						}
						
						//去掉最后边的","
						String result = hql.substring(0, hql.length() - 2);
						//设置查询参数
						System.out.println("hql = "+ hql + "\n" + "result = " + result );
					
						Query  q = s.createQuery(result);
						for (int i = 0; i < params.size(); ++i) {
							q.setParameter(i, params.get(i));
							System.out.println("参数 I" + params.get(i));
						}
						// 设置页面
						q.setFirstResult((currentPage - 1) * pageSize);
						q.setMaxResults(pageSize);
						books = q.list();
						return books;
					}
				}
			);
		
		return books;
		
	}
	
	//查二手书
	@SuppressWarnings("unchecked")
	public List<TBook> queryBooksOrderByUsed(QueryBookInfo queryBookInfo) throws Exception{
		//按条件查询的条件
		//基本查询条件
		final String name = queryBookInfo.getName(); //作者姓名
		final String isbn = queryBookInfo.getIsbn(); ////ISBN
		final String authorName = queryBookInfo.getAuthorName(); //书名
		final String publisher = queryBookInfo.getPublisher(); //出版社
		final Integer type = queryBookInfo.getType(); //类型
		final Integer used = queryBookInfo.getUsed();  //是否二手
		
		//分段查询
		final Date beginDate = queryBookInfo.getBeginDate();   //出版时间段
		final Date endDate = queryBookInfo.getEndDate();
		
		final Double beginPrice = queryBookInfo.getBeginPrice(); //价格段查询
		final Double endPrice = queryBookInfo.getEndPrice();
		
		final Integer beginGrade = queryBookInfo.getBeginGrade(); // 按客户评分
		final Integer endGrade = queryBookInfo.getEndGrade();
		
		final Double beginDiscount = queryBookInfo.getBeginDiscount(); //按折扣查
		final Double endDiscount = queryBookInfo.getEndDiscount();
		
		final Integer beginStockNum = queryBookInfo.getBeginStockNum(); //库存量
		final Integer endStockNum = queryBookInfo.getEndStockNum();
		
		//排序条件
		final String orderByPrice = queryBookInfo.getOrderByPrice(); //价格排序
		final String orderByDate = queryBookInfo.getOrderByDate(); //时间排序 
		final String orderBySaleNum = queryBookInfo.getOrderBySaleNum(); //销量排序 
		final String orderByGrade = queryBookInfo.getOrderByGrade(); //按用户评分
		final String orderByDiscount = queryBookInfo.getOrderByDiscount(); //按折扣
		
		
		
		
		List<TBook> books = this.getHibernateTemplate().execute(
				new HibernateCallback() {
		
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						
						//HQL语句
		
						StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where b.stockNum > b.saleNum and b.used = 1 ");
						// 查询结果
						List<TBook> books = new ArrayList<TBook>();
						//存放查询参数
						List<Object> params = new ArrayList<Object>();
						
						//动态的拼接hql语句
						//作者姓名
						if (name != null && !"".equals(name.trim())) {
							hql.append(" and b.name like ? ");
							params.add("%" + name + "%");
						}
						//ISBN
						if (isbn != null && !"".equals(isbn.trim())) {
							hql.append(" and b.isbn = ? ");
							params.add(isbn);
						}
						//作者姓名
						if (authorName != null && !"".equals(authorName.trim())) {
							hql.append(" and a.name like ? ");
							params.add("%" + authorName + "%");
						}
						//出版社
						if (publisher != null && !"".equals(publisher.trim())) {
							hql.append(" and b.publisher like ? ");
							params.add("%" + publisher + "%");
						}
						//类型
						if (type != null && type != 0) {
							hql.append(" and b.type = ? ");
							params.add(type);
						}
						
						
						//时间段
						if (beginDate != null) {
							hql.append(" and b.publishDate >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and b.publishDate <= ? ");
							params.add(endDate);
						}
						//价格段
						if (beginPrice != null) {
							hql.append(" and b.hbPrice >= ? ");
							params.add(beginPrice);
						}
						if (endPrice != null) {
							hql.append(" and b.hbPrice <= ? ");
							params.add(endPrice);
						}
						//评分段
						if(beginGrade != null) {
							hql.append(" and b.grade >= ? ");
							params.add(beginGrade);
						}
						if (endGrade != null) {
							hql.append(" and b.grade <= ? ");
							params.add(endGrade);
						}
						
						//折扣段
						if (beginDiscount != null) {
							hql.append(" and b.discount >= ? ");
							params.add(beginDiscount);
						}
						if (endDiscount != null) {
							hql.append(" and b.discount <= ? ");
							params.add(endDiscount);
						}
						//库存量
						if (beginStockNum != null) {
							hql.append(" and b.stockNum >= ? ");
							params.add(beginStockNum);
						}
						if (endStockNum != null) {
							hql.append(" and b.stockNum <= ? ");
							params.add(endStockNum);
						}
						//按评分排序
						hql.append(" order by b.grade desc ");
						
						//是否已经有order
						boolean isOrder = false;
						//按价格排序
						if (orderByPrice != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByPrice.equals("1")) {
								hql.append(" b.hbPrice asc , ");
							} else {
								hql.append(" b.hbPrice desc , ");
							}
							isOrder = true;
						}
						//时间排序 
						if (orderByDate != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByDate.equals("1")) {
								hql.append(" b.publishDate asc , ");
							} else {
								hql.append(" b.publishDate desc , ");
							}
							isOrder = true;
						}
						//销量排序 
						if (orderBySaleNum != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderBySaleNum.equals("1")) {
								hql.append(" b.saleNum asc , ");
							} else {
								hql.append(" b.saleNum desc , ");
							}
							isOrder = true;
						}
						//按评分排序
						if (orderByGrade != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByGrade.equals("1")) {
								hql.append(" b.grade asc , ");
							} else {
								hql.append(" b.grade desc, ");
							}
							isOrder = true;
						}
						//按折扣查询
						if (orderByDiscount != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByDiscount.equals("1")) {
								hql.append(" b.discount asc , ");
							} else {
								hql.append(" b.discount desc , ");
							}
							isOrder = true;
						}
						
						//去掉最后边的","
						String result = "";
						if (isOrder == true) {
							result = hql.substring(0, hql.length() - 2);
						} else {
							result = hql.toString();
						}
						
						//设置查询参数
						System.out.println("hql = "+ hql + "\n" + "result = " + result );
					
						Query  q = s.createQuery(result);
						for (int i = 0; i < params.size(); ++i) {
							q.setParameter(i, params.get(i));
							System.out.println("参数 I" + params.get(i));
						}
						// 设置页面
						q.setFirstResult((currentPage - 1) * pageSize);
						q.setMaxResults(pageSize);
						books = q.list();
						return books;
					}
				}
			);
		
		return books;
	}
	

	
	
	//按条件查询所得用户信息
	@SuppressWarnings("unchecked")
	public List<TBook> query(QueryBookInfo queryBookInfo) throws Exception {
		//按条件查询的条件
		final String clientInputInfo = queryBookInfo.getClientInputInfo(); //用户在检索框中输入的,可能是书名,也可能是作者名
		//基本查询条件
		final Integer id = queryBookInfo.getId();  //书籍编号
		final String name = queryBookInfo.getName(); //作者姓名
		final String isbn = queryBookInfo.getIsbn(); ////ISBN
		final String authorName = queryBookInfo.getAuthorName(); //书名
		final String publisher = queryBookInfo.getPublisher(); //出版社
		final Integer type = queryBookInfo.getType(); //父类型
		final Integer chType = queryBookInfo.getChType(); //子类型
		final Integer used = queryBookInfo.getUsed();  //是否二手
		
		//分段查询
		final Date beginDate = queryBookInfo.getBeginDate();   //出版时间段
		final Date endDate = queryBookInfo.getEndDate();
		
		final Double beginPrice = queryBookInfo.getBeginPrice(); //价格段查询
		final Double endPrice = queryBookInfo.getEndPrice();
		
		final Integer beginGrade = queryBookInfo.getBeginGrade(); // 按客户评分
		final Integer endGrade = queryBookInfo.getEndGrade();
		
		final Double beginDiscount = queryBookInfo.getBeginDiscount(); //按折扣查
		final Double endDiscount = queryBookInfo.getEndDiscount();
		
		final Integer beginStockNum = queryBookInfo.getBeginStockNum(); //库存量
		final Integer endStockNum = queryBookInfo.getEndStockNum();
		
		//排序条件
		final String orderByPrice = queryBookInfo.getOrderByPrice(); //价格排序
		final String orderByDate = queryBookInfo.getOrderByDate(); //时间排序 
		final String orderBySaleNum = queryBookInfo.getOrderBySaleNum(); //销量排序 
		final String orderByGrade = queryBookInfo.getOrderByGrade(); //按用户评分
		final String orderByDiscount = queryBookInfo.getOrderByDiscount(); //按折扣
		
		
		
		
		List<TBook> books = this.getHibernateTemplate().execute(
				new HibernateCallback() {
		
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						
						//HQL语句
		
						StringBuilder hql = new StringBuilder(" select distinct b from TBook b join b.TAuthors a where 1 = 1 ");
						// 查询结果
						List<TBook> books = new ArrayList<TBook>();
						//存放查询参数
						List<Object> params = new ArrayList<Object>();
						
						//动态的拼接hql语句
						//可能是书名，也可能是作者名
						if (clientInputInfo != null && !"".equals(clientInputInfo.trim())) {
							hql.append(" and ( b.name like ? or a.name like ? ) ");
							params.add("%" + clientInputInfo + "%"); params.add("%" + clientInputInfo + "%");
						}
						//书籍编号
						if (null != id && id != 0) {
							hql.append(" and b.id = ? ");
							params.add(id);
						}
						//作者姓名
						if (name != null && !"".equals(name.trim())) {
							hql.append(" and b.name like ? ");
							params.add("%" + name + "%");
						}
						//ISBN
						if (isbn != null && !"".equals(isbn.trim())) {
							hql.append(" and b.isbn = ? ");
							params.add(isbn);
						}
						//作者姓名
						if (authorName != null && !"".equals(authorName.trim())) {
							hql.append(" and a.name like ? ");
							params.add("%" + authorName + "%");
						}
						//出版社
						if (publisher != null && !"".equals(publisher.trim())) {
							hql.append(" and b.publisher like ? ");
							params.add("%" + publisher + "%");
						}
						
						//父类型
						if (type != null && type != 0) {
							hql.append(" and b.ftype = ? ");
							params.add(type);  
						}
						//子类型
						if (null != chType && chType != 0) {
							hql.append(" and b.type = ? ");
							params.add(chType);
						}
						//是否为二手
						if (used != null) {
							hql.append(" and b.used = ? ");
							params.add(used);
						}
						
						//时间段
						if (beginDate != null) {
							hql.append(" and b.publishDate >= ? ");
							params.add(beginDate);
						}
						if (endDate != null) {
							hql.append(" and b.publishDate <= ? ");
							params.add(endDate);
						}
						//价格段
						if (beginPrice != null) {
							hql.append(" and b.hbPrice >= ? ");
							params.add(beginPrice);
						}
						if (endPrice != null) {
							hql.append(" and b.hbPrice <= ? ");
							params.add(endPrice);
						}
						//评分段
						if(beginGrade != null) {
							hql.append(" and b.grade >= ? ");
							params.add(beginGrade);
						}
						if (endGrade != null) {
							hql.append(" and b.grade <= ? ");
							params.add(endGrade);
						}
						
						//折扣段
						if (beginDiscount != null) {
							hql.append(" and b.discount >= ? ");
							params.add(beginDiscount);
						}
						if (endDiscount != null) {
							hql.append(" and b.discount <= ? ");
							params.add(endDiscount);
						}
						//库存量
						if (beginStockNum != null) {
							hql.append(" and b.stockNum >= ? ");
							params.add(beginStockNum);
						}
						if (endStockNum != null) {
							hql.append(" and b.stockNum <= ? ");
							params.add(endStockNum);
						}
						//是否已经有order
						boolean isOrder = false;
						//按价格排序
						if (orderByPrice != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByPrice.equals("1")) {
								hql.append(" b.hbPrice asc , ");
							} else {
								hql.append(" b.hbPrice desc , ");
							}
							isOrder = true;
						}
						//时间排序 
						if (orderByDate != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByDate.equals("1")) {
								hql.append(" b.publishDate asc , ");
							} else {
								hql.append(" b.publishDate desc , ");
							}
							isOrder = true;
						}
						//销量排序 
						if (orderBySaleNum != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderBySaleNum.equals("1")) {
								hql.append(" b.saleNum asc , ");
							} else {
								hql.append(" b.saleNum desc , ");
							}
							isOrder = true;
						}
						//按评分排序
						if (orderByGrade != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByGrade.equals("1")) {
								hql.append(" b.grade asc , ");
							} else {
								hql.append(" b.grade desc, ");
							}
							isOrder = true;
						}
						//按折扣查询
						if (orderByDiscount != null) {
							if (!isOrder) hql.append(" order by ");
							if (orderByDiscount.equals("1")) {
								hql.append(" b.discount asc , ");
							} else {
								hql.append(" b.discount desc , ");
							}
							isOrder = true;
						}
						//去掉最后边的","
						String result = "";
						if (isOrder == true) {
							result = hql.substring(0, hql.length() - 1);
						} else {
							result = hql.toString();
						}
						
						
						//设置查询参数
						System.out.println("hql = " + hql );
						Query  q = s.createQuery(result);
						for (int i = 0; i < params.size(); ++i) {
							q.setParameter(i, params.get(i));
							System.out.println("参数 I : " + i + " == " + params.get(i));
						}
						// 设置页面
						q.setFirstResult((currentPage - 1) * pageSize);
						q.setMaxResults(pageSize);
						books = q.list();
						return books;
					}
				}
			);
		
		return books;
	}
	
	//得到按条件查询的总页数
	@SuppressWarnings("unchecked")
	public Integer getAllPage(QueryBookInfo queryBookInfo) throws Exception {
		//按条件查询的条件
		final String clientInputInfo = queryBookInfo.getClientInputInfo(); //用户在检索框中输入的,可能是书名,也可能是作者名

		//基本查询条件
		final String name = queryBookInfo.getName(); //作者姓名
		final String isbn = queryBookInfo.getIsbn(); ////ISBN
		final String authorName = queryBookInfo.getAuthorName(); //书名
		final String publisher = queryBookInfo.getPublisher(); //出版社
		final Integer type = queryBookInfo.getType(); //类型
		final Integer chType = queryBookInfo.getChType(); // 子类型
		final Integer used = queryBookInfo.getUsed();  //是否二手
		
		//分段查询
		final Date beginDate = queryBookInfo.getBeginDate();   //出版时间段
		final Date endDate = queryBookInfo.getEndDate();
		
		final Double beginPrice = queryBookInfo.getBeginPrice(); //价格段查询
		final Double endPrice = queryBookInfo.getEndPrice();
		
		final Integer beginGrade = queryBookInfo.getBeginGrade(); // 按客户评分
		final Integer endGrade = queryBookInfo.getEndGrade();
		
		final Double beginDiscount = queryBookInfo.getBeginDiscount(); //按折扣查
		final Double endDiscount = queryBookInfo.getEndDiscount();
		
		final Integer beginStockNum = queryBookInfo.getBeginStockNum(); //库存量
		final Integer endStockNum = queryBookInfo.getEndStockNum();
		
		//排序条件
		final String orderByPrice = queryBookInfo.getOrderByPrice(); //价格排序
		final String orderByDate = queryBookInfo.getOrderByDate(); //时间排序 
		final String orderBySaleNum = queryBookInfo.getOrderBySaleNum(); //销量排序 
		final String orderByGrade = queryBookInfo.getOrderByGrade(); //按用户评分
		final String orderByDiscount = queryBookInfo.getOrderByDiscount(); //按折扣
		
		
		Integer allPage = this.getHibernateTemplate().execute(
			new HibernateCallback() {
	
				public Object doInHibernate(Session s)
						throws HibernateException, SQLException {
					//HQL语句
					StringBuilder hql = new StringBuilder(" select count(distinct b) from TBook b join b.TAuthors a where 1 = 1 ");
					//存放查询参数
					List<Object> params = new ArrayList<Object>();
					
					//动态的拼接hql语句
					if (clientInputInfo != null && !"".equals(clientInputInfo.trim())) {
						hql.append(" and ( b.name like ? or a.name like ? ) ");
						params.add("%" + clientInputInfo + "%"); params.add("%" + clientInputInfo + "%");
					}
					//作者姓名
					if (name != null && !"".equals(name.trim())) {
						hql.append(" and b.name like ? ");
						params.add("%" + name + "%");
					}
					//ISBN
					if (isbn != null && !"".equals(isbn.trim())) {
						hql.append(" and b.isbn = ? ");
						params.add(isbn);
					}
					//作者姓名
					if (authorName != null && !"".equals(authorName.trim())) {
						hql.append(" and a.name like ? ");
						params.add("%" + authorName + "%");
					}
					//出版社
					if (publisher != null && !"".equals(publisher.trim())) {
						hql.append(" and b.publisher like ? ");
						params.add("%" + publisher + "%");
					}
					//父类型
					if (type != null && type != 0) {
						hql.append(" and b.ftype = ? ");
						params.add(type);  
					}
					//子类型
					if (null != chType && chType != 0) {
						hql.append(" and b.type = ? ");
						params.add(chType);
					}
					//是否为二手
					if (used != null) {
						hql.append(" and b.used = ? ");
						params.add(used);
					}
					
					//时间段
					if (beginDate != null) {
						hql.append(" and b.publishDate >= ? ");
						params.add(beginDate);
					}
					if (endDate != null) {
						hql.append(" and b.publishDate <= ? ");
						params.add(endDate);
					}
					//价格段
					if (beginPrice != null) {
						hql.append(" and b.hbPrice >= ? ");
						params.add(beginPrice);
					}
					if (endPrice != null) {
						hql.append(" and b.hbPrice <= ? ");
						params.add(endPrice);
					}
					//评分段
					if(beginGrade != null) {
						hql.append(" and b.grade >= ? ");
						params.add(beginGrade);
					}
					if (endGrade != null) {
						hql.append(" and b.grade <= ? ");
						params.add(endGrade);
					}
					
					//折扣段
					if (beginDiscount != null) {
						hql.append(" and b.discount >= ? ");
						params.add(beginDiscount);
					}
					if (endDiscount != null) {
						hql.append(" and b.discount <= ? ");
						params.add(endDiscount);
					}
					//库存量
					if (beginStockNum != null) {
						hql.append(" and b.stockNum >= ? ");
						params.add(beginStockNum);
					}
					if (endStockNum != null) {
						hql.append(" and b.stockNum <= ? ");
						params.add(endStockNum);
					}
					//是否已经有order
					boolean isOrder = false;
					//按价格排序
					if (orderByPrice != null) {
						if (!isOrder) hql.append(" order by ");
						if (orderByPrice.equals("1")) {
							hql.append(" b.hbPrice asc , ");
						} else {
							hql.append(" b.hbPrice desc , ");
						}
						isOrder = true;
					}
					//时间排序 
					if (orderByDate != null) {
						if (!isOrder) hql.append(" order by ");
						if (orderByDate.equals("1")) {
							hql.append(" b.publishDate asc , ");
						} else {
							hql.append(" b.publishDate desc , ");
						}
						isOrder = true;
					}
					//销量排序 
					if (orderBySaleNum != null) {
						if (!isOrder) hql.append(" order by ");
						if (orderBySaleNum.equals("1")) {
							hql.append(" b.saleNum asc , ");
						} else {
							hql.append(" b.saleNum desc , ");
						}
						isOrder = true;
					}
					//按评分排序
					if (orderByGrade != null) {
						if (!isOrder) hql.append(" order by ");
						if (orderByGrade.equals("1")) {
							hql.append(" b.grade asc , ");
						} else {
							hql.append(" b.grade desc, ");
						}
						isOrder = true;
					}
					//按折扣查询
					if (orderByDiscount != null) {
						if (!isOrder) hql.append(" order by ");
						if (orderByDiscount.equals("1")) {
							hql.append(" b.discount asc , ");
						} else {
							hql.append(" b.discount desc , ");
						}
						isOrder = true;
					}
					//去掉最后边的","
					String result = "";
					if (isOrder == true) {
						result = hql.substring(0, hql.length() - 2);
					} else {
						result = hql.toString();
					}
					System.out.println("allPage result = " + result);
					//设置查询参数
					System.out.println("Book 查询参数 : " );
					Query  q = s.createQuery(hql.toString());
					for (int i = 0; i < params.size(); ++i) {
						System.out.println(params.get(i).toString());
						q.setParameter(i, params.get(i));
					}
					Integer allPage = (Integer.parseInt(q.uniqueResult().toString()) + pageSize - 1) / pageSize;
					return allPage;
				}
			}
		);
		return allPage;
	}
	
	
	//分页查询 设置当前页
	public void setCurrentPage(int currentPage) throws Exception {
		this.currentPage = currentPage;
	}
	//设置页面大小
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	//测试HQL
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> test(){
		Configuration cfg = new Configuration();
		cfg.configure();
		Session s = cfg.buildSessionFactory().openSession();
		Query q = s.createQuery("select new map(a.name as name, b.price as price, b.name as bname) from TBook b join b.TAuthors a where a.name = ? ");
		q.setParameter(0, "大宝1");
		return q.list();
	}
	@SuppressWarnings("unchecked")
	public static List<TBook> test2(){
		Configuration cfg = new Configuration();
		cfg.configure();
		Session s = cfg.buildSessionFactory().openSession();
		Query q = s.createQuery("selet b from  TBook b join b.TAuthors a  where a.name = ? ");
		q.setParameter(0, "大宝1");
		return (List<TBook> )q.list();
	}
	public static void main(String[] args) throws Exception {
		/*List<Map<String, Object>> books = new BookDaoImpl().test();
		for (Map<String, Object> map : books) {
			for (Map.Entry<String, Object> en : map.entrySet()) {
				System.out.println(en.getKey() + " : " + en.getValue());
			}
		}*/
		/*List<TBook> books = new BookDaoImpl().test2();
		for (TBook b : books) {
			System.out.println(b.getName() + b.getTAuthors().size());
		}*/
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		IBookDao bookDao = (IBookDao)ctx.getBean("bookDao");
		TBook book = bookDao.queryBookByIsbn("isbna");
		System.out.println(book.getName());
	}

}
