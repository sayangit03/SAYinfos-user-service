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

import com.example.demo.bean.UserDetails;
import com.example.demo.bean.UserLogin;
import com.example.demo.bean.UserRegistration;
import com.example.demo.bean.UserRegistrationBean;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.repository.UserRegistrationRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
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
		log.info("Fetching user details..");
		List<UserDetails> usrDetailsList = detailsRepo.findAll();
		return usrDetailsList;
	}

	@GetMapping("/getUserDetails/user/{uniqueName}/pwd/{pwd}")
	public boolean getUserDetailsByUniqueName(@PathVariable String uniqueName, @PathVariable String pwd) {
		log.info("Authentication started with user name: " + uniqueName);
		List<UserLogin> loginDetail = loginRepo.findByUniqueName(uniqueName);
		if (loginDetail.size() == 0)
			return false;

		boolean okToGoFlag = passwordEncoder.matches(pwd, loginDetail.get(0).getUserPwd());
		if (okToGoFlag)
			return true;
		return false;
	}

	@PostMapping(path = "/userRegistration", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String userRegistration(@RequestBody UserRegistrationBean user) {
		log.info("Registration started for user: " + user.getName());
		UserRegistration userCheck = userRegRepo.findByUserEmail(user.getEmail());
		if (userCheck == null) {
			UserRegistration userReg = new UserRegistration();
			userReg.setId((int) userRegRepo.count() + 1);
			userReg.setUserName(user.getName());
			userReg.setUserEmail(user.getEmail());
			userReg.setUserPwd(passwordEncoder.encode(user.getPassword()));
			userReg.setUserPhnNum(user.getPhone());
			userReg.setUserAdrs(user.getLocation());
			userReg.setUserStatus(user.isStatus());
			userReg.setRegDate(new Date());

			userRegRepo.save(userReg);
		} else {
			return "Already registered";
		}
		return "Done!";
	}

	@GetMapping("/approveAdmin/{email}")
	public String approveAdmin(@PathVariable String email) {
		log.info("Approving as role admin: " + email);
		UserRegistration userCheck = userRegRepo.findByUserEmail(email);
		if (!userCheck.isUserStatus()) {
			UserLogin login = new UserLogin();
			login.setId((int) loginRepo.count() + 1);
			login.setUserName(userCheck.getUserName());
			login.setUserPwd(userCheck.getUserPwd());
			login.setUserRole("Admin");
			login.setUniqueName(userCheck.getUserEmail().substring(0, 3).toLowerCase() + "_a0" + userCheck.getId());

			UserDetails details = new UserDetails();
			details.setId((int) detailsRepo.count() + 1);
			details.setUserAdrs(userCheck.getUserAdrs());
			details.setUserPhnNum(userCheck.getUserPhnNum());
			details.setUserEmail(userCheck.getUserEmail());

			details.setLogin(login);

			loginRepo.save(login);
			detailsRepo.save(details);

			userCheck.setUserStatus(true);
			userRegRepo.save(userCheck);

			return "Approved" + login.getUniqueName();
		}
		return "NotApproved";
	}

	@GetMapping("/approveUser/{email}")
	public String approveUser(@PathVariable String email) {
		log.info("Approving as role user: " + email);
		UserRegistration userCheck = userRegRepo.findByUserEmail(email);
		if (!userCheck.isUserStatus()) {
			UserLogin login = new UserLogin();
			login.setId((int) loginRepo.count() + 1);
			login.setUserName(userCheck.getUserName());
			login.setUserPwd(userCheck.getUserPwd());
			login.setUserRole("User");
			login.setUniqueName(userCheck.getUserEmail().substring(0, 3).toLowerCase() + "_u0" + userCheck.getId());

			UserDetails details = new UserDetails();
			details.setId((int) detailsRepo.count() + 1);
			details.setUserAdrs(userCheck.getUserAdrs());
			details.setUserPhnNum(userCheck.getUserPhnNum());
			details.setUserEmail(userCheck.getUserEmail());

			details.setLogin(login);

			loginRepo.save(login);
			detailsRepo.save(details);

			userCheck.setUserStatus(true);
			userRegRepo.save(userCheck);

			return "Approved" + login.getUniqueName();
		}
		return "NotApproved";
	}

	@GetMapping("/getRegUserDetails")
	public List<UserRegistration> getRegUserDetails() {
		log.info("Fetching registered user details..");
		List<UserRegistration> usrRegDetailsList = userRegRepo.findAll();
		return usrRegDetailsList;
	}

	@GetMapping("/removeUserFromLogin/{email}")
	public String removeUserFromLogin(@PathVariable String email) {
		log.info("Removing user details from login..");
		UserDetails user = detailsRepo.findByUserEmail(email);
		int userId = user.getId();
		loginRepo.deleteById(userId);
		detailsRepo.deleteById(userId);

		UserRegistration userCheck = userRegRepo.findByUserEmail(email);
		userCheck.setUserStatus(false);
		userRegRepo.save(userCheck);

		return "Removed";
	}
}
