package cn.hbook.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.hbook.bean.TBook;
import cn.hbook.service.IBookService;
import cn.hbook.service.IOrderService;
import cn.hbook.vo.CartItem;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//业务逻辑类
	private IOrderService orderService;  
	private IBookService bookService;
	
	
	private Integer bookid; //否买的书
	private Integer buyNum = 1; //购买的数量
	private String isbn; //书籍的isbn
	
	
	//加入购物车
	@SuppressWarnings("unchecked")
	public String addToCart() throws Exception {
		System.out.println("bookid = " + bookid + " buyNum = " + buyNum);
		//获得要够买的书籍
		TBook book = bookService.queryById(bookid);
		//构造购物车中物件
		CartItem cartItem = new CartItem(book, buyNum, buyNum*book.getHbPrice(), new Date());
		//拿出购物车来  注意这里用的HttpSession
		HttpSession session =  ServletActionContext.getRequest().getSession();
		//测试可以去除user
		/*TUser user = (TUser)session.getAttribute("user");
		System.out.println("user = " + user.getUserName());*/
		Map<String, CartItem> cart = (Map<String, CartItem>)session.getAttribute("cart");
		//购物车不存在
		if (cart == null) {
			cart = new HashMap<String, CartItem>();
		} 
		/*
		 * 检查之前是否购买过，如果已经购买过，累计加1
		 */
		String isbn = book.getIsbn();
		if(cart.get(isbn) != null) {
			CartItem item = cart.get(isbn);
			item.setNum(item.getNum() + buyNum);
			item.setPrice(item.getNum()*item.getBook().getHbPrice());
			
		} else {
			cart.put(book.getIsbn(), cartItem); 
		}
		//收到想客户端发送cookie， 实现关闭浏览器之后还能访问session中的值
	    String jsessionid = session.getId();
		System.out.println("jsessionid = " + jsessionid);   
	    
		Cookie cookie = new Cookie("JSESSIONID", jsessionid);  //request.getSession();默认发送的cookie的名字为JSESSIONID
	    cookie.setMaxAge(14*24*60*60); //保存两周
		cookie.setPath("/MyHBook");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(cookie);
		session.setAttribute("cart", cart);
		/*
		 * 打印购物车
		 */
		System.out.println("cart detail :");
		Iterator<String> it =  cart.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			CartItem value = cart.get(key);
			System.out.println("key = " + key + " value = " + value.getBook().getName());
		}
		
		return "addToCart";
	}
	public String delete() {
		HttpSession session =  ServletActionContext.getRequest().getSession();
		@SuppressWarnings("unchecked")
		Map<String, CartItem> cart = (Map<String, CartItem>)session.getAttribute("cart");
		cart.remove(isbn);
		
		/*
		 * 打印购物车
		 */
		System.out.println("cart detail :");
		Iterator<String> it =  cart.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			CartItem value = cart.get(key);
			System.out.println("key = " + key + " value = " + value.getBook().getName());
		}
		
		session.setAttribute("cart", cart);
		return "delete";
	}
	
	
	
	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public Integer getBuyNum() {
		return buyNum;
	}


	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	

	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
