package cn.hbook.service.impl;

import java.util.List;
import java.util.Map;

import cn.hbook.bean.TAuthor;
import cn.hbook.dao.IAuthorDao;
import cn.hbook.service.IAuthorService;

public class AuthorServiceImpl implements IAuthorService {
	private int currentPage = 1;  
	private int pageSize = 3;
	private IAuthorDao authorDao;
	
	//添加作者信息
	public boolean save(TAuthor author) throws Exception {
		return authorDao.save(author);
	}
	//删除作者信息
	public boolean delete(TAuthor author) throws Exception {
		return authorDao.delete(author);
	}
	//批量删除作者
	public boolean deleteAll(List<String> ids) throws Exception {
		return authorDao.deleteAll(ids);
	}
	//修改作者信息
	public boolean update(TAuthor author) throws Exception {
		return authorDao.update(author);
	}
	//根据作者的ID查询作者信息
	public TAuthor queryById(Integer authorId) throws Exception {
		return authorDao.queryById(authorId);
	}
	//根据作者的Id 或者 name 查找出作者的Id , name
	public List<Map<String, Object>> queryAuthor(Integer id, String name)
				throws Exception {
		return authorDao.queryAuthor(id, name);
	}

	//查询出所有作者的信息
	public List<TAuthor> queryAuthors() throws Exception {
		this.authorDao.setCurrentPage(currentPage);
		return authorDao.queryAuthors();
	}
	//根据条件查询作者
	public List<TAuthor> query(TAuthor author) throws Exception {
		this.authorDao.setCurrentPage(currentPage);
		this.authorDao.setPageSize(pageSize);
		return authorDao.query(author);
	}
	//获得根据条件查询时的总页数
	public Integer getAllPage(TAuthor author) throws Exception {
		this.authorDao.setCurrentPage(currentPage);
		this.authorDao.setPageSize(pageSize);
		return authorDao.getAllPage(author);
	}
	
	//分页设置
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public IAuthorDao getAuthorDao() {
		return authorDao;
	}

	public void setAuthorDao(IAuthorDao authorDao) {
		this.authorDao = authorDao;
	}
	
	
	
}
