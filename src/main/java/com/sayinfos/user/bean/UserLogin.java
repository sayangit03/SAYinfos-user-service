package com.sayinfos.user.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("user_login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

	@Id
	private int id;
	private String uniqueName;
	private String userName;
	private String userPwd;
	private String userRole;
}
