package cn.hbook.Junit.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cn.hbook.bean.TBook;
import cn.hbook.bean.TOrder;
import cn.hbook.bean.TOrderdetail;
import cn.hbook.bean.TUser;
import cn.hbook.form.QueryOrderDetailInfo;
import cn.hbook.service.IOrderDetailService;

public class OrderDetailServiceImplTest {

	private static IOrderDetailService detailService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		detailService = (IOrderDetailService)ctx.getBean("orderDetailService");
	}

	@Test
	public void testSave() {
		TUser user = new TUser(1);
		TOrder order = new TOrder(1);
		TBook book = new TBook(22);
		TOrderdetail orderdetail = new TOrderdetail(order, book, user, 3, 44.3, new Date());
		try {
			detailService.save(orderdetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryOrderDetails() {
		List<TOrderdetail> ods = new ArrayList<TOrderdetail>();
		try {
			ods = detailService.queryOrderDetails();
			for (TOrderdetail od : ods ) {
				System.out.println(od.getSaleDate() + " : " + od.getTUser().getUserName() + " : " + od.getTBook().getName() + " : " + od.getTOrder().getPriceTotal());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testQuery() {
		QueryOrderDetailInfo detailInfo = new QueryOrderDetailInfo();
		detailInfo.setUserName("");
		try {
			List<TOrderdetail> ods = detailService.query(detailInfo);
			for (TOrderdetail od : ods) {
				System.out.println(od.getSaleDate() + " : " + od.getTUser().getUserName() + " : " + od.getTBook().getName() + " : " + od.getTOrder().getPriceTotal());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetAllPage() {
		QueryOrderDetailInfo detailInfo = new QueryOrderDetailInfo();
		detailInfo.setUserName("gbx");
		try {
			Integer allPage = detailService.getAllPage(detailInfo);
			System.out.println(allPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
