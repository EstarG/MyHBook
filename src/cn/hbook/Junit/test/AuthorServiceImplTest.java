package cn.hbook.Junit.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hbook.bean.TAuthor;
import cn.hbook.service.IAuthorService;

public class AuthorServiceImplTest {
	private static IAuthorService authorService = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		authorService = (IAuthorService)ctx.getBean("authorService");
	}
	//测试保存作者信息

	@Test
	public void testSave() {
	
		for (int i = 5; i < 10; ++i) {
			TAuthor author = new TAuthor("大宝" + i, "高富帅" + i);
			try {
				authorService.save(author);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//测试删除
	@Test
	public void testDelete() {
		TAuthor author = new TAuthor(2);
		try {
			authorService.delete(author);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试批量删除作者
	@Test
	public void testDeleteAll() {
		List<String> ids = new ArrayList<String>();
		ids.add(3 + ""); ids.add(4 + "");
		try {
			authorService.deleteAll(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//测试更新作者信息
	@Test
	public void testUpdate() {
		try {
			TAuthor author = authorService.queryById(5);
			author.setName("小明");
			authorService.update(author);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//测试通过作者的ID查询作者
	@Test
	public void testQueryById() {
		try {
			TAuthor author = authorService.queryById(1);
			System.out.println(author.getName() + " : " + author.getInfor() + "书的数量" + author.getTBooks().size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//测试查找所有作者
	@Test
	public void testQueryAuthors() {
		try {
			List<TAuthor> authors = authorService.queryAuthors();
			for (TAuthor author : authors) {
				System.out.println(author.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 测试按条件查询获得的所有满足作者的信息
	@Test
	public void testQuery() {
		TAuthor author = new TAuthor();
		author.setName("大宝");
		
		try {
			List<TAuthor> authors = authorService.query(author);
			for (TAuthor a : authors) {
				System.out.println(a.getName() + " " + a.getInfor());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//测试获得按条件查询得到作者信息的总页数
	@Test
	public void testGetAllPage() {
		TAuthor author = new TAuthor();
		author.setName("大宝");
		try {
			Integer allPage = authorService.getAllPage(author);
			System.out.println(allPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
