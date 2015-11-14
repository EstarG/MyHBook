package cn.hbook.service;

import java.util.List;

import cn.hbook.bean.TLog;
import cn.hbook.form.QueryLogInfo;

public interface ILogService {
	//记录日志
	public boolean save(TLog log) throws Exception;
	//查看所有日志信息
	public List<TLog> queryAll() throws Exception;
    //根据条件查询日志
	public List<TLog> query(QueryLogInfo queryLogInfo) throws Exception;
	public Integer getAllPage(QueryLogInfo queryLogInfo) throws Exception;
	
	public void setPageSize(int pageSize) ;
	public void setCurrentPage(int currentPage) ;
}
