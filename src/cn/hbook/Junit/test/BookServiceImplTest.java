package cn.hbook.Junit.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hbook.bean.TAuthor;
import cn.hbook.bean.TBook;
import cn.hbook.bean.TDescription;
import cn.hbook.form.QueryBookInfo;
import cn.hbook.service.IBookService;

public class BookServiceImplTest {

	private static IBookService bookService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookService = (IBookService)ctx.getBean("bookService");
	}

	@Test
	public void testSave() {
		for (int i = 24; i <= 44; ++i) {
			TDescription description = new TDescription();
			description.setAuthorInfo(
					" 鲁迅离开公寓避难。十二月十四晚上脊肉作鲁迅先生葬礼 " + 
					"痛，盗汗。1936年一月肩及肋都疼得厉害。三月二日下午骤然气喘。五月十五日病情又严重，医生说是胃病。从这之后一直发烧，" +
					"三十一日，史沫特莱女士引美国邓医生来诊断，病情非常危险。");
			description.setCatalogInfo("鲁迅离开公寓避难。十二月十四晚上脊肉作鲁迅先生葬礼 " + 
					"痛，盗汗。1936年一月肩及肋都疼得厉害。三月二日下午骤然气喘。五月十五日病情又严重，医生说是胃病。从这之后一直发烧，" +
					"三十一日，史沫特莱女士引美国邓医生来诊断，病情非常危险。");
			description.setContent(" 鲁迅离开公寓避难。十二月十四晚上脊肉作鲁迅先生葬礼 " + 
					"痛，盗汗。1936年一月肩及肋都疼得厉害。三月二日下午骤然气喘。五月十五日病情又严重，医生说是胃病。从这之后一直发烧，" +
					"三十一日，史沫特莱女士引美国邓医生来诊断，病情非常危险。");
			
			TBook book = new TBook("isbnacb" + (i - 19), "java程序设计" + (i - 19), "机械出版社", new Date(), 39.0, 5.0, "images/book3.jpg", 1, 30, 45, 23, 1);
			book.setTDescription(description);
			
			description.setTBook(book); //在主对象这一段设置cascade=all 这样书的保存就会对description产生影响啦
			Set<TAuthor> authors = new HashSet<TAuthor>();
			authors.add(new TAuthor(12));
			//authors.add(new TAuthor(6));
			book.setTAuthors(authors);
			try {
				bookService.save(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		TDescription description = new TDescription();
//		description.setAuthorInfo("小明是一个热衷于文学创作的作家");
//		description.setCatalogInfo("1:文学 2:小说");
//		description.setContent("你行我行大家行");
//		TBook book = new TBook("123", "java程序设计" + "test1", "电子工业出版社", new Date(), 33.3, 4.4, "/images/book1.jpg", 1, 90, 45, 23, 0);
//		
//		book.setTDescription(description);
//		description.setTBook(book); //在主对象这一段设置cascade=all 这样书的保存就会对description产生影响啦
//		
//		Set<TAuthor> authors = new HashSet<TAuthor>();
//		authors.add(new TAuthor(1));
//		authors.add(new TAuthor(6));
//		
//		book.setTAuthors(authors);
//		
//		try {
//			bookService.save(book);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void testQueryBooksOrderByDate() throws Exception {
		
		bookService.setPageSize(8);
		Integer allPage = bookService.getAllPage(new QueryBookInfo());
		
		System.out.println(allPage);
//		QueryBookInfo queryBookInfo = new QueryBookInfo();
//		
//		List<TBook> newBooks = bookService.queryBooksOrderByDate(queryBookInfo);
//		
//		for (TBook b : newBooks) {
//			System.out.println(b.getPicture() + b.getName() + b.getId() + b.getPublishDate());
//		}
	}
	@Test
	public void u() throws Exception {
		QueryBookInfo queryBookInfo = new QueryBookInfo();
		//queryBookInfo.setIsLimit(1);
		List<TBook> newBooks = bookService.queryBooksOrderByUsed(queryBookInfo);
		
		for (TBook b : newBooks) {
			System.out.println(b.getPicture() + b.getName() + b.getId() + b.getPublishDate());
		}
	}
	@Test
	public void testDelete() {
		try {
			bookService.delete(new TBook(17));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void testDeleteAll() {
		List<String> ids = new ArrayList<String>();
		ids.add(18 + ""); ids.add(19 + "");
		try {
			bookService.deleteAll(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testUpdate() {
		TBook book = null;
		try {
			book = bookService.queryById(23);
			book.setName("C++TTT");
			TDescription des =  book.getTDescription();
			des.setContent("C++是本好书");
			book.setTDescription(des);
			bookService.update(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryById() {
		try {
			TBook book = bookService.queryById(20);
			System.out.println(book.getName() + book.getPublishDate() + "概述的Des" + book.getTDescription().getContent());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryBooks() {
		List<TBook> books = new ArrayList<TBook>();
		try {
			books = bookService.queryBooks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TBook book : books) {
			System.out.println(book.getName());
			System.out.println(book.getTDescription().getContent());
		}
	}

	@Test
	public void testGetAllPage() {
		try {
			//Integer allPage = bookService.getAllPage(new TBook(), new QueryBookInfo());
			//System.out.println("allPage = " + allPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    /*
     * 未通过
     */
	@Test
	public void testQuery() {
		TBook book = new TBook(); book.setName("Java");
		try {
			List<TBook> books = bookService.query(new QueryBookInfo());
			int count = 0;
			for (TBook b : books) {
				System.out.println("第" + (++count) + "本书");
				System.out.println(b.getName());
				for (Object a : b.getTAuthors()) {
					TAuthor au = (TAuthor) a;
					System.out.println("author" + au.getName());
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testSetCurrentPage() {
	}

	@Test
	public void testSetPageSize() {
	}

	@Test
	public void testGetBookDao() {
	}

	@Test
	public void testSetBookDao() {
	}

	@Test
	public void testGetCurrentPage() {
	}

	@Test
	public void testGetPageSize() {
	}

}
