package cn.hbook.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String realName;
	private String userName;
	private String password;
	private String sex;
	private String email;
	private String phone;
	private String userQq;
	private Integer province;
	private Integer city;
	private String address;
	private Date birthday;
	private Integer type;
	private Set TLogs = new HashSet(0);
	private Set TOrders = new HashSet(0);
	private Set TOrderdetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public TUser() {
	}
	
	public TUser(Integer id) {
		super();
		this.id = id;
	}
	


	public TUser(Integer id, String realName, String userName, String password,
			String sex, String email, String phone, String userQq,
			String address, Date birthday, Integer type) {
		super();
		this.id = id;
		this.realName = realName;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.userQq = userQq;
		this.address = address;
		this.birthday = birthday;
		this.type = type;
	}

	/** full constructor */
	public TUser(String realName, String userName, String password,
			String email, String phone, String userQq, String address,
			Integer type, Set TLogs, Set TOrders, Set TOrderdetails) {
		this.realName = realName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.userQq = userQq;
		this.address = address;
		this.type = type;
		this.TLogs = TLogs;
		this.TOrders = TOrders;
		this.TOrderdetails = TOrderdetails;
	}

	// Property accessors
	
	public Integer getId() {
		return this.id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserQq() {
		return this.userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set getTLogs() {
		return this.TLogs;
	}

	public void setTLogs(Set TLogs) {
		this.TLogs = TLogs;
	}

	public Set getTOrders() {
		return this.TOrders;
	}

	public void setTOrders(Set TOrders) {
		this.TOrders = TOrders;
	}

	public Set getTOrderdetails() {
		return this.TOrderdetails;
	}

	public void setTOrderdetails(Set TOrderdetails) {
		this.TOrderdetails = TOrderdetails;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return realName + ":" + userName + ":" + password + ":" + sex + ":" + email + ":" + phone 
		+ ":" + userQq + ":" + address + ":" + birthday + ": " + type;
	}
	
	

}