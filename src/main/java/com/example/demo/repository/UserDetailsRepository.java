package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.bean.UserDetails;

public interface UserDetailsRepository extends MongoRepository<UserDetails, Integer> {

	UserDetails findByUserEmail(String userEmail);
}
