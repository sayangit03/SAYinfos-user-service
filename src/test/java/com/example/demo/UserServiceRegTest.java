package com.example.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.sayinfos.user.bean.UserRegistration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Import(UserServiceTestConfig.class)
@SpringBootTest(properties = "spring.config.location=src/test/resources/application.properties")
public class UserServiceRegTest {

	@Autowired
	UserServiceTestConfig conf;

	@BeforeClass
	public static void beforeClass() {
		log.info("Junit is starting...");
	}

	@Test
	void testUserRegistration() {
		UserRegistration usrReg1 = new UserRegistration();
		usrReg1.setId(100);
		usrReg1.setUserName("Mock Name 01");
		usrReg1.setUserEmail("mock@mock.com");
		usrReg1.setUserAdrs("Mock Address");
		usrReg1.setUserName("Mock User 01");
		usrReg1.setRegDate(new Date());
		usrReg1.setUserPhnNum("1234567890");
		usrReg1.setUserPwd("MockPwd@123");
		usrReg1.setUserStatus(false);

		UserRegistration usrReg2 = new UserRegistration();
		usrReg2.setId(101);
		usrReg2.setUserName("Mock Name 02");
		usrReg2.setUserEmail("mock@mock.com");
		usrReg2.setUserAdrs("Mock Address");
		usrReg2.setUserName("Mock User 02");
		usrReg2.setRegDate(new Date());
		usrReg2.setUserPhnNum("1234567890");
		usrReg2.setUserPwd("MockPwd@123");
		usrReg2.setUserStatus(false);

		List<UserRegistration> userRegList = Arrays.asList(usrReg1, usrReg2);

		Mockito.when(conf.regRepo.findAll()).thenReturn(userRegList);

		Assert.assertEquals(100, conf.userCon.getRegUserDetails().get(0).getId());

	}
}
