package cn.hbook.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TBook entity. @author MyEclipse Persistence Tools
 */

public class TBook implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4897476377202628509L;
	private Integer id;
	private String isbn;
	private String name;
	private String publisher;
	private Date publishDate;
	private Double price;
	private Double hbPrice;
	private Double discount;
	private String picture;
	private Integer ftype;
	private Integer type;
	private Integer grade = 0;
	private Integer stockNum;
	private Integer saleNum = 0;
	private Integer used = 1;
	private Set TAuthors = new HashSet(0);
	private Set TOrderdetails = new HashSet(0);
	private TDescription TDescription;

	// Constructors

	/** default constructor */
	public TBook() {
	}
	
	public TBook(Integer id) {
		super();
		this.id = id;
	}
	

	public TBook(String isbn, String name, String publisher, Date publishDate,
			Double price, Double discount, String picture, Integer type,
			Integer grade, Integer stockNum, Integer saleNum, Integer used) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.price = price;
		this.discount = discount;
		this.picture = picture;
		this.type = type;
		this.grade = grade;
		this.stockNum = stockNum;
		this.saleNum = saleNum;
		this.used = used;
	}

	/** full constructor */
	public TBook(String isbn, String name, String publisher, Date publishDate,
			Double price, Double discount, String picture, Integer type,
			Integer grade, Integer stockNum, Integer saleNum, Integer used,
			Set TAuthors,  TDescription TDescription) {
		this.isbn = isbn;
		this.name = name;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.price = price;
		this.discount = discount;
		this.picture = picture;
		this.type = type;
		this.grade = grade;
		this.stockNum = stockNum;
		this.saleNum = saleNum;
		this.used = used;
		this.TAuthors = TAuthors;
		this.TDescription = TDescription;
	}

	// Property accessors

	

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getStockNum() {
		return this.stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Integer getSaleNum() {
		return this.saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getUsed() {
		return this.used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Set getTAuthors() {
		return this.TAuthors;
	}

	public void setTAuthors(Set TAuthors) {
		this.TAuthors = TAuthors;
	}
	public TDescription getTDescription() {
		return this.TDescription;
	}

	public void setTDescription(TDescription TDescription) {
		this.TDescription = TDescription;
	}

	public Set getTOrderdetails() {
		return TOrderdetails;
	}

	public void setTOrderdetails(Set tOrderdetails) {
		TOrderdetails = tOrderdetails;
	}

	public Double getHbPrice() {
		return hbPrice;
	}

	public void setHbPrice(Double hbPrice) {
		this.hbPrice = hbPrice;
	}

	public Integer getFtype() {
		return ftype;
	}

	public void setFtype(Integer ftype) {
		this.ftype = ftype;
	}
	
}