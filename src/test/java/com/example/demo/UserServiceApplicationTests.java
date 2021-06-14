package com.example.demo;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sayinfos.user.UserServiceApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = "spring.config.location=src/test/resources/application.properties", classes = UserServiceApplication.class)
class UserServiceApplicationTests {

	@BeforeClass
	public static void beforeClass() {
		log.info("Junit is starting...");
	}

	@Test
	void contextLoads() {
		Assert.assertTrue(true);
	}

}
