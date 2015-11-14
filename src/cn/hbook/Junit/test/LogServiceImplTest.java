package cn.hbook.Junit.test;


import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hbook.bean.TLog;
import cn.hbook.bean.TUser;
import cn.hbook.form.QueryLogInfo;
import cn.hbook.service.ILogService;

public class LogServiceImplTest {

	private static ILogService logService; 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/MyHBook/WebRoot/WEB-INF/applicationContext.xml");
		logService = (ILogService)ctx.getBean("logService");
	}
	//测试保存
	@Test
	public void testSave() {
		TUser user = new TUser(7);
		TLog log = new TLog(user, "127.1.1.1", new Date(), "用户添加书籍");
		try {
			logService.save(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试查询所有日志信息
	@Test
	public void testQueryAll() {
		List<TLog> logs = null;
		try {
			logs = logService.queryAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TLog log : logs) {
			System.out.println(log.getInfo() + " : ");
			//这里如果想同时获得用户信息必须 在 ORM文件中<many2one>配置lazy = false; 
			//将懒加载设为false
			System.out.println(log.getTUser().getUserName());  
		}
	}
	//测试按条件查询日志信息
	@Test
	public void testQuery() {
		QueryLogInfo info = new QueryLogInfo();
		//info.setUserid(1);
		info.setUserName("gbx");
		try {
			List<TLog> logs = logService.query(info);
			for (TLog log : logs) {
				System.out.println(log.getInfo() + " : user : " + log.getTUser().getRealName());
				System.out.println(">>>>>>>>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//测试获得按条件查询的总页数
	@Test
	public void testGetAllPage() {
		QueryLogInfo info = new QueryLogInfo();
		//info.setUserid(1);
		info.setUserIp("127.1.1.1");
		try {
			Integer allPage = logService.getAllPage(info);
			System.out.println("allPage = " + allPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
