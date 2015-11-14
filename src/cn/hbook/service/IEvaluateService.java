package cn.hbook.service;

public interface IEvaluateService {
	
	/** 用户给书籍评分  */
	public void evaluate(Integer userId, Integer bookId, Integer value) throws Exception;
	
	/** 获得用户之前的评分  */
	public Integer getValue(Integer userId, Integer bookId) throws Exception ;

}
