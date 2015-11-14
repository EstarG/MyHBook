package cn.hbook.web;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.hbook.bean.TUser;
import cn.hbook.form.QueryUserInfo;
import cn.hbook.service.IUserService;
import cn.hbook.utils.MD5;
import cn.hbook.utils.SyscodeUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class UserAction extends ActionSupport implements ModelDriven<TUser>, RequestAware, SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3850344485319002928L;
	
	/** 系统编码管理 */
	private SyscodeUtils sysutil;
	private QueryUserInfo queryUserInfo = new QueryUserInfo(); //收集查询信息
	private Map<String, Object> request;  //保存是否成功数据到前台显示
	private Map<String, Object> session; //用户注册成功后添加入session
	
	private TUser user = new TUser(); // 接受form表单提交的数据
    private IUserService userService; // 业务逻辑处理类
    private List<Map<String, Object>> users = new ArrayList<Map<String,Object>>(); // 记录查询结果
    private List<String> ids = new ArrayList<String>(); //收集全选时的id
    private String inputUserIds; //收集用户输入的删除的用户id
    
    /** 用户权限  */
    private List<Map<String, String>> permissions = new ArrayList<Map<String,String>>();
    
    /** 省份选择 */
    private List<Map<String, String>> provinces = new ArrayList<Map<String,String>>();
    
    /** 城市选择 */
    private List<Map<String, String>> citys = new ArrayList<Map<String,String>>();
    
    /** 查询城市时省份的fkey */
    private String fkey;
    
	private Integer currentPage;  //当前页
    private Integer allPage ; //总页数
    
    /** 用户是否存在的标记 */
    private String isExistUser;
    
    /** 更新用户时显示其所在的城市  */
    private String cityName;
    
    
    
    /**
     * 初始化用户权限选择
     */
    private void init() {
    	List<Map<String, String>> temp = sysutil.getOption("permission");
    	Map<String, String> initMap = new HashMap<String, String>();
    	initMap.put("fkey", "0"); initMap.put("fvalue", "请选择用户类型");
    	this.permissions.add(initMap);
    	this.permissions.addAll(temp);
    	this.provinces = sysutil.getOption("province");
    	this.citys = sysutil.getOption("city");
    }
    
    //添加用户
    public String add() throws Exception{
    	//初始化用户类型
    	init();
    	System.out.println("permissions : " + permissions + "\n" + "provinces = " + provinces + "\n" + "citys = " + citys);
    	System.out.println(">>>" + user.toString());
    	System.out.println("user.getProvince() = " + user.getProvince() + " user.getCity() = " + user.getCity());
    	
    	//填充地址属性
    	StringBuilder address = new StringBuilder();
    	for (Map<String, String> province : provinces) {
    		if (province.get("fkey").equals(user.getProvince() + "")) {
    			address.append(province.get("fvalue"));
    			break;
    		}
    	}
    	for (Map<String, String> city : citys) {
    		if (city.get("fkey").equals(user.getCity() + "")) {
    			address.append(city.get("fvalue"));
    			break;
    		}
    	}
    	System.out.println("adress = " + address.toString());
    	user.setAddress(address.toString());
    	
    	//对用户填写的密码进行MD5加密
    	user.setPassword(MD5.getMD5(user.getPassword()));
    	//保存
    	userService.save(user);
    	//放置消息
    	request.put("message", "添加成功");
    	return "addSuccess";
    }
    //查询出所有的用户
    public String queryUsers() throws Exception {
    	//初始化权限选择
    	init();
    	System.out.println(">>>>" + queryUserInfo);
    	System.out.println("查询条件为： " + queryUserInfo.toString());
		//设置当前页
		if (currentPage == null || currentPage == 0) {
			currentPage = 1;
		}
		System.out.println("currentPage" + currentPage);
		this.userService.setCurrentPage(currentPage);
		this.userService.setPageSize(8);
		//获得总页数
		this.allPage = this.userService.getAllPage(queryUserInfo);
		
		//按条件查询用户
    	this.users = userService.query(queryUserInfo);
    	
    	return "queryUsers";
    }
    //删除用户
    public String delete() throws Exception {
    	userService.delete(user);
    	request.put("message", "删除成功"); //设置提示消息
    	return "delete";
    }
    public String deleteAll() throws Exception {
    	if (inputUserIds != null) {
    		List<String> inuptID = Arrays.asList( inputUserIds.split(";") );
    		ids.addAll(inuptID);
    	}
    	
    	System.out.println("ids  :　" + ids);
    	
    	
    	userService.deleteAll(ids);
    	request.put("message", "删除成功"); //设置提示消息
    	ids.clear();  //防止返回到前段默认都选择了
    	return "delete";
    }
    //通过ID查询用户
    public String queryById() throws Exception {
    	//初始化下拉列表
    	init();
    	System.out.println("user信息" + user.getId());
    	this.user = userService.queryById(user.getId());
    	//填充城市名字
    	for (Map<String, String> city : citys) {
    		if (city.get("fkey").equals(user.getCity() + "")) {
    			this.cityName = city.get("fvalue");
    			break;
    		}
    	}
    	System.out.println("cityName = " + cityName);
    	return "queryById";
    }
    //更新用户信息
    public String update() throws Exception {
    	init();
    	System.out.println("user ; " + user.getUserName() + user.getId());
    	//填充地址属性
    	StringBuilder address = new StringBuilder();
    	for (Map<String, String> province : provinces) {
    		if (province.get("fkey").equals(user.getProvince() + "")) {
    			address.append(province.get("fvalue"));
    			break;
    		}
    	}
    	for (Map<String, String> city : citys) {
    		if (city.get("fkey").equals(user.getCity() + "")) {
    			address.append(city.get("fvalue"));
    			break;
    		}
    	}
    	System.out.println("adress = " + address.toString());
    	user.setAddress(address.toString());
    	//填充城市名字
    	for (Map<String, String> city : citys) {
    		if (city.get("fkey").equals(user.getCity() + "")) {
    			this.cityName = city.get("fvalue");
    			break;
    		}
    	}
    	System.out.println("cityName = " + cityName);
    	
    	//对用户填写的密码进行MD5加密
    	user.setPassword(MD5.getMD5(user.getPassword()));
    	
    	userService.update(user);
    	
    	request.put("message", "更新成功");
    	return "update";
    }
    /**
     * 点击查看用户详情
     * 
     * @return
     * @throws Exception 
     */
    public String queryUserDetail() throws Exception {
    	init();
    	System.out.println("查看用户详情 : user ID = " + user.getId());
    	this.user = userService.queryById(user.getId());
    	return "queryUserDetail";
    }
    
    /** 客户端操作  */
    
    //客户端用户注册
    public String register() throws Exception {
    	System.out.println(">>>" + user.toString());
    	TUser tmpUser = userService.queryUserByName(user.getUserName());
    	if (tmpUser != null) {
    		request.put("message", "用户名不合法");
    		return "toRegister";
    	}
    	//对用户填写的密码进行MD5加密
    	user.setPassword(MD5.getMD5(user.getPassword()));
    	
    	userService.save(user);
    	session.put("user", user);
    	return "register";
    }
    //客户端查看个人信息
    public String findById() throws Exception{
    	this.user = userService.queryById(this.user.getId());
    	return "findById";
    }
    //客户端更新用户信息
    public String viewUpdate() throws Exception {
    	System.out.println("userType = " + user.getType());
    	userService.update(user);
    	return "viewUpdate";
    }
    
    
    /** 异步Ajax请求   */
    
    /**
     * 查询指定省份下的城市名称
     * @return
     */
    public String queryCitys() {
    	System.out.println("fkey = " + fkey);
    	this.citys = sysutil.getOption("city", fkey);
    	System.out.println("citys = " + citys);
    	return "queryCitys";
    }
    
    /**
     * 查询userName是否存在
     * @return
     * @throws Exception 
     */
    public String isExistUserName() throws Exception {
    	System.out.println("isExistUserName   --->   userName = " + user.getUserName());
    	TUser tuser = userService.queryUserByName(user.getUserName().trim());
    	
    	if (tuser == null) {
    		isExistUser = "0";
    	} else {
    		isExistUser = "1";
    	}
    	System.out.println("isExistUser = "  + isExistUser);
    	return "isExistUserName";
    }
    
   
    
    
    // setter getter
	public TUser getModel() {
		return user;
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

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getRequest() {
		return request;
	}
	public List<Map<String, Object>> getUsers() {
		return users;
	}
	public void setUsers(List<Map<String, Object>> users) {
		this.users = users;
	}
	public QueryUserInfo getQueryUserInfo() {
		return queryUserInfo;
	}
	public void setQueryUserInfo(QueryUserInfo queryUserInfo) {
		this.queryUserInfo = queryUserInfo;
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
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getInputUserIds() {
		return inputUserIds;
	}

	public void setInputUserIds(String inputUserIds) {
		this.inputUserIds = inputUserIds;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public List<Map<String, String>> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Map<String, String>> permissions) {
		this.permissions = permissions;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public SyscodeUtils getSysutil() {
		return sysutil;
	}

	public void setSysutil(SyscodeUtils sysutil) {
		this.sysutil = sysutil;
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
	 * @return the fkey
	 */
	public String getFkey() {
		return fkey;
	}

	/**
	 * @param fkey the fkey to set
	 */
	public void setFkey(String fkey) {
		this.fkey = fkey;
	}

	/**
	 * @return the isExistUser
	 */
	public String getIsExistUser() {
		return isExistUser;
	}

	/**
	 * @param isExistUser the isExistUser to set
	 */
	public void setIsExistUser(String isExistUser) {
		this.isExistUser = isExistUser;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
