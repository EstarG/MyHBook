package cn.hbook.dao;

import java.util.List;
import java.util.Map;

import cn.hbook.bean.TUser;
import cn.hbook.form.QueryUserInfo;

public interface IUserDao {
	//添加用户
	public boolean save(TUser user) throws Exception; 
	//删除用户
	public boolean delete(TUser user) throws Exception; 
	//批量删除
	public boolean deleteAll(List<String> ids) throws Exception;
	//更新用户信息
	public boolean update(TUser user) throws Exception;
	//根据id查询用户
	public TUser queryById(int userid) throws Exception;
	
	/**
	 * 根据用户名查找用户
	 * 
	 * @param uaerName
	 * @return
	 * @throws Exception
	 */
	public TUser queryUserByName(String userName) throws Exception;
	
	//检查用户是否合法
	public TUser checkLegal(String userName, String password) throws Exception;
	//得到总页数
	int  getAllPage(QueryUserInfo queryUserInfo) throws Exception;
	//查询所有用户
	public List<Map<String, Object>> query(QueryUserInfo queryUserInfo) throws Exception;
	public List<TUser> queryUsers() throws Exception;
	//分页查询 设置当前页
	public void setCurrentPage(int currentPage) throws Exception;
	//设置页面大小
	public void setPageSize(int pageSize);
}
