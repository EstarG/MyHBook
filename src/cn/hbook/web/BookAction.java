package cn.hbook.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.Cookie;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import cn.hbook.bean.TAuthor;
import cn.hbook.bean.TBook;
import cn.hbook.bean.TDescription;
import cn.hbook.form.QueryBookInfo;
import cn.hbook.service.IAuthorService;
import cn.hbook.service.IBookService;
import cn.hbook.utils.SyscodeUtils;
import cn.hbook.utils.TokenProcessor;
import cn.hbook.vo.Category;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class BookAction extends ActionSupport implements ModelDriven<TBook>, RequestAware{
	private IBookService bookService; //业务处理类
	private IAuthorService authorService;
	
	/** 系统编码管理 */
	private SyscodeUtils sysutil;
	
	private String fkey;
	
	private QueryBookInfo queryBookInfo = new QueryBookInfo(); //收集前端查询信息类
	private TDescription description = new TDescription(); //每本书都有一个书籍描述
	private TBook book = new TBook(); //查询一本书籍信息的记录,
	private List<TBook> books = new ArrayList<TBook>(); // 查询所有书籍的记录
	private Map<String, Object> request; //存放更新消息
	private List<String> ids = new ArrayList<String>(); // 批量删除时全选的id
	private String inputBookIds;  //用户输入的删除ID
	private List<String> authorIds = new ArrayList<String>(); //书籍对应额作者
	private String inputAuthorIds; //用户输入的作者的编号
	
	private List<Map<String, String>> faCategory = new ArrayList<Map<String,String>>();  //分类选择
	private List<Map<String, String>> chCategory = new ArrayList<Map<String,String>>();
	private List<Map<String, String>> allCategory = new ArrayList<Map<String,String>>();
	
	/** 书籍分类 */
	private Map<Category, List<Category>> categorys = new TreeMap<Category, List<Category>>();
	
	/** 更新书籍的时候显示二级分类 的名字*/
	private String subCategoryName;
	
	/** 更新作者时显示作者的名字   */
	private String authors;
	
	/** 条件选择查询详情供回显   */
	private String selectDetail;
	
	/** 一下通过xml实现课外部配置 */
	
	/** 特价书 specialbookId */
	private String specialbookId;
	
    /** 特价书 */
	private TBook specialbook;
	
	/** 广告的引入 */
	private Map<String, String> advertisement = new HashMap<String, String>();
	
	
 	//各种查询的结果记录
	private List<TBook> newBooks = new ArrayList<TBook>();
	private List<TBook> hotBooks = new ArrayList<TBook>();
	private List<TBook> lowBooks = new ArrayList<TBook>();
	private List<TBook> highBooks = new ArrayList<TBook>();
	private List<TBook> usedBooks = new ArrayList<TBook>();
	
	private Integer currentPage;  //当前页
    private Integer allPage ; //总页数
    
    
    private File uploadFile;

    private String uploadFileContentType; // 文件的内容类型

    private String uploadFileFileName; // 上传文件名
    
    private String isExitIsbn; //判断输入的isbn是否合法
    
    /** token生成器  */
    private TokenProcessor tokenProcessor;
    
    
    
    /**
     *添加书籍时的分类初始化
     */
    public void init() {
    	List<Map<String, String>> temp = this.sysutil.getOption("mainCategory");
    	Map<String, String>  initMap = new HashMap<String, String>();
    	initMap.put("fkey", "0"); initMap.put("fvalue", "请选择类型");  //添加请选择类型的选项
    	faCategory.add(initMap);
    	faCategory.addAll(temp);
    	
    	Map<String, String>  initMap2 = new HashMap<String, String>(); 
    	List<Map<String, String>> temp2 = this.sysutil.getOption("subCategory");
    	initMap2.put("fkey", "0"); initMap2.put("fvalue", "请选择子类型");  //添加请选择子类型的选项
     	chCategory.add(initMap2);
     	chCategory.addAll(temp2);
    }
    
    /**
     * 左侧书籍分类导航栏的初始化
     * @return
     */
	public String initAllCategory() {
		categorys.clear();
		//得到所有的主分类
		List<Map<String, String>> tmainCategorys = sysutil.getOption("mainCategory");
		System.out.println("size = " + tmainCategorys.size());
		//枚举主分类
		for (Map<String, String> mp : tmainCategorys) {
			
			
			System.out.println("id = " + mp.get("fkey") + " name = " + mp.get("fvalue"));
			//构造主分类
			Category mainCategory = new Category(mp.get("fkey"), mp.get("fvalue"));
			List<Category> subCategories = new ArrayList<Category>();
			//枚举子分类
			List<Map<String, String>> tsubCategorys = sysutil.getOption("subCategory", mp.get("fkey"));
		   
			for (Map<String, String> mp2 : tsubCategorys) {
				Category subCategory = new Category(mp2.get("fkey"), mp2.get("fvalue"));
				System.out.println(">>>>id = " + mp2.get("fkey") + " name = " + mp2.get("fvalue"));
				subCategories.add(subCategory);
			}
			categorys.put(mainCategory, subCategories);
		}
		return "getAllCategory";
	}

    
    //添加图书
    public String add() throws Exception {
    	System.out.println("添加图书");
    	//处理一下空格
    	description.setContent(description.getContent().trim());
    	description.setCatalogInfo(description.getCatalogInfo().trim());
    	description.setAuthorInfo(description.getAuthorInfo().trim());
    	//给书添加描述
    	book.setTDescription(description);
    	description.setTBook(book);
    	System.out.println("des = " + description.getContent() + description.getAuthorInfo());
    	
    	//给书添加作者
    	System.out.println("添加的作者的编号： " + queryBookInfo.getAuthorIds());
    	authorIds = Arrays.asList( queryBookInfo.getAuthorIds().split(";") );
    	Set<TAuthor> authors = new HashSet<TAuthor>();
    	for (String authorId : authorIds) {
    		System.out.println("author id = " + authorId);
    		authors.add( authorService.queryById(Integer.parseInt(authorId)));  //必须都找出来否则作者的信息会被更新
    	}
    	book.setTAuthors(authors);
    	//保存图书的图片 名字=isbn + 唯一token
    	System.out.println(uploadFile + " " + uploadFileContentType + " " + uploadFileFileName);
    	String RealPath = ServletActionContext.getServletContext().getRealPath("/images");
    	System.out.println("RealPath = " + RealPath);
		File file = new File(RealPath);
		FileUtils.copyFile(uploadFile, new File(file,book.getIsbn() + uploadFileFileName));
    	
    	book.setPicture("images/" + book.getIsbn() + uploadFileFileName);
    	book.setHbPrice(book.getPrice()*book.getDiscount()/10.0);
    	book.setSaleNum(0); //新添加的书籍默认销量为0
    	//默认给予5分评分
    	book.setGrade(5);
    	
    	bookService.save(book);
    	request.put("message", "添加成功");
    	return "addSuccess";
    }
    //删除图书
    public String delete() throws Exception {
    	bookService.delete(book);
    	request.put("message", "删除成功"); //设置提示消息
    	return "delete";
    }
    //批量删除图书
    public String deleteAll() throws Exception {
    	//获得用户输入的要删除的书的ID
    	if (inputAuthorIds != null && !"".equals(inputAuthorIds.trim())) {
    		List<String> inuptID = Arrays.asList( inputAuthorIds.split(";") );
    		ids.addAll(inuptID);
    	}
    	bookService.deleteAll(ids);
    	request.put("message", "删除成功"); //设置提示消息
    	ids.clear();  //防止返回到前段默认都选择了
    	return "delete";
    }
    // 通过各种条件查询
    public String query() throws Exception {
    	//初始化选择项
    	init();
    	//设置当前页
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setCurrentPage(currentPage);
		System.out.println("是否是二手书 " + " : " + queryBookInfo.getUsed());
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
		//查询结果
		books = this.bookService.query(queryBookInfo);
    	return "query";
    }
    
    /**
     * 更新书籍前的查询
     * @return
     * @throws Exception 
     */
    public String updateQuery() throws Exception {
    	System.out.println("id = " + queryBookInfo.getId() + "isbn = " + queryBookInfo.getIsbn()
    			+ "name = " + queryBookInfo.getName());
    	books = this.bookService.query(queryBookInfo);
    	System.out.println("books = " + books);
    	return "updateQuery";
    }
    
    //通过ID查询图书
    public String queryById() throws Exception {
    	//初始化书籍分类
    	init();
    	System.out.println("book信息" + book.getId());
    	this.book = bookService.queryById(book.getId());
    	System.out.println("chCategory = " + chCategory + "\n" + "book type = " + book.getType());
    	
    	//初始化二级分类名字
    	for (Map<String, String> mp : chCategory) {
    		if ( mp.get("fkey").equals(book.getType() + "")) {
    			this.subCategoryName = mp.get("fvalue");
    			break;
    		}
    	}
    	System.out.println("subCategoryName = " + subCategoryName);
    	//作者编号信息
    	StringBuilder sb = new StringBuilder();  
    	//作者姓名信息
    	this.authors = "";
    	for (Object obj : this.book.getTAuthors()) {
    		TAuthor author = (TAuthor)obj;
    		sb.append(author.getId()+";");
    		this.authors = this.authors + author.getName() + ";";
    	}
    	if (sb.length() > 0) queryBookInfo.setAuthorIds(sb.toString());
    	System.out.println("作者的编号: " + queryBookInfo.getAuthorIds());
    	return "queryById";
    }
    //查询图书详细信息
    public String queryBookDetail() throws Exception {
    	init();
    	System.out.println("book id = " + book.getId());
    	this.book = bookService.queryById(book.getId());
    	return "queryBookDetail";
    }
    
    /**
     * 更新图书信息
     * 
     * @return
     * @throws Exception
     */
    public String update() throws  Exception {
    	//初始化书籍分类
    	init();
    	System.out.println("更新图书");
    	//处理一下空格
    	description.setContent(description.getContent().trim());
    	description.setCatalogInfo(description.getCatalogInfo().trim());
    	description.setAuthorInfo(description.getAuthorInfo().trim());
    	System.out.println("description Content =" + description.getContent());
    	//给书添加描述
    	book.setTDescription(description);
    	description.setTBook(book);
    	System.out.println("des =" + description.getContent() + description.getAuthorInfo());
    	//给书添加作者
    	System.out.println("添加的作者的编号： " + queryBookInfo.getAuthorIds());
    	authorIds = Arrays.asList( queryBookInfo.getAuthorIds().split(";") );
    	Set<TAuthor> authors = new HashSet<TAuthor>();
    	for (String authorId : authorIds) {
    		System.out.println("author id = " + authorId);
    		authors.add( authorService.queryById(Integer.parseInt(authorId)));  //必须都找出来否则作者的信息会被更新
    	}
    	book.setTAuthors(authors);
    	//如果更改了图片
    	System.out.println("uploadFile = " + uploadFile + " " + uploadFileContentType + " " + uploadFileFileName);
    	if (uploadFile != null) {
	    	String RealPath = ServletActionContext.getServletContext().getRealPath("/images");
	    	System.out.println("RealPath = " + RealPath);
			File file = new File(RealPath);
			FileUtils.copyFile(uploadFile, new File(file,book.getIsbn() + uploadFileFileName));
			book.setPicture("images/" + book.getIsbn() + uploadFileFileName);
    	}
    	//更新价格
    	book.setHbPrice(book.getPrice()*book.getDiscount()/10.0);
    	
    	bookService.update(book);
    	//初始化二级分类名字
    	for (Map<String, String> mp : chCategory) {
    		if ( mp.get("fkey").equals(book.getType() + "")) {
    			this.subCategoryName = mp.get("fvalue");
    			break;
    		}
    	}
    	request.put("message", "更新成功");
    	return "update";
    }
    
    //异步Ajax
    //书籍的分类的查询
    public String getSubCategory() {
    	System.out.println("fkey = " + fkey);
    	chCategory = sysutil.getOption("subCategory", fkey);
    	if (null == chCategory) chCategory = new ArrayList<Map<String,String>>();
    	Map<String, String>  initMap = new HashMap<String, String>();
    	initMap.put("fkey", "0"); initMap.put("fvalue", "请选子类型");
    	chCategory.add(initMap);
    	for (Map<String, String> mp : chCategory) {
    		System.out.println(mp.get("fkey") + " : " + mp.get("fvalue"));
    	}
    	return "getSubCategory";
    }
    //查询ISBN是否存在
    public String isExistISBN() throws Exception {
    	System.out.println("查询ISBN是否存在 : book ISBN = " + book.getIsbn());
    	TBook tbook = bookService.queryBookByIsbn(book.getIsbn());
    	if (tbook != null) {
    		isExitIsbn = "0";
    	} else {
    		isExitIsbn = "1";
    	}
    	return "isExistISBN";
    }
    
    
    //客户端操作
    
    
   // private String authorNames = "";
    public String clientQueryById() throws Exception {
    	//左侧书籍分类的初始化
    	initAllCategory();
    	
    	System.out.println("book信息" + book.getId());
    	this.book = bookService.queryById(book.getId());
    	
    	//：重写cookie 
        String value = buildCookie(book.getId() + "", request);    
        System.out.println("value = " + value);
        Cookie cookie = new Cookie("bookHistory", value);
        cookie.setMaxAge(30*24*60*60); //设置缓存一个月
        cookie.setPath("/MyHBook");
        ServletActionContext.getResponse().addCookie(cookie);
        
       //今日特价的书籍
	    specialbook = bookService.queryById(Integer.parseInt(specialbookId));
	    if (specialbook == null) {
	    	specialbook = new TBook();
	    }
    	return "clientQueryById";
    }

    
    private String buildCookie(String id, Map<String, Object> request2) {
    	 String bookHistory = null;
         Cookie[] cookies = ServletActionContext.getRequest().getCookies();
         for (int i = 0; cookies != null && i < cookies.length; ++i) {
             if (cookies[i].getName().equals("bookHistory")) {
                 bookHistory = cookies[i].getValue();
                 break;
             }
         }
         if (bookHistory == null) return id;
         LinkedList<String> ids = new LinkedList<String>(Arrays.asList(bookHistory.split("\\,")));
         if (ids.contains(id)) {
             ids.remove(id);
         } else {
             if (ids.size() >= 4) {
                 ids.removeLast();
             }
         }
         ids.addFirst(id);
         StringBuilder sb = new StringBuilder();
         for (String i : ids) {
             sb.append(i + ",");
         }
         sb.deleteCharAt(sb.length() - 1);
          
         System.out.println("sb = " + sb.toString());
         return sb.toString();
	}


	//查询新书
    public String newBooks() throws Exception {
    	@SuppressWarnings("unchecked")
		List<TBook> likeBooks = (List<TBook>)ServletActionContext.getRequest().getSession().getAttribute("likeBooks");
    	System.out.println(" likeBooks = " +  likeBooks);
    	
    	//设置当前页
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setPageSize(8);
		this.bookService.setCurrentPage(currentPage);
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
		//查询结果
		System.out.println("queryInfo " + queryBookInfo.toString() + "selectDetail = " + selectDetail);
		newBooks = this.bookService.queryBooksOrderByDate(queryBookInfo);
		System.out.println("newBooks size = " + newBooks.size());
		
		int i = 1;
		for (TBook book : newBooks) {
			System.out.println(i + ":");
			for (Object obj : book.getTAuthors()) {
				TAuthor author = (TAuthor) obj;
				System.out.println("author name = " + author.getName());
			}
			i++;
		}
		
    	return "newBooks";
    }
    //查询热销书籍
    public String hotBooks() throws Exception {
    	//设置当前页
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setPageSize(8);
		this.bookService.setCurrentPage(currentPage);
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
		//查询结果
		hotBooks = this.bookService.queryBooksOrderBySaleNum(queryBookInfo);
		System.out.println("hotBooks size = " + hotBooks.size());
		
		int i = 1;
		for (TBook book : hotBooks) {
			System.out.println(i + ":");
			for (Object obj : book.getTAuthors()) {
				TAuthor author = (TAuthor) obj;
				System.out.println("author name = " + author.getName());
			}
			i++;
		}
    	return "hotBooks";
    }
    //查询低价书籍
    public String lowBooks() throws Exception {
    	//设置当前页
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setPageSize(8);
		this.bookService.setCurrentPage(currentPage);
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
		//查询结果
		lowBooks = this.bookService.queryBooksOrderByPrice(queryBookInfo);
		System.out.println("lowBooks size = " + lowBooks.size());
		
		int i = 1;
		for (TBook book : lowBooks) {
			System.out.println(i + ":");
			for (Object obj : book.getTAuthors()) {
				TAuthor author = (TAuthor) obj;
				System.out.println("author name = " + author.getName());
			}
			i++;
		}
    	return "lowBooks";
    }
    //查询高评书
    public String highBooks() throws Exception {
    	//设置当前页
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setPageSize(8);
		this.bookService.setCurrentPage(currentPage);
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
		//查询结果
		highBooks = this.bookService.queryBooksOrderByGrade(queryBookInfo);
		System.out.println("highBooks size = " + highBooks.size());
		
		int i = 1;
		for (TBook book : highBooks) {
			System.out.println(i + ":");
			for (Object obj : book.getTAuthors()) {
				TAuthor author = (TAuthor) obj;
				System.out.println("author name = " + author.getName());
			}
			i++;
		}
    	return "highBooks";
    }
    //查询二手书
    public String usedBooks() throws Exception {
    	//设置当前页
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setPageSize(8);
		this.bookService.setCurrentPage(currentPage);
	
		//二手要设置查询条件 
		queryBookInfo.setUsed(1);
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
		//查询结果
		usedBooks = this.bookService.queryBooksOrderByUsed(queryBookInfo);
		System.out.println("usedBooks size = " + usedBooks.size());
		
		int i = 1;
		for (TBook book : usedBooks) {
			System.out.println(i + ":");
			for (Object obj : book.getTAuthors()) {
				TAuthor author = (TAuthor) obj;
				System.out.println("author name = " + author.getName());
			}
			i++;
		}
    	return "usedBooks";
    }
    //高级查找
    public String advSearch() throws Exception {
    	System.out.println("queryBookInfo = " + queryBookInfo.getClientInputInfo());
    	//设置当前页
    	if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setPageSize(8);
		this.bookService.setCurrentPage(currentPage);
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
		
    	books = bookService.query(queryBookInfo);
    	System.out.println("books size = " + books.size());
    	return "advSearch";
    }
    //在输入框中查找
    public String search() throws Exception {
    	System.out.println("搜索框中输入的数据 ：" + queryBookInfo.getClientInputInfo());
    	//设置当前页
    	if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setPageSize(8);
		this.bookService.setCurrentPage(currentPage);
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
		
    	books = bookService.query(queryBookInfo);
    	System.out.println("books size = " + books.size());
    	return "search";
    }
    //分类查找
    public String queryByCategory() throws Exception {
    	//设置当前页
    	if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.bookService.setPageSize(8);
		this.bookService.setCurrentPage(currentPage);
		//获得总页数
		this.allPage = this.bookService.getAllPage(queryBookInfo);
    	books = bookService.queryBooksByCategory(queryBookInfo);
    	System.out.println("books size = " + books.size());
    	return "queryByCategory";
    }
    
    
    
	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	public QueryBookInfo getQueryBookInfo() {
		return queryBookInfo;
	}

	public void setQueryBookInfo(QueryBookInfo queryBookInfo) {
		this.queryBookInfo = queryBookInfo;
	}

	public TDescription getDescription() {
		return description;
	}

	public void setDescription(TDescription description) {
		this.description = description;
	}

	public TBook getBook() {
		return book;
	}

	public void setBook(TBook book) {
		this.book = book;
	}

	public List<TBook> getBooks() {
		return books;
	}

	public void setBooks(List<TBook> books) {
		this.books = books;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getAllPage() {
		return allPage;
	}

	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	public TBook getModel() {
		return book;
	}
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getInputBookIds() {
		return inputBookIds;
	}

	public void setInputBookIds(String inputBookIds) {
		this.inputBookIds = inputBookIds;
	}

	public List<String> getAuthorIds() {
		return authorIds;
	}

	public void setAuthorIds(List<String> authorIds) {
		this.authorIds = authorIds;
	}

	public String getInputAuthorIds() {
		return inputAuthorIds;
	}

	public void setInputAuthorIds(String inputAuthorIds) {
		this.inputAuthorIds = inputAuthorIds;
	}

	public IAuthorService getAuthorService() {
		return authorService;
	}

	public void setAuthorService(IAuthorService authorService) {
		this.authorService = authorService;
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
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
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


	public SyscodeUtils getSysutil() {
		return sysutil;
	}


	public void setSysutil(SyscodeUtils sysutil) {
		this.sysutil = sysutil;
	}


	public List<Map<String, String>> getAllCategory() {
		return allCategory;
	}


	public void setAllCategory(List<Map<String, String>> allCategory) {
		this.allCategory = allCategory;
	}


	public String getFkey() {
		return fkey;
	}


	public void setFkey(String fkey) {
		this.fkey = fkey;
	}


	public String getIsExitIsbn() {
		return isExitIsbn;
	}


	public void setIsExitIsbn(String isExitIsbn) {
		this.isExitIsbn = isExitIsbn;
	}


	public String getAuthors() {
		return authors;
	}


	public void setAuthors(String authors) {
		this.authors = authors;
	}


	public String getSubCategoryName() {
		return subCategoryName;
	}


	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}


	/**
	 * @return the selectDetail
	 */
	public String getSelectDetail() {
		return selectDetail;
	}


	/**
	 * @param selectDetail the selectDetail to set
	 */
	public void setSelectDetail(String selectDetail) {
		this.selectDetail = selectDetail;
	}


	/**
	 * @return the tokenProcessor
	 */
	public TokenProcessor getTokenProcessor() {
		return tokenProcessor;
	}


	/**
	 * @param tokenProcessor the tokenProcessor to set
	 */
	public void setTokenProcessor(TokenProcessor tokenProcessor) {
		this.tokenProcessor = tokenProcessor;
	}

	/**
	 * @return the categorys
	 */
	public Map<Category, List<Category>> getCategorys() {
		return categorys;
	}

	/**
	 * @param categorys the categorys to set
	 */
	public void setCategorys(Map<Category, List<Category>> categorys) {
		this.categorys = categorys;
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

	
}
