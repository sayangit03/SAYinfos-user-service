package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.bean.UserLogin;

public interface UserLoginRepository extends MongoRepository<UserLogin, Integer> {

	List<UserLogin> findByUniqueName(String uniqueName);
}
