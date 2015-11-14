package cn.hbook.Junit.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hbook.bean.TUser;
import cn.hbook.service.IUserService;

public class UserServiceImplTest {
	
	private static IUserService userService = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = (IUserService)ctx.getBean("userService");
	}
    
	// 测试添加
	@Test
	public void testSave() {
		for (int i = 5; i < 10; ++i) {
			try {
				//userService.save(new TUser("大宝" + i, "gbx" + i, "123", "男","gbaoxing@163.com", "18766965912", "123456", "山东省德州市", 1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//测试删除
	@Test
	public void testDelete() {
		TUser user = new TUser();
		user.setId(2);
		try {
			userService.delete(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试删除
	@Test
	public void testDeleteAll() {
		List<String> ids = new ArrayList<String>();
		for (int i = 3; i <= 4; ++i) {
			ids.add(i + "");
		}
		try { 
			userService.deleteAll(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//测试更新
	@Test
	public void testUpdate() {
		try {
			TUser user = userService.queryById(3);
			user.setRealName("你行");
			userService.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//测试根据ID查询
	@Test
	public void testQueryById() {
		TUser user = null;
		try {
			user = userService.queryById(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("3号用户信息 : " + user.getRealName() + user.getUserName());
	}

	//测试检查用户合法性
	@Test
	public void testCheckLegal() {
		TUser user = null;
		try {
			user = userService.checkLegal("gbx", "123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user == null) {
			System.out.println("非法用户");
		} else{
			System.out.println("合法用户， 允许登录");
		}
		
	}
	//测试获得按条件查询总页数
	@Test
	public void testGetAllPage() {
		TUser user = new TUser();
		user.setRealName("大宝");
		int allPage = 0;
		try {
			//allPage = userService.getAllPage(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("allPage = " + allPage);
	}
	//测试按条件查询
	@Test
	public void testQuery() {
		TUser user = new TUser();
		user.setRealName("大宝");
		try {
			//List<Map<String, Object>> users = userService.query(user);
			/*for (Map<String, Object> map : users) {
				System.out.println("一条用户： ");
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					System.out.println(entry.getKey() + " : " + entry.getValue());
				} 
			}*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试获得所有用户
	@Test
	public void testQueryUsers() {
		List<TUser> users = null;
		try {
			users = userService.queryUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TUser user : users) {
			System.out.println(user.getUserName() + " : " + user.getPassword() + " : " + user.getRealName());
		}
		
	}

	@Test
	public void testSetCurrentPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPageSize() {
		fail("Not yet implemented");
	}

}
