package cn.hbook.form;

import java.util.Date;

public class QueryBookInfo {
	 
	private Integer id;  //书籍编号
	
	private String authorName;  //作者姓名
	
	private String isbn;  //ISBN
	
	private String name; //书名
	
	private String publisher; //出版社
	
 	private Integer type;  //父类型
 	
 	private Integer chType; //子类型
 	
	private Integer used; //是否二手
	
	private String clientInputInfo; //用户在搜索框中输入的信息
	
	private String authorIds;
	
	
	private Date beginDate; //出版时间段
	private Date endDate;
	
	private Double beginPrice; //价格段查询
	private Double endPrice;
	
	private Integer beginGrade; // 按客户评分
	private Integer endGrade ;
	
	private Double beginDiscount; //按折扣查
	private Double endDiscount; 
	
	private Integer beginStockNum; //库存量
	private Integer endStockNum; 
	
	
	
	
	private String orderByPrice; // 0 不按价格排序  1按价格升序 2按价格降序
	private String orderByDate;  // 0 不按时间排序  1按时间升序 2按时间降序
	private String orderBySaleNum; // 0 不销量格排序  1按销量升序 2按销量降序
	private String orderByGrade; //    按评分排序
	private String orderByDiscount; // 按折扣排序
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getUsed() {
		return used;
	}
	public void setUsed(Integer used) {
		this.used = used;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getBeginPrice() {
		return beginPrice;
	}
	public void setBeginPrice(Double beginPrice) {
		this.beginPrice = beginPrice;
	}
	public Double getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}
	public Integer getBeginGrade() {
		return beginGrade;
	}
	public void setBeginGrade(Integer beginGrade) {
		this.beginGrade = beginGrade;
	}
	public Integer getEndGrade() {
		return endGrade;
	}
	public void setEndGrade(Integer endGrade) {
		this.endGrade = endGrade;
	}
	public Double getBeginDiscount() {
		return beginDiscount;
	}
	public void setBeginDiscount(Double beginDiscount) {
		this.beginDiscount = beginDiscount;
	}
	public Double getEndDiscount() {
		return endDiscount;
	}
	public void setEndDiscount(Double endDiscount) {
		this.endDiscount = endDiscount;
	}
	public Integer getBeginStockNum() {
		return beginStockNum;
	}
	public void setBeginStockNum(Integer beginStockNum) {
		this.beginStockNum = beginStockNum;
	}
	public Integer getEndStockNum() {
		return endStockNum;
	}
	public void setEndStockNum(Integer endStockNum) {
		this.endStockNum = endStockNum;
	}
	public String getOrderByPrice() {
		return orderByPrice;
	}
	public void setOrderByPrice(String orderByPrice) {
		this.orderByPrice = orderByPrice;
	}
	public String getOrderByDate() {
		return orderByDate;
	}
	public void setOrderByDate(String orderByDate) {
		this.orderByDate = orderByDate;
	}
	public String getOrderBySaleNum() {
		return orderBySaleNum;
	}
	public void setOrderBySaleNum(String orderBySaleNum) {
		this.orderBySaleNum = orderBySaleNum;
	}
	public String getOrderByGrade() {
		return orderByGrade;
	}
	public void setOrderByGrade(String orderByGrade) {
		this.orderByGrade = orderByGrade;
	}
	public String getOrderByDiscount() {
		return orderByDiscount;
	}
	public void setOrderByDiscount(String orderByDiscount) {
		this.orderByDiscount = orderByDiscount;
	}
	public String getClientInputInfo() {
		return clientInputInfo;
	}
	public void setClientInputInfo(String clientInputInfo) {
		this.clientInputInfo = clientInputInfo;
	}
	public Integer getChType() {
		return chType;
	}
	public void setChType(Integer chType) {
		this.chType = chType;
	}
	public String getAuthorIds() {
		return authorIds;
	}
	public void setAuthorIds(String authorIds) {
		this.authorIds = authorIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryBookInfo [beginPrice=" + beginPrice + ", endPrice="
				+ endPrice + ", beginGrade=" + beginGrade + ", endGrade="
				+ endGrade + ", beginDiscount=" + beginDiscount
				+ ", endDiscount=" + endDiscount + "]";
	}
	
	
}
