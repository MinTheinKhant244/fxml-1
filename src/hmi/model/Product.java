package hmi.model;

public class Product {
	private int id, price, cid;
	private String name, description, cname;
	
	public Product(String name, int price, int cid, String description) {
		this.price = price;
		this.name = name;
		this.cid = cid;
		this.description = description;
	}
	
	public Product(int id, String name, int price, int cid, String description) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.cid = cid;
		this.description = description;
	}

	public Product(int id, String name, int price, int cid, String description, String cname) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.cid = cid;
		this.description = description;
		this.cname = cname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	
}
