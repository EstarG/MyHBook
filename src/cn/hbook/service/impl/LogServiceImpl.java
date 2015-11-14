package cn.hbook.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.hbook.bean.TLog;
import cn.hbook.dao.ILogDao;
import cn.hbook.form.QueryLogInfo;
import cn.hbook.service.ILogService;

@Transactional
public class LogServiceImpl implements ILogService {
	
	private int pageSize = 3;
	private int currentPage = 1;
	
	private ILogDao logDao; 
	
	//记录日志信息
	public boolean save(TLog log) throws Exception {
		return logDao.save(log);
	}
	//查询所有日志信息
	public List<TLog> queryAll() throws Exception {
		this.logDao.setCurrentPage(currentPage);
		return logDao.queryAll();
	}
	//按条件查询日志信息  参数queryLogInfo 记录的我们的查询条件
	public List<TLog> query(QueryLogInfo queryLogInfo) throws Exception {
		this.logDao.setCurrentPage(currentPage);
		this.logDao.setPageSize(pageSize);
		return logDao.query(queryLogInfo);
	}
	//获得按条件查询时的总页数
	public Integer getAllPage(QueryLogInfo queryLogInfo) throws Exception {
		this.logDao.setCurrentPage(currentPage);
		this.logDao.setPageSize(pageSize);
		return logDao.getAllPage(queryLogInfo);
	}

	//分页设置
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public ILogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}
}
