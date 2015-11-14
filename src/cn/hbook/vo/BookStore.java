/**
 * 
 */
package cn.hbook.vo;

import java.io.Serializable;

/**
 * @author E_star
 *
 */
public class BookStore implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8046373091021120964L;

	/** 地址 */
	private String url;
	
	/** 名字 */
	private String name;
	
	/** 图片 */
	private String picture;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
