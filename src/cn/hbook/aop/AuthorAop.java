package cn.hbook.aop;

import java.util.Date;
import java.util.Map;

import org.aspectj.lang.JoinPoint;

import cn.hbook.bean.TAuthor;
import cn.hbook.bean.TLog;
import cn.hbook.bean.TUser;
import cn.hbook.service.ILogService;

import com.opensymphony.xwork2.ActionContext;

public class AuthorAop {
	
	/** 日志服务组件  */
	private ILogService logService;
	
	/** 日志对象 */
	private TLog log ;
	
	/**
	 * 关于保存的后置通知
	 * @throws Exception
	 */
	public void afterSave(JoinPoint jp) throws Exception  {
		System.out.println("author.save 的后置通知");
		Map<String,Object> session = ActionContext.getContext().getSession();
		TUser user = (TUser)session.get("admin");
		System.out.println("userName = " + user.getUserName());
		TAuthor author = (TAuthor)jp.getArgs()[0];
		log = new TLog(user, (String)session.get("ip"), new Date(), "用户：" + user.getUserName()  + "-执行-" +  "添加作者操作, 添加的作者为 ： ID : " + author.getId() + "作者名：" + author.getName());
		logService.save(log);
	}
	
	/**
	 * 关于更新的后置通知
	 * 
	 * @throws Exception
	 */
	public void afterUpdate(JoinPoint jp) throws Exception  {
		System.out.println("author.update 的后置通知");
		Map<String,Object> session = ActionContext.getContext().getSession();
		TUser user = (TUser)session.get("admin");
		System.out.println("userName = " + user.getUserName());
		TAuthor author = (TAuthor)jp.getArgs()[0];
		log = new TLog(user, (String)session.get("ip"), new Date(), "用户：" + user.getUserName()  + "-执行-" +  "更新作者操作, 更新作者为: ID ： " + author.getId() + "作者名  ：" + author.getName());
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
	
	
}
