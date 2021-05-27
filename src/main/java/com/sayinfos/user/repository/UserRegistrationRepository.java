package com.sayinfos.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sayinfos.user.bean.UserRegistration;

public interface UserRegistrationRepository extends MongoRepository<UserRegistration, Integer> {

	UserRegistration findByUserEmail(String userEmail);

}
