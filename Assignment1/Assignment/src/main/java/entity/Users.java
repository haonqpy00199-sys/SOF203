package entity;

import java.util.Date;

public class Users {
	private String Id;
	private String Password;
	private String Fullname;
	private Date Birthday;
	private Boolean Gender;
	private String Mobile;
	private String Email;
	private Boolean Role;
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFullname() {
		return Fullname;
	}
	public void setFullname(String fullname) {
		Fullname = fullname;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public Boolean getGender() {
		return Gender;
	}
	public void setGender(Boolean gender) {
		Gender = gender;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String moble) {
		Mobile = moble;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Boolean getRole() {
		return Role;
	}
	public void setRole(Boolean role) {
		Role = role;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Users(String id, String password, String fullname, Date birthday, Boolean gender, String moble, String email,
			Boolean role) {
		super();
		Id = id;
		Password = password;
		Fullname = fullname;
		Birthday = birthday;
		Gender = gender;
		Mobile = moble;
		Email = email;
		Role = role;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
