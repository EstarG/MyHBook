package cn.hbook.vo;

//根据类别的ID排续， ID小的排在前， 显示分类。
public class Category implements Comparable<Category>{
	private String id;
	private String name;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int compareTo(Category o) {
		Integer id1 = Integer.parseInt(id);
		Integer id2 = Integer.parseInt(o.getId());
		return id1.compareTo(id2);
	}
	
	
	

}
