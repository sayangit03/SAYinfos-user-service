package com.example.demo.bean;

public class UserRegistrationBean {
	
	private String name;
	private String location;
	private String email;
	private String phone;
	private String password;
	private boolean status;
	
	public UserRegistrationBean() {
		
	}
	
	public UserRegistrationBean(String name, String location, String email, String phone, String password, boolean status) {
		super();
		this.name = name;
		this.location = location;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.status = status;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
