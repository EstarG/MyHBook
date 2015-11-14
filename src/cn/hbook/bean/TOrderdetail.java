package cn.hbook.bean;

import java.util.Date;

/**
 * TOrderdetail entity. @author MyEclipse Persistence Tools
 */

public class TOrderdetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private TOrder TOrder;
	private TBook TBook;
	private TUser TUser;
	private Integer num;
	private Double price;
	private Date saleDate;

	// Constructors

	/** default constructor */
	public TOrderdetail() {
	}
	
	public TOrderdetail(Integer id) {
		super();
		this.id = id;
	}

	/** full constructor */
	

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public TOrderdetail(cn.hbook.bean.TOrder tOrder, cn.hbook.bean.TBook tBook,
			cn.hbook.bean.TUser tUser, Integer num, Double price, Date saleDate) {
		super();
		TOrder = tOrder;
		TBook = tBook;
		TUser = tUser;
		this.num = num;
		this.price = price;
		this.saleDate = saleDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TOrder getTOrder() {
		return this.TOrder;
	}

	public void setTOrder(TOrder TOrder) {
		this.TOrder = TOrder;
	}

	public TBook getTBook() {
		return this.TBook;
	}

	public void setTBook(TBook TBook) {
		this.TBook = TBook;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	

}