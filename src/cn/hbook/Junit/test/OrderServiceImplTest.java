package cn.hbook.Junit.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hbook.bean.TOrder;
import cn.hbook.bean.TOrderdetail;
import cn.hbook.form.QueryOrderInfo;
import cn.hbook.service.IOrderService;

public class OrderServiceImplTest {

	private static IOrderService orderService; 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		orderService = (IOrderService)ctx.getBean("orderService");
		
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
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
	public void testQueryOrders() throws Exception {
		QueryOrderInfo queryOrdertInfo = new QueryOrderInfo();
		queryOrdertInfo.setUserName("gbx");
		List<TOrder> orders = orderService.query(queryOrdertInfo);
		for (TOrder order : orders) {
			System.out.println(order.getId() + " : " + order.getNumTotal() + " : " + order.getPriceTotal() + " : "
					+ order.getTUser().getUserName());
			for (Object obj : order.getTOrderdetails()) {
				TOrderdetail orderdetail = (TOrderdetail)obj;
				System.out.println(orderdetail.getTBook().getName() + " : " + orderdetail.getTUser().getRealName());
			}
		}
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}
	@Test
	public void testQueryById() throws Exception {
		List<TOrder> orders = new ArrayList<TOrder>();
		orders = orderService.queryById(15);
		for (TOrder order : orders) {
			System.out.println(order.getTUser().getUserName());
			for (Object obj : order.getTOrderdetails()) {
				TOrderdetail od = (TOrderdetail)obj;
				System.out.println(od.getTBook().getName());
			}
		}
	}

	@Test
	public void testGetAllPage() {
		fail("Not yet implemented");
	}

}
