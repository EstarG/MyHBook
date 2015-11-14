package cn.hbook.bean;

import java.util.Date;

/**
 * TLog entity. @author MyEclipse Persistence Tools
 */

public class TLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private TUser TUser;
	private String userip;
	private Date logdate;
	private String info;

	// Constructors

	/** default constructor */
	public TLog() {
	}

	/** full constructor */
	public TLog(TUser TUser, String userip, Date logdate, String info) {
		this.TUser = TUser;
		this.userip = userip;
		this.logdate = logdate;
		this.info = info;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public String getUserip() {
		return this.userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}