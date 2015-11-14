package cn.hbook.form;

import java.util.Date;

public class QueryUserInfo {
	private String userName;
	private String realName;
	private String sex;     //性别
	private Integer type;    //权限
	private String address; // 查询某个地方的用户
	private Date beginDate;  //  哪一年代的用户
	private Date endDate;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	@Override
	public String toString() {
		return userName + " : " + realName + " : " + sex + " : " + type + " : " + 
		address + " : " ;
	}
	
}
