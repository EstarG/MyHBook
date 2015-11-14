package cn.hbook.bean;

/**
 * TDescription entity. @author MyEclipse Persistence Tools
 */

public class TDescription implements java.io.Serializable {

	// Fields

	private Integer id;
	private TBook TBook;
	private String content;
	private String authorInfo;
	private String catalogInfo;

	// Constructors

	/** default constructor */
	public TDescription() {
	}

	/** minimal constructor */
	public TDescription(TBook TBook) {
		this.TBook = TBook;
	}

	/** full constructor */
	public TDescription(TBook TBook, String content, String authorInfo,
			String catalogInfo) {
		this.TBook = TBook;
		this.content = content;
		this.authorInfo = authorInfo;
		this.catalogInfo = catalogInfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TBook getTBook() {
		return this.TBook;
	}

	public void setTBook(TBook TBook) {
		this.TBook = TBook;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorInfo() {
		return this.authorInfo;
	}

	public void setAuthorInfo(String authorInfo) {
		this.authorInfo = authorInfo;
	}

	public String getCatalogInfo() {
		return this.catalogInfo;
	}

	public void setCatalogInfo(String catalogInfo) {
		this.catalogInfo = catalogInfo;
	}

}