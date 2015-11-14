package cn.hbook.dao;

import java.util.List;
import java.util.Map;

import javax.management.Query;

import cn.hbook.bean.TBook;
import cn.hbook.form.QueryBookInfo;

public interface IBookDao {
	//添加书籍
	public boolean save(TBook book) throws Exception;
	//删除书籍
	public boolean delete(TBook book) throws Exception;
	//批量删除
	public boolean deleteAll(List<String> ids) throws Exception;
	//更新书籍信息
	public boolean update(TBook book) throws Exception;
	
	//根据id查询数据
	public TBook queryById(Integer bookid) throws Exception;
	//根据ISBN查询书籍信息
	public TBook queryBookByIsbn(String isbn) throws Exception;
	//查询所有书籍
	public List<TBook> queryBooks() throws Exception;
	//分类查找
	public List<TBook> queryBooksByCategory(QueryBookInfo queryBookInfo) throws Exception ;
	//查询新书
	public List<TBook> queryBooksOrderByDate(QueryBookInfo queryBookInfo) throws Exception;
	//查二手书
	public List<TBook> queryBooksOrderByUsed(QueryBookInfo queryBookInfo) throws Exception;
	//按评分排续., 查高分书籍
	public List<TBook> queryBooksOrderByGrade(QueryBookInfo queryBookInfo) throws Exception;
	//按价格排续., 查低价书籍
	public List<TBook> queryBooksOrderByPrice(QueryBookInfo queryBookInfo) throws Exception;
	//按销量排续。 查热卖书籍
	public List<TBook> queryBooksOrderBySaleNum(QueryBookInfo queryBookInfo) throws Exception;
	
	//得到按条件查询的总页数
	public Integer getAllPage(QueryBookInfo queryBookInfo) throws Exception;
	//按条件查询所得用户信息
	public List<TBook> query(QueryBookInfo queryBookInfo) throws Exception;
	//分页查询 设置当前页
	public void setCurrentPage(int currentPage) throws Exception;
	//设置页面大小
	public void setPageSize(int pageSize);
}
