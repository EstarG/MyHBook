package cn.hbook.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.hbook.bean.TBook;
import cn.hbook.dao.IBookDao;
import cn.hbook.form.QueryBookInfo;
import cn.hbook.service.IBookService;

@Transactional
public class BookServiceImpl implements IBookService {
	private int currentPage = 1;
	private int pageSize = 8;
	private IBookDao bookDao;

	// 添加书籍
	public boolean save(TBook book) throws Exception {
		return bookDao.save(book);
	}

	// 删除书籍
	public boolean delete(TBook book) throws Exception {
		return bookDao.delete(book);
	}

	// 批量删除
	public boolean deleteAll(List<String> ids) throws Exception {
		return bookDao.deleteAll(ids);
	}

	// 更新书籍信息
	public boolean update(TBook book) throws Exception {
		return bookDao.update(book);
	}

	// 根据id查询数据
	public TBook queryById(Integer bookid) throws Exception {
		return bookDao.queryById(bookid);
	}
	// 根据ISBN查询书籍
	public TBook queryBookByIsbn(String isbn) throws Exception {
		return bookDao.queryBookByIsbn(isbn);
	}

	// 查询所有书籍
	public List<TBook> queryBooks() throws Exception {
		this.bookDao.setCurrentPage(currentPage);
		return bookDao.queryBooks();
	}

	public List<TBook> queryBooksByCategory(QueryBookInfo queryBookInfo)
			throws Exception {
		this.bookDao.setCurrentPage(currentPage);
		this.bookDao.setPageSize(pageSize);
		return bookDao.queryBooksByCategory(queryBookInfo);
	}


	// 查询新书
	public List<TBook> queryBooksOrderByDate(QueryBookInfo queryBookInfo)
			throws Exception {
		this.bookDao.setPageSize(pageSize);
		this.bookDao.setCurrentPage(currentPage);
		return bookDao.queryBooksOrderByDate(queryBookInfo);
	}
	//查二手书
	public List<TBook> queryBooksOrderByUsed(QueryBookInfo queryBookInfo)
			throws Exception {
		this.bookDao.setPageSize(pageSize);
		this.bookDao.setCurrentPage(currentPage);
		return bookDao.queryBooksOrderByUsed(queryBookInfo);
	}
	//查高分书
	public List<TBook> queryBooksOrderByGrade(QueryBookInfo queryBookInfo)
			throws Exception {
		this.bookDao.setPageSize(pageSize);
		this.bookDao.setCurrentPage(currentPage);
		return bookDao.queryBooksOrderByGrade(queryBookInfo);
	}
	//查低价书
	public List<TBook> queryBooksOrderByPrice(QueryBookInfo queryBookInfo)
			throws Exception {
		this.bookDao.setPageSize(pageSize);
		this.bookDao.setCurrentPage(currentPage);
		return bookDao.queryBooksOrderByPrice(queryBookInfo);
	}
	//查热销书
	public List<TBook> queryBooksOrderBySaleNum(QueryBookInfo queryBookInfo)
			throws Exception {
		this.bookDao.setPageSize(pageSize);
		this.bookDao.setCurrentPage(currentPage);
		return bookDao.queryBooksOrderBySaleNum(queryBookInfo);
	}

	// 得到按条件查询的总页数
	public Integer getAllPage(QueryBookInfo queryBookInfo) throws Exception {
		this.bookDao.setPageSize(pageSize);
		this.bookDao.setCurrentPage(currentPage);
		return bookDao.getAllPage(queryBookInfo);
	}

	// 按条件查询所得用户信息
	public List<TBook> query(QueryBookInfo queryBookInfo) throws Exception {
		this.bookDao.setPageSize(pageSize);
		this.bookDao.setCurrentPage(currentPage);
		return bookDao.query(queryBookInfo);
	}

	// 分页查询 设置当前页
	public void setCurrentPage(int currentPage) throws Exception {
		this.currentPage = currentPage;
	}

	// 设置页面大小
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

}
