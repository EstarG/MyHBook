/**
 * 
 */
package cn.hbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.hbook.dao.IEvaluateDao;
import cn.hbook.utils.DBUtils;

/**
 * @author E_star
 *
 */
public class EvaluateDaoImpl implements IEvaluateDao {

	public void evaluate(Integer userId, Integer bookId, Integer value)
			throws Exception {
		 Connection conn = DBUtils.getConnection();
         StringBuilder sql = new StringBuilder("insert into t_user_book values(?, ?, ?)");
         PreparedStatement ps = conn.prepareStatement(sql.toString());
         ps.setInt(1, userId);
         ps.setInt(2, bookId);
         ps.setInt(3, value);
         ps.executeUpdate();
         DBUtils.close(ps);
         DBUtils.close(conn);
	}
	
	public Integer getValue(Integer userId, Integer bookId) throws Exception {
		 Connection conn = DBUtils.getConnection();
         StringBuilder sql = new StringBuilder("select value from t_user_book where userid = ? and bookid = ?");
         PreparedStatement ps = conn.prepareStatement(sql.toString());
         ps.setInt(1, userId);
         ps.setInt(2, bookId);
         ResultSet rs = ps.executeQuery();
         Integer value = 0;
         while (rs.next()) {
        	 value = rs.getInt("value");
         }
         return value;
	}

	

}
