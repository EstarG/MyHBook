/**
 * 
 */
package cn.hbook.form;

/**
 * @author E_star
 *
 */
public class PaymentRequest {
	/** 请求命令,在线支付固定为Buy */
	private String   p0_Cmd;  
	
	/** 商家ID */
	private String   p1_MerId; 
	
	/** 商家的交易定单号  */
	private String   p2_Order; 
	
	/** 订单金额 */
	private String 	 p3_Amt; 
	
	/** 货币单位 */
	private String   p4_Cur; 
	
	/** 商品ID */
	private String	 p5_Pid; 
	
	/** 商品种类 */
	private String	 p6_Pcat; 
	
	/** 商品描述  */
	private String	 p7_Pdesc; 
	
	/** 交易结果通知地址  */
	private String	 p8_Url; 
	
	/** 需要填写送货信息 0：不需要 1:需要 */
	private String	 p9_SAF; 
	
	/** 商家扩展信息 */
	private String	 pa_MP; 
	
	/**  银行ID */
	private String	 pd_FrpId; 
	
	/** 应答机制 为“1”: 需要应答机制;为“0”: 不需要应答机制  */
	private String pr_NeedResponse;
	
	/**  MD5-hmac验证码  */
	private String   hmac;

	/**
	 * @return the p0_Cmd
	 */
	public String getP0_Cmd() {
		return p0_Cmd;
	}

	/**
	 * @param p0_Cmd the p0_Cmd to set
	 */
	public void setP0_Cmd(String p0_Cmd) {
		this.p0_Cmd = p0_Cmd;
	}

	/**
	 * @return the p1_MerId
	 */
	public String getP1_MerId() {
		return p1_MerId;
	}

	/**
	 * @param p1_MerId the p1_MerId to set
	 */
	public void setP1_MerId(String p1_MerId) {
		this.p1_MerId = p1_MerId;
	}

	/**
	 * @return the p2_Order
	 */
	public String getP2_Order() {
		return p2_Order;
	}

	/**
	 * @param p2_Order the p2_Order to set
	 */
	public void setP2_Order(String p2_Order) {
		this.p2_Order = p2_Order;
	}

	/**
	 * @return the p3_Amt
	 */
	public String getP3_Amt() {
		return p3_Amt;
	}

	/**
	 * @param p3_Amt the p3_Amt to set
	 */
	public void setP3_Amt(String p3_Amt) {
		this.p3_Amt = p3_Amt;
	}

	/**
	 * @return the p4_Cur
	 */
	public String getP4_Cur() {
		return p4_Cur;
	}

	/**
	 * @param p4_Cur the p4_Cur to set
	 */
	public void setP4_Cur(String p4_Cur) {
		this.p4_Cur = p4_Cur;
	}

	/**
	 * @return the p5_Pid
	 */
	public String getP5_Pid() {
		return p5_Pid;
	}

	/**
	 * @param p5_Pid the p5_Pid to set
	 */
	public void setP5_Pid(String p5_Pid) {
		this.p5_Pid = p5_Pid;
	}

	/**
	 * @return the p6_Pcat
	 */
	public String getP6_Pcat() {
		return p6_Pcat;
	}

	/**
	 * @param p6_Pcat the p6_Pcat to set
	 */
	public void setP6_Pcat(String p6_Pcat) {
		this.p6_Pcat = p6_Pcat;
	}

	/**
	 * @return the p7_Pdesc
	 */
	public String getP7_Pdesc() {
		return p7_Pdesc;
	}

	/**
	 * @param p7_Pdesc the p7_Pdesc to set
	 */
	public void setP7_Pdesc(String p7_Pdesc) {
		this.p7_Pdesc = p7_Pdesc;
	}

	/**
	 * @return the p8_Url
	 */
	public String getP8_Url() {
		return p8_Url;
	}

	/**
	 * @param p8_Url the p8_Url to set
	 */
	public void setP8_Url(String p8_Url) {
		this.p8_Url = p8_Url;
	}

	/**
	 * @return the p9_SAF
	 */
	public String getP9_SAF() {
		return p9_SAF;
	}

	/**
	 * @param p9_SAF the p9_SAF to set
	 */
	public void setP9_SAF(String p9_SAF) {
		this.p9_SAF = p9_SAF;
	}

	/**
	 * @return the pa_MP
	 */
	public String getPa_MP() {
		return pa_MP;
	}

	/**
	 * @param pa_MP the pa_MP to set
	 */
	public void setPa_MP(String pa_MP) {
		this.pa_MP = pa_MP;
	}

	/**
	 * @return the pd_FrpId
	 */
	public String getPd_FrpId() {
		return pd_FrpId;
	}

	/**
	 * @param pd_FrpId the pd_FrpId to set
	 */
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	/**
	 * @return the pr_NeedResponse
	 */
	public String getPr_NeedResponse() {
		return pr_NeedResponse;
	}

	/**
	 * @param pr_NeedResponse the pr_NeedResponse to set
	 */
	public void setPr_NeedResponse(String pr_NeedResponse) {
		this.pr_NeedResponse = pr_NeedResponse;
	}

	/**
	 * @return the hmac
	 */
	public String getHmac() {
		return hmac;
	}

	/**
	 * @param hmac the hmac to set
	 */
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	
	

}
