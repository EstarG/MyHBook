/**
 * 
 */
package cn.hbook.web;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import cn.hbook.bean.TOrder;
import cn.hbook.bean.TOrderdetail;
import cn.hbook.bean.TUser;
import cn.hbook.form.PaymentRequest;
import cn.hbook.service.IOrderDetailService;
import cn.hbook.service.IOrderService;
import cn.hbook.utils.ConfigInfo;
import cn.hbook.utils.PaymentUtil;
import cn.hbook.utils.TokenProcessor;
import cn.hbook.vo.CartItem;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author E_star
 *
 */
public class PaymentAction extends ActionSupport implements ModelDriven<PaymentRequest>, RequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3710371964736165199L;
	
	/** 订单业务类  */
	private IOrderService orderService;
	
	/** 订单细节业务类  */
	private IOrderDetailService detailService;
	
	/** 请求对象 */
	private Map<String, Object> request;
	
	/** 支付请求对象  */
	private PaymentRequest paymentRequest = new PaymentRequest();
	
	/** 支付金额 */
	private Double amount;
	
	/** 自动生成的编号 */
	private String orderToken;
	 
	
	/**支付完成的消息 */
	private String message;
	
	public String gotoPayment() {
		
		HttpSession session =  ServletActionContext.getRequest().getSession();
		@SuppressWarnings("unchecked")
		Map<String, CartItem> cart = (Map<String, CartItem>)session.getAttribute("cart");
		amount = new Double(0);
		for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
			CartItem item = entry.getValue();
			amount += item.getPrice();
		}
		
		orderToken = TokenProcessor.generateToken();
		System.out.println("amount = " + amount + "订单号：　" + orderToken);
		
		return "gotoPayment";
		
	}
	public String paymentRequest() {
		System.out.println("---gotoPayment begin ---");
		//前台将订单号， 金额 ， 选择的银行传进来了
		
		//设置商户ID
		
		String orderid = (String)request.get("orderid");//订单号
		String amount = (String)request.get("amount");//支付金额
		String pd_FrpId = (String)request.get("pd_FrpId");//选择的支付银行
		String p1_MerId = ConfigInfo.getValue("p1_MerId");
		String keyValue = ConfigInfo.getValue("keyValue");
		String merchantCallbackURL = ConfigInfo.getValue("merchantCallbackURL");		
		String messageType = "Buy"; // 请求命令,在线支付固定为Buy
		String currency = "CNY"; // 货币单位
		String productDesc = ""; // 商品描述
		String productCat = ""; // 商品种类
		String productId = ""; // 商品ID
		String addressFlag = "0"; // 需要填写送货信息 0：不需要 1:需要		
		String sMctProperties = ""; // 商家扩展信息
		String pr_NeedResponse = "0"; // 应答机制
		String md5hmac = PaymentUtil.buildHmac(messageType, p1_MerId, orderid, amount, currency,
				productId, productCat, productDesc, merchantCallbackURL, addressFlag, sMctProperties, 
				pd_FrpId, pr_NeedResponse, keyValue);
		
		paymentRequest.setP0_Cmd(messageType);
		paymentRequest.setP1_MerId(p1_MerId);
		paymentRequest.setP2_Order(orderid);
		paymentRequest.setP3_Amt(amount);
		paymentRequest.setP4_Cur(currency);
		paymentRequest.setP5_Pid(productId);
		paymentRequest.setP6_Pcat(productCat);
		paymentRequest.setP7_Pdesc(productDesc);
		paymentRequest.setP8_Url(merchantCallbackURL);
		paymentRequest.setP9_SAF(addressFlag);
		paymentRequest.setPa_MP(sMctProperties);
		paymentRequest.setPd_FrpId(pd_FrpId);
		paymentRequest.setPr_NeedResponse(pr_NeedResponse);
		paymentRequest.setHmac(md5hmac); 
		
		return "paymentRequest";
	}
	
	public String payment() throws Exception {
		
		//真正的制度流程 
		/*
		String keyValue = ConfigInfo.getValue("keyValue"); // 商家密钥
		
		String sCmd = (String)request.get("r0_Cmd"); //业务类型
		String sResultCode =  (String)request.get("r1_Code"); //扣款结果,该字段值为1时表示扣款成功.
		String sTrxId =  (String)request.get("r2_TrxId"); //YeePay易宝交易订单号
		String amount =  (String)request.get("r3_Amt");//扣款金额,交易结束后,YeePay易宝交易系统将实际扣款金额返回给商户
		String currency =  (String)request.get("r4_Cur");//交易币种,人民币为CNY
		String productId =  (String)request.get("r5_Pid");//商品ID
		String orderId =  (String)request.get("r6_Order");//商户订单号
		String userId =  (String)request.get("r7_Uid");//YeePay易宝会员ID
		String mp  =  (String)request.get("r8_MP");//商户扩展信息,可以任意填写1K 的字符串,交易返回时将原样返回
		String bType =  (String)request.get("r9_BType");//交易结果通知类型,1: 交易成功回调(浏览器重定向)2: 交易成功主动通知(服务器点对点通讯)
		String rb_BankId  =  (String)request.get("rb_BankId");//支付银行
		String rp_PayDate =  (String)request.get("rp_PayDate");//在银行支付时的时间
		String hmac =  (String)request.get("hmac");//MD5交易签名
		
		boolean result = PaymentUtil.verifyCallback(hmac, merchantID, sCmd, sResultCode, sTrxId, amount,
				currency, productId, orderId, userId, mp, bType, keyValue);
		if(result){
			if("1".equals(sResultCode)){
				// 逻辑处理， 保存订单， 清空购物车， 显示支付成功
			    message = "订单号为:"+ orderId+ "的订单支付成功了";
				message += ",用户支付了"+ amount +"元";
				message +=",交易结果通知类型:";
				if("1".equals(bType)){
					 message += "浏览器重定向";
				}else if("2".equals(bType)){
					 message += "易宝支付网关后台程序通知";
				}
				message += ",易宝订单系统中的订单号为:"+ sTrxId;
				
			}else{
				message = "用户支付失";
			}
		}else{
			message = "数据来源不合法";
		}
		*/
		
		//自己模拟
		//1. 取出购物车 ＋　当前用户
		HttpSession session =  ServletActionContext.getRequest().getSession();
		@SuppressWarnings("unchecked")
		Map<String, CartItem> cart = (Map<String, CartItem>)session.getAttribute("cart");
		TUser user = (TUser)session.getAttribute("user");
		//2. 定义订单对象
		TOrder order = new TOrder();
		//3.定义购物车中的项目set
		Set<TOrderdetail> orderdetails = new HashSet<TOrderdetail>();
		//4.遍历购物车取出来保存数据库
		Double priceTotal = new Double(0);
		Integer numTotal = new Integer(0);
		for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
			CartItem item = entry.getValue();
			TOrderdetail orderdetail = convert2Orderdetail(item, order, user);
			//detailService.save(orderdetail);
			orderdetails.add(orderdetail);
			numTotal += item.getNum();
			priceTotal += item.getPrice();
		}
		order.setNumTotal(Double.valueOf(numTotal));
		order.setPriceTotal(priceTotal);
		order.setTOrderdetails(orderdetails);
		order.setTUser(user);
		order.setOrderDate(new Date());
		
		//保存订单
		orderService.save(order);
		//清空购物车
		session.removeAttribute("cart");
		message = "支付成功了";
		
		return "payment";
	}

	private TOrderdetail convert2Orderdetail(CartItem item, TOrder order, TUser user) {
		TOrderdetail orderdetail = new TOrderdetail();
		orderdetail.setNum(item.getNum());
		orderdetail.setPrice(item.getPrice());
		orderdetail.setTBook(item.getBook());
		orderdetail.setSaleDate(new Date());
		orderdetail.setTUser(user);
		orderdetail.setTOrder(order);
		return orderdetail;
	}

	public PaymentRequest getModel() {
		return paymentRequest;
	}

	/**
	 * @return the paymentRequest
	 */
	public PaymentRequest getPaymentRequest() {
		return paymentRequest;
	}

	/**
	 * @param paymentRequest the paymentRequest to set
	 */
	public void setPaymentRequest(PaymentRequest paymentRequest) {
		this.paymentRequest = paymentRequest;
	}


	public void setRequest(Map<String, Object> arg0) {
		request = arg0;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the request
	 */
	public Map<String, Object> getRequest() {
		return request;
	}

	/**
	 * @return the orderService
	 */
	public IOrderService getOrderService() {
		return orderService;
	}

	/**
	 * @param orderService the orderService to set
	 */
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	/**
	 * @return the detailService
	 */
	public IOrderDetailService getDetailService() {
		return detailService;
	}
	/**
	 * @param detailService the detailService to set
	 */
	public void setDetailService(IOrderDetailService detailService) {
		this.detailService = detailService;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @return the orderToken
	 */
	public String getOrderToken() {
		return orderToken;
	}
	/**
	 * @param orderToken the orderToken to set
	 */
	public void setOrderToken(String orderToken) {
		this.orderToken = orderToken;
	}
	
	
}


