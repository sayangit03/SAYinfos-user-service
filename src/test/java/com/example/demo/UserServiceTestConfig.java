package com.example.demo;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.controller.UserController;
import com.example.demo.repository.UserRegistrationRepository;

@Configuration
public class UserServiceTestConfig {

	@SpyBean
	UserController userCon;

	@MockBean
	UserRegistrationRepository regRepo;

}
