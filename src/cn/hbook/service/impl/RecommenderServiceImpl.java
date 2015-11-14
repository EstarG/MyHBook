package cn.hbook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import cn.hbook.bean.TBook;
import cn.hbook.dao.IBookDao;
import cn.hbook.service.IRecommenderService;

public class RecommenderServiceImpl implements IRecommenderService {
	
	/** 关联关系表 */
	private static final String PERFERENCETABLE = "t_user_book";
	
	/** 表中用户标识的列名 */
	private static final String USERID_COLUMN = "userid"; 
	
	/**  表中书籍标识的列名 */
	private static final String ITEMID_COLUMN = "bookid"; 
	
	/** 表中评分的列名 */
	private static final String PERFERENCE_COLUMN = "value"; 
	
	private static final int K = 2;
	
	/** 数据模型  */
	private static JDBCDataModel dataModel;
	
	/** 书籍服务类 */
	private IBookDao bookDao;
	
	
	
	static {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://localhost/hbook?user=root&password=1");
		dataModel = new MySQLJDBCDataModel(dataSource,
				PERFERENCETABLE, USERID_COLUMN, ITEMID_COLUMN,
				PERFERENCE_COLUMN, null);
	}

	public List<TBook> baseUserCFRecommender(int userId, int howMany) throws Exception {
		// 1,计算相似度
		UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(getDataModel());
		// 2,查找k紧邻
		UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(K, userSimilarity, getDataModel());
	    // 3,构造推荐引擎
		Recommender recommender = new GenericUserBasedRecommender(getDataModel(), userNeighborhood, userSimilarity);
		// 为用户userId推荐howMany个Item
		List<RecommendedItem> recommendations = recommender.recommend(userId, howMany);
		return getBookList(recommendations);
		
	}

	

	public List<TBook> baseItemCFRecommender(int userId, int howMany) throws Exception {
		ItemSimilarity itemsimilarity = new PearsonCorrelationSimilarity(getDataModel());
		Recommender recommender = new GenericItemBasedRecommender(getDataModel(),itemsimilarity);
		List<RecommendedItem> recommendations = recommender.recommend(userId, howMany);
		return  getBookList(recommendations);
	}

	public List<TBook> baseSlopOneRecommender(int userId, int howMany) throws Exception {
		Recommender recommender = new SlopeOneRecommender(getDataModel());
		List<RecommendedItem> recommendations = recommender.recommend(userId, howMany);
		return  getBookList(recommendations);
	}

	/**
	 * @return the dataModel
	 */
	public static JDBCDataModel getDataModel() {
		return dataModel;
	}

	/**
	 * @param dataModel the dataModel to set
	 */
	public static void setDataModel(JDBCDataModel dataModel) {
		RecommenderServiceImpl.dataModel = dataModel;
	}
	
	/**
	 * 将智能推荐的物品转化为我们的书籍
	 * 
	 * @param recommendations
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private List<TBook> getBookList(List<RecommendedItem> recommendations) throws NumberFormatException, Exception {
		List<TBook> books = new ArrayList<TBook>();
		if (null == recommendations || recommendations.size() == 0) {
			return books;
		}
		for (RecommendedItem item : recommendations) {
			System.out.println(item);
			books.add(bookDao.queryById(Integer.parseInt(item.getItemID() + "")));
		}
		return books;
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
