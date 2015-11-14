package cn.hbook.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import cn.hbook.bean.TAuthor;
import cn.hbook.service.IAuthorService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AuthorAction extends ActionSupport implements ModelDriven<TAuthor>, RequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8116732033827759216L;
	
	private IAuthorService authorService; // 业务逻辑处理类
	private TAuthor author = new TAuthor(); //作者实体接收参数
	private Map<String, Object> request; //保存修改更新消息
	private List<TAuthor> authors = new ArrayList<TAuthor>(); //存放查询结果
	private List<String> ids = new ArrayList<String>();  // 存放批量删除的用户ID
	private String inputAuthorIds; //收集用户输入的删除的作者id
	
	/** 根据作者ID和姓名查找作者  */
	private List<Map<String, Object>> authorsIdAndNname = new ArrayList<Map<String, Object>>(); 
	
	
	private Integer currentPage;  //当前页
    private Integer allPage ; //总页数
	
    
    //添加作者
    public String add() throws Exception{
    	System.out.println(">>>" + author.toString());
    	authorService.save(author);
    	request.put("message", "添加成功");
  
    	return "addSuccess";
    }
    
    /**
     * 查询作者详情
     * 
     * @return
     * @throws Exception 
     */
    public String queryAuthorDetail() throws Exception{
    	System.out.println("uqueryAuthorDetail author ID = " + author.getId());
    	this.author = authorService.queryById(author.getId());
    	return "queryAuthorDetail";
    }
    
    //查询出所有的作者
    public String query() throws Exception {
    	
		//设置当前页
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.authorService.setCurrentPage(currentPage);
		this.authorService.setPageSize(8);
		//获得总页数
		this.allPage = this.authorService.getAllPage(author);
		
		//按条件查询作者
    	this.authors = authorService.query(author);
    	
    	ids.clear(); // 防止默认全选
    	
    	return "queryAuthors";
    }
    //删除作者
    public String delete() throws Exception {
    	authorService.delete(author);
    	request.put("message", "删除成功"); //设置提示消息
    	return "delete";
    }
    public String deleteAll() throws Exception {
    	if (inputAuthorIds != null) {
    		List<String> inuptID = Arrays.asList( inputAuthorIds.split(";") );
    		ids.addAll(inuptID);
    	}
    	
    	System.out.println("ids  :　" + ids);
    	authorService.deleteAll(ids);
    	request.put("message", "删除成功"); //设置提示消息
    	ids.clear();  //防止返回到前段默认都选择了
    	return "delete";
    }
    //通过ID查询作者
    public String queryById() throws Exception {
    	System.out.println("user信息" + author.getId());
    	this.author = authorService.queryById(author.getId());
    	return "queryById";
    }
    //更新作者信息
    public String update() throws Exception {
    	String result = "";
    	authorService.update(author);
    	result = "update";
    	request.put("message", "更新成功");
    	return result;
    }
   
    // Ajax 异步请求
    public String queryAuthor() throws Exception {
    	System.out.println("异步Ajax author  id = " + author.getId() + " name = " + author.getName());
    	authorsIdAndNname = authorService.queryAuthor(author.getId(), author.getName());
    	for (Map<String, Object> mp : authorsIdAndNname) {
    		System.out.println(mp.get("id") + "  " + mp.get("name"));
    	}
    	return "queryAuthor";
    }
    
    
    
	
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public TAuthor getModel() {
		return this.author;
	}
	public IAuthorService getAuthorService() {
		return authorService;
	}
	public void setAuthorService(IAuthorService authorService) {
		this.authorService = authorService;
	}
	public TAuthor getAuthor() {
		return author;
	}
	public void setAuthor(TAuthor author) {
		this.author = author;
	}
	public List<TAuthor> getAuthors() {
		return authors;
	}
	public void setAuthors(List<TAuthor> authors) {
		this.authors = authors;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public String getInputAuthorIds() {
		return inputAuthorIds;
	}
	public void setInputAuthorIds(String inputAuthorIds) {
		this.inputAuthorIds = inputAuthorIds;
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
	public List<Map<String, Object>> getAuthorsIdAndNname() {
		return authorsIdAndNname;
	}
	public void setAuthorsIdAndNname(List<Map<String, Object>> authorsIdAndNname) {
		this.authorsIdAndNname = authorsIdAndNname;
	}
	

}
