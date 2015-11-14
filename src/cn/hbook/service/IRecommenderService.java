/**
 * 
 */
package cn.hbook.service;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;

import cn.hbook.bean.TBook;

/**
 * 只能推荐接口
 * 
 * @author E_star
 */
public interface IRecommenderService {
	
	/**
	 *基于用户相似度的推荐实现
	 * 
	 * @param userId
	 * @param howMany
	 * @return
	 * @throws TasteException 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public List<TBook> baseUserCFRecommender(int userId, int howMany) throws Exception;
	
	/**
	 * 基于内容相似度的推荐实现
	 * 
	 * @param userId
	 * @param howMany
	 * @return
	 */
	public List<TBook> baseItemCFRecommender(int userId, int howMany) throws Exception ;
	
	/**
	 * SlopeOne Recommeder 的实现
	 * 
	 * @param userId
	 * @param howMany
	 * @return
	 */
	public List<TBook> baseSlopOneRecommender(int userId, int howMany) throws Exception ;
	
}
