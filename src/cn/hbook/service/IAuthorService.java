package cn.hbook.service;

import java.util.List;
import java.util.Map;

import cn.hbook.bean.TAuthor;

public interface IAuthorService {
	//添加作者信息
	public boolean save(TAuthor author) throws Exception;
	//删除作者信息
	public boolean delete(TAuthor author) throws Exception;
	//批量删除作者
	public boolean deleteAll(List<String> ids) throws Exception;
	//修改作者信息
	public boolean update(TAuthor author) throws Exception;
	//根据作者的ID查询作者信息
	public TAuthor queryById(Integer authorId) throws Exception;
	//根据作者的Id 或者 name 查找出作者的Id , name
	public List<Map<String, Object>> queryAuthor(Integer id, String name) throws Exception;
	//查询出所有作者的信息
	public List<TAuthor> queryAuthors() throws Exception;
	//根据条件查询作者
	public List<TAuthor> query(TAuthor author)throws Exception;
	//获得根据条件查询时的总页数
	public Integer getAllPage(TAuthor author)throws Exception;
	
	//分页设置
	public void setPageSize(int pageSize);
	public void setCurrentPage(int currentPage);

}
