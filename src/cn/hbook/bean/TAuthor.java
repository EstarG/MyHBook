package cn.hbook.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * TAuthor entity. @author MyEclipse Persistence Tools
 */

public class TAuthor implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5489095731800225890L;
	
	private Integer id;
	private String name;
	private String infor;
	private Set TBooks = new HashSet(0);

	// Constructors

	/** default constructor */
	public TAuthor() {
	}
	

	public TAuthor(Integer id) {
		super();
		this.id = id;
	}


	public TAuthor(String name, String infor) {
		super();
		this.name = name;
		this.infor = infor;
	}


	/** full constructor */
	public TAuthor(String name, String infor, Set TBooks) {
		this.name = name;
		this.infor = infor;
		this.TBooks = TBooks;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfor() {
		return this.infor;
	}

	public void setInfor(String infor) {
		this.infor = infor;
	}

	public Set getTBooks() {
		return this.TBooks;
	}

	public void setTBooks(Set TBooks) {
		this.TBooks = TBooks;
	}

}