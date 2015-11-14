package cn.hbook.bean;

/**
 * TCategory entity. @author MyEclipse Persistence Tools
 */

public class TCategory implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String cname;
	private String ckey;
	private String cvalue;
	private String cfkey;

	// Constructors

	/** default constructor */
	public TCategory() {
	}

	/** full constructor */
	public TCategory(String cname, String ckey, String cvalue, String cfkey) {
		this.cname = cname;
		this.ckey = ckey;
		this.cvalue = cvalue;
		this.cfkey = cfkey;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCkey() {
		return this.ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getCvalue() {
		return this.cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	public String getCfkey() {
		return this.cfkey;
	}

	public void setCfkey(String cfkey) {
		this.cfkey = cfkey;
	}

}