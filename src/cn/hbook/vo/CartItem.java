package cn.hbook.vo;

import java.io.Serializable;
import java.util.Date;

import cn.hbook.bean.TBook;

public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TBook book;
	private Integer num;
	private Double price;
	private Date saleDate;
	
	
	
	public CartItem(TBook book, Integer num, Double price, Date saleDate) {
		super();
		this.book = book;
		this.num = num;
		this.price = price;
		this.saleDate = saleDate;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TBook getBook() {
		return book;
	}
	public void setBook(TBook book) {
		this.book = book;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
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
