package entity;

public class Categories {

	private String Id;
	private String Name;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Categories(String id, String name) {
		super();
		Id = id;
		Name = name;
	}
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
