package hmi.model;

public class Category {
	@Override
	public String toString() {
		return this.getName();
	}

	private int id;
	private String name, description;
	
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Category(int id,String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
