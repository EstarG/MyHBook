package cn.hbook.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeanUtils;

import cn.hbook.bean.TBook;
import cn.hbook.bean.TUser;
import cn.hbook.form.QueryBookInfo;
import cn.hbook.service.IBookService;
import cn.hbook.service.IRecommenderService;
import cn.hbook.service.IUserService;
import cn.hbook.utils.MD5;
import cn.hbook.utils.SyscodeUtils;
import cn.hbook.utils.TokenProcessor;
import cn.hbook.vo.BookStore;
import cn.hbook.vo.Category;

import com.opensymphony.xwork2.ActionSupport;

public class UIAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = -334125590224974244L;

	private IBookService bookService; //逻辑处理类
	
	/** 用户信息管理 */
	private IUserService userService;
	
	/** 智能推荐服务 */
	private IRecommenderService recommenderService;
	
	/** 系统编码管理类    */
 	private SyscodeUtils syscodeUtils;
	
	private Map<String, Object> session;
	private QueryBookInfo queryBookInfo = new QueryBookInfo();  //book查询信息
	
	private List<TBook> newBooks = new ArrayList<TBook>();
	private List<TBook> hotBooks = new ArrayList<TBook>();
	private List<TBook> lowBooks = new ArrayList<TBook>();
	private List<TBook> highBooks = new ArrayList<TBook>();
	private List<TBook> usedBooks = new ArrayList<TBook>();
	private TBook randomNewBook = new TBook();
	private TBook randLowBook = new TBook();
	
	/** 书籍分类 */
	private Map<Category, List<Category>> categorys = new TreeMap<Category, List<Category>>();
	 
	private List<Map<String, String>> faCategory;  //书籍分类选择
	private List<Map<String, String>> chCategory;
	
	/** 推荐的书籍  */
	private List<TBook> recommendBooks = new ArrayList<TBook>();
	
	/** 智能推荐图书的数量    */
	private final Integer  RECOMMENDNUM  =   5;
	
	/** 用户权限  */
    private List<Map<String, String>> permissions;
    
    /** 省份选择 */
    private List<Map<String, String>> provinces;
    
    /** 城市选择 */
    private List<Map<String, String>> citys;
    
    /** 浏览过的书籍  */
    private List<TBook> lookedBooks = new ArrayList<TBook>();
    
    /** 猜你喜欢 */
    private List<TBook> likeBooks = new ArrayList<TBook>();
    
    /** 特价书 */
	private TBook specialbook;
	
	/** 一下通过xml实现课外部配置 */
	
	/** 特价书 specialbookId */
	private String specialbookId;
	
	/** 广告的引入 */
	private Map<String, String> advertisement = new HashMap<String, String>();

	/** 热门标签  */
	private Map<String, String> hotTags = new HashMap<String, String>();
	
	/** 图书咨询 */
	private Map<String, String> bookInformation = new HashMap<String, String>();
	
	/** 经典套装书籍  */
	private List<String> bookSuitIds = new ArrayList<String>();
	private List<TBook> bookSuit = new ArrayList<TBook>();
	
	/** 儿童图书专题 */
	private List<String> childrenBooksIds = new ArrayList<String>();
	private List<TBook> childrenBooks = new ArrayList<TBook>();
	
	/** 考试书籍专题 */
	private List<String> examBooksIds = new ArrayList<String>();
	private List<TBook> examBooks = new ArrayList<TBook>();
	
	/** 热门搜索  */
	private Map<String, String> hotSearch = new HashMap<String, String>();
	
	/** 导航下的快捷图标 */
	private Map<String, String> shortKey = new HashMap<String, String>();
	
	/** 滑动推荐 */
	private List<String> slideAdv = new ArrayList<String>();
	
	/** 用户找回密码时接收参数的对象   */
	private TUser user = new TUser();
	
	/** 修改密码时的新密码和重复密码   */
	private String newPassword1;
	private String newPassword2;
	
	/** 热门书店 */
	private List<BookStore> hotBookStores = new ArrayList<BookStore>();
	
	public void init() {
    	faCategory = this.syscodeUtils.getOption("mainCategory");
    	chCategory = this.syscodeUtils.getOption("subCategory");
    }
	
	/**
	 * 初始化权限， 省份， 城市 选择列表
	 */
	public void initUser() {
		this.permissions = syscodeUtils.getOption("permission");
    	this.provinces = syscodeUtils.getOption("province");
    	this.citys = syscodeUtils.getOption("city");
	}
	
	//添加用户的初始化
	public String addUser(){
		/*
		 * 处理初始化操作
		 */
		initUser();
		System.out.println("addUser 操作");
		return "addUser";
	}
	//删除用户的初始化
	public String deleteUser() {
		/*
		 * 处理初始化操作
		 */
		System.out.println("deleteUser 操作");
		return "deleteUser";
	}
	//添加作者
	public String addAuthor() {
		/*
		 * 处理初始化操作
		 */
		System.out.println("addAuthor 操作");
		return "addAuthor";
	}
	//删除作者
	public String deleteAuthor() {
		/*
		 * 处理初始化操作
		 */
		System.out.println("deleteAuthor 操作");
		return "deleteAuthor";
	}
	
	//添加书籍信息， 要得到作者信息， 以及分类信息
	public String addBook() {
		/*
		 * 处理初始化操作
		 */
		init();
		System.out.println("addBook 操作");
		return "addBook";
	}
	public String deleteBook() {
		/*
		 * 处理初始化操作
		 */
		System.out.println("deleteBook 操作");
		return "deleteBook";
	}
	
	/**
	 * 后台更新书籍时高级查找
	 * @return
	 */
	public String adminSearchBook() {
		return "adminSearchBook";
	}
	
	//访问前端主页的初始化
	public String index() throws Exception {
		
		//读取图书分类
		getAllCategory();
		//新书
		bookService.setCurrentPage(1);
		
		bookService.setPageSize(8);
		newBooks = bookService.queryBooksOrderByDate(queryBookInfo);
		
		randomNewBook = newBooks.get(new Random().nextInt(8)); //[0,8)
		System.out.println("newBooks = " + newBooks.size());
		//热门书
		bookService.setPageSize(5);
		hotBooks = bookService.queryBooksOrderBySaleNum(queryBookInfo);
		//低价书
		bookService.setPageSize(5);
		lowBooks = bookService.queryBooksOrderByPrice(queryBookInfo);
		randLowBook = lowBooks.get(new Random().nextInt(5));
		System.out.println("lowBooks = " + lowBooks.size() + " : " + lowBooks + " : " + randLowBook);
		
		//高分书
		bookService.setPageSize(5);
		highBooks = bookService.queryBooksOrderByGrade(queryBookInfo);
		System.out.println("highBooks = " + highBooks.size() + ": " + highBooks);
		//二手书
		bookService.setPageSize(12);
		usedBooks = bookService.queryBooksOrderByUsed(queryBookInfo);
		System.out.println("usedBooks = " + usedBooks.size() + " : " + usedBooks);
		
		//智能推荐书籍
		recommendBooks.clear();
		List<TBook> temp = new ArrayList<TBook>();
		temp.add(cloneBook(newBooks.get(0))); temp.add(cloneBook(hotBooks.get(0))); temp.add(cloneBook(lowBooks.get(0))); 
		temp.add(cloneBook(highBooks.get(0))); temp.add(cloneBook(usedBooks.get(0)));
		for (int i = 0; i < 5; ++i) {
			temp.get(i).setPicture(slideAdv.get(i));
			recommendBooks.add(temp.get(i));
		}
		ServletActionContext.getRequest().getSession().setAttribute("recommendBooks", recommendBooks);
		System.out.println("recommendBooks = " + recommendBooks);
		
		//猜你喜欢
		likeBooks.clear();
		likeBooks = getrecommendBooks();
		System.out.println("猜你喜欢Mahout推荐的书籍： ");
		for (TBook book : likeBooks) {
			System.out.println(book.getId() + " : " + book.getName() + " : " + book.getIsbn() );
		}
		if (likeBooks.size() < RECOMMENDNUM) {
			int needNum = RECOMMENDNUM - likeBooks.size();
			for (int i = 0; i < needNum; ++i) {
				likeBooks.add(hotBooks.get(i));
			}
		}
		ServletActionContext.getRequest().getSession().setAttribute("likeBooks", likeBooks);
		System.out.println("likeBooks = " + likeBooks);
		
		//利用cookie技术实现浏览历史
		HttpServletRequest request =  ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
	    String[] ids = null;
	    for (int i = 0; cookies != null && i < cookies.length; ++i) {
	       if (cookies[i].getName().equals("bookHistory")) {
	          ids = cookies[i].getValue().split("\\,");
	          cookies[i].setMaxAge(0);
	       }
	    }
	    System.out.println("ids = " + ids);
	    lookedBooks = new ArrayList<TBook>();
	    for (int i = 0; ids != null && i < ids.length; ++i) {
	        TBook b = bookService.queryById(Integer.parseInt(ids[i]));
	        lookedBooks.add(b);
	    }
	    session.put("lookedBooks", lookedBooks);
	    
	    //今日特价的书籍
	    specialbook = bookService.queryById(Integer.parseInt(specialbookId));
	    if (specialbook == null) {
	    	specialbook = new TBook();
	    }
	    
	    //经典套装
	    bookSuit.clear();
	    for (String index : bookSuitIds) {
	    	System.out.println("index = " + index);
	    	bookSuit.add(bookService.queryById(Integer.parseInt(index)));
	    }
	    System.out.println("bookSuit = " + bookSuit);
	    
	    //儿童馆
	    childrenBooks.clear();
	    for (String index : childrenBooksIds) {
	    	System.out.println("index = " + index);
	    	childrenBooks.add(bookService.queryById(Integer.parseInt(index)));
	    }
	    System.out.println("childrenBooks = " + childrenBooks);
	    
	    //考试专区
	    examBooks.clear();
	    for (String index : examBooksIds) {
	    	System.out.println("index = " + index);
	    	examBooks.add(bookService.queryById(Integer.parseInt(index)));
	    }
	    System.out.println("examBooks = " + examBooks + examBooks.size());
	    
	    // 将配置的热门搜索放入session中
	    session.put("hotSearch", hotSearch);
	    
	    // 导航下的快捷图标 
	    session.put("shortKey", shortKey);
	    
	    for (BookStore bookstore : hotBookStores) {
	    	System.out.println(bookstore.getName() + " : " + bookstore.getUrl() + " : " + bookstore.getPicture());
	    }
	    //将配置的热门书店放入session中
	    session.put("hotBookStores", hotBookStores);
	    
	    System.out.println("advertisement = " + advertisement.size() + " : " + advertisement);
		return "index";
	}
	
	/**
	 * 复制书籍
	 * @param tBook
	 * @return
	 */
	private TBook cloneBook(TBook tBook) {
		TBook book = new TBook();
		BeanUtils.copyProperties(tBook, book);
		System.out.println("copy book = " + book.getName());
		return book;
	}

	/***
	 * 得到智能推荐的书籍
	 * 
	 * @return
	 * @throws Exception 
	 */
	private List<TBook> getrecommendBooks() throws Exception {
		Integer userId = 0;
		if (session.get("user") != null) {
			userId = ((TUser)session.get("user")).getId(); 
		}
		return recommenderService.baseUserCFRecommender(userId, RECOMMENDNUM);
	}

	/**
	 * 后台系统登录
	 * 
	 * @return
	 */
	public String loginIndex() {
		
		return "loginIndex";
		
	}
	/**
	 * 查询所有分类
	 * @return
	 */
	
	public String getAllCategory() {
		categorys.clear();
		//得到所有的主分类
		List<Map<String, String>> tmainCategorys = syscodeUtils.getOption("mainCategory");
		System.out.println("size = " + tmainCategorys.size());
		//枚举主分类
		for (Map<String, String> mp : tmainCategorys) {
			
			
			System.out.println("id = " + mp.get("fkey") + " name = " + mp.get("fvalue"));
			//构造主分类
			Category mainCategory = new Category(mp.get("fkey"), mp.get("fvalue"));
			List<Category> subCategories = new ArrayList<Category>();
			//枚举子分类
			List<Map<String, String>> tsubCategorys = syscodeUtils.getOption("subCategory", mp.get("fkey"));
		   
			for (Map<String, String> mp2 : tsubCategorys) {
				Category subCategory = new Category(mp2.get("fkey"), mp2.get("fvalue"));
				System.out.println(">>>>id = " + mp2.get("fkey") + " name = " + mp2.get("fvalue"));
				subCategories.add(subCategory);
			}
			categorys.put(mainCategory, subCategories);
		}
		return "getAllCategory";
	}
	
	/**
	 * 前台登录
	 * @return
	 */
	public String login() {
		return "login";
	}
	
	/**
	 * 前台去我的购物车
	 * @return
	 */
	public String gotoMyBuyCart() {
		return "gotoMyBuyCart";
	}
	
	/**
	 * 找回密码
	 * @return
	 * @throws Exception 
	 */
	public String retrive() throws Exception {
		System.out.println("找回密码信息： " + this.user.getUserName() + " : " + this.user.getEmail() + " :  " + this.user.getPhone());
		TUser user = userService.queryUserByName(this.user.getUserName());
		HttpServletRequest request =  ServletActionContext.getRequest();
		String message = "";
		if (null == user) {
			message = "输入信息不正确";
			request.setAttribute("message", message);
			return "retrievePassword";
		}
		if (!this.user.getPhone().equals(user.getPhone())){
			message = "输入手机号信息不正确";
			request.setAttribute("message", message);
			return "retrievePassword";
		}
		if (!this.user.getEmail().equals(user.getEmail())) {
			message = "输入邮箱信息不正确";
			request.setAttribute("message", message);
			return "retrievePassword";
		}
		StringBuffer password = new StringBuffer();
		for (int i = 0; i < 6; ++i){
			password.append(TokenProcessor.letter[new Random().nextInt(62)]);
		}
		
		message = "您的新密码为" + password + "请妥善保管";
		
		user.setPassword(MD5.getMD5(password.toString()));
		userService.update(user);
		
		request.setAttribute("message", message);
		return "retrievePassword";
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws Exception 
	 */
	public String changePassword() throws Exception {
		System.out.println("修改密码信息： " + this.user.getUserName() + " :  " + this.user.getPassword() + " : " + newPassword1 + " : " + newPassword2);
		
		TUser user = userService.checkLegal(this.user.getUserName(),MD5.getMD5(this.user.getPassword()) );
		HttpServletRequest request =  ServletActionContext.getRequest();
		String message = "";
		
		if (null == user) {
			message = "用户名/密码错误";
			request.setAttribute("message", message);
			return "changePassword";
		}
		if (!newPassword1.trim().equals(newPassword2.trim())) {
			message = "新密码两次输入不一致";
			request.setAttribute("message", message);
			return "changePassword";
		}
		//修改密码
		user.setPassword(MD5.getMD5(newPassword1));
		userService.update(user);
		message = "修改成功";
		request.setAttribute("message", message);
		return "changePassword";
	}
	
	private String picture;
	
	public String getPicture() {
		return picture;
	}
	
	public QueryBookInfo getQueryBookInfo() {
		return queryBookInfo;
	}
	public void setQueryBookInfo(QueryBookInfo queryBookInfo) {
		this.queryBookInfo = queryBookInfo;
	}
	public IBookService getBookService() {
		return bookService;
	}
	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public List<TBook> getNewBooks() {
		return newBooks;
	}
	public void setNewBooks(List<TBook> newBooks) {
		this.newBooks = newBooks;
	}
	public List<TBook> getHotBooks() {
		return hotBooks;
	}
	public void setHotBooks(List<TBook> hotBooks) {
		this.hotBooks = hotBooks;
	}
	public List<TBook> getLowBooks() {
		return lowBooks;
	}
	public void setLowBooks(List<TBook> lowBooks) {
		this.lowBooks = lowBooks;
	}
	public List<TBook> getHighBooks() {
		return highBooks;
	}
	public void setHighBooks(List<TBook> highBooks) {
		this.highBooks = highBooks;
	}
	public List<TBook> getUsedBooks() {
		return usedBooks;
	}
	public void setUsedBooks(List<TBook> usedBooks) {
		this.usedBooks = usedBooks;
	}
	public TBook getRandLowBook() {
		return randLowBook;
	}
	public void setRandLowBook(TBook randLowBook) {
		this.randLowBook = randLowBook;
	}
	public TBook getRandomNewBook() {
		return randomNewBook;
	}
	public void setRandomNewBook(TBook randomNewBook) {
		this.randomNewBook = randomNewBook;
	}
	public SyscodeUtils getSyscodeUtils() {
		return syscodeUtils;
	}
	public void setSyscodeUtils(SyscodeUtils syscodeUtils) {
		this.syscodeUtils = syscodeUtils;
	}
	public Map<Category, List<Category>> getCategorys() {
		return categorys;
	}
	public void setCategorys(Map<Category, List<Category>> categorys) {
		this.categorys = categorys;
	}
	public List<Map<String, String>> getFaCategory() {
		return faCategory;
	}
	public void setFaCategory(List<Map<String, String>> faCategory) {
		this.faCategory = faCategory;
	}
	public List<Map<String, String>> getChCategory() {
		return chCategory;
	}
	public void setChCategory(List<Map<String, String>> chCategory) {
		this.chCategory = chCategory;
	}

	/**
	 * @return the permissions
	 */
	public List<Map<String, String>> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<Map<String, String>> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the provinces
	 */
	public List<Map<String, String>> getProvinces() {
		return provinces;
	}

	/**
	 * @param provinces the provinces to set
	 */
	public void setProvinces(List<Map<String, String>> provinces) {
		this.provinces = provinces;
	}

	/**
	 * @return the citys
	 */
	public List<Map<String, String>> getCitys() {
		return citys;
	}

	/**
	 * @param citys the citys to set
	 */
	public void setCitys(List<Map<String, String>> citys) {
		this.citys = citys;
	}

	/**
	 * @return the recommendBooks
	 */
	public List<TBook> getRecommendBooks() {
		return recommendBooks;
	}

	/**
	 * @param recommendBooks the recommendBooks to set
	 */
	public void setRecommendBooks(List<TBook> recommendBooks) {
		this.recommendBooks = recommendBooks;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the lookedBooks
	 */
	public List<TBook> getLookedBooks() {
		return lookedBooks;
	}

	/**
	 * @param lookedBooks the lookedBooks to set
	 */
	public void setLookedBooks(List<TBook> lookedBooks) {
		this.lookedBooks = lookedBooks;
	}

	/**
	 * @return the likeBooks
	 */
	public List<TBook> getLikeBooks() {
		return likeBooks;
	}

	/**
	 * @param likeBooks the likeBooks to set
	 */
	public void setLikeBooks(List<TBook> likeBooks) {
		this.likeBooks = likeBooks;
	}

	/**
	 * @return the specialbook
	 */
	public TBook getSpecialbook() {
		return specialbook;
	}

	/**
	 * @param specialbook the specialbook to set
	 */
	public void setSpecialbook(TBook specialbook) {
		this.specialbook = specialbook;
	}

	/**
	 * @return the specialbookId
	 */
	public String getSpecialbookId() {
		return specialbookId;
	}

	/**
	 * @param specialbookId the specialbookId to set
	 */
	public void setSpecialbookId(String specialbookId) {
		this.specialbookId = specialbookId;
	}

	/**
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * @return the advertisement
	 */
	public Map<String, String> getAdvertisement() {
		return advertisement;
	}

	/**
	 * @param advertisement the advertisement to set
	 */
	public void setAdvertisement(Map<String, String> advertisement) {
		this.advertisement = advertisement;
	}

	/**
	 * @return the hotTags
	 */
	public Map<String, String> getHotTags() {
		return hotTags;
	}

	/**
	 * @param hotTags the hotTags to set
	 */
	public void setHotTags(Map<String, String> hotTags) {
		this.hotTags = hotTags;
	}

	/**
	 * @return the recommenderService
	 */
	public IRecommenderService getRecommenderService() {
		return recommenderService;
	}

	/**
	 * @param recommenderService the recommenderService to set
	 */
	public void setRecommenderService(IRecommenderService recommenderService) {
		this.recommenderService = recommenderService;
	}

	/**
	 * @return the bookInformation
	 */
	public Map<String, String> getBookInformation() {
		return bookInformation;
	}

	/**
	 * @param bookInformation the bookInformation to set
	 */
	public void setBookInformation(Map<String, String> bookInformation) {
		this.bookInformation = bookInformation;
	}

	/**
	 * @return the bookSuitIds
	 */
	public List<String> getBookSuitIds() {
		return bookSuitIds;
	}

	/**
	 * @param bookSuitIds the bookSuitIds to set
	 */
	public void setBookSuitIds(List<String> bookSuitIds) {
		this.bookSuitIds = bookSuitIds;
	}

	/**
	 * @return the bookSuit
	 */
	public List<TBook> getBookSuit() {
		return bookSuit;
	}

	/**
	 * @param bookSuit the bookSuit to set
	 */
	public void setBookSuit(List<TBook> bookSuit) {
		this.bookSuit = bookSuit;
	}

	/**
	 * @return the childrenBooksIds
	 */
	public List<String> getChildrenBooksIds() {
		return childrenBooksIds;
	}

	/**
	 * @param childrenBooksIds the childrenBooksIds to set
	 */
	public void setChildrenBooksIds(List<String> childrenBooksIds) {
		this.childrenBooksIds = childrenBooksIds;
	}

	/**
	 * @return the childrenBooks
	 */
	public List<TBook> getChildrenBooks() {
		return childrenBooks;
	}

	/**
	 * @param childrenBooks the childrenBooks to set
	 */
	public void setChildrenBooks(List<TBook> childrenBooks) {
		this.childrenBooks = childrenBooks;
	}

	/**
	 * @return the examBooksIds
	 */
	public List<String> getExamBooksIds() {
		return examBooksIds;
	}

	/**
	 * @param examBooksIds the examBooksIds to set
	 */
	public void setExamBooksIds(List<String> examBooksIds) {
		this.examBooksIds = examBooksIds;
	}

	/**
	 * @return the examBooks
	 */
	public List<TBook> getExamBooks() {
		return examBooks;
	}

	/**
	 * @param examBooks the examBooks to set
	 */
	public void setExamBooks(List<TBook> examBooks) {
		this.examBooks = examBooks;
	}

	/**
	 * @return the hotSearch
	 */
	public Map<String, String> getHotSearch() {
		return hotSearch;
	}

	/**
	 * @param hotSearch the hotSearch to set
	 */
	public void setHotSearch(Map<String, String> hotSearch) {
		this.hotSearch = hotSearch;
	}

	/**
	 * @return the shortKey
	 */
	public Map<String, String> getShortKey() {
		return shortKey;
	}

	/**
	 * @param shortKey the shortKey to set
	 */
	public void setShortKey(Map<String, String> shortKey) {
		this.shortKey = shortKey;
	}

	/**
	 * @return the slideAdv
	 */
	public List<String> getSlideAdv() {
		return slideAdv;
	}

	/**
	 * @param slideAdv the slideAdv to set
	 */
	public void setSlideAdv(List<String> slideAdv) {
		this.slideAdv = slideAdv;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public List<BookStore> getHotBookStores() {
		return hotBookStores;
	}

	public void setHotBookStores(List<BookStore> hotBookStores) {
		this.hotBookStores = hotBookStores;
	}
}
