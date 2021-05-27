package com.sayinfos.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sayinfos.user.bean.UserDetails;

public interface UserDetailsRepository extends MongoRepository<UserDetails, Integer> {

	UserDetails findByUserEmail(String userEmail);
}
