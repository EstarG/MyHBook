package cn.hbook.web;

import org.apache.struts2.ServletActionContext;

import cn.hbook.bean.TBook;
import cn.hbook.bean.TUser;
import cn.hbook.service.IBookService;
import cn.hbook.service.IEvaluateService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class EvaluateAction extends ActionSupport implements ModelDriven<TBook>{

	private static final long serialVersionUID = 1L;
	
	/** 书籍业务处理类 */
	private IBookService bookService; 
	
	/** 书籍评级业务处理类  */
	private IEvaluateService evaluateService;
	
	/** 用户 */
	private Integer userId;
	
	/** 分数 */
	private Integer value;
	
	/** 书籍模型  */
	private TBook book;
	
	/**
	 * 评价前的渲染
	 * 
	 * @return
	 * @throws Exception
	 */
	public String indexEvaluate() throws Exception {
		
		book = bookService.queryById(book.getId());
		return "indexEvaluate";
	}
	
	/**
	 *书籍评价
	 * 
	 * @return
	 * @throws Exception
	 */
	public String evaluate() throws Exception {
		userId = ((TUser)ServletActionContext.getRequest().getSession().getAttribute("user")).getId();
		System.out.println("评价参数：  userId = " + userId + " booid = " + book.getId() + " value = " + value);
		
		evaluateService.evaluate(userId, book.getId(), value);
		ServletActionContext.getRequest().setAttribute("message", "评价成功");
		return "evaluate";
	}


	public TBook getModel() {
		return book;
	}

	/**
	 * @return the bookService
	 */
	public IBookService getBookService() {
		return bookService;
	}

	/**
	 * @param bookService the bookService to set
	 */
	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * @return the evaluateService
	 */
	public IEvaluateService getEvaluateService() {
		return evaluateService;
	}

	/**
	 * @param evaluateService the evaluateService to set
	 */
	public void setEvaluateService(IEvaluateService evaluateService) {
		this.evaluateService = evaluateService;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @return the book
	 */
	public TBook getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(TBook book) {
		this.book = book;
	}


}
