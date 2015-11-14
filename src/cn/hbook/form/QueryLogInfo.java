package cn.hbook.form;

import java.util.Date;
/*
 * 用来收集查询日志时收集日志的表单提交消息
 */
public class QueryLogInfo {
	private String userName; //按用户明查看日志
	private String userIp; //按IP查看日志
	private Date beginDate; // 	起止时间
	private Date endDate;
	private String info; // 按具体操作查询
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "userName : " + userName + " userIp : " + userIp + "info : " + info;
	}
 	
}
