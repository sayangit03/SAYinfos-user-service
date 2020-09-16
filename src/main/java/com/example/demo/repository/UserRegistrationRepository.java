package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.bean.UserRegistration;

public interface UserRegistrationRepository extends MongoRepository<UserRegistration, Integer> {

	UserRegistration findByUserEmail(String userEmail);

}
