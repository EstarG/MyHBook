package cn.hbook.interceptor;

import java.util.Map;

import cn.hbook.bean.TUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class loginInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("------loginInterceptor invoked begin------");  
		Map<String,Object> session = ActionContext.getContext().getSession();
		TUser user = (TUser)session.get("admin");
		if (user != null && user.getType() == 83)  {
			String result = invocation.invoke();  
			return result; 
		}
        System.out.println("------loginInterceptor invoked finish------");  
        return "adminNotLogin";
	}

}
