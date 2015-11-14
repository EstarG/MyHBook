package cn.hbook.service.impl;


import cn.hbook.bean.TBook;
import cn.hbook.dao.IBookDao;
import cn.hbook.dao.IEvaluateDao;
import cn.hbook.service.IEvaluateService;

public class EvaluateServiceImpl implements IEvaluateService {
	
	/** 评分dao */
	private IEvaluateDao evaluateDao;
	
	private IBookDao bookDao;

	public void evaluate(Integer userId, Integer bookId, Integer value)
			throws Exception {
		evaluateDao.evaluate(userId, bookId, value);
		update(bookId, value);
	}
	
	/** 更新评分 */
	private void update(Integer bookId, Integer value) throws Exception {
		TBook book = bookDao.queryById(bookId);
		System.out.println("评价前的分数 ： " + book.getGrade());
		book.setGrade((book.getGrade() + value)*5/10);
		System.out.println("评价后的分数 ： " + book.getGrade());
		bookDao.update(book);
	}
	
	public Integer getValue(Integer userId, Integer bookId) throws Exception {
		return evaluateDao.getValue(userId, bookId);
	}
	
	

	/**
	 * @return the evaluateDao
	 */
	public IEvaluateDao getEvaluateDao() {
		return evaluateDao;
	}

	/**
	 * @param evaluateDao the evaluateDao to set
	 */
	public void setEvaluateDao(IEvaluateDao evaluateDao) {
		this.evaluateDao = evaluateDao;
	}

	/**
	 * @return the bookDao
	 */
	public IBookDao getBookDao() {
		return bookDao;
	}

	/**
	 * @param bookDao the bookDao to set
	 */
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	
	
}
