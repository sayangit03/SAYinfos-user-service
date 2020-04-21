package com.example.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_user_details")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "user_phnnum")
	private String userPhnNum;
	
	@NotNull
	@Column(name = "user_email")
	private String userEmail;
	
	@NotNull
	@Column(name = "user_adrs")
	private String userAdrs;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "login_id", nullable = false)
	//@JsonManagedReference
	private UserLogin login;
	
	public UserDetails() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserPhnNum() {
		return userPhnNum;
	}

	public void setUserPhnNum(String userPhnNum) {
		this.userPhnNum = userPhnNum;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserAdrs() {
		return userAdrs;
	}

	public void setUserAdrs(String userAdrs) {
		this.userAdrs = userAdrs;
	}

	public UserLogin getLogin() {
		return login;
	}

	public void setLogin(UserLogin login) {
		this.login = login;
	}
}
