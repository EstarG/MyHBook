package cn.hbook.aop;

import java.util.Date;
import java.util.Map;

import org.aspectj.lang.JoinPoint;

import com.opensymphony.xwork2.ActionContext;

import cn.hbook.bean.TLog;
import cn.hbook.bean.TUser;
import cn.hbook.service.ILogService;

public class UserAop {
	
	/** 日志服务组件  */
	private ILogService logService;
	
	/** 日志对象 */
	private TLog log ;
	
	/**
	 * 关于保存的后置通知
	 * @throws Exception
	 */
	public void afterSave(JoinPoint jp) throws Exception  {
		System.out.println("user.save 的后置通知");
		Map<String,Object> session = ActionContext.getContext().getSession();
		TUser user = (TUser)session.get("admin");
		//不记录用户注册
		if (user != null) {
			System.out.println("userName = " + user.getUserName());
			TUser tuser = (TUser)jp.getArgs()[0];
			log = new TLog(user, (String)session.get("ip"), new Date(), "用户：" + user.getUserName()  + "-执行-" +  "添加用户操作, 添加的用户为 ： ID : " + tuser.getId() + "用户名：" + tuser.getUserName());
			logService.save(log);
		}
	}
	
	/**
	 * 关于更新的后置通知
	 * 
	 * @throws Exception
	 */
	public void afterUpdate(JoinPoint jp) throws Exception  {
		System.out.println("user.update 的后置通知");
		Map<String,Object> session = ActionContext.getContext().getSession();
		TUser user = (TUser)session.get("admin");
		if (user == null) {
			user = (TUser) session.get("user");
		}
		System.out.println("userName = " + user.getUserName());
		TUser tuser = (TUser)jp.getArgs()[0];
		log = new TLog(user, (String)session.get("ip"), new Date(), "用户：" + user.getUserName()  + "-执行-" +  "更新用户操作, 更新用户为: ID ： " + tuser.getId() + "用户名  ：" + tuser.getUserName());
		logService.save(log);
	}
	
	
	/**
	 * 关于删除的后置通知
	 * 
	 * @throws Exception
	 */
	public void beforeDalete(JoinPoint jp) throws Exception  {
		System.out.println("user.delete 的后置通知");
		Map<String,Object> session = ActionContext.getContext().getSession();
		TUser user = (TUser)session.get("admin");
		System.out.println("userName = " + user.getUserName());
		TUser tuser = (TUser)jp.getArgs()[0];
		//String userName = tuser.getUserName();
		//System.out.println("delete user Name = " + userName);
		log = new TLog(user, (String)session.get("ip"), new Date(), "用户：" + user.getUserName()  + "-执行-" +  "删除用户操作, 删除用户为: ID ： " + tuser.getId() );
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
