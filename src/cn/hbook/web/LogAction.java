package cn.hbook.web;

import java.util.ArrayList;
import java.util.List;

import cn.hbook.bean.TLog;
import cn.hbook.form.QueryLogInfo;
import cn.hbook.service.ILogService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LogAction extends ActionSupport implements ModelDriven<QueryLogInfo>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6704492157204398935L;

	private ILogService logService; //业务处理类
	
	private QueryLogInfo queryLogInfo = new QueryLogInfo();  //收集前端form表单的提交的查询信息
	private List<TLog> logs = new ArrayList<TLog>();  // 记录查询结果
	
	
    private Integer currentPage;  //当前页
    private Integer allPage ; //总页数
    
    
    public String query() throws Exception {
    	
    	System.out.println("queryLogInfo = : " + queryLogInfo.toString());
    	String result = "";
    	//设置当前页
    	if (currentPage == null || currentPage == 0) {
    		currentPage = 1;
    	}
    	logService.setCurrentPage(currentPage);
    	logService.setPageSize(10);
    	//记录总页数
    	allPage = logService.getAllPage(queryLogInfo);
    	//记录查询结果
    	logs = logService.query(queryLogInfo);
    	result = "query";
    	return result;
    }

    
    //setter getter
    
	public QueryLogInfo getQueryLogInfo() {
		return queryLogInfo;
	}

	public void setQueryLogInfo(QueryLogInfo queryLogInfo) {
		this.queryLogInfo = queryLogInfo;
	}

	public List<TLog> getLogs() {
		return logs;
	}

	public void setLogs(List<TLog> logs) {
		this.logs = logs;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getAllPage() {
		return allPage;
	}

	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}


	public ILogService getLogService() {
		return logService;
	}


	public void setLogService(ILogService logService) {
		this.logService = logService;
	}


	public QueryLogInfo getModel() {
		return queryLogInfo;
	}
	
}
