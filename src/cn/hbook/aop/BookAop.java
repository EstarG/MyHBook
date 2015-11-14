package cn.hbook.aop;

import java.util.Date;
import java.util.Map;

import org.aspectj.lang.JoinPoint;

import cn.hbook.bean.TBook;
import cn.hbook.bean.TLog;
import cn.hbook.bean.TUser;
import cn.hbook.service.ILogService;

import com.opensymphony.xwork2.ActionContext;

/**
 * 对于书籍操作的AOP日志记录一些关于书籍的关键性操作
 * @author E_star
 *
 */
public class BookAop {
	
	/** 日志服务组件  */
	private ILogService logService;
	
	/** 日志对象 */
	private TLog log ;
	
	/**
	 * 关于保存的后置通知
	 * @throws Exception
	 */
	public void afterSave(JoinPoint jp) throws Exception  {
		System.out.println("book.save 的后置通知");
		Map<String,Object> session = ActionContext.getContext().getSession();
		TUser user = (TUser)session.get("admin");
		System.out.println("userName = " + user.getUserName());
		TBook book = (TBook)jp.getArgs()[0];
		log = new TLog(user, (String)session.get("ip"), new Date(), "用户：" + user.getUserName()  + "-执行-" +  "添加书籍操作, 添加的书籍为 ： ISBN : " + book.getIsbn() + "名字：" + book.getName());
		logService.save(log);
	}
	
	/**
	 * 关于更新的后置通知
	 * 
	 * @throws Exception
	 */
	public void afterUpdate(JoinPoint jp) throws Exception  {
		System.out.println("book.update 的后置通知");
		Map<String,Object> session = ActionContext.getContext().getSession();
		TUser user = (TUser)session.get("admin");
		System.out.println("userName = " + user.getUserName());
		TBook book = (TBook)jp.getArgs()[0];
		log = new TLog(user, (String)session.get("ip"), new Date(), "用户：" + user.getUserName()  + "-执行-" +  "更新书籍操作, 更新书籍为: ISBN ： " + book.getIsbn() + "名字  ：" + book.getName());
		logService.save(log);
	}
	
	
	

	/**
	 * @return the logService
	 */
	public ILogService getLogService() {
		return logService;
	}

	/**
	 * @param logService the logService to set
	 */
	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	/**
	 * @return the log
	 */
	public TLog getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(TLog log) {
		this.log = log;
	}
}
