package cn.hbook.web;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.hbook.bean.TUser;
import cn.hbook.service.IUserService;
import cn.hbook.utils.MD5;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<TUser>, SessionAware, RequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -110379117288430927L;
	
	private Map<String, Object> session; //会的 用户的回话session
	private Map<String, Object> request; //用来保存请求信息
	private TUser user = new TUser(); //用户对象用来接收表单提交数据
	
	/** 用户输入的校验码 */
	private String verifycode; 
	
	/** 校验码校验 */
	private String isEqual;
	
	private IUserService userService;  // 用户业务逻辑处理类， 用来处理用户的相关操作 必须在spring中配置，并且提供setter
	

	//管理员登陆
	public String adminLogin() throws Exception {
		String result = null;
		//获得系统提供的验证码
		String rand =  ((String) session.get("rand")).trim();
		
		// 获取用户IP地址
		String ip = org.apache.struts2.ServletActionContext.getRequest()
				.getRemoteAddr();
		System.out.println("login User Ip = " + ip);
		session.put("ip", ip);
				
		//首先检查用户是否合法
		TUser admin = userService.checkLegal(user.getUserName(), MD5.getMD5(user.getPassword()));
		
		//检查用户是否为管理员， 验证码是否正确
		
		if (admin == null){
			request.put("message", "用户名/密码错误");
			result = "adminLoginFailure";
			return result;
		}
		if (verifycode == null ) {
			//设置提示消息
			request.put("message", "请输入验证码");
			result = "adminLoginFailure";
			return result;
		}
		if (!verifycode.equals(rand)) {
			//设置提示消息
			request.put("message", "验证码错误");
			result = "adminLoginFailure";
			return result;
		}
		if (admin.getType() != 83) {
			request.put("message", "系统管理员才可登陆");
			result = "adminLoginFailure";
			return result;
		}
		System.out.println(user.getUserName() + " : " + user.getPassword() + " : " + rand + "admin = " + admin.getUserName() + "verifycode" + verifycode);
		session.put("admin", admin);
		result =  "adminLoginSuccess";
		return result;
	}
	//用户登陆
	public String login() throws Exception {
		String result = null;
		//获得系统提供的验证码
		String rand =  ((String) session.get("rand")).trim();
		System.out.println("rand = " + rand + "user = " + this.user);
		//获取用户IP地址
		String ip = org.apache.struts2.ServletActionContext.getRequest().getRemoteAddr();
		System.out.println("login User Ip = " + ip);
		session.put("ip", ip);
		//首先检查用户是否合法
		TUser user = userService.checkLegal(this.user.getUserName(), MD5.getMD5(this.user.getPassword()));
		//检查用户是否为管理员， 验证码是否正确
		if (user == null) {
			request.put("message", "用户名/密码错误");
			result = "loginFailure";
		} else if (verifycode == "") {
			//设置提示消息
			request.put("message", "请输入验证码");
			result = "loginFailure";
		} else if (!verifycode.equals(rand)) {
			//设置提示消息
			request.put("message", "验证码错误");
			result = "loginFailure";
		}else {
			System.out.println("》》》 ： 用户成功登陆");
			session.put("user", user);
			System.out.println("userId = " + user.getId());
			result =  "loginSuccess";
		}
		return result;
	}
	public String isOkCode() {
		System.out.println("进入 isOkCode");
		//获得系统提供的验证码
		String rand =  ((String) session.get("rand")).trim();
		if (verifycode == null || !verifycode.trim().equals(rand)) {
			isEqual = "1";
		} else {
			isEqual = "0";
		}
		System.out.println("rand = " + rand + "verifycode = " + verifycode);
		return "isOkCode";
	}
	
	
	//管理员退出登录
	public String adminLogout(){
		session.remove("admin");
		return "adminLogout";
	}
	public String logout(){
		session.remove("user");
		return "logout";
	}
	
	// 一系列的getter setter 
	
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	
	public TUser getModel() {
		return user;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	/**
	 * @return the isEqual
	 */
	public String getIsEqual() {
		return isEqual;
	}
	/**
	 * @param isEqual the isEqual to set
	 */
	public void setIsEqual(String isEqual) {
		this.isEqual = isEqual;
	}

	
	
}
