package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.UserRegistrationBean;
import com.example.demo.bean.UserDetails;
import com.example.demo.bean.UserLogin;
import com.example.demo.bean.UserRegistration;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.repository.UserRegistrationRepository;

@RestController
public class UserController {

	@Autowired
	UserLoginRepository loginRepo;

	@Autowired
	UserDetailsRepository detailsRepo;

	@Autowired
	UserRegistrationRepository userRegRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/getUserDetails")
	public List<UserDetails> getUserDetails() {
		List<UserDetails> usrDetailsList = detailsRepo.findAll();
		return usrDetailsList;
	}
	
	@GetMapping("/getUserDetails/user/{uniqueName}/pwd/{pwd}")
	public boolean getUserDetailsByUniqueName(@PathVariable String uniqueName, @PathVariable String pwd) {
		List<UserDetails> usrDetail = detailsRepo.findByUniqueName(uniqueName);
		if(usrDetail.size()==0)
			return false;
		
		boolean okToGoFlag = passwordEncoder.matches(pwd, usrDetail.get(0).getLogin().getUserPwd());
		if(okToGoFlag)
			return true;
		return false;
	}

	@PostMapping(path = "/userRegistration", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String userRegistration(@RequestBody UserRegistrationBean user) {
		System.out.println(">>>>>>>>>>>>"+user.getName());
		
		UserRegistration userCheck = userRegRepo.findByUserEmail(user.getEmail());
		if(userCheck == null){
			UserRegistration userReg = new UserRegistration();
			userReg.setUserName(user.getName());
			userReg.setUserEmail(user.getEmail());
			userReg.setUserPwd(passwordEncoder.encode(user.getPassword()));
			userReg.setUserPhnNum(user.getPhone());
			userReg.setUserAdrs(user.getLocation());
			userReg.setUserStatus(user.isStatus());
			userReg.setRegDate(new Date());

			userRegRepo.save(userReg);
		}
		else {
			return "Already registered";
		}
		return "Done!";
	}
	
	@GetMapping("/approveUser")
	public String approveUser(UserRegistration user) {
		user = new UserRegistration();
		user.setUserEmail("sayman.eye@gmail.com");
		
		
		UserRegistration userCheck = userRegRepo.findByUserEmail(user.getUserEmail());
		if(!userCheck.isUserStatus()) {
			UserLogin login = new UserLogin();
			login.setUserName(userCheck.getUserName());
			login.setUserPwd(userCheck.getUserPwd());
			login.setUserRole("Admin");
			login.setUniqueName(userCheck.getUserEmail().substring(0, 3)+"_0"+userCheck.getId());

			UserDetails details = new UserDetails();
			details.setUserAdrs(userCheck.getUserAdrs());
			details.setUserPhnNum(userCheck.getUserPhnNum());
			details.setUserEmail(userCheck.getUserEmail());

			login.setUserDetails(details);
			details.setLogin(login);

			loginRepo.save(login);
			detailsRepo.save(details);
			
			userCheck.setUserStatus(true);
			userRegRepo.save(userCheck);
		}
		return "User approved!";
	}
	
	@GetMapping("/getRegUserDetails")
	public List<UserRegistration> getRegUserDetails() {
		List<UserRegistration> usrRegDetailsList = userRegRepo.findAll();
		return usrRegDetailsList;
	}

}
