package cn.hbook.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.hbook.bean.TUser;
import cn.hbook.dao.IUserDao;
import cn.hbook.form.QueryUserInfo;
import cn.hbook.service.IUserService;

@Transactional
public class UserServiceImpl implements IUserService {
	private int pageSize = 3;
	private int currentPage = 1;
	private IUserDao userDao;
	
	//添加用户
	public boolean save(TUser user) throws Exception {
		return userDao.save(user);
	}
	//删除用户
	public boolean delete(TUser user) throws Exception {
		return userDao.delete(user);
	}
	//批量删除用户
	public boolean deleteAll(List<String> ids) throws Exception {
		return userDao.deleteAll(ids);
	}
	//更新用户信息
	public boolean update(TUser user) throws Exception {
		return userDao.update(user);
	}
	//根据ID查找用户
	public TUser queryById(int userid) throws Exception {
		return userDao.queryById(userid);
	}
	
	/**
	 * 根据用户名查找用户
	 * 
	 * @param uaerName
	 * @return
	 * @throws Exception
	 */
	public TUser queryUserByName(String userName) throws Exception {
		return userDao.queryUserByName(userName);
	}
	
	//检查用户的合法性
	public TUser checkLegal(String userName, String password) throws Exception {
		return userDao.checkLegal(userName, password);
	}
	//获得查询结果的总页数
	public int getAllPage(QueryUserInfo queryUserInfo) throws Exception {
		userDao.setCurrentPage(currentPage);
		userDao.setPageSize(pageSize);
		return userDao.getAllPage(queryUserInfo);
	}
	//按条件查询用户信息 以Map的形式返回
	public List<Map<String, Object>> query(QueryUserInfo queryUserInfo) throws Exception {
		userDao.setCurrentPage(currentPage);
		userDao.setPageSize(pageSize);
		return userDao.query(queryUserInfo);
	}
	//查询所有用户信息
	public List<TUser> queryUsers() throws Exception {
		return userDao.queryUsers();
	}

	public void setCurrentPage(int currentPage) throws Exception {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	
}
