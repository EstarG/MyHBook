package cn.hbook.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TOrder entity. @author MyEclipse Persistence Tools
 */

public class TOrder implements java.io.Serializable {

	// Fields

	private Integer id;
	private TUser TUser;
	private Date orderDate;
	private Double priceTotal;
	private Double numTotal;
	private Set TOrderdetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public TOrder() {
	}
	
	public TOrder(Integer id) {
		super();
		this.id = id;
	}

	/** full constructor */
	public TOrder(TUser TUser, Date orderDate, Double priceTotal,
			Double numTotal, Set TOrderdetails) {
		this.TUser = TUser;
		this.orderDate = orderDate;
		this.priceTotal = priceTotal;
		this.numTotal = numTotal;
		this.TOrderdetails = TOrderdetails;
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

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getPriceTotal() {
		return this.priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public Double getNumTotal() {
		return this.numTotal;
	}

	public void setNumTotal(Double numTotal) {
		this.numTotal = numTotal;
	}

	public Set getTOrderdetails() {
		return this.TOrderdetails;
	}

	public void setTOrderdetails(Set TOrderdetails) {
		this.TOrderdetails = TOrderdetails;
	}

}