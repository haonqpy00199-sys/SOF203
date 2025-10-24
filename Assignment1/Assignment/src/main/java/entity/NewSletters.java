package entity;

public class NewSletters {

	private String Email;
	private Boolean Enabled;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Boolean getEnabled() {
		return Enabled;
	}
	public void setEnabled(Boolean enabled) {
		Enabled = enabled;
	}
	public NewSletters(String email, Boolean enabled) {
		super();
		Email = email;
		Enabled = enabled;
	}
	public NewSletters() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
