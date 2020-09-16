package com.example.demo.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("user_registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration {

	@Id
	private int id;
	private String userName;
	private String userPwd;
	private String userPhnNum;
	private String userEmail;
	private String userAdrs;
	private boolean userStatus;
	private Date regDate;
}
