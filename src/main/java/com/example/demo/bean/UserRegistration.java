package com.example.demo.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_user_reg")
public class UserRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "user_name")
	private String userName;
	
	@NotNull
	@Column(name = "user_pwd")
	private String userPwd;
	
	@NotNull
	@Column(name = "user_phnnum")
	private String userPhnNum;
	
	@NotNull
	@Column(name = "user_email")
	private String userEmail;
	
	@NotNull
	@Column(name = "user_adrs")
	private String userAdrs;
	
	@NotNull
	@Column(name = "user_status")
	private boolean userStatus;
	
	@NotNull
	@Column(name = "userreg_date")
	private Date regDate;
	
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public UserRegistration() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

}
