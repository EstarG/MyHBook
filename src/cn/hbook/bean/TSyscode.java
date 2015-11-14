package cn.hbook.bean;

/**
 * TSyscode entity. @author MyEclipse Persistence Tools
 */

public class TSyscode implements java.io.Serializable {

	// Fields

	private Integer fid;
	private String fname;
	private String fkey;
	private String fvalue;
	private String parentKey;

	// Constructors

	/** default constructor */
	public TSyscode() {
	}

	/** full constructor */
	public TSyscode(String fname, String fkey, String fvalue, String parentKey) {
		this.fname = fname;
		this.fkey = fkey;
		this.fvalue = fvalue;
		this.parentKey = parentKey;
	}

	// Property accessors

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFkey() {
		return this.fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey;
	}

	public String getFvalue() {
		return this.fvalue;
	}

	public void setFvalue(String fvalue) {
		this.fvalue = fvalue;
	}

	public String getParentKey() {
		return this.parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

}